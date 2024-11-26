import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class TabelleCSV {

        private DefaultTableModel tableModel;
        private JTable table;
        private TableRowSorter<DefaultTableModel> sorter;
        private String autore;
        private String titolo;
        private String anno;


        public TabelleCSV() {
            tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            table = new JTable(tableModel);

            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem openItem = new JMenuItem("Informazioni");

            openItem.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();  // Get the selected row when the menu item is clicked
                getInfo(table, selectedRow);
                visualizzaLibro();
            });

            popupMenu.add(openItem);
            table.setComponentPopupMenu(popupMenu);

            sorter = new TableRowSorter<>(tableModel);
            table.setRowSorter(sorter);
        }

        public void loadCSV(String filePath) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                List<String[]> rows = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    String[] cells = parseCSVLine(line);
                    rows.add(cells);
                }

                if (!rows.isEmpty()) {
                    String[] columnNames = rows.get(0);
                    tableModel.setColumnIdentifiers(columnNames);

                    for (int i = 1; i < rows.size(); i++) {
                        tableModel.addRow(rows.get(i));
                    }
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private String[] parseCSVLine(String line) {
            StringBuilder cell = new StringBuilder();
            List<String> cells = new ArrayList<>();

            boolean inQuotes = false;

            for (char ch : line.toCharArray()) {
                if (ch == '"') {
                    inQuotes = !inQuotes;
                } else if (ch == ',' && !inQuotes) {
                    cells.add(cell.toString().trim());
                    cell.setLength(0);
                } else {
                    cell.append(ch);
                }
            }

            cells.add(cell.toString().trim());
            return cells.toArray(new String[0]);
        }

        public void cercaLibro(String titleFilter, String authorFilter, String yearFilter) {
            List<RowFilter<Object, Object>> filters = new ArrayList<>();

            if (titleFilter != null && !titleFilter.trim().isEmpty()) {
                filters.add(RowFilter.regexFilter("(?i)" + titleFilter, 0));
            }
            if (authorFilter != null && !authorFilter.trim().isEmpty()) {
                filters.add(RowFilter.regexFilter("(?i)" + authorFilter, 1));
            }
            if (yearFilter != null && !yearFilter.trim().isEmpty()) {
                filters.add(RowFilter.regexFilter("^" + yearFilter + "$", 2));
            }

            RowFilter<Object, Object> compoundRowFilter = RowFilter.andFilter(filters);
            sorter.setRowFilter(compoundRowFilter);
        }

        public JTable getTable() {
            return table;
        }

        public void visualizzaLibro() {
            Libro libro = new Libro();
        }

        public void getInfo(JTable table, int row) {
            if (row != -1) {
                titolo = table.getValueAt(row, 0).toString();
                autore = table.getValueAt(row, 1).toString();
                anno = table.getValueAt(row, 2).toString();


            } else {
                // Handle the case when no row is selected (optional)
                JOptionPane.showMessageDialog(table, "Error");

            }
        }
    }

