package mavem.ti2cc.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mavem.ti2cc.Classes.*;
import mavem.ti2cc.service.PersonagemService;

public class PersonagemDAO extends DAO {
    public PersonagemDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Personagem personagem, int UserId) {
        boolean status = false;

        try {

            String sql = "INSERT INTO usuario.atributo (id_atributo, carisma, inteligencia, constituicao, destreza, forca, sabedoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);

            st.setInt(1, personagem.getAtributo().getId());
            st.setInt(2, personagem.getAtributo().getCarisma());
            st.setInt(3, personagem.getAtributo().getInteligencia());
            st.setInt(4, personagem.getAtributo().getConstituicao());
            st.setInt(5, personagem.getAtributo().getDestreza());
            st.setInt(6, personagem.getAtributo().getForca());
            st.setInt(7, personagem.getAtributo().getSabedoria());

            System.out.println(st);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                status = true;
            }

            sql = "INSERT INTO usuario.personagem (nome, descricao, religiao, historia, tendencia, usuario_id, nome_classe, nome_raca, id_atributo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            st = conexao.prepareStatement(sql);
            st.setString(1, personagem.getNome());
            st.setString(2, personagem.getDescricao());
            st.setString(3, personagem.getReligiao());
            st.setString(4, personagem.getHistoria());
            st.setString(5, personagem.getTendencia());
            st.setInt(6, UserId);
            st.setString(7, personagem.getClasse().getName());
            st.setString(8, personagem.getRaca().getNome());
            st.setInt(9, personagem.getAtributo().getId());

            System.out.println(st);
            rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                status = true;
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public List<Personagem> get(int userId) {
        List<Personagem> personagens = new ArrayList<Personagem>();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AtributoDAO atributoDAO = new AtributoDAO();
        ClasseDAO classeDAO = new ClasseDAO();
        RacaDAO racaDAO = new RacaDAO();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM usuario.personagem WHERE usuario_id = " + userId;

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Personagem personagem = new Personagem(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("religiao"),
                        rs.getString("historia"),
                        rs.getString("tendencia"),
                        rs.getInt("usuario_id"),
                        rs.getString("nome_classe"),
                        rs.getString("nome_raca"),
                        rs.getInt("id_atributo"));

                // Obter informações do usuário associado
                Atributo atributo = atributoDAO.get(personagem.getIdAtributo());
                Usuario usuario = usuarioDAO.get(userId);
                Classe classe = classeDAO.get(personagem.getNomeClasse());
                Raca raca = racaDAO.get(personagem.getNomeRaca());

                personagem.setUsuario(usuario);
                personagem.setClasse(classe);
                personagem.setAtributos(atributo);
                personagem.setRaca(raca);

                personagens.add(personagem); // Adicionando o personagem à lista
            }
            st.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return personagens;
    }

    public boolean insertImage(int UserId, String nome) {
        boolean status = true;
        try {
            String sql = "UPDATE usuario.personagem SET img = ? WHERE nome = ?";
            String url = "https://fotosdeperfil.blob.core.windows.net/fotos/perfil" + UserId + ".png";

            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, url);
            st.setString(2, nome);
            System.out.println(st);
            int rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir imagem no bd:" + e.getMessage());
            status = false;
        }
        return status;
    }

    public String getImageById(String userId) {
        String url = "";
        String sql = "SELECT img FROM usuario.personagem WHERE nome = ?";
        try {
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, userId);
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

    public Personagem getPersonbyName(String Name) {
        String sql = "SELECT * FROM usuario.personagem WHERE nome = ?";
        Personagem personagem = null;
        try {
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, Name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String historia = rs.getString("historia");
                String religiao = rs.getString("religiao");
                String tendencia = rs.getString("tendencia");
                int user_id = rs.getInt("usuario_id");
                String nome_classe = rs.getString("nome_classe");
                String nome_raca = rs.getString("nome_raca");
                int id_atributo = rs.getInt("id_atributo");
                String img = rs.getString("img");

                personagem = new Personagem(name, descricao, religiao, historia, tendencia, user_id, nome_classe,
                        nome_raca, id_atributo);
            }
        } catch (SQLException e) {
            System.out.println("Erro" + e.getMessage());
        }
        System.out.println(personagem.getNome());
        return personagem;
        
    }

    public boolean delete(String Name) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM usuario.personagem WHERE nome = '" + Name + "'";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

}
