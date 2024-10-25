package mavem.ti2cc.Classes;

import java.util.Random;

public class Atributo {
    private int id_atributo;
    private int carisma;
    private int inteligencia;
    private int constituicao;
    private int destreza;
    private int forca;
    private int sabedoria;

    public Atributo(){
        Random random = new Random();
        int cod = random.nextInt(1000000);
        this.id_atributo = cod;        
        this.carisma = 0;
        this.inteligencia = 0;
        this.constituicao = 0;
        this.destreza = 0;
        this.forca = 0;
        this.sabedoria = 0;
    }

    public Atributo(int carisma, int inteligencia, int constituicao, int destreza, int forca, int sabedoria){
        Random random = new Random();
        int cod = random.nextInt(1000000);
        this.id_atributo = cod;
        this.carisma = carisma;
        this.inteligencia = inteligencia;
        this.constituicao = constituicao;
        this.destreza = destreza;
        this.forca = forca;
        this.sabedoria = sabedoria;
    }

    public Atributo(int id,int carisma, int inteligencia, int constituicao, int destreza, int forca, int sabedoria){
        this.id_atributo = id;
        this.carisma = carisma;
        this.inteligencia = inteligencia;
        this.constituicao = constituicao;
        this.destreza = destreza;
        this.forca = forca;
        this.sabedoria = sabedoria;
    }

    public int getId(){
        return this.id_atributo;
    }

    public int getCarisma(){
        return this.carisma;
    }

    public int getInteligencia(){
        return this.inteligencia;
    }

    public int getConstituicao(){
        return this.constituicao;
    }

    public int getDestreza(){
        return this.destreza;
    }

    public int getForca(){
        return this.forca;
    }

    public int getSabedoria(){
        return this.sabedoria;
    }


    public void setId(int id){
        this.id_atributo = id;
    }
    
    public void setCarisma(int carisma){
        this.carisma = carisma;
    }

    public void setInteligencia(int inteligencia){
        this.inteligencia = inteligencia;
    }

    public void setConstituicao(int constituicao){
        this.constituicao = constituicao;
    }

    public void setDestreza(int destreza){
        this.destreza = destreza;
    }

    public void setForca(int forca){
        this.forca = forca;
    }

    public void setSabedoria(int sabedoria){
        this.sabedoria = sabedoria;
    }
}
