public class Libreria {
    private String userName;
    private String nome;
    private String libri;

    public Libreria(){}

    public void setUserName(String s){
        userName = s;
    }
    public void setNome(String s){
        nome = s;
    }
    public void setLibri(String s){
        libri = s;
    }


    //teseaerrar

    public String getUsername(){
        return userName;
    }
    public String getNome(){
        return nome;
    }
    public String getLibri(){
        return libri;
    }

    public String toString(){
        return this.nome;
    }
    
}
