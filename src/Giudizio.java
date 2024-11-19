public class Giudizio {

    private String username;
    private int valutazioneStile;
    private int valutazioneContenuto;
    private int valutazioneGradevolezza;
    private int valutazioneOriginalita;
    private int valutazioneEdizione;

    private String commentoStile;
    private String commentoContenuto;
    private String commentoGradevolezza;
    private String commentoOriginalita;
    private String commentoEdizione;

    public Giudizio() {

    }


    public void setUsername(String s) {
        username = s;
    }

    public void setValutazioneStile(int s) {
        valutazioneStile = s;
    }

    public void setValutazioneContenuto(int s) {
        valutazioneContenuto = s;
    }

    public void setValutazioneGradevolezza(int s) {
        valutazioneGradevolezza = s;
    }

    public void setValutazioneOriginalita(int s) {
        valutazioneOriginalita = s;
    }

    public void setValutazioneEdizione(int s) {
        valutazioneEdizione = s;
    }

    public void setCommentoStile(String s) {
        commentoStile = s;
    }

    public void setCommentoContenuto(String s) {
        commentoContenuto = s;
    }

    public void setCommentoGradevolezza(String s) {
        commentoGradevolezza = s;
    }

    public void setCommentoOriginalita(String s) {
        commentoOriginalita = s;
    }

    public void setCommentoEdizione(String s) {
        commentoEdizione = s;
    }

    public String getUsername() {
        return username;
    }

    public int getValutazioneStile() {
        return valutazioneStile;
    }

    public int getValutazioneContenuto() {
        return valutazioneContenuto;
    }

    public int getValutazioneGradevolezza() {
        return valutazioneGradevolezza;
    }

    public int getValutazioneOriginalita() {
        return valutazioneOriginalita;
    }

    public int getValutazioneEdizione() {
        return getValutazioneContenuto();
    }


    public String getCommentoStile() {
        return commentoStile;
    }

    public String getCommentoContenuto() {
        return commentoContenuto;
    }

    public String getCommentoGradevolezza() {
        return commentoGradevolezza;
    }

    public String getCommentoOriginalita() {
        return commentoOriginalita;
    }

    public String getCommentoEdizione() {
        return getCommentoContenuto();
    }
}
