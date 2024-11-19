import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;



public class RisultatiRicerca extends javax.swing.JFrame {
    private String userName;
    private JList<Libro> risultati;
    private String filtro1;
    private String filtro2;
    private String nomeLibreria;
    private Libro libroSelezionato;
    private boolean selezionaUnAltro;
    private boolean aggiungi;
    private Libreria lb;

    private ArrayList<Libro> listaRicerca = new ArrayList<Libro>();
    String risultatoTitoli;

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

    private void actionListenerIndietro(ActionEvent e) {
        setVisible(false);
        (new CercaLibro(this.userName, nomeLibreria, risultatoTitoli, true, aggiungi)).setVisible(true);
        dispose();
    }

    private void actionListenerGiudica(ActionEvent e) {
        if (this.risultati.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Selezionare un libro", "Errore Selezione", 2);

        } else {
            setVisible(false);
            (new GiudicaLibro(this.userName, this, this.risultati.getSelectedValue(), this.lb)).setVisible(true);
            dispose();
        }
    }

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
