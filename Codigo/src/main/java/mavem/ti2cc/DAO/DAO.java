package mavem.ti2cc.DAO;

import java.sql.*;
import java.security.*;
import java.math.*;

public class DAO {
	protected Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "";
		String mydatabase = "postgres";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "";
		String password = "";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexao efeituada com sucesso!aaa");
			 
		}catch(ClassNotFoundException e) {
			System.err.println("Conexão NAO efetuada");
		}catch(SQLException e) {
			System.err.println("Conexao não efetuada com postgree");
			e.printStackTrace(); 
		}
		return status;
	}
	
	public boolean close() {
		boolean status = false;
		try {
			conexao.close();
			status = true;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public static String toMD5(String senha)throws Exception{
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(), 0, senha.length());
		return new BigInteger(1, m.digest()).toString(16);
	}
}
