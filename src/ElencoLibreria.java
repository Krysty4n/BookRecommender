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
 * Questa classe consente di scegliere se:
 * 1) Visualizzare una libreria;
 * 2) Aggiungere una libreria;
 * 3) Cancellare una libreria;
 * 4) Tornare all'area riservta;
 * Rende visibili con un elenco le playlist di un utente.
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */


public class ElencoLibreria extends javax.swing.JFrame {
    /**
     * Contiene lo username del cliente che ha eseguito l'accesso.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private String username;

    /**
     * Contiene il risultato del metodo esisteLb.
     * viene inizializzato a false come condizione base.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private boolean exist = false;
    /**
     * Contiene il nome della/e librerie trovate dalla ricerca
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private JList<Libreria> risultati;
    /**
     * Conterrà l'oggetto Libreria selezionato dalla JList risultati.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private Libreria libreriaSelezionata;
    /**
     * Contiene gli oggetti Libreria trovati dal metodo ricercaLibreria.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private ArrayList<Libreria> listaRicerca = new ArrayList<Libreria>();
    /**
     * Metodo costruttore della finestra ElencoLibreria
     * @param username In fase di costruzione dell'oggetto, questo parametro verrà utoilizzato per valorizzare
     * l'attributo username della classe,
     */


    public ElencoLibreria(String username) {

        super("Risultati Ricerca");
        this.username = username;
        this.setSize(330, 260);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            ricercaLibreria();
        } catch (EOFException eof) {
            eof.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(listaRicerca.size() == 0) {

            //JLABEL

            JLabel titoloL = new JLabel("Librerie trovate: 0");
            titoloL.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
            titoloL.setForeground(Color.decode("#6699FF"));
//JBUTTON
            JButton indietro = new JButton("Indietro");
            JButton crea = new JButton("Crea");
            indietro.addActionListener(this::actionListenerIndietro);
            crea.addActionListener(this::actionListenerCrea);
//JLIST
            JList<String> vuota = new JList<>();
            vuota.setPreferredSize(new Dimension(260, 100));
            vuota.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane js = new JScrollPane(vuota);
//JLIST VUOTA
            DefaultListModel<String> lm = new DefaultListModel<String>();
            lm.addElement("Non sono presenti librerie");
            vuota.setModel(lm);
//JPANEL
            JPanel pTitolo = new JPanel(new FlowLayout());
            JPanel pCentro = new JPanel();
            JPanel pBottoni = new JPanel(new FlowLayout());

            pTitolo.add(titoloL);
            pCentro.add(crea);
            pCentro.add(js);
            pBottoni.add(indietro);
            pBottoni.add(crea);

            Container principale = this.getContentPane();
            principale.setLayout(new BorderLayout());
            principale.add(pTitolo, BorderLayout.NORTH);
            principale.add(pCentro, BorderLayout.CENTER);
            principale.add(pBottoni, BorderLayout.SOUTH);
            this.pack();
            this.setVisible(true);
        }
        else {
//JLABEL
            JLabel titoloL = new JLabel("Librerie trovate: " + listaRicerca.size());
            titoloL.setFont(new Font("Impact", Font.PLAIN, 20));
            titoloL.setForeground(new Color(24, 24, 24));
//JBUTTON
            JButton visualizza = new JButton("Visualizza");
            JButton indietro = new JButton("Indietro");
            JButton crea = new JButton("Crea");
            JButton cancella = new JButton("Cancella");
            visualizza.addActionListener(this::actionListenerVisualizza);
            crea.addActionListener(this::actionListenerCrea);
            cancella.addActionListener(this::actionListenerCancella);
//JLIST
            risultati = new JList<>();
            risultati.setPreferredSize(new Dimension(260, listaRicerca.size()*18));
            risultati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane js = new JScrollPane(risultati);

            //Riempo la JList con gli oggetti Libreria
            DefaultListModel<Libreria> lm = new DefaultListModel<Libreria>();
            for (Libreria l : listaRicerca)
                lm.addElement(l);
            risultati.setModel(lm);

            //JPanel
            JPanel pTitolo = new JPanel(new FlowLayout());
            JPanel pCentro = new JPanel();
            JPanel pBottoni = new JPanel(new FlowLayout());

            pTitolo.add(titoloL);
            pCentro.add(crea);
            pCentro.add(js);
            pBottoni.add(indietro);
            pBottoni.add(crea);
            pBottoni.add(visualizza);
            pBottoni.add(cancella);

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
     * Al clic del bottone Indietro verrà creata una nuova finestra AreaRiservata
     * e verrà chiusa la finestra ElencoLibreria in esecuzione.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone indietro
     * @see AreaRiservata
     */

    private void actionListenerIndietro(ActionEvent e) {
        this.setVisible(false);
        new AreaRiservata(username).setVisible(true);
        this.dispose();
    }
    /**
     * Al clic del bottone crea verrà invocato il metodo esisteLb e a seconda del risultato:
     * True: verrà creata una nuova finestra CercaLibro e verrà chiusa la finestra ElencoLibreria in esecuzione;
     * False: verrà viusalizzato un messagio d'errore.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone giudica.
     * @see RegistraLibreria (metodo presente in RisultatiRicerca)
     */

    private void actionListenerCrea(ActionEvent e){

        String inputNomeLibreria = JOptionPane.showInputDialog(this, "Inserisci il nome della Libreria da cercare ", "Crea libreria", 3);
        if (inputNomeLibreria != null && "".equals(inputNomeLibreria)){
            JOptionPane.showMessageDialog(this, "E' necessario inserire un valore", "Errore Ricerca", 2);
        }else if (inputNomeLibreria != null){
            try {
                exist = esisteLb(inputNomeLibreria);
            } catch (EOFException eof) {
                eof.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(exist == false){
                setVisible(false);
                (new CercaLibro(username, inputNomeLibreria, "", true, false)).setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Esiste già una Libreria con questo nome", "Errore", 2);
            }
        }
    }
    /**
     * Al clic del bottone Cancella, se è stata selezionata una libreria nella JList risultati,
     * verrà invocato il metodo cancellaLibreria che andrà ad eliminare la Libreria selezionata.
     * Succesivamente verrà creata una nuova finestra AreaRiservata
     * e verrà chiusa la finestra ElencoLibreria in esecuzione.
     * Nel caso in cui non fosse stata selezionata una libreria, verrà mostrata una finestra contenente un messaggio d'errore.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone cancella.
     * @see AreaRiservata
     */

    private void actionListenerCancella(ActionEvent e){
        if(risultati.getSelectedValue() == null){
            JOptionPane.showMessageDialog(this, "Selezionare una libreria", "Errore Selezione", JOptionPane.WARNING_MESSAGE);
        }else {
            for (Libreria l : listaRicerca) {
                if(risultati.getSelectedValue().equals(l))
                    libreriaSelezionata = l;
            }
            try {
                cancellaLibreria(libreriaSelezionata.getUsername(), libreriaSelezionata.getNome());
            } catch (EOFException eof) {
                eof.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.setVisible(false);
            JOptionPane.showMessageDialog(this, "Libreria cancellata correttamente", "Cancellazione", 1);
            new AreaRiservata(username).setVisible(true);
            dispose();
        }
    }
    /**
     * Al clic del bottone Visualizza, se è stata selezionata una libreria nella JList
     * risultati, verrà creata una nuova finestra VisualizzaLibreriae verrà
     * settata a "false" la visibilità della finestra ElencoLibreria in esecuzione.
     * Nel caso in cui non fosse stata selezionata una libreria, verrà mostrata una finestra
     * contenente un messaggio d'errore.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone visualizza.
     * @see VisualizzaLibreria
     */

    private void actionListenerVisualizza(ActionEvent e) {
        if(risultati.getSelectedValue() == null){
            JOptionPane.showMessageDialog(this, "Selezionare una libreria", "Errore Selezione", JOptionPane.WARNING_MESSAGE);
        }else {
            for (Libreria l : listaRicerca) {
                if(risultati.getSelectedValue().equals(l))
                    libreriaSelezionata = l;
            }
            this.setVisible(false);
            new VisualizzaLibreria(libreriaSelezionata, username);
        }
    }
    /**
     * Metodo che si occupa di effettuare la ricerca della/e libreria sulla base del parametro nickname
     * all'interno del file "Libreria.dati". Viene unicamente richiamato in fase della costruzione della
     * finestra con il solo scopo di mostrare all'utente i risultati trovati all'interno della JList risultati.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * del tipo IOException che può essere sollevata dal metodo.
     * @throws EOFException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * del tipo EOFException che può essere sollevata dal metodo.
     */

    private void ricercaLibreria() throws IOException, EOFException{
        int indice = 0;
        String temp;
        Libreria provvisoria;

        File a = new File("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\Libreria.txt"); //da completare

        if (!a.exists())
            a.createNewFile();
        
        FileReader f = new FileReader(a);
        BufferedReader br = new BufferedReader(f);

        while ((temp = br.readLine()) != null) {
            if(temp.length() > 8) {
                if(temp.substring(0, 8).contentEquals("username")) {
                    if(temp.substring(9).toLowerCase().trim().equals(username.toLowerCase().trim())) {
                        provvisoria = new Libreria();
                        provvisoria.setUserName(username);
                        provvisoria.setNome(br.readLine().substring(5));
                        provvisoria.setLibri(br.readLine().replace("libri", ""));
                        listaRicerca.add(indice, provvisoria);
                        indice++;
                    }else {
                        for(int j=0; j<2; j++) br.readLine();
                    }
                }
            }
        }
        br.close();
    }

    /**
     * Metodo che si occupa di effettuare la ricerca della/e libreria sulla base del parametro nickname
     * all'interno del file "Libreria.dati". Viene unicamente richiamato in fase della costruzione della
     * finestra con il solo scopo di mostrare all'utente i risultati trovati all'interno della JList risultati.
     * @return ritorna un valore booleano che indica se esiste già il nome che si vuole dare alla libreria
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * del tipo IOException che può essere sollevata dal metodo.
     * @throws EOFException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * del tipo EOFException che può essere sollevata dal metodo.
     */

    private boolean esisteLb(String nome) throws IOException, EOFException {
        String temporanea;
        Libreria provvisorio;
        boolean trovato1=false;
        boolean trovato2=false;
        boolean esiste=false;

        File m = new File("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\Libreria.txt"); //da completare
        if (!m.exists())
            m.createNewFile();
        
        FileReader f = new FileReader(m);
        BufferedReader br = new BufferedReader(f);
        while ((temporanea =  br.readLine()) != null){
            if(temporanea.length()>8){
                if(temporanea.substring(0, 8).contentEquals("username")) {
                    if(temporanea.substring(9).toLowerCase().trim().equals(username.toLowerCase().trim())) {
                        trovato1=true;
                        if((temporanea = br.readLine()) != null) {
                            if(temporanea.substring(0, 4).contentEquals("nome")) {
                                if(temporanea.substring(5).trim().equals(nome.trim())) {
                                    trovato2 = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        br.close();
        if(trovato1 == true && trovato2 == true) {
            esiste = true;
        }
        return esiste;
    }
    /**
     * Metodo che si occupa dei effettuare la cancellazione di una libreria sulla base dei parametri passati nel metodo
     * actionListenerCancella all'interno del file "Libreria.dati". Viene unicamente richiamato in fase di cancellazione della libreria
     * nel metodo actionListenerCancella.
     * @param username Contiene il nickname dell'utente a cui appartiene la libreria.
     * @param name Contiene un nome della libreria da cancellare
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * del tipo IOException che può essere sollevata dal metodo.
     */

    private void cancellaLibreria(String username, String name) throws IOException {

        File a = new File ("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\Libreria.txt"); //da completare
        if (!a.exists());
            a.createNewFile();
        
        String letto = "";
        String nuovo = "";
        String vecchioContenuto = "";
        FileReader f = new FileReader(a);
        BufferedReader br = new BufferedReader(f);

        while ((letto = br.readLine()) != null) {
            vecchioContenuto = letto+"\n";
            if(letto.length()>8){
                if(letto.substring(0, 8).contentEquals("username")) {
                    if(letto.substring(9).trim().equals(username.trim())) {
                        if((letto = br.readLine()) != null) {
                            vecchioContenuto = vecchioContenuto + letto + "\n";
                            if(letto.substring(0, 4).contentEquals("nome")) {
                                if(letto.substring(5).trim().equals(name.trim())) {
                                    if((letto = br.readLine()) != null) {

                                    }
                                }else {
                                    if((letto = br.readLine()) != null) {
                                        nuovo = nuovo + vecchioContenuto + letto + "\n";
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
        bw.write(nuovo);
        bw.close();

    }

}
