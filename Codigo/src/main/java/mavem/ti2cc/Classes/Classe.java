package mavem.ti2cc.Classes;

public class Classe {
    private String name;
    private String descricao;

    public Classe(){
        this.name = "";
        this.descricao = "";
    }

    public Classe(String name){
        this.name = name;
        this.descricao = "";
    }

    public Classe(String name,  String descricao){
        this.name = name;
        this.descricao = descricao;
    }

    public String getName(){
        return this.name;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
