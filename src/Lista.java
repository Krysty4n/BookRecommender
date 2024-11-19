import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Lista extends JComponent {
    File listaLibri = new File("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\BooksDatasetClean.csv");
    JFrame frame;
    JScrollPane scrollPane;
    JList<String> myList = new JList<>();
    SchermataAvvio schermataAvvio;


public Lista() {
    frame = new JFrame("LISTA");
    frame.setSize(500, 250);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setLayout(new BorderLayout());
    leggiFile();

    scrollPane = new JScrollPane(myList);
    scrollPane.setHorizontalScrollBar(null);

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
