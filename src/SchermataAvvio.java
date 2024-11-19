import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SchermataAvvio extends JFrame{

    public SchermataAvvio(){
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

    private void actionListenerRegistrati(ActionEvent e){
        setVisible(false);
        (new registrazioneUtente()).setVisible(true);
        dispose();
    }

    private void actionListenerAccedi(ActionEvent e){
        setVisible(false);
        (new Accesso()).setVisible(true);
        dispose();
    }


    private void actionListenerConsultaRepo(ActionEvent e){
        setVisible(false);
        (new CercaLibro("", "", "", true, false)).setVisible(true);
        dispose();
    }

    private void actionListenerLista(ActionEvent e){
        setVisible(false);
        (new Lista()).setVisible(true);
        dispose();
    }
}
