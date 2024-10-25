package mavem.ti2cc.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mavem.ti2cc.Classes.*;
import mavem.ti2cc.service.PersonagemService;

public class MagiasDAO extends DAO{
    public MagiasDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
    
    public boolean setMagia(String nome, String descricao, String nome_personagem, int nivel) {
        boolean status = false;

        try {

            String sql = "INSERT INTO usuario.magia (nome, nivel, descricao, nome_personagem) VALUES (?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
        	Magia magias = new Magia(nome, descricao, nome_personagem, nivel);

            st = conexao.prepareStatement(sql);
            st.setString(1, magias.getNome());
            st.setInt(2, magias.getNivel());
            st.setString(3, magias.getDescricao());
            st.setString(4, magias.getPersonagemNome());
            

            System.out.println(st);
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
    
    public ArrayList<Magia> getAllMagias(String nomePersonagem) {
        ArrayList<Magia> magias = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuario.magia WHERE nome_personagem = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, nomePersonagem);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                // Crie um objeto Magia para cada linha do resultado
                Magia magia = new Magia();
                magia.setNome(rs.getString("nome"));
                magia.setNivel(rs.getInt("nivel"));
                magia.setDescricao(rs.getString("descricao"));
                magia.setPersonagemNome(rs.getString("nome_personagem"));

                // Adicione o objeto Magia ao ArrayList
                magias.add(magia);
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return magias;
    }

    
}
