//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
/**
 * Questa classe mostra l'elenco di libri disponibili nella repository.
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */

public class Lista extends JComponent {
    File listaLibri = new File("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\BooksDatasetClean.csv");
    JFrame frame;
    JScrollPane scrollPane;
    JList<String> myList = new JList<>();
    SchermataAvvio schermataAvvio;
    /**
     * Questo metodo crea una finestra nella quale si possono visionare i libri dell'elenco
     * Questo metodo richiama il metodo leggifile
     */


public Lista() {
    frame = new JFrame("LISTA");
    frame.setSize(500, 250);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setLayout(new BorderLayout());
    leggiFile();
//inizializzazione scrollPane, con inserimento della lista
    scrollPane = new JScrollPane(myList);
    scrollPane.setHorizontalScrollBar(null);
//aggiungo lo scrolPane al frame
    frame.add(scrollPane, BorderLayout.CENTER);

    JPanel pannello = new JPanel();

    pannello.setLayout(new BorderLayout());
    JLabel label = new JLabel("ELENCO LIBRI");
    label.setForeground(new Color(0, 0, 0));
    label.setFont(new Font("Impact", Font.PLAIN, 0));
    JButton indietro = new JButton("indietro");

    indietro.addActionListener(new ActionListener() {
        @Override

        public void actionPerformed(ActionEvent e) {
            schermataAvvio = new SchermataAvvio();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }


    });


    pannello.add(label, BorderLayout.CENTER);
    pannello.add(indietro, BorderLayout.EAST);
    frame.add(pannello, BorderLayout.NORTH);
    frame.setVisible(true);
}

    /**
     * Questo metodo prende in input il file DA COMPLETARE
     * Inserire in un array tutti i nome dei libri (Anno, Autore, Titolo), che poi verranno inseriti in una JList
     */
    public void leggiFile() {
        int count = 0;
        String rigaDaDividere;
        String[] rigaDivisa;
        String[] listaLibriCompleta = new String[515576];
        try {
            Scanner obj = new Scanner(listaLibri);
            while (obj.hasNextLine()) {
                rigaDaDividere = obj.nextLine();
                rigaDivisa = rigaDaDividere.split("<SEP>");

                String rigaCompleta = "";
                for (int i = 0; i < rigaDivisa.length; i++) {
                    if (i != 1) {
                        if (i == 0) {
                            rigaCompleta += rigaDivisa[i] + " ";
                        } else if (i == 2) {
                            rigaCompleta += rigaDivisa[i] + "    ";
                        } else
                            rigaCompleta += rigaDivisa[i];
                    }
                }
                listaLibriCompleta[count] = rigaCompleta;
                count++;
                if (count < 0) {
                    break;
                }
            }
            myList = new JList<>(listaLibriCompleta);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
