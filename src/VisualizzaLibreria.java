//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
/**
 * Questa classe permette di scegliere se:
 * 1) Visualizzare un libro;
 * 2) Aggiungere un libro alla libreria;
 * 3) Cancellare un libro dalla libreria;
 * 4) Giudicare un libro;
 * 5) Tornare all'elenco delle librerie.
 * Rende visibili con un elenco i libri di una specifica libreria di un utente.
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */
public class VisualizzaLibreria extends javax.swing.JFrame {
    /**
     * Contiene il nickname del cliente che ha eseguito l'accesso.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String userName;
    /**
     * Contiene l'oggetto di tipo Libreria ricevuto.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private Libreria l;
    /**
     * Contiene il nome della libreria che contiene i libri.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String nome;
    /**
     * Contiene gli oggetti di tipo Libro trovati dalla ricerca.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private JList<Libro> risultati;
    /**
     * Conterrà l'oggetto Libro selezionato dalla JList risultati.à
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private Libro LibroSelezionato;
    /**
     * Conterrà gli oggetti Libro trovati dal metodo ricercaLibri.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private ArrayList<Libro> listaRicerca = new ArrayList<Libro>();
    /**
     * Metodo costruttore della finestra VisualizzaLibreria
     * @param l valorizza l'attributo p della classe.
     * @param username In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo username della classe
     */

