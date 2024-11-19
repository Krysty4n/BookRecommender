//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA
/**
 * Questa classe definisce l'oggetto Libreria.
 * Richiamando il metodo costruttore Ã¨ possibile instanziare un oggetto Libreria
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */

public class Libreria {
    /**
     * Contiene il 'nickname' relativo alla libreria selezionata.
     * Dichiarato private cosÃ¬ da essere visto solo dalla classe attuale
     */
    private String userName;
    /**
     * Contiene il 'nome' relativo alla libreria selezionata.
     * Dichiarato private cosÃ¬ da essere visto solo dalla classe attuale
     */

    private String nome;
    /**
     * Contiene la stringa con i libri relativi alla libreria selezionata.
     * Dichiarato private cosÃ¬ da essere visto solo dalla classe attuale
     */
    private String libri;
    /**
     * Metodo costruttore della classe Libreria.
     */

    public Libreria(){}
    /**
     * Permette di settare il valore dell'attributo 'nickname' dell'oggetto.
     * @param s Valorizza l'attributo nickname dell'oggetto su cui Ã¨ richiamato.
     */

    public void setUserName(String s){
        userName = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'nome' dell'oggetto.
     * @param s Valorizza l'attributo nome dell'oggetto su cui Ã¨ richiamato.
     */
    public void setNome(String s){
        nome = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'brani' dell'oggetto.
     * @param s Valorizza l'attributo libri dell'oggetto su cui Ã¨ richiamato.
     */
    public void setLibri(String s){
        libri = s;
    }
    //METODI GET

    /**
     * Restituisce il valore dell'attributo 'nickname' dell'oggetto.
     * @return Il valore dell'attributo nickname
     */



    public String getUsername(){
        return userName;
    }
    /**
     * Restituisce il valore dell'attributo 'nome' dell'oggetto.
     * @return Il valore dell'attributo nome
     */

    public String getNome(){
        return nome;
    }
    /**
     * Restituisce il valore dell'attributo 'libri' dell'oggetto.
     * @return Il valore dell'attributo libri
     */

    public String getLibri(){
        return libri;
    }
    /**
     * Override
     * Restituisce il valore dell'attributo 'nome' dell'oggetto sovrascrivendo il metodo toString.
     * @return Il valore dell'attributo nome
     */


    public String toString(){
        return this.nome;
    }
    
}
