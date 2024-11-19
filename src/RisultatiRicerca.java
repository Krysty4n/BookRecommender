//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Questa classe mostra il/i libro/i trovato/i dalla ricerca e permette di:
 * 1) Visualizzare un libro;
 * 2) Aggiungere un libro alla libreria;
 * 3) Creare una libreria;
 * 4) Giudicare un libro;
 * 5) Tornare al tipo di ricerca;
 * Rende visibili con un elenco i libri cercati dall'utente.
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 * @see VisualizzaLibro
 * @see GiudicaLibro
 * @see CercaLibro
 * @see ElencoLibreria
 */



public class RisultatiRicerca extends javax.swing.JFrame {
    /**
     * Contiene lo username del cliente che ha eseguito l'accesso.
     * Se invece è stata utilizzata la funzionalità "consulta repository", questo attributo conterrà una stringa vuota.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */
    private String userName;

    /**
     * Contiene gli oggetti di tipo Libro, trovati dalla ricerca.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */

    private JList<Libro> risultati;
    /**
     * Può contenere il nome ricercato nella finestra CercaLibro, in base al
     * metodo di ricerca scelto.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */
    private String filtro1;
    /**
     * Nel caso in cui sia stata utilizzata la ricerca per autore e anno, questo attributo contiene
     * il tipo ricercato nella finestra percedente.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */
    private String filtro2;
    /**
     * Conterrà il nome dell'oggetto Libreria a cui appartiene il libro.
     * Se viene passasto da RisultatoRicerca risulta null.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */
    private String nomeLibreria;
    /**
     * Conterrà l'oggetto Libro selezionato dalla JList risultati.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */
    private Libro libroSelezionato;
    /**
     * Contiene una variabile booleana che permette l'aggiunta di più libri.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */
    private boolean selezionaUnAltro;
    /*
     * Contiene una variabile booleana che permette la visualizzazione di alcuni bottoni solo dopo il login.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */
    private boolean aggiungi;
    /**
     * Conterrà l'oggetto Libreria che deve essere passato alla finestra GiudicaLibro.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */
    private Libreria lb;
    /**
     * Contiene gli oggetti Libro trovati dal metodo CercaLibro.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */


    private ArrayList<Libro> listaRicerca = new ArrayList<Libro>();
    /**
     * Contiene i libri da aggiungere alla libreria.
     * Potrebbe essere vuota.
     * Dichiarato private così da essere visto solo dalla classe attuale.
     */

    String risultatoTitoli;
    /**
     *  Metodo costruttore della finestra RisultatoRicerca
     * @param filtro1 Valorizza l'attributo filtro1 della classe.
     * @param filtro2 Valorizza l'attributo filtro2 della classe.
     * @param tipoRicerca Contiene un numero da 1 a 2, che indica quale tipo di ricerca si intedde effettuare:
     * 1) per titolo
     * 2) per autore e anno
     * @param userName In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo nickname della classe.
     * @param nomeLibreria In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo nomePlaylist della classe.
     * @param risultatoTitoli In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo risultatotitoli della classe.
     * @param add In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo aggiungi della classe.
     */

    public RisultatiRicerca(String filtro1, String filtro2, int tipoRicerca, String userName, String nomeLibreria, String risultatoTitoli, boolean add) {
        super("Risultati Ricerca");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName = userName;
        this.filtro1 = filtro1;
        this.filtro2 = filtro2;
        this.nomeLibreria = nomeLibreria;
        this.risultatoTitoli = risultatoTitoli;
        this.aggiungi = add;
        this.selezionaUnAltro = true;
        lb = new Libreria();
        lb.setNome("");
        try {
            cercaLibro(tipoRicerca);

        } catch (EOFException eof) {
            eof.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (this.listaRicerca.size() == 0) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JOptionPane.showMessageDialog(this, "Nessun libro trovato", "Errore", 2);
            setVisible(false);
            if (aggiungi) (new CercaLibro(userName, nomeLibreria, risultatoTitoli, true, aggiungi)).setVisible(true);
            else if (risultatoTitoli.equals(""))
                (new CercaLibro(userName, nomeLibreria, risultatoTitoli, true, aggiungi)).setVisible(true);
            else (new CercaLibro(userName, nomeLibreria, risultatoTitoli, false, aggiungi)).setVisible(true);
            dispose();


        } else
            setSize(330, 260);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);

        JLabel titoloL = new JLabel("Risultati ricerca: " + this.listaRicerca.size());
        titoloL.setFont(new Font("Impact", 0, 0));
        titoloL.setForeground(new Color(0,0,0));

