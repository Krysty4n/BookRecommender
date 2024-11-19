//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 * Questa classe permette di scegliere se:
 * 1) registrare un nuovo utente ;
 * 2) accedere con un account già creato;
 * 3) cercare i libri nel repository, ma senza la possibilità di eseguire azioni;
 * 4) Visualizzare la lista intera di libri.
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */

public class SchermataAvvio extends JFrame{
    /**
     * Metodo principale della classe SchermataAvvio
     * Crea la finestra con tutte le componenti al suo interno
     */


    public SchermataAvvio(){
        //impostazioni finestra
        super("Book Reccomender");
        setSize(500, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//Jlabel
        JLabel titolo =new JLabel("Book Reccomander");
        titolo.setForeground(new Color(24, 24, 24));
        titolo.setFont(new Font("Impact", Font.PLAIN, 35));
        JLabel testo = new JLabel ("Immergiti nel mondo della lettura");
        testo.setForeground(new Color(24, 24, 24));
        testo.setFont(new Font("Lobster", Font.ITALIC, 17));
        ImageIcon immagine = new ImageIcon( "C:\\Users\\david\\Desktop\\Book- Recommender\\data\\ICONE\\libraryPhoto.jpeg");
        JLabel contenitoreImmagine = new JLabel(immagine);
//Jbotton
        JButton cercaLibri = new JButton("Cerca libri");
        JButton registrati = new JButton("Registrati");
        JButton accedi = new JButton("Accedi");
        JButton listaLibri = new JButton("Elenco libri");


       registrati.addActionListener(this ::actionListenerRegistrati);
       accedi.addActionListener(this::actionListenerAccedi);
       cercaLibri.addActionListener(this::actionListenerConsultaRepo);
       listaLibri.addActionListener(this::actionListenerLista);

        JPanel pannelloBottoni = new JPanel();
        Dimension dimesioni = getPreferredSize();
        dimesioni.width = 150;
        pannelloBottoni.setPreferredSize(dimesioni);
        pannelloBottoni.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();




//Primo bottone

        gbc.gridx = 1;
        gbc.gridy = 2;

        gbc.gridwidth = 7;

        gbc.ipady = 10;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        pannelloBottoni.add(registrati, gbc);

// secondo bottone
        gbc.gridx = 1;
        gbc.gridy = 3;

        gbc.gridwidth = 7;

        gbc.ipady = 10;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        pannelloBottoni.add(accedi, gbc);

// terzo bottone
        gbc.gridx = 1;
        gbc.gridy = 4;

        gbc.gridwidth = 3;

        gbc.ipady = 10;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        pannelloBottoni.add(listaLibri, gbc);
//quarto bottone
        gbc.gridx = 1;
        gbc.gridx = 4;
        gbc.gridwidth = 3;
        gbc.ipady = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pannelloBottoni.add(cercaLibri,gbc);




        JPanel pannelloCentro = new JPanel(new BorderLayout());

            JPanel pannelloTitolo = new JPanel();
            pannelloTitolo.add(titolo);
            JPanel pannelloTesto = new JPanel();
            pannelloTesto.add(testo);

            pannelloCentro.add(pannelloTitolo, BorderLayout.NORTH);
            pannelloCentro.add(contenitoreImmagine, BorderLayout.CENTER);
            pannelloCentro.add(pannelloTesto, BorderLayout.SOUTH);

            Container schermataAvvio = getContentPane();
            schermataAvvio.setLayout(new BorderLayout());
            schermataAvvio.add(pannelloBottoni,BorderLayout.CENTER);
            schermataAvvio.add(pannelloTitolo,BorderLayout.NORTH);
            schermataAvvio.add(pannelloTesto,BorderLayout.SOUTH);
            

            setVisible(true);
    }
    //creazione di ActionListener dei JButton

    /**
     * Al clic del bottone registrati verrà creata una nuova finestra RegistraUtente
     * e verrà chiusa la finestra Avvio in esecuzione.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone registrati
     * @see RegistraUtente
     */

    private void actionListenerRegistrati(ActionEvent e){
        setVisible(false);
        (new registrazioneUtente()).setVisible(true);
        dispose();
    }
    /**
     * Al clic del bottone accedi verrà creata una nuova fienstra Accesso
     * e verrà chiusa la finestra Avvio in esecuzione.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone indietro
     * @see Accesso
     */

    private void actionListenerAccedi(ActionEvent e){
        setVisible(false);
        (new Accesso()).setVisible(true);
        dispose();
    }
    /**
     * Al clic del bottone cerca verrà creata una nuova fienstra CercaLibro
     * e verrà chiusa la finestra Avvio in esecuzione.
     * Alla creazione di CercaLibro verranno passati come parametri:
     * 1) una stringa che corrisponde al Nickname
     * 2) una stringa che corrisponde al nome Libreria
     * 3) una stringa che corrisponde ai risultati ricerca
     * 4) un boolean che servirà ad attivare l'opzione del tasto indietro nella classe CercaLibro
     * 5) un boolean che servirà ad attivare un'opzione differente nella classe RisultatiRicerca
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone cerca
     * @see CercaLibro
     */


    private void actionListenerConsultaRepo(ActionEvent e){
        setVisible(false);
        (new CercaLibro("", "", "", true, false)).setVisible(true);
        dispose();
    }
    /**
     * Al clic del bottone listaLibri verrà creata una nuova finestra Lista
     * e verrà chiusa la finestra Avvio in esecuzione.
     * @param e Oggetto di tipo ActionEvent contentente tutte le informazioni sul clic del bottone listaLibri
     */


    private void actionListenerLista(ActionEvent e){
        setVisible(false);
        (new Lista()).setVisible(true);
        dispose();
    }
}
