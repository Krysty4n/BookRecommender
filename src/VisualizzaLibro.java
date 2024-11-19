//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Questa classe consente di visualizzare i dati e le recensioni associate al libro selezionato
 * nella finestra RisultatiRicerca oppure nella finestra VisualizzaLibreria.
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */

public class VisualizzaLibro extends javax.swing.JFrame {
    /**
     * Contiene l'oggetto Libro passato dal metodo precedente.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private Libro Libro;
    /**
     * Contiene lo username del cliente che ha eseguito l'accesso.
     * Se invece è stata utilizzata la funzionalità "consulta repository", questo attributo conterrrà una stringa vuota.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private String userName;
    /**
     * Contiene il riferimento della finestra "padre" RisultatiRicerca.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */


    private RisultatiRicerca risultatiRicerca;
    /**
     * Contiene il riferimento della finestra "padre" VisualizzaLibreria.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private VisualizzaLibreria libriLibreria;
    /**
     * Contiene il valore di ritorno associato al tipo di istanza r.
     * Permette il ritorno alla finestra "padre" corretta.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private int ritorno;
    /**
     * Contiene il nome del libro da visualizzare.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private String nomeLibro;
    /**
     * Contiene gli oggetti di tipo Giudizio trovati in fase di ricerca.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private ArrayList<Giudizio> giudizi = new ArrayList<Giudizio>();
    /**
     * Contiene le stringhe con i dettagli di tutti i giudizi trovati, relativi al ristorante.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private JList<String> risultati;
    /**
     * Area in cui il cliente può visualizzare il voto assegnato al singolo criterio.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private JTextArea areaVoto;
    /**
     * Area in cui il cliente può visualizzare il commento assegnato al libro per ogni
     * giudizio presente nella JList risultati.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private JTextArea areaCommento;
    /**
     * Array in cui vengono assegnati tutti in commenti dei criteri.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private int[] emozioni = new int[]{0,0,0,0,0};
    /**
     * Array in cui vengono assegnate tutte le valutazioni dei criteri.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private int[] Valutazione = new int[]{0,0,0,0,0};
    /**
     * Array in cui vengono assegnate tutte le medie delle valutazioni dei criteri.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */

    private double[] media = new double[]{0,0,0,0,0};
    /**
     * Metodo costruttore della classe VisualizzaLibro
     * @param username In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo nickname della classe.
     * @param l  In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo Libro della classe.
     * @param r In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo libriLibreria oppure l'attributo risultatiRicerca della classe.
     */

