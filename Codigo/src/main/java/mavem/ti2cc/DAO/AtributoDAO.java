package mavem.ti2cc.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mavem.ti2cc.Classes.*;
import mavem.ti2cc.service.PersonagemService;

public class AtributoDAO extends DAO {
	
    public AtributoDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
	
    public Atributo get(int idAtrbuto){
        Atributo atributo = new Atributo();

        try{
            String sql = "SELECT * FROM usuario.atributo WHERE id_atributo = " + idAtrbuto;
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){
                atributo = new Atributo(rs.getInt("id_atributo"), rs.getInt("carisma"), rs.getInt("inteligencia"), rs.getInt("constituicao"), rs.getInt("destreza"), rs.getInt("forca"), rs.getInt("sabedoria"));
            }
            st.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return atributo;
    }
}
