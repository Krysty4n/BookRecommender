package bookrecommender;

import javax.swing.*;
import java.awt.*;

public class Pannello {
    JFrame pannello;

    public Pannello{
        pannello = new JFrame("Book Recommender");
        pannello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pannello.setSize(1440,720);
        pannello.setLocationRelativeTo(null);
        pannello.setResizable(true);
        pannello.setLayout(new BorderLayout());

        ToolBar toolBar = new ToolBar();

        TabellaCsv tabella = new TabellaCsv;
        tabellaCsv.loadCSV(getCSVfilePath);

        JTable table =tabella.getTable;

    }

}
