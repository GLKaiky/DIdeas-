package mavem.ti2cc.Classes;

public class Usuario {
	private int ID;
	private String Nome;
	private String NickName;
	private String Email;
	private String Password;	
	private String img;
	
	
	//construtor
	public Usuario() {
		this.ID = 0;
		this.Nome = "";
		this.NickName = "";
		this.Email = "";
		this.Password = "";		
	}
	
	public Usuario(int ID, String Nome, String NickName, String Email, String Password) {
		this.ID = ID;
		this.Nome = Nome;
		this.NickName = NickName;
		this.Email = Email;
		this.Password = Password;
	}
	
	public Usuario(int ID, String Nome, String NickName, String Email, String Password, String img) {
		this.ID = ID;
		this.Nome = Nome;
		this.NickName = NickName;
		this.Email = Email;
		this.Password = Password;
		this.img = img;
	}
//gets
	public int getId() {
		return this.ID;
	}
	
	public String getName() {
		return this.Nome;
	}
	
	public String getNickName() {
		return this.NickName;
	}
	
	public String getEmail() {
		return this.Email;
	}
	
	public String getPassword() {
		return this.Password;
	}
	
	//sets
	public void setId(int Id) {
		this.ID = Id;
	}
	
	public void setName(String Name){
		this.Nome = Name;
	}

	public void setNickName(String NickName){
		this.NickName = NickName;
	}

	public void setEmail(String Email){
		this.Email = Email;
	}

	public void setPassword(String Password){
		this.Password = Password;
	}

	@Override

	public String toString(){
		return "Usuario [nome =" + Nome + ",nickname=" + NickName + ",email" + Email + ",password" + Password + "]";
	}
}
