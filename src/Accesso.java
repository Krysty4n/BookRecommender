//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.HashMap;
import java.util.*;
/**
 * Questa classe consente di scegliere se:
 * 1) effettuare il Login alla propria area riservata;
 * 2) tornare indietro alla pagina iniziale;
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */

public class Accesso extends javax.swing.JFrame {

    /**
     * Conterrà lo userName digitato nell'omonimo campo.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private JTextField userName;
    /**
     * Conterrà la password digitata nell'omonimo campo.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private final JPasswordField password;
    /**
     * Metodo costruttore della finestra Accesso
     */
    
    public Accesso() { 
        super("ACCESSO");
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//JLABEL
            JLabel usernameL = new JLabel("Username: ");
            JLabel passwordL = new JLabel("Password: ");
            JLabel accessoL = new JLabel("Effettua l'accesso");
            accessoL.setFont(new Font("Impact", Font.PLAIN, 35));
            accessoL.setForeground(new Color(24, 24, 24));

//JBUTTON
            JButton indietro = new JButton("Indietro");
            JButton accedi = new JButton("Accedi");
            indietro.addActionListener(this::actionListenerIndietro);
            accedi.addActionListener(this::actionListenerAccedi);

        //JTEXTFIELD
            this.userName = new JTextField(10);
            this.password = new JPasswordField(10);
            userName.setBorder(BorderFactory.createMatteBorder(0,0,2,0, new Color(24, 24, 24)));
            password.setBorder(BorderFactory.createMatteBorder(0,0,2,0, new Color(24, 24, 24)));

    //JPANEL
            JPanel pannelloTitolo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
            JPanel pannelloUserName = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel pannelloPassword = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel pannelloBottoni = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel pannelloCentro = new JPanel();
//aggiungo gli elementi (JButton, JTextField) ai pannelli creati
            pannelloTitolo.add(accessoL);
            pannelloUserName.add(usernameL);
            pannelloUserName.add(this.userName);
            pannelloPassword.add(passwordL);
            pannelloPassword.add(this.password);
            pannelloBottoni.add(indietro);
            pannelloBottoni.add(accedi);
            pannelloCentro.add(pannelloUserName);
            pannelloCentro.add(pannelloPassword);



        //aggiungo alla finestra tutti i pannelli
            Container principale = getContentPane();
            principale.setLayout(new BorderLayout());
            principale.add(pannelloTitolo, BorderLayout.NORTH);
            principale.add(pannelloCentro, BorderLayout.CENTER);
            principale.add(pannelloBottoni, BorderLayout.SOUTH);
            setVisible(true);


        }
    /**
     * Al clic del bottone Indietro verrà creata una nuova finestra Principale
     * e verrà chiusa la finestra Accesso in esecuzione.
     * @param e Oggetto  di tipo ActionEvent contenente tuute le informazioni sul clic del bottone Indietro
     * @see SchermataAvvio
     */



        private void actionListenerIndietro(ActionEvent e) {
            setVisible(false);
            (new SchermataAvvio()).setVisible(true);
            dispose();

        }

    /**
     * Al clic del bottone Accedi verrà effettuata la ricerca di userName e password
     * richiamando il metodo accessoUtente. Se quest'ultimoritorna come valore:
     * 1) true, verrà creata una nuova finestra AreaRiservata
     * passando la stringa userName come parametro al costruttore e verrà chiusa la finestra Accesso in esecuzione.
     * 2) false, verrà una finestra JOptionPane contenente un messaggio di errore.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazioni sul clic del bottone Accedi
     * @see RisultatiRicerca
     */



            private void actionListenerAccedi(ActionEvent e) { 
                try{
                    boolean login = accessoUtente(this.userName.getText(), String.valueOf(this.password.getPassword()));
                    if(login) {
                        JOptionPane.showMessageDialog(this, "Bentornato, " + this.userName.getText() + "!", "Acesso Corretto", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        (new AreaRiservata(this.userName.getText())).setVisible(true);
                        dispose();
                
                    } else {
                        JOptionPane.showMessageDialog(this, "Credenziali errate", "Credenziali errate", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (EOFException eof) {
                    eof.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
    /**
     * Vengono ricercati i due parametri, in ingresso al metodo (nickname e password), all'interno del file "UtentiRegistrati.dati" e, se vengono trovati, il metodo
     * restituisce "true" altrimenti restituisce "false".
     * @param userName contiene lo username che verrà ricercato all'interno del file "UtentiRegistrati.dati"
     * @param password contiene la password che verrà ricercata all'interno del file "UtentiRegistrati.dati"
     * @return Accedi: "true" se nickname e password corretti, "false" se non corretti.
     * @throws IOException testo
     * @throws EOFException testo
     */
        


            private boolean accessoUtente(String userName, String password) throws IOException, EOFException {
                boolean accedi = false;

                String tmp = "";
                File a = new File("/Users/christianperri/Desktop/Book- Recommender/data/UtentiRegistrati.txt");

            if(!a.exists())
                a.createNewFile();
                FileReader f = new FileReader(a);
                BufferedReader br = new BufferedReader(f);

              while((tmp = br.readLine()) != null){
                  if (tmp.length() > 9){

                      if (tmp.substring(0, 8).equals("userName") && tmp.substring(9).equals(userName)) {
                          br.readLine();
                          tmp = br.readLine();
                          if (tmp.trim().substring(9).equals(password)) {
                              accedi = true;
                          }
                      }

                      }
                }
            

               
               /*  while ((tmp = br.readLine()) != null) {
                    if (tmp.length() > 9)
                        if (tmp.substring(0, 8).equals("username") && tmp.substring(9).equals(userName)) {
                            tmp = br.readLine();
                            if (tmp.substring(9).equals(userName))
                                accedi = true;
                                
                        }
                } */
               
               br.close();
                return accedi;
                




            } 

        


    }
