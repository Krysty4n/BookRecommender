import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class CercaLibro extends javax.swing.JFrame {

    private String username;
    private String nomeGenere;
    private String risultatiTitoli;
    private boolean enabled;

    public CercaLibro(String username, String nomeGenere, String risultatiT, boolean abilita, boolean add) {
        super("Selezione Ricerca");
        pack();
        setSize(400, 300);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);

        this.enabled = add;
        this.username = username;
        this.nomeGenere = nomeGenere;
        this.risultatiTitoli = risultatiT;

        JLabel descrizioneL = new JLabel("Seleziona una delle seguenti ricerche: ");
        descrizioneL.setFont(new Font("Impact", 0, 20));
        descrizioneL.setForeground(new Color(24, 24, 24));

        JButton autoreEanno = new JButton("Ricerca per autore e anno");
        JButton autore = new JButton("Ricerca per autore");
        JButton titolo = new JButton("Ricerca per titolo");
        
        JButton indietro = new JButton("Annulla");
        autoreEanno.addActionListener(this::actionListenerAutoreEanno);
        autore.addActionListener(this::actionListenerAutore);
        titolo.addActionListener(this::actionListenerTitolo);
        indietro.addActionListener(this::actionListenerIndietro);

        if (abilita == false) {
            indietro.setEnabled(false);
        }

        if (nomeGenere.equals("")) {
            indietro.setEnabled(true);
        }


        JPanel pTitolo = new JPanel(new FlowLayout(1, 0, 20));
        JPanel pLogout = new JPanel(new FlowLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());


        pTitolo.add(descrizioneL);
        pLogout.add(indietro);
        p1.add(titolo);
        p2.add(autore);
        p3.add(autoreEanno);


        Container principale = getContentPane();
        principale.setLayout(new GridLayout(5, 1));
        principale.add(pTitolo);
        principale.add(pLogout);
        principale.add(p1);
        principale.add(p2);
        principale.add(p3);

        setVisible(true);
        dispose();

    }

    private void actionListenerTitolo(ActionEvent e) {
        String inputTitolo = JOptionPane.showInputDialog(this, "Inserisci il  titolo del libro da cercare :", "Ricerca per titolo", 3);
        if (inputTitolo != null && "".equals(inputTitolo)) {
            JOptionPane.showMessageDialog(this, "E' necessario inserire un valore", "Errore ricerca", 2);

        } else if (inputTitolo != null) {
            this.setVisible(false);
            new RisultatiRicerca(inputTitolo.toLowerCase(),"",1,username,nomeGenere,risultatiTitoli,enabled);
            dispose();
        }
    }

    private void actionListenerAutore(ActionEvent e){
        String inputAutore = JOptionPane.showInputDialog(this, "Inserisci il nome dell'autore :", "Ricerca per autore", 3);
        if (inputAutore != null && "".equals(inputAutore)) {
            JOptionPane.showMessageDialog(this, "E' necessario inserire un valore", "Errore ricerca", 2);

        } else if (inputAutore != null) {
            this.setVisible(false);
            new RisultatiRicerca(inputAutore.toLowerCase(),"",1,username,nomeGenere,risultatiTitoli,enabled);
            dispose();
        }
    }


    private void actionListenerIndietro(ActionEvent e) {

        if (nomeGenere.equals("") && username.equals("")) {
            setVisible(false);
            (new SchermataAvvio()).setVisible(true);
            dispose();
        } else if (!nomeGenere.equals("") && !username.equals("")) {
            setVisible(false);
            (new ElencoLibreria(username)).setVisible(true);
            dispose();
        } else {
            setVisible(false);
            (new AreaRiservata(username)).setVisible(true);
            dispose();

        }

    }

    private void actionListenerAutoreEanno(ActionEvent e) {
        String inputAutore = JOptionPane.showInputDialog(this, "Inserisci l'autore da ricercare: ", "Ricerca per autore", 0);
        if (inputAutore != null && "".equals(inputAutore)) {
            JOptionPane.showMessageDialog(this, "E' necessario inserire un valore", "Errore Ricerca", 0);

        } else if (inputAutore != null) {
            String inputAnno = JOptionPane.showInputDialog(this, "Inserisci l'anno: ", "Ricerca per anno", 0);

            if (inputAnno != null) {
                boolean procedi = false;
                char[] carattere = inputAnno.toCharArray();

                for (int i = 0; i < inputAnno.length(); i++) {
                    if (carattere[i] >= '0' && carattere[i] <= '0' || (carattere[i] == '\b')) {
                        procedi = true;
                    } else {
                        procedi = false;
                        break;
                    }
                }

                if (procedi == false) {
                    JOptionPane.showMessageDialog(this, "Sono ammessi solo numeri", "Errore Ricerca", 0);
                } else {
                    int anno = Integer.parseInt(inputAnno);
                    System.out.print(anno);
                    if (1899 < anno) {
                        this.setVisible(false);
                        new RisultatiRicerca(inputAutore.toLowerCase(), inputAnno.toLowerCase(), 0, username, nomeGenere, risultatiTitoli, enabled);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "E' necessario inserire un valore maggiore di 1899", "Errore Ricerca", 0);
                    }
                }
            }
        }
    }
}
