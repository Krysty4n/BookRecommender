//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA
/**
 * Questa clsse definisce l'oggetto Giudizio.
 * Richiamando il metodo costruttore è possibile instanziare un oggetto Giudizio
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */
public class Giudizio {
    /**
     * Contiene in il nickname del cliente che ha espresso il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private String username;
    /**
     * Contiene la valutazione del parametro 'Stile' del cliente che ha effettuati il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private int valutazioneStile;
    /**
     * Contiene la valutazione del parametro 'Contenuto' del cliente che ha effettuati il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private int valutazioneContenuto;
    /**
     * Contiene la valutazione del parametro 'Gradevolezza' del cliente che ha effettuati il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private int valutazioneGradevolezza;
    /**
     * Contiene la valutazione del parametro 'Originalità' del cliente che ha effettuati il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private int valutazioneOriginalita;
    /**
     * Contiene la valutazione del parametro 'Edizione' del cliente che ha effettuati il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private int valutazioneEdizione;
    /**
     * Contiene il commento del parametro 'Stile' del cliente che ha espresso il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private String commentoStile;
    /**
     * Contiene il commento del parametro 'Contenuto' del cliente che ha espresso il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String commentoContenuto;
    /**
     * Contiene il commento del parametro 'Gradevolezza' del cliente che ha espresso il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String commentoGradevolezza;
    /**
     * Contiene il commento del parametro 'Originalità' del cliente che ha espresso il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String commentoOriginalita;
    /**
     * Contiene il commento del parametro 'Edizione' del cliente che ha espresso il giudizio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String commentoEdizione;
    /**
     * Metodo costruttore vuoto della classe Giudizio.
     */

    public Giudizio() {

    }
//METODO SET
    /**
     * Permette di settare il valore dell'attributo 'nickname' dell'oggetto.
     * @param s Valorizza l'attributo nickname dell'oggetto su cui è richiamato.
     */


    public void setUsername(String s) {
        username = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'Stile' dell'oggetto.
     * @param s Valorizza l'attributo valutazioneStile dell'oggetto su cui è richiamato.
     */

    public void setValutazioneStile(int s) {
        valutazioneStile = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'Contenuto' dell'oggetto.
     * @param s Valorizza l'attributo valutazioneContenuto dell'oggetto su cui è richiamato.
     */

    public void setValutazioneContenuto(int s) {
        valutazioneContenuto = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'Gradevolezza' dell'oggetto.
     * @param s Valorizza l'attributo valutazioneGradevolezza dell'oggetto su cui è richiamato.
     */

    public void setValutazioneGradevolezza(int s) {
        valutazioneGradevolezza = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'Originalità' dell'oggetto.
     * @param s Valorizza l'attributo valutazioneOriginalità dell'oggetto su cui è richiamato.
     */

    public void setValutazioneOriginalita(int s) {
        valutazioneOriginalita = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'Edizione' dell'oggetto.
     * @param s Valorizza l'attributo valutazioneEdizione dell'oggetto su cui è richiamato.
     */

    public void setValutazioneEdizione(int s) {
        valutazioneEdizione = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'commento' riferito al criterio 'Stile' dell'oggetto.
     * @param s Valorizza l'attributo commentoStile dell'oggetto su cui è richiamato.
     */

    public void setCommentoStile(String s) {
        commentoStile = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'commento' riferito al criterio 'Contenuto' dell'oggetto.
     * @param s Valorizza l'attributo commentoContenuto dell'oggetto su cui è richiamato.
     */

    public void setCommentoContenuto(String s) {
        commentoContenuto = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'commento' riferito al criterio 'Gradevolezza' dell'oggetto.
     * @param s Valorizza l'attributo commentoGradevolezza dell'oggetto su cui è richiamato.
     */

    public void setCommentoGradevolezza(String s) {
        commentoGradevolezza = s;
    }

    /**
     * Permette di settare il valore dell'attributo 'commento' riferito al criterio 'Originalità' dell'oggetto.
     * @param s Valorizza l'attributo commentoOriginalità dell'oggetto su cui è richiamato.
     */

    public void setCommentoOriginalita(String s) {
        commentoOriginalita = s;
    }
    /**
     * Permette di settare il valore dell'attributo 'commento' riferito al criterio 'Edizione' dell'oggetto.
     * @param s Valorizza l'attributo commentoEdizione dell'oggetto su cui è richiamato.
     */

    public void setCommentoEdizione(String s) {
        commentoEdizione = s;
    }
    //METODI GET

    /**
     * Restituisce il valore dell'attributo 'nickname' dell'oggetto.
     * @return Il valore dell'attributo nickname
     */


    public String getUsername() {
        return username;
    }
    /**
     * Restituisce il valore dell'attributo 'Stile' dell'oggetto.
     * @return Il valore dell'attributo valutazioneStile
     */


    public int getValutazioneStile() {
        return valutazioneStile;
    }
    /**
     * Restituisce il valore dell'attributo 'Contenuto' dell'oggetto.
     * @return Il valore dell'attributo valutazioneContenuto
     */

    public int getValutazioneContenuto() {
        return valutazioneContenuto;
    }
    /**
     * Restituisce il valore dell'attributo 'Gradevolezza' dell'oggetto.
     * @return Il valore dell'attributo valutazioneGradevolezza
     */


    public int getValutazioneGradevolezza() {
        return valutazioneGradevolezza;
    }
    /**
     * Restituisce il valore dell'attributo 'Originalità' dell'oggetto.
     * @return Il valore dell'attributo valutazioneOriginalità
     */

    public int getValutazioneOriginalita() {
        return valutazioneOriginalita;
    }
    /**
     * Restituisce il valore dell'attributo 'Edizione' dell'oggetto.
     * @return Il valore dell'attributo valutazioneEdizione
     */

    public int getValutazioneEdizione() {
        return getValutazioneContenuto();
    }
    /**
     * Restituisce il valore dell'attributo 'commento' riferito al criterio 'Stile' dell'oggetto.
     * @return Il valore dell'attributo commentoStile
     */


    public String getCommentoStile() {
        return commentoStile;
    }
    /**
     * Restituisce il valore dell'attributo 'commento' riferito al criterio 'Contenuto' dell'oggetto.
     * @return Il valore dell'attributo commentoContenuto
     */

    public String getCommentoContenuto() {
        return commentoContenuto;
    }
    /**
     * Restituisce il valore dell'attributo 'commento' riferito al criterio 'Gradevolezza' dell'oggetto.
     * @return Il valore dell'attributo commentoGradevolezza
     */

    public String getCommentoGradevolezza() {
        return commentoGradevolezza;
    }
    /**
     * Restituisce il valore dell'attributo 'commento' riferito al criterio 'Originalità' dell'oggetto.
     * @return Il valore dell'attributo commentoOriginalità
     */

    public String getCommentoOriginalita() {
        return commentoOriginalita;
    }
    /**
     * Restituisce il valore dell'attributo 'commento' riferito al criterio 'Edizione' dell'oggetto.
     * @return Il valore dell'attributo commentoEdizione
     */

    public String getCommentoEdizione() {
        return getCommentoContenuto();
    }
}
