package mavem.ti2cc.DAO;

import mavem.ti2cc.Classes.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class UsuarioDAO extends DAO {
	public UsuarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public boolean insert(Usuario usuario) throws Exception {
		boolean status = false;
		try {// Criar objeto
			String sql = "INSERT INTO usuario.usuarios (id, username, email, senha, nome) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, usuario.getId());
			st.setString(2, usuario.getNickName());
			st.setString(3, usuario.getEmail());
			st.setString(4, DAO.toMD5(usuario.getPassword()));
			st.setString(5, usuario.getName());
			
			System.out.println(st); // Apenas para depuração, para ver a consulta preparada antes de executar

			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				status = true;
			}

			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean insertImage(int UserId) {
		boolean status = true;
		try {
		String sql = "UPDATE usuario.usuarios SET img = ? WHERE id = ?";
		String url = "https://fotosdeperfil.blob.core.windows.net/blob/perfil" + UserId + ".png";
		
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, url);
			st.setInt(2, UserId);
			System.out.println(st);
			int rowsAffected = st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir imagem no bd:" + e.getMessage());
			status = false;
		}
		return status;
	}

	public String getImageById(int userId) {
	    String url = "";
	    String sql = "SELECT img FROM usuario.usuarios WHERE id = ?";
	    try {
	        PreparedStatement st = conexao.prepareStatement(sql);
	        st.setInt(1, userId);
	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            // Ler a URL da imagem do ResultSet
	            url = rs.getString("img");
	        }

	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	        System.out.println("Erro ao recuperar imagem do banco de dados: " + e.getMessage());
	    }
	    System.out.println(url);
	    return url;
	}


	public Usuario get(int codigo) {
		Usuario usuario = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * 	FROM usuario.usuarios WHERE id = " + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("username"),
						rs.getString("email"), rs.getString("senha"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}

	public Usuario get(String Username, String Senha) throws Exception {
		Usuario usuario = null;
		Senha = DAO.toMD5(Senha);

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario.usuarios WHERE username = '" + Username + "' AND senha = '" + Senha
					+ "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("username"),
						rs.getString("email"), rs.getString("senha"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}

	public List<Usuario> get() {
		return get("");
	}

	public List<Usuario> getOrderById() {
		return get("id");
	}

	public List<Usuario> getOrderByUsername() {
		return get("username");
	}

	public List<Usuario> getOrderByEmail() {
		return get("email");
	}

	public List<Usuario> getOrderBySenha() {
		return get("senha");
	}

	public List<Usuario> getOrderByNome() {
		return get("nome");
	}

	private List<Usuario> get(String orderBy) {
		List<Usuario> usuario = new ArrayList<Usuario>();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario.usuarios"
					+ ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Usuario u = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("username"),
						rs.getString("email"), rs.getString("senha"));
				usuario.add(u);
			}
			st.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println(usuario);
		return usuario;
	}

	public boolean delete(int UserID) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM usuario.usuarios WHERE id = " + UserID;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean modify(String requisition, int typeRequisition, int UserId) throws Exception {
		boolean status = false;
		if(typeRequisition == 0) {
			try {
				Statement st = conexao.createStatement();
				String sql = "UPDATE usuario.usuarios SET username = '" + requisition + "'" + " WHERE id = " + UserId;
				System.out.println(sql);
				st.executeUpdate(sql);
				st.close();
				status = true;
			}catch(SQLException e) {
				System.err.println(e.getMessage());
			}
		}else {
			try {
				Statement st = conexao.createStatement();
				String sql = "UPDATE usuario.usuarios SET senha = '" + DAO.toMD5(requisition) + "'" + " WHERE id = " + UserId;
				System.out.println(sql);
				st.executeUpdate(sql);
				st.close();
				status = true;
			}catch(SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		
		return status;
	}

}
