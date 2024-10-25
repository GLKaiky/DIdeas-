package mavem.ti2cc.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mavem.ti2cc.Classes.*;
import mavem.ti2cc.service.PersonagemService;

public class ClasseDAO extends DAO{
	
    public ClasseDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
	
    public Classe get(String nome_classe){
        Classe classe = new Classe();

        try{
            String sql = "SELECT * FROM usuario.classe WHERE nome = '" + nome_classe + "'";
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            System.out.println(sql);
            
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                classe = new Classe(rs.getString("nome"), rs.getString("descricao"));
            }
            st.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        return classe;

    }
}