    public VisualizzaLibro(String username, Libro l, Object r) {
        super("Visualizza: " + l.getTitolo());
        this.setSize(680, 580);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.Libro = l;
        this.nomeLibro = l.getTitolo();
        this.userName = userName;

        if(r instanceof RisultatiRicerca) {
            this.risultatiRicerca = (RisultatiRicerca) r;
            this.ritorno = 0;
        }
        else {
            this.libriLibreria = (VisualizzaLibreria) r;
            this.ritorno = 1;
        }

        try {
            visualizzaEmozioneLibro();
        } catch (EOFException eof) {
            eof.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JLabel titoloL = new JLabel("Dettagli " + nomeLibro);
        JLabel libroL = new JLabel("NOME: " + nomeLibro);
        JLabel annoL = new JLabel("ANNO: " + l.getAnno());
        JLabel autoreL = new JLabel("AUTORE: " + l.getAutore());
        JLabel riepilogoL = new JLabel("MEDIA GIUDIZI: ");
        JLabel stileL = new JLabel("Stile: " + media[0]);
        JLabel contenutoL = new JLabel("Contenuto: " + media[1]);
        JLabel gradevolezzaL = new JLabel("Gradevolezzaz: " + media[2]);
        JLabel originalitaL = new JLabel("Originalità: " + media[3]);
        JLabel edizioneL = new JLabel("Edizione: " + media[4]);
        titoloL.setFont(new Font("Impact", Font.PLAIN, 20));
        titoloL.setForeground(new Color(24, 24, 24));

        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(this::actionListenerIndietro);

        risultati = new JList<>();
        risultati.setPreferredSize(new Dimension(200, 100));
        risultati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        risultati.addListSelectionListener(this::risultatiSelectionListener);
        JScrollPane js = new JScrollPane(risultati);
        DefaultListModel<String> lm = new DefaultListModel<String>();

        areaVoto = new JTextArea("Selezionare un utente", 5, 20);  //non so se bisogna cambiare il numero di righe da 9 a 5
        areaVoto.setLineWrap(true);
        areaVoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        areaVoto.setEditable(false);
        areaVoto.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JScrollPane jsP = new JScrollPane(areaVoto);

        areaCommento = new JTextArea("Selezionare un utente", 5,20); //stessa cosa
        areaCommento.setLineWrap(true);
        areaCommento.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        areaCommento.setEditable(false);
        areaCommento.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JScrollPane jsC = new JScrollPane(areaCommento);

        String temp = "";
        int i = 1;
        for (Giudizio g : giudizi) {
            temp = i + ") Username: " + g.getUsername();
            lm.addElement(temp);
            i++;
        }
        if(i == 1) {
            lm.addElement("Non sono presenti recensioni");
            risultati.setModel(lm);
            JOptionPane.showMessageDialog(this, "Non sono presenti recensioni per il libro selezionato", "Nessun elemento da visualizzare", 2);
        } else {
            risultati.setModel(lm);
        }

        JPanel pInfoLibro = new JPanel(new GridLayout(0, 1, 0, 2));
        JPanel pRiepilogoGiudizi = new JPanel(new GridLayout(0,1));
        JPanel pBottoni = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel pTitolo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JPanel pLabelCommento = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel pLabelVoti = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel pLabelGiudizi = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel pSinistro = new JPanel();
        JPanel pCentro = new JPanel();
        JPanel pMod = new JPanel();
        JPanel pInterno = new JPanel();
        JPanel pInterno2 = new JPanel();

        pSinistro.setLayout(new BoxLayout(pSinistro, BoxLayout.Y_AXIS));
        pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
        pMod.setLayout(new BoxLayout(pMod, BoxLayout.X_AXIS));
        pInterno.setLayout(new BoxLayout(pInterno, BoxLayout.Y_AXIS));
        pInterno2.setLayout((new BoxLayout(pInterno2, BoxLayout.Y_AXIS)));
        pLabelVoti.add(new JLabel("VOTO UTENTE: "));
        pLabelCommento.add(new JLabel("COMMENTI"));
        pLabelGiudizi.add(new JLabel("GIUDIZI: "));
        pInfoLibro.add(libroL);
        pInfoLibro.add(annoL);
        pInfoLibro.add(autoreL);
        pRiepilogoGiudizi.add(riepilogoL);
        pRiepilogoGiudizi.add(stileL);
        pRiepilogoGiudizi.add(contenutoL);
        pRiepilogoGiudizi.add(gradevolezzaL);
        pRiepilogoGiudizi.add(originalitaL);
        pRiepilogoGiudizi.add(edizioneL);
        pBottoni.add(indietro);
        pTitolo.add(titoloL);
        pSinistro.add(pInfoLibro);
        pSinistro.add(Box.createRigidArea(new Dimension(0, 20)));
        pSinistro.add(pRiepilogoGiudizi);
        pCentro.add(pLabelGiudizi);
        pCentro.add(js);
        pCentro.add(Box.createRigidArea(new Dimension(0, 10)));
        pInterno.add(pLabelVoti);
        pInterno.add(jsP);
        pInterno2.add(pLabelCommento);
        pInterno2.add(jsC);
        pMod.add(pInterno);
        pMod.add(pInterno2);
        pCentro.add(pMod);

        JPanel principale = new JPanel();
        principale.setBorder(new EmptyBorder(0, 10, 0, 10));
        principale.setLayout(new BorderLayout(10, 5));
        principale.add(pTitolo, BorderLayout.NORTH);
        principale.add(pSinistro, BorderLayout.WEST);
        principale.add(pCentro, BorderLayout.CENTER);
        principale.add(pBottoni, BorderLayout.SOUTH);

        this.getContentPane().add(principale);
        this.setVisible(true);

    }
    /**
     * Alla selezione di un giudizio presente nella JList risultati viene valorizzata la JTextArea
     * areaCommento con il commento e la valutazione associata al giudizio selezionato.
     * @param e Oggetto di tipo ListSelectionEvent contenente tutte le informazioni sul clic di un elemento della JList risultati
     */

    private void risultatiSelectionListener(ListSelectionEvent e) {
        if(!risultati.getSelectedValue().equals("Non sono presenti recensioni")) {
            int indice = Integer.parseInt(risultati.getSelectedValue().substring(0, 1));
            Giudizio giudizioSelezionato = giudizi.get(indice - 1);
            String temp = 
                      "STILE: " + giudizioSelezionato.getCommentoStile() + "\n"                    
                     +"CONTENUTO': " + giudizioSelezionato.getCommentoContenuto() + "\n"
                     +"GRADEVOLEZZA: " + giudizioSelezionato.getCommentoGradevolezza() + "\n"
                     +"ORIGINALITA': " + giudizioSelezionato.getCommentoOriginalita() + "\n"
                     +"EDIZIONE: " + giudizioSelezionato.getCommentoEdizione();
            areaCommento.setText(temp);
            String temp2 = 
                      "Stile: " + giudizioSelezionato.getValutazioneStile() + "\n"
                     +"Contenuto: " + giudizioSelezionato.getValutazioneContenuto() + "\n"
                     +"Gradevolezza: " + giudizioSelezionato.getValutazioneGradevolezza() + "\n"
                     +"Originalità: " + giudizioSelezionato.getValutazioneOriginalita() + "\n"
                     +"Edizione: " + giudizioSelezionato.getValutazioneEdizione();
            areaVoto.setText(temp2);
        }
    }
    /**
     * Al clic del bottone indietro viene settata a "true" la visibilità della schermata "padre"
     * RisultatiRicerca oppure VisualizzaLibreria e successivamente viene chiusa la schermata VisualizzaLibro in esecuzione
     * @param e Oggetto di tipo ActionEvent  contenente tutte le informazioni sul clic del pulsante indietro
     * @see RisultatiRicerca
     */

    private void actionListenerIndietro(ActionEvent e) {
        if(ritorno == 0) {
            this.setVisible(false);
            risultatiRicerca.setVisible(true);
            this.dispose();
        }else {
            this.setVisible(false);
            libriLibreria.setVisible(true);
            this.dispose();
        }
    }
    /**
     * Il metodo si occupa di effettuare la ricerca di tutti i giudizi associati al libro selezionato presenti nel
     * file "Criteri.dati" e di inserirli all'interno dell'ArrayList giudizi, attributo della classe.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     * @throws EOFException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo EOFException che può essere sollevata dal metodo.
     */

    private void visualizzaEmozioneLibro() throws IOException, EOFException {
        int indice = 0;
        String temp = "";
        String letto = "";
        boolean mediaok = false;
        Giudizio provvisorio;

        FileReader f = new FileReader("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\Emozioni.txt"); 
        BufferedReader br = new BufferedReader(f);

        while ((letto = br.readLine()) != null) {
            var coding = letto;
            String[] StringaSeparata = coding.split("\\$");
            if(StringaSeparata[0].trim().equals(Libro.getId().trim())) {
                mediaok = true;
                provvisorio = new Giudizio();
                    provvisorio.setUsername(StringaSeparata[2]);
                        temp = StringaSeparata[3].substring(0, StringaSeparata[3].indexOf(":"));
                        provvisorio.setValutazioneStile(Integer.valueOf(temp));
                        temp = StringaSeparata[3].substring(StringaSeparata[3].indexOf(":") + 1);
                        System.out.println("Emozione: " + StringaSeparata[3]);
                        provvisorio.setCommentoStile(temp);
                        emozioni[0]++;
                        Valutazione[0] = Valutazione[0] + provvisorio.getValutazioneStile();

                        temp = StringaSeparata[4].substring(0, StringaSeparata[4].indexOf(":"));
                        provvisorio.setValutazioneContenuto(Integer.valueOf(temp));
                        temp = StringaSeparata[4].substring(StringaSeparata[4].indexOf(":") + 1);
                        provvisorio.setCommentoContenuto(temp);
                        emozioni[1]++;
                        Valutazione[1] = Valutazione[1] + provvisorio.getValutazioneContenuto();

                        temp = StringaSeparata[5].substring(0, StringaSeparata[5].indexOf(":"));
                        provvisorio.setValutazioneGradevolezza(Integer.valueOf(temp));
                        temp = StringaSeparata[5].substring(StringaSeparata[5].indexOf(":") + 1);
                        provvisorio.setCommentoGradevolezza(temp);
                        emozioni[2]++;
                        Valutazione[2] = Valutazione[2] + provvisorio.getValutazioneGradevolezza();

                        temp = StringaSeparata[6].substring(0, StringaSeparata[6].indexOf(":"));
                        provvisorio.setValutazioneOriginalita(Integer.valueOf(temp));
                        temp = StringaSeparata[6].substring(StringaSeparata[6].indexOf(":") + 1);
                        provvisorio.setCommentoOriginalita(temp);
                        emozioni[3]++;
                        Valutazione[3] = Valutazione[3] + provvisorio.getValutazioneOriginalita();

                        temp = StringaSeparata[7].substring(0, StringaSeparata[7].indexOf(":"));
                        provvisorio.setValutazioneEdizione(Integer.valueOf(temp));
                        temp = StringaSeparata[7].substring(StringaSeparata[7].indexOf(":") + 1);
                        provvisorio.setCommentoEdizione(temp);
                        emozioni[4]++;
                        Valutazione[4] = Valutazione[4] + provvisorio.getValutazioneEdizione();

                    giudizi.add(indice, provvisorio);
                    indice++;
            }
        }

        br.close();
        if(mediaok == true) {
            for(int h = 0; h < emozioni.length; h++) {
                media[h] = Double.valueOf(Valutazione[h]) / Double.valueOf(emozioni[h]);
                System.out.println("N volte Emozione" + h + " :" + emozioni[h]);
                System.out.println("Valutazione totale" + h + " :" + Valutazione[h]);
                System.out.println("MEDIA" + h + " :" + media[h]);
            }
        }
    }

}
