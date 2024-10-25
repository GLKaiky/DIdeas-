package mavem.ti2cc.Classes;
import java.util.Random;

public class Personagem {
    private String nome;
    private String religiao;
    private String descricao;
    private String historia;
    private String tendencia;
    private int usuario_id;
    private String nome_classe;
    private String nome_raca;
    private int id_atributo;
    private Usuario usuario;
    private Classe classe;
    private Raca raca;
    private Atributo atributo;

    
    public Personagem(){
    	classe = new Classe();
    	raca = new Raca();
    	atributo = new Atributo();
        this.nome = "";
        this.descricao = "";
        this.religiao = "";
        this.historia = "";
        this.tendencia = "";
    }

    public Personagem(String nome, String descricao, String religiao, String historia, String tendencia, int usuario_id, String nome_classe, String nome_raca, int id_atributo){
        this.nome = nome;
        this.descricao = descricao;
        this.religiao = religiao;
        this.tendencia = tendencia;
        this.usuario_id = usuario_id;
        this.nome_classe = nome_classe;
        this.nome_raca = nome_raca;
        this.id_atributo = id_atributo;
    }

    public Personagem(String nome, String religiao, String descricao, String historia, String tendencia, String classe_nome, String raca_nome, int carisma, int inteligencia, int constituicao, int destreza, int forca, int sabedoria){     
    	classe.setName(classe_nome);
        raca.setNome(raca_nome);
        atributo.setCarisma(carisma);
        atributo.setInteligencia(inteligencia);
        atributo.setConstituicao(constituicao);
        atributo.setDestreza(destreza);
        atributo.setForca(forca);
        atributo.setSabedoria(sabedoria);
        this.nome = nome;
        this.religiao = religiao;
        this.descricao = descricao;
        this.historia = historia;
        this.tendencia = tendencia;
    }

    public int getIdUsuario(){
        return this.usuario_id;
    }

    public String getNomeClasse(){
        return this.nome_classe;
    }

    public String getNomeRaca(){
        return this.nome_raca;
    }

    public int getIdAtributo(){
        return this.id_atributo;
    }

    public Classe getClasse(){
        return this.classe;
    }

    public Raca getRaca(){
        return this.raca;
    }

    public Atributo getAtributo(){
        return this.atributo;
    }

    public String getNome(){
        return this.nome;
    }

    public String getReligiao(){
        return this.religiao;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public String getHistoria(){
        return this.historia;
    }

    public String getTendencia(){
        return this.tendencia;
    }

    //sets

    public void setAtributos(int carisma, int inteligencia, int constituicao, int destreza, int forca, int sabedoria){
        this.atributo.setCarisma(carisma);
        this.atributo.setInteligencia(inteligencia);
        this.atributo.setConstituicao(constituicao);
        this.atributo.setDestreza(destreza);
        this.atributo.setForca(forca);
        this.atributo.setSabedoria(sabedoria);

    }

    public void setAtributos(Atributo atributo){
        this.atributo = atributo;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public void setClasse(String nome){
        this.classe.setName(nome);
    }

    public void setClasse(Classe classe){
        this.classe = classe;
    }

    public void setRaca(String nome){
        this.raca.setNome(nome);
    }

    public void setRaca(Raca raca){
        this.raca = raca;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setReligiao(String Religiao){
        this.religiao = Religiao;
    }

    public void setDescricao(String Descricao){
        this.descricao = Descricao;
    }

    public void setHistoria(String historia){
        this.historia = historia;
    }

    public void setTendencia(String tendencia){
        this.tendencia = tendencia;
    }
}