        //JButton
        JButton visualizza = new JButton("Visualizza");
        JButton indietro = new JButton("Indietro");
        JButton giudica = new JButton("Giudica");
        JButton addAllaLibreria = new JButton("Aggiungi alla Libreria");
        JButton addLibro = new JButton("Aggiungi");
        visualizza.addActionListener(this::actionListenerVisualizza);
        indietro.addActionListener(this::actionListenerIndietro);
        giudica.addActionListener(this::actionListenerGiudica);
        addAllaLibreria.addActionListener(this::actionListeneraddAllaLibreria);
        addLibro.addActionListener(this::actionListeneraddLibro);

        if (nomeLibreria.equals("") || userName.equals(""))
            addAllaLibreria.setEnabled(false);
        if (userName.equals("") || !nomeLibreria.equals(""))
            giudica.setEnabled(false);
        if (aggiungi == false)
            addLibro.setEnabled(false);
        //Jlist
        this.risultati = new JList<>();
        System.out.println(this.listaRicerca.size());
        this.risultati.setPreferredSize(new Dimension(460, this.listaRicerca.size() * 18));
        this.risultati.setSelectionMode(0);
        JScrollPane js = new JScrollPane(this.risultati);
        DefaultListModel<Libro> lm = new DefaultListModel<>();
        for (Libro lb : this.listaRicerca) {
            lm.addElement(lb);

            //JPanel

            JPanel pTitolo = new JPanel(new FlowLayout());
            JPanel pCentro = new JPanel();
            JPanel pBottoni = new JPanel(new FlowLayout());
            pTitolo.add(titoloL);
            pCentro.add(giudica);
            pCentro.add(js);
            pBottoni.add(indietro);
            pBottoni.add(visualizza);
            pBottoni.add(giudica);
            if (aggiungi == false) {
                pBottoni.add(addAllaLibreria);

            } else {
                pBottoni.add(addLibro);

            }
            Container principale = getContentPane();
            principale.setLayout(new BorderLayout());
            principale.add(pTitolo, "North");
            principale.add(pCentro, "Center");
            principale.add(pBottoni, "South");
            pack();
            setVisible(true);


        }


    }
    /**
     * Al clic del bottone indietro verrà creata una nuova finestra CercaLibro
     * e verrà chiusa la finestra VisualizzaLibreria in esecuzione.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone indietro
     * @see ElencoLibreria
     */

    private void actionListenerIndietro(ActionEvent e) {
        setVisible(false);
        (new CercaLibro(this.userName, nomeLibreria, risultatoTitoli, true, aggiungi)).setVisible(true);
        dispose();
    }
    /**
     * Al clic del bottone giudica , se è stato selezionato un libro nella JList
     * risultati, viene creata una nuova finestra GiudicaLibro e viene
     * settata a "false" la visibilità della finestra RisultatiRicerca in esecuzione.
     * Nel caso in cui non fosse stato selezionato un brano, viene mostrata una finestra
     * contenente un messaggio di errore.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone giudica
     * @see GiudicaLibro
     */

    private void actionListenerGiudica(ActionEvent e) {
        if (this.risultati.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Selezionare un libro", "Errore Selezione", 2);

        } else {
            setVisible(false);
            (new GiudicaLibro(this.userName, this, this.risultati.getSelectedValue(), this.lb)).setVisible(true);
            dispose();
        }
    }
    /**
     * Al clic del bottone addLibreria, se è stato selezionato un libro nella JList
     * risultati, viene invocato il metodo RegistraLibreria che permette di aggiungere i libri selezionati.
     * Nel caso in cui non fosse stato selezionato un libro, viene mostrata una finestra
     * contenente un messaggio d'errore.
     * Il metodo effettua diversi controlli e risulta chiamabile solo quando viene creata una nuova libreria.
     * Permette tramite una finestra l'aggiunta di più libri mantenendo o utilizzando una nuova ricerca.
     * Se si vanno ad aggiungere più canzoni viene creata una nuova finestra CercaLibro e viene
     * settata a "false" la visibilità della finestra RisultatiRicerca in esecuzione.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone giudica
     * @see CercaLibro
     */

    private void actionListeneraddAllaLibreria(ActionEvent e) {
        if (this.risultati.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Selezionare un libro", "Errore selezione", 2);

        } else {
            Object[] option = {"Aggiungi", "NO"};
            Object[] option2 = {"Cambia", "Mantieni"};
            int conferma = 0;
            int cambioTitolo = 0;
            if (selezionaUnAltro) {
                //risultatiTitoli = risultatiTitoli+"\\$";
                conferma = JOptionPane.showOptionDialog(null, "Vuoi aggoingere un altro linro?", "Seleziona libro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
                if (conferma == 0) {
                    if (risultatoTitoli.equals(""))
                        risultatoTitoli = risultatoTitoli + this.risultati.getSelectedValue().getId();
                    else risultatoTitoli = risultatoTitoli + "$" + this.risultati.getSelectedValue().getId();
                    cambioTitolo = JOptionPane.showOptionDialog(null, "Vuoi utilizzare lo stesso parametro di ricerca?", "Seleziona Libro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option2, option2[0]);

                    if (cambioTitolo == 0) {
                        setVisible(false);
                        (new CercaLibro(userName, nomeLibreria, risultatoTitoli, false, false)).setVisible(true);
                        dispose();

                    }

                } else if (conferma == 1) {
                    if (risultatoTitoli.equals(""))
                        risultatoTitoli = risultatoTitoli + this.risultati.getSelectedValue().getId();
                    else risultatoTitoli = risultatoTitoli + "$" + this.risultati.getSelectedValue().getId();
                    selezionaUnAltro = false;
                    try {
                        RegistraLibreria(risultatoTitoli);

                    } catch (IOException ex) {
                        Logger.getLogger(RisultatiRicerca.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            }

        }
    }
    /**
     * Al clic del bottone visualizza, se è stato selezionato un libro nella JList
     * risultati, viene creata una nuova finestra VisualizzaLibro e viene
     * settata a "false" la visibilità della finestra RisultatiRicerca in esecuzione.
     * Nel caso in cui non fosse stato selezionato un libro, viene mostrata una finestra
     * contenente un messaggio d'errore.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone visualizza
     * @see VisualizzaLibro
     */

    private void actionListenerVisualizza(ActionEvent e) {
        if (this.risultati.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Seleziona un libro", "Errore Selezione", 2);

        } else {
            for (Libro lb : this.listaRicerca) {
                if (this.risultati.getSelectedValue().equals(lb))
                    this.libroSelezionato = lb;

            }
            setVisible(false);
            (new VisualizzaLibro(userName, libroSelezionato, this)).setVisible(true);
            dispose();
        }
    }
    /**
     * Al clic del bottone addLibro, se è stato selezionato un libro nella JList
     * risultati, viene invocato il metodo creaLibro che permette di aggiungere il libro selezionato alla libreria.
     * Nel caso in cui non fosse stato selezionato un libro, viene mostrata una finestra
     * contenente un messaggio d'errore.
     * Il metodo risulta chiamabile solo quando viene aggiunto un solo brano ad una libreria.
     * Al termine del metodo viene creata una nuova finestra ElencoLibreria e viene settata a "false"
     * la visibilità della finestra RisultatiRicerca in esecuzione.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone giudica
     * @see ElencoLibreria
     */

    private void actionListeneraddLibro(ActionEvent e) {
        if (this.risultati.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Selezionare un libro", "Errore selezione", 2);
        } else {
            try {
                creaLibro(this.risultati.getSelectedValue().getId());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Libro aggiunto correttamento", "Aggiunto", 1);
            this.setVisible(false);
            new ElencoLibreria(userName);
            dispose();

        }

    }
    /**
     * Metod che si occupa di effettuare la ricerca del/dei libro/i sulla base dei parametri inseriti nella schermata
     * CercaLibro all'interno del file "Libri.dati".
     * Viene unicamente richiamato in fase di costruzione della
     * finestra con il solo scopo di mostrare all'utente i risultati all'interno della JList risultati.
     * @param index Contiene un numero da 1 a 2 indicante il tipo di ricerca da effettuare:
     * 1) Titolo;
     * 2) Autore e anno.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     * @throws EOFException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo EOFException che può essere sollevata dal metodo.
     */

    private void cercaLibro(int index) throws IOException, EOFException {
        int indice = 0;
        String tmp = "";
        FileReader f = new FileReader("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\BooksDatasetClean.csv");
        BufferedReader br = new BufferedReader(f);
        if (index == 1) {
            while ((tmp = br.readLine()) != null) {
                var coding = tmp;
                String[] StringaSeparata = coding.split("\\$");
                if (StringaSeparata[1].contains("ID:")) {
                    StringaSeparata[4] = StringaSeparata[4].replace("Titolo;", "");
                    if (StringaSeparata[4].toLowerCase().contains(this.filtro1)) {
                        Libro provvisorio = new Libro();
                        provvisorio.setId(StringaSeparata[1].replace("ID:", ""));
                        provvisorio.setTitolo(StringaSeparata[2].replace("Titolo:", ""));
                        provvisorio.setAnno(StringaSeparata[3].replace("Anno:", ""));
                        provvisorio.setGenere(StringaSeparata[4].replace("Genere:", ""));
                        this.listaRicerca.add(indice, provvisorio);
                        indice++;


                    }


                }
            }
            br.close();

        } else {
            while ((tmp = br.readLine()) != null) {
                var coding = tmp;
                String[] StringaSeparata = coding.split("\\$");
                if (StringaSeparata[1].contains("ID:")) {
                    StringaSeparata[3] = StringaSeparata[3].replace("Autore:", "");
                    if (StringaSeparata[3].toLowerCase().contains(this.filtro1)) {
                        Libro provvisorio = new Libro();
                        provvisorio.setAutore(StringaSeparata[3]);
                        StringaSeparata[2] = StringaSeparata[2].replace("Anno;", "");
                        if (StringaSeparata[2].toLowerCase().contains(this.filtro2)) {
                            provvisorio.setId(StringaSeparata[1].replace("ID:", ""));
                            provvisorio.setTitolo(StringaSeparata[2].replace("Titolo:", ""));
                            provvisorio.setAnno(StringaSeparata[2]);
                            provvisorio.setGenere(StringaSeparata[4].replace("Genere:", ""));
                            this.listaRicerca.add(indice, provvisorio);
                            indice++;


                        }
                    }
                }
            }
        }
    }
    /**
     * Metodo che si occupa di effettuare la registrazione della nuova libreria sulla base dei parametri inseriti nella schermata
     * CercaLibro e RisultatiRicerca all'interno del file "Libreria.dati".
     * Viene chiamato in fase di costruzione della nuova libreria.
     * @param s Contiene la stringa di libri da inserire nella playlist.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     * @throws EOFException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo EOFException che può essere sollevata dal metodo.
     */

    private void RegistraLibreria(String s) throws IOException, EOFException {
        try {
            String separatore = ":";
            String aCapo = "\n";
            String composizione = "username: " + userName;
            composizione = composizione + "\nnome:" + nomeLibreria;
            composizione = composizione + "\nlibro:";
            composizione = composizione + s + "\n";
            File f = new File("C:\\\\Users\\david\\Desktop\\Book- Recommender\\data\\Libreria.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            if (!f.exists())
                f.createNewFile();
            bw.append(composizione);
            bw.close();
            JOptionPane.showMessageDialog(this, "Registrazione Libreria effettuata con successo", "Registrazione", 1);
            setVisible(false);
            (new ElencoLibreria(userName)).setVisible(true);
            dispose();


        } catch (EOFException eof) {
            eof.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Metodo che si occupa di effettuare l'aggiunta del nuovo libro selezionato alla libreria sulla base dei perimetri inseriti nella schermata
     * CercaLibro e RisultatiRicerca all'interno del file "Libreria.dati".
     * Viene chiamato in fase di aggiunta di un libro ad una libreria.
     * @param idSel Contiene l'id della canzone da aggiungere alla libreria.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     */

    private void creaLibro(String idSel) throws IOException {
        File a = new File("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\Libreria.txt");
        if (!a.exists())
            a.createNewFile();
        String letto = "";
        String vecchioContenuto = "";
        FileReader f = new FileReader(a);
        BufferedReader br = new BufferedReader(f);
        while ((letto = br.readLine()) != null) {
            vecchioContenuto = vecchioContenuto + letto + "\n";
            if (letto.length() > 8) {
                if (letto.substring(0, 8).contentEquals("userName")) {
                    if (letto.substring(9).trim().equals(userName.trim())) {
                        if ((letto = br.readLine()) != null) {
                            vecchioContenuto = vecchioContenuto + letto + "\n";
                            if (letto.substring(5).trim().equals(nomeLibreria.trim())) {
                                if ((letto = br.readLine()) != null) {
                                    if (letto.substring(0, 5).contentEquals("libri")) {
                                        if (letto.trim().length() < 7) letto = letto + idSel.trim();
                                        else letto = letto + "$" + idSel.trim();
                                        vecchioContenuto = vecchioContenuto + letto + "\n";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(a, false));
        bw.write(vecchioContenuto);
        bw.write(vecchioContenuto);
        bw.close();
    }
}
