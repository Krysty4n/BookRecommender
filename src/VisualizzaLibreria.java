import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class VisualizzaLibreria extends javax.swing.JFrame {
    private String userName;
    private Libreria l;
    private String nome;
    private JList<Libro> risultati;
    private Libro LibroSelezionato;
    private ArrayList<Libro> listaRicerca = new ArrayList<Libro>();

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


    private void actionListenerIndietro(ActionEvent e) {
        this.setVisible(false);
        new ElencoLibreria(userName).setVisible(true);
        this.dispose();
    }

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


    private void actionListenerAggiungiLibro(ActionEvent e) {
        this.setVisible(false);
        (new CercaLibro(userName, this.l.getNome(), "", true, true)).setVisible(true);
        dispose();
    }


    private void actionListenerGiudica(ActionEvent e) {
        if (this.risultati.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Seleziona un libro", "Errore selezione", 0);
        } else {
            setVisible(false);
            (new GiudicaLibro(userName, this, this.risultati.getSelectedValue(), this.l)).setVisible(true);
            dispose();
        }
    }

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
