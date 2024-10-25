package mavem.ti2cc.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mavem.ti2cc.Classes.*;
import mavem.ti2cc.service.PersonagemService;

public class RacaDAO extends DAO{
	
    public RacaDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
	
    public Raca get(String nome_raca){
        Raca raca = new Raca();

        try{
            String sql = "SELECT * FROM usuario.raca WHERE nome = '" + nome_raca + "'";
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            System.out.println(sql);
            
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                raca = new Raca(rs.getString("nome"), rs.getString("descricao"));
            }
            st.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        return raca;

    }
}
