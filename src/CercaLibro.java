//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Questa clsse permette di scegliere se:
 * 1) Cercare un libro per Titolo;
 * 2) Cercare un libro per Autore e Anno;
 * 3) tornare alla finestra precedente
 * Permette di cercare i libri sia per la visualizzazione che per l'aggiunta alla libreria.
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */


public class CercaLibro extends javax.swing.JFrame {
    /**
     * Contiene lo username del cliente che ha eseguito l'accesso.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */
    private String username;
    /**
     * Contiene il nome del genere a cui verrà aggiunto il libro
     * Contiene una stringa vuota se si cerca solamente il libro.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String nomeGenere;


    /**
     * Contiene i libri da aggiungere alla libreria.
     * potrebbe essere vuota.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String risultatiTitoli;
    /**
     * Contiene un valore boolean che permette l'aggiunta del libro alla libreria.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private boolean enabled;
    /**
     * Metodo costruttore della finestra CercaLibro
     * @param username In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo username della classe.
     * @param nomeGenere In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo nomeLibreria della classe.
     * @param risultatiT In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo resultTitoli della classe.
     * @param abilita In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per abilitare
     * il bottone indietro della classe.
     * @param add In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo enabled della classe.
     */

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
//JLABEL
        JLabel descrizioneL = new JLabel("Seleziona una delle seguenti ricerche: ");
        descrizioneL.setFont(new Font("Impact", 0, 20));
        descrizioneL.setForeground(new Color(24, 24, 24));
//JBUTTON
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

//JPANEL
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

    /**
     * Al clic del bottone Titolo verrà inizialmente creata una nuova finestra in cui inserire il titolo da cercare,
     * se non viene inserito verrà visualizzato un messaggio d'errore,
     * altrimenti verrà creata una nuova finestra RisultatiRicerca
     * e verrà chiusa la finestra CercaLibro in esecuzione.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone indietro
     * @see RisultatiRicerca
     */
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
/**
 * Al clic del bottone indietro  a seconda dei valori nomeLibreria e nickname:
 * 1) verrà creata una nuova finestra Principale
 * e verrà chiusa la finestra CercaLibro in esecuzione;
 * 2) verrà creata una nuova finestra ElencoLibreria
 * e verrà chiusa la finestra CercaLibro in esecuzione;
 * 3) verrà creata una nuova finestra AreaRiservata
 * e verrà chiusa la finestra CercaLibro in esecuzione;
 * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone indietro
 * @see Principale
 * @see ElencoLibreria
 * @see AreaRiservata
 */


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
    /**
     * Al clic del bottone AutoreEAnno verrà inizialmente creata una nuova finestra in cui inserire l'autore da cercare
     * e se non viene inserito verrà visualizzato un messaggio d'errore,
     * verrà poi creata una nuova finestra in cui inserirel'anno da cercare
     * e se non viene inserito verrà visualizzato un messaggio di errore.
     * Se i valori vengono inseriti correttamente  verrà creata una nuova finestra RisultatiRicerca
     * e verrà chiusa la finestra CercaLibro in esecuzione.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone indietro
     * @see RisultatiRicerca
     */

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
