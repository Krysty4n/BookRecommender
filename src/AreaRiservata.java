import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AreaRiservata extends javax.swing.JFrame {
    private final String userName;

    public AreaRiservata(String username){
        super("AREA RISERVATA");
        setSize(500,250);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);

        this.userName = username;
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

        //JLabel

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
    private void actionListenerLougout(ActionEvent e){
        setVisible(false);
        (new SchermataAvvio()).setVisible(true);
        dispose();
    }
    private void actionListenerCerca(ActionEvent e){
        setVisible(false);
        (new CercaLibro(userName, "", "",true,false)).setVisible(true);
        dispose();
    }
    private void actionListenerLibreria(ActionEvent e){
        setVisible(false);
        (new ElencoLibreria(userName)).setVisible(true);
        dispose();
    }

}
