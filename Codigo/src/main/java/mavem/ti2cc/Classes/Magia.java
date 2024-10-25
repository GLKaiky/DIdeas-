package mavem.ti2cc.Classes;

public class Magia {
	private String Nome;
	private	String Descricao;
	private String Personagem_Nome;
	private int nivel;
	
	//construtor
	
	public Magia() {
		this.Nome = "";
		this.Descricao = "";
		this.Personagem_Nome = "";
	}
	
	public Magia(String nome, String descricao, String personagem_Nome, int nivel) {
		this.Nome = nome;
		this.Descricao = descricao;
		this.Personagem_Nome = personagem_Nome;
		this.nivel = nivel;
	}
	
	//gets
	
	public String getNome() {
		return this.Nome;
	}
	
	public int getNivel() {
		return this.nivel;
	}
	
	public String getPersonagemNome() {
		return this.Personagem_Nome;
	}
	
	public String getDescricao() {
		return this.Descricao;
	}
	
	//sets
	
	public void setPersonagemNome(String personagemNome) {
		this.Personagem_Nome = personagemNome;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public void setNome(String nome) {
		this.Nome = nome;
	}
	
	public void setDescricao(String descricao) {
		this.Descricao = descricao;
	}
	
	
}
