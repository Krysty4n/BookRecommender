import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class ElencoLibreria extends javax.swing.JFrame {

    private String username;

    private boolean exist = false;

    private JList<Libreria> risultati;

    private Libreria libreriaSelezionata;

    private ArrayList<Libreria> listaRicerca = new ArrayList<Libreria>();

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

            JLabel titoloL = new JLabel("Librerie trovate: 0");
            titoloL.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
            titoloL.setForeground(Color.decode("#6699FF"));

            JButton indietro = new JButton("Indietro");
            JButton crea = new JButton("Crea");
            indietro.addActionListener(this::actionListenerIndietro);
            crea.addActionListener(this::actionListenerCrea);

            JList<String> vuota = new JList<>();
            vuota.setPreferredSize(new Dimension(260, 100));
            vuota.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane js = new JScrollPane(vuota);

            DefaultListModel<String> lm = new DefaultListModel<String>();
            lm.addElement("Non sono presenti librerie");
            vuota.setModel(lm);

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

            JLabel titoloL = new JLabel("Librerie trovate: " + listaRicerca.size());
            titoloL.setFont(new Font("Impact", Font.PLAIN, 20));
            titoloL.setForeground(new Color(24, 24, 24));

            JButton visualizza = new JButton("Visualizza");
            JButton indietro = new JButton("Indietro");
            JButton crea = new JButton("Crea");
            JButton cancella = new JButton("Cancella");
            visualizza.addActionListener(this::actionListenerVisualizza);
            crea.addActionListener(this::actionListenerCrea);
            cancella.addActionListener(this::actionListenerCancella);

            risultati = new JList<>();
            risultati.setPreferredSize(new Dimension(260, listaRicerca.size()*18));
            risultati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane js = new JScrollPane(risultati);

            DefaultListModel<Libreria> lm = new DefaultListModel<Libreria>();
            for (Libreria l : listaRicerca)
                lm.addElement(l);
            risultati.setModel(lm);

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

    private void actionListenerIndietro(ActionEvent e) {
        this.setVisible(false);
        new AreaRiservata(username).setVisible(true);
        this.dispose();
    }

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
