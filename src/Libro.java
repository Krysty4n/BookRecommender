//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA

/**
 * Questa classe definisce l'oggetto Libro.
 * Richiamando il metodo costruttore è possibile instanziare un ogetto Brano
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */
public class Libro {
    /**
     * Contiene l'id del libro selezionato.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private String id;
    /**
     * Contiene il titolo del libro selezionato.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String titolo;
    /**
     * Contiene l'autore del libro selezionato.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String autore;
    /**
     * Contiene l'anno del libro selezionato.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private String anno;

    /**
     * Contiene il genere del libro selezionato.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String genere;
    /**
     * Contiene la libreria a cui il brano selezionato appartiene.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String libreria;
    /**
     * Contiene il numero di pagine del libro selezionato.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String pagine;
    //METODI SET

    /**
     * Permette di settare il valore dell'attributo 'id' dell'oggetto.
     * @param s Valorizza l'attributo id dell'oggetto su cui è richiamato
     */

    public void setId(String s) {
     this.id = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'titolo' dell'oggetto.
     * @param s Valorizza l'attributo titolo dell'oggetto su cui è richiamato
     */

    public void setTitolo(String s) {
     this.titolo = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'autore' dell'oggetto.
     * @param s Valorizza l'attributo autore dell'oggetto su cui è richiamato
     */

    public void setAutore(String s) {
     this.autore = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'anno' dell'oggetto.
     * @param s Valorizza l'attributo anno dell'oggetto su cui è richiamato
     */

    public void setAnno(String s) {
     this.anno = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'genere' dell'oggetto.
     * @param s Valorizza l'attributo genere dell'oggetto su cui è richiamato
     */

    public void setGenere(String s){
     this.genere = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'libreria' dell'oggetto.
     * @param s Valorizza l'attributo libreria dell'oggetto su cui è richiamato
     */


    public void setLibreria(String s ){
        this.libreria = s;
    }
    public void setPagine(String s){
        this.pagine = s;
    }
    //METODI GET

    /**
     * Restituisce il valore dell'attributo 'id' dell'oggetto.
     * @return il valore dell'attributo id
     */


    public String getId() {
     return this.id;
    }
    /**
     * Restituisce il valore dell'attributo 'titolo' dell'oggetto.
     * @return il valore dell'attributo titolo
     */



    public String getTitolo() {
     return this.titolo;
    }
    /**
     * Restituisce il valore dell'attributo 'autore' dell'oggetto.
     * @return il valore dell'attributo autore
     */

    public String getAutore() {
     return this.autore;
    }
    /**
     * Restituisce il valore dell'attributo 'anno' dell'oggetto.
     * @return il valore dell'attributo anno
     */

    public String getAnno() {
     return this.anno;
    }
    /**
     * Restituisce il valore dell'attributo 'genere' dell'oggetto.
     * @return il valore dell'attributo genere
     */

    public String getGenere() {
     return this.genere;
    }
    /**
     * Restituisce il valore dell'attributo 'libreria' dell'oggetto.
     * @return il valore dell'attributo libreria
     */

    public String toString() {
     return this.titolo;
    }
    /**
     * Override
     * Restituisce il valore dell'attributo 'titolo' dell'oggetto sovrascrivendo il metodo toString.
     * @return il valore dell'attributo titolo
     */

    public String getLibreria(){
        return this.libreria;
    }
    public String getPagine() {
        return this.pagine;


    }

    }
