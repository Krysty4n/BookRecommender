public class Libro {
    private String id;
    private String titolo;
    private String autore;
    private String anno;
    private String genere;
    private String libreria;
    private String pagine;

    public void setId(String s) {
     this.id = s;
    }

    public void setTitolo(String s) {
     this.titolo = s;
    }

    public void setAutore(String s) {
     this.autore = s;
    }

    public void setAnno(String s) {
     this.anno = s;
    }

    public void setGenere(String s){
     this.genere = s;
    }

    public void setLibreria(String s ){
        this.libreria = s;
    }
    public void setPagine(String s){
        this.pagine = s;
    }



    public String getId() {
     return this.id;
    }


    public String getTitolo() {
     return this.titolo;
    }

    public String getAutore() {
     return this.autore;
    }

    public String getAnno() {
     return this.anno;
    }

    public String getGenere() {
     return this.genere;
    }

    public String toString() {
     return this.titolo;
    }

    public String getLibreria(){
        return this.libreria;
    }
    public String getPagine() {
        return this.pagine;


    }

    }
