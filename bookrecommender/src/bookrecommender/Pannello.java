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

        JScrollPane scrollPane = new JScrollPane(table);

        searchPanel  searchPanel1 = new searchPanel(tabella);
        searchPanel1.setPreferredSize(new Dimension(300,300));

        pannello.add(toolBar,BorderLayout.NORTH);
        pannello.add(searchPanel1, BorderLayout.EAST);
        pannello.add(scrollPane, BorderLayout.CENTER);

        pannello.setVisible(true);



    }

    String getCSVfilePath(){
        FilePathOSBased filePathOSBased = new FilePathOSBased();
        return filePathOSBase.getFilePath() + "Libri.dati.csv";

    }

}
