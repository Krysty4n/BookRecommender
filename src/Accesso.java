import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.HashMap;
import java.util.*;


public class Accesso extends javax.swing.JFrame {
    private JTextField userName;
    private final JPasswordField password;
    
    public Accesso() { 
        super("ACCESSO");
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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


            this.userName = new JTextField(10);
            this.password = new JPasswordField(10);
            userName.setBorder(BorderFactory.createMatteBorder(0,0,2,0, new Color(24, 24, 24)));
            password.setBorder(BorderFactory.createMatteBorder(0,0,2,0, new Color(24, 24, 24)));


            JPanel pannelloTitolo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
            JPanel pannelloUserName = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel pannelloPassword = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel pannelloBottoni = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel pannelloCentro = new JPanel();

            pannelloTitolo.add(accessoL);
            pannelloUserName.add(usernameL);
            pannelloUserName.add(this.userName);
            pannelloPassword.add(passwordL);
            pannelloPassword.add(this.password);
            pannelloBottoni.add(indietro);
            pannelloBottoni.add(accedi);
            pannelloCentro.add(pannelloUserName);
            pannelloCentro.add(pannelloPassword);



            Container principale = getContentPane();
            principale.setLayout(new BorderLayout());
            principale.add(pannelloTitolo, BorderLayout.NORTH);
            principale.add(pannelloCentro, BorderLayout.CENTER);
            principale.add(pannelloBottoni, BorderLayout.SOUTH);
            setVisible(true);


        }



        private void actionListenerIndietro(ActionEvent e) {
            setVisible(false);
            (new SchermataAvvio()).setVisible(true);
            dispose();

        }



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
        


            private boolean accessoUtente(String userName, String password) throws IOException, EOFException {
                boolean accedi = false;

                String tmp = "";
                File a = new File("/Users/christianperri/Desktop/Book- Recommender/data/UtentiRegistrati.txt");

            if(!a.exists())
                a.createNewFile();
                FileReader f = new FileReader(a);
                BufferedReader br = new BufferedReader(f);

                // per lettura del file da usare per capire errore del file
                while(br.ready()){
                    System.out.println((char)br.read());
                }


              while((tmp = br.readLine()) != null){
                    //dividi riga in username e password
                    String[] credenziali = tmp.split(":");
                    if(credenziali.length == 2){
                        String fileUserName = credenziali[0].trim();
                        String filePassword = credenziali[1].trim();
                        System.out.println("Username file: " + fileUserName + ", Password file: " + filePassword);
                        System.out.println("Username input: " + userName + ", Password input: " + password);
                  if(fileUserName.equals(userName) && filePassword.equals(password)){
                        accedi = true;
                        break;
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
