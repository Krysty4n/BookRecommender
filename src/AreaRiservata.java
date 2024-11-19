//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Questa classe permette di scegliere se:
 * 1) effettuare il LOgout dalla propria area riservta;
 * 2) consultare i libri presenti nel repository con funzionalità complete;
 * 3) visualizzare le proprie librerie;
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */

public class AreaRiservata extends javax.swing.JFrame {


        /**
         * Conterrà lo userName utilizzato sia dalla finestra corrente che come parametro per richiamare altri costruttori
         * Dichiarato private così da essere visto solo dalla classe attuale
         */



        private final String userName;

    /**
     * Metodo costruttore della classe Area Riservata
     * @param userName In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo nickname della classe.
     */

    public AreaRiservata(String userName){
        super("AREA RISERVATA");
        setSize(500,250);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        /**
         * assegno la variabile passata al costruttore alla varibile locale username.
         */
        this.userName = userName;
        //JLabel
        JLabel accessoLib = new JLabel("Benvenuto  ");
        accessoLib.setFont(new Font("Impact", 0, 35));
        accessoLib.setForeground(new Color(24, 24, 24));
        JLabel nameLib = new JLabel(this.userName);
        nameLib.setFont(new Font("Impact", 0,35));
        nameLib.setForeground(new Color(24, 24, 24));

        //JBotton
        JButton logout = new JButton("Lougout");
        JButton cerca = new JButton("Cerca");
        JButton libereria = new JButton("La mia libreria");
        logout.addActionListener(this::actionListenerLougout);
        cerca.addActionListener(this::actionListenerCerca);
        libereria.addActionListener(this::actionListenerLibreria);
        JButton visulizzaLibri = new JButton("Elenco libri");

        //JPANEL

        JPanel pTitolo = new JPanel(new FlowLayout(1,0,70));
        JPanel pBottoni = new JPanel(new FlowLayout(1));

        pTitolo.add(accessoLib);
        pTitolo.add(nameLib);
        pBottoni.add(logout);
        pBottoni.add(cerca);
        pBottoni.add(libereria);
        Container principale = getContentPane();
        principale.setLayout(new BorderLayout());
        principale.add(pTitolo, BorderLayout.CENTER);
        principale.add(pBottoni, BorderLayout.SOUTH);
        setVisible(true);



    }
    /**
     * Al clic del bottone logout verrà creata una nuova finestra Principale
     * e verrà chiusa la finestra Area Riservata in esecuzione.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazioni sul clic del bottone logout
     * @see Principale
     */
    private void actionListenerLougout(ActionEvent e){
        setVisible(false);
        (new SchermataAvvio()).setVisible(true);
        dispose();
    }
    /**
     * Al clic del bottone cerca verrà creata una nuova finestra CercaLibro
     * e verrà chiusa la finestra Area Riservata in esecuzione.
     * Alla creazione di CercaLibro verranno passati come parametri:
     * 1) una stringa che corrisponde allo username
     * 2) una stringa che corrisponde al nome della libreria
     * 3) una striga che corrisponde ai risultati ricerca
     * 4) un boolean che servirà ad attivare l'opzione del tasto Indietro nella classe CercaLibro
     * 5) un boolean che servirà ad abilitare un'opzione differente nella classe RisultatiRicerca
     * @param e Oggetto di tipo ActiovEvent contenente tutte le informazioni sul clic del bottone Cerca
     * @see CercaLibro
     */
    private void actionListenerCerca(ActionEvent e){
        setVisible(false);
        (new CercaLibro(userName, "", "",true,false)).setVisible(true);
        dispose();
    }
    /**
     * Al clic del bottone Libreria verrà chiamato il costruttore della classe ElencoLibreria
     * e verrà chiusa la finestra Area Riservata in esecuzione.
     * Verà passato il parametro nickname che sarà utilizzato nella classe ElencoLibri.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazioni sul clic del bottone libreria
     * @see ElencoLibreria
     */
    private void actionListenerLibreria(ActionEvent e){
        setVisible(false);
        (new ElencoLibreria(userName)).setVisible(true);
        dispose();
    }

}