    public VisualizzaLibreria(Libreria l, String username) {
        super(l.getNome());
        this.setSize(0, 0);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userName = username;
        this.l = l;
        this.nome = l.getNome();

        try {
            ricercaLibri();
        } catch (EOFException eof) {
            eof.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (listaRicerca.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nuessun Libro", "Info", JOptionPane.INFORMATION_MESSAGE);

            JLabel titoloL = new JLabel("Libri presenti: 0");
            titoloL.setFont(new Font("Impact", Font.PLAIN, 0));
            titoloL.setForeground(new Color(0, 0, 0));


            JButton indietro = new JButton("Indietro");
            JButton add = new JButton("Aggiungi");
            indietro.addActionListener(this::actionListenerIndietro);
            add.addActionListener(this::actionListenerAggiungiLibro);


            JList<String> vuota = new JList<>();
            vuota.setPreferredSize(new Dimension(0, 0));
            vuota.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane js = new JScrollPane(vuota);


            DefaultListModel<String> lm = new DefaultListModel<String>();
            lm.addElement("Non sono presenti Libri");
            vuota.setModel(lm);


            JPanel pTitolo = new JPanel(new FlowLayout());
            JPanel pCentro = new JPanel();
            JPanel pBottoni = new JPanel(new FlowLayout());


            pTitolo.add(titoloL);
            pCentro.add(js);
            pBottoni.add(indietro);
            pBottoni.add(add);

            Container principale = this.getContentPane();
            principale.setLayout(new BorderLayout());
            principale.add(pTitolo, BorderLayout.NORTH);
            principale.add(pCentro, BorderLayout.CENTER);
            principale.add(pBottoni, BorderLayout.SOUTH);
            this.pack();
            this.setVisible(true);


        } else {

            JLabel titoloL = new JLabel("Libri presenti: " + listaRicerca.size());
            titoloL.setFont(new Font("Impact", Font.PLAIN, 0));
            titoloL.setForeground(new Color(0, 0, 0));

            JButton visualizza = new JButton("Visualizza");
            JButton indietro = new JButton("Indietro");
            JButton cancella = new JButton("Cancella");
            JButton giudica = new JButton("Giudica");
            JButton add = new JButton("Aggiungi");
            visualizza.addActionListener(this::actionListenerVisualizza);
            indietro.addActionListener(this::actionListenerIndietro);
            cancella.addActionListener(this::actionListenerCancellaLibro);
            giudica.addActionListener(this::actionListenerGiudica);
            add.addActionListener(this::actionListenerAggiungiLibro);

            risultati = new JList<>();
            risultati.setPreferredSize(new Dimension(0, listaRicerca.size() * 18));
            risultati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane js = new JScrollPane(risultati);


            DefaultListModel<Libro> lm = new DefaultListModel<Libro>();
            for (Libro lib : listaRicerca) {
                lm.addElement(lib);
                risultati.setModel(lm);
            }

            JPanel pTitolo = new JPanel(new FlowLayout());
            JPanel pCentro = new JPanel();
            JPanel pBottoni = new JPanel(new FlowLayout());


            pTitolo.add(titoloL);
            pCentro.add(js);
            pBottoni.add(indietro);
            pBottoni.add(cancella);
            pBottoni.add(add);
            pBottoni.add(visualizza);
            pBottoni.add(giudica);

            Container principale = this.getContentPane();
            principale.setLayout(new BorderLayout());
            principale.add(pTitolo, BorderLayout.NORTH);
            principale.add(pCentro, BorderLayout.CENTER);
            principale.add(pBottoni, BorderLayout.SOUTH);
            this.pack();
            this.setVisible(true);

        }
    }
    /**
     * Al clic del bottone indietro verrà creata una nuova finestra ElencoLibrerie
     * e verrà chiusa la finestra VisualizzaLibreria in esecuzione.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone indietro
     * @see ElencoLibreria
     */


    private void actionListenerIndietro(ActionEvent e) {
        this.setVisible(false);
        new ElencoLibreria(userName).setVisible(true);
        this.dispose();
    }
    /**
     * Al clic del bottone cancella verrà cancellato il libro selezionato utilizzando il metodo cancellaLibro,
     * verrà creata una nuova finestra ElencoLibrerie e vverà chiusa la finestra VisualizzaLibreria in esecuzione.
     * se non è stato selezionato nessun libro verrà visualizzato un messaggio d'errore.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone cancella
     * @see ElencoLibreria
     */

    private void actionListenerCancellaLibro(ActionEvent e) {
        if (this.risultati.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Selezionare un libro", "Errore selezione", 0);
        } else {
            try {
                cancellaLibro(this.risultati.getSelectedValue().getId());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Libro cancellato correttamente", "Cancellazione", 0);
            this.setVisible(false);
            new ElencoLibreria(userName);
            dispose();
        }
    }
    /**
     * Al clic del bottone aggiungi verrà creata una nuova finestra CercaLibro
     * e verrà chiusa la finestra VisualizzaLibreria in esecuzione.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone aggiungi
     * @see CercaLibro
     */



    private void actionListenerAggiungiLibro(ActionEvent e) {
        this.setVisible(false);
        (new CercaLibro(userName, this.l.getNome(), "", true, true)).setVisible(true);
        dispose();
    }
    /**
     * Al clic del bottone giudica, se è stato selezionato un libro nella JList risultati,
     * viene creata una nuova finestra GiudicaLibro e viene
     * settata a "false" la visibilità della finestra VisualizzaLibreria in esecuzione.
     * Nel caso in cui non fosse stato selezionato un libro, viene mostrata una finestra contenente un messaggio d'errore.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone giudica
     * @see GiudicaLibro
     */


    private void actionListenerGiudica(ActionEvent e) {
        if (this.risultati.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Seleziona un libro", "Errore selezione", 0);
        } else {
            setVisible(false);
            (new GiudicaLibro(userName, this, this.risultati.getSelectedValue(), this.l)).setVisible(true);
            dispose();
        }
    }
    /**
     * Al clic del bottone visualizza, se è stato selezionato un brano nella JList risultati,
     * viene creata una nuova finestra VisualizzaLibro e viene
     * settata a "false" la visibilità della finestra VisualizzaLibreria in esecuzione.
     * Nel caso in cui non fosse stato selezionato un libro, viene mostrata una finestra contenente un messaggio d'errore.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone visualizza
     * @see VisualizzaLibro
     */

    private void actionListenerVisualizza(ActionEvent e) {
        if (risultati.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Seleziona un libro", "Errore selazione", JOptionPane.WARNING_MESSAGE);
        } else {
            for (Libro l : listaRicerca) {
                if (risultati.getSelectedValue().equals(l)) {
                    LibroSelezionato = l;
                }
            }
            this.setVisible(false);
            new VisualizzaLibro(userName, LibroSelezionato, this).setVisible(true);
        }
    }
    /**
     * Metodo che si occupa di effettuare la ricerca del/dei libro/i sulla base dei parametri inseriti nella schermata
     * ElencoLibreria all'interno del file "Libri.dati". Viene unicamente richiamato in fase di costruzione della
     * finestra con il solo scopo di mostrare all'utente i risultati trovati all'interno della JList risultati.
     * A sua volta utilizza il metodo dettagliLibro per riempire la JList risultati di oggetti di tipo Libro
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     * @throws EOFException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo EOFException che può essere sollevata dal metodo.
     */


    private void ricercaLibri() throws IOException, EOFException {
        if (!l.getLibri().equals("")) {
            int indice = 0;
            String tmp = l.getLibri();
            Libro provvisorio;
            var coding = tmp;
            String[] StringaSeparata = coding.split("\\$");

            for (int i = 0; i < StringaSeparata.length; i++) {
                provvisorio = new Libro();
                provvisorio = dettagliLibro(StringaSeparata[i].trim());
                listaRicerca.add(indice, provvisorio);
                indice++;
            }
        }
    }
    /**
     * Metodo che si occupa di effettuare la ricerca dei dettagli dei libri sulla base dei parametri passati dal metodo
     * ricercaLibri all'interno del file "Libri.dati". Viene unicamente richiamato in fase di costruzione della finestra
     * con il solo scopo di restituire oggetti di tipo Libro per riempire la JList risultati.
     * @param id è la stringa che permette la ricerca dei dettagli della canzone nei file.
     * @return Ritorno l'oggetto Libro che corrisponde al parametro id passato.
     * @throws FileNotFoundException FileNotFoundException Dichiara che, in fase di richiamo di questo metodo, dovr&agrave; essere gestita un'eccezione
     * di tipo FileNotFoundException che pu&ograve; essere sollevata dal metodo.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     */


    private Libro dettagliLibro(String id) throws FileNotFoundException, IOException {
        Libro l = new Libro();
        String tmp = "";
        FileReader f = new FileReader("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\BooksDatasetClean.csv");
        BufferedReader br = new BufferedReader(f);

        while ((tmp = br.readLine()) != null) {
            var coding = tmp;
            String[] StringaSeparata = coding.split("\\$");
            if (StringaSeparata[1].contains("ID:")) {
                StringaSeparata[1] = StringaSeparata[1].replace("ID:", "");
                if (StringaSeparata[1].trim().equals(id.trim())) {
                    l.setId(StringaSeparata[1]);
                    l.setAnno(StringaSeparata[2].replace("Anno:", ""));
                    l.setAutore(StringaSeparata[3].replace("Autore:", ""));
                    l.setTitolo(StringaSeparata[4].replace("Titolo:", ""));
                    l.setGenere(StringaSeparata[5].replace("Genere:", ""));
                    l.setLibreria(StringaSeparata[6].replace("Libreria:", ""));
                    l.setPagine(StringaSeparata[7].replace("Pagine:", ""));
                }
            }
        }
        return l;
    }

    /**
     * Metod che si occupa di effettuare la cancellazione del libro selezionato passato dal metodo
     * actionListenerCancellaLibro all'interno del file "Libreria.dati".
     * Viene sempre richiamato quando si vuole effettuare la cancellazione di un libro dalla libreria.
     * @param idSel è la stringa che permette la ricerca e l'eliminazione del libro corretto dal file.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     */
    private void cancellaLibro(String idSel) throws IOException {
        File a = new File("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\Libreria.txt");
        if (!a.exists()) {
            a.createNewFile();
            String letto = "";
            String vecchioContenuto = "";
            FileReader f = new FileReader(a);
            BufferedReader br = new BufferedReader(f);


            while ((letto = br.readLine()) != null) {
                vecchioContenuto = vecchioContenuto + letto + "\n";
                if (letto.length() > 8) {
                    if (letto.substring(0, 8).contentEquals("username")) {
                        if (letto.substring(9).trim().equals(userName.trim())) {
                            if ((letto = br.readLine()) != null) {
                                vecchioContenuto = vecchioContenuto + letto + "\n";
                                if (letto.substring(0, 4).contentEquals("nome")) {
                                    if (letto.substring(5).trim().equals(nome.trim())) {
                                        if (letto.substring(0, 5).contentEquals("Libri")) {
                                            String s = "Libri";
                                            String[] stringaSeparata = letto.substring(6).trim().split("\\$");
                                            int lunghezza = stringaSeparata.length;
                                            for (int i = 0; i < lunghezza; i++) {
                                                if (stringaSeparata[i].trim().equalsIgnoreCase(idSel.trim())) {
                                                    System.out.println("Ssep ID case:" + stringaSeparata[i]);
                                                    System.out.println("ID passato case:" + idSel.trim());
                                                } else {
                                                    if (s.equals("Libri:")) {
                                                        s = s + stringaSeparata[i];
                                                    } else {
                                                        s = s + "$" + stringaSeparata[i];
                                                    }
                                                }
                                                vecchioContenuto = vecchioContenuto + s + "\n";
                                            }

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
            bw.close();
        }

    }
}
