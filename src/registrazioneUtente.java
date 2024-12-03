//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.*;
/**
 * Questa classe permette di scegliere se:
 * 1) effettuare la registrazione di un nuovo utente;
 * 2) tornare indietro alla pagina iniziale;
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */hiafofoh


public class registrazioneUtente extends javax.swing.JFrame {
    /**
     * Conterrà il nome digitato nell'omonimo campo.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private JTextField nome;
    /**
     * Conterrà il cognome digitato nell'omonimo campo.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    /* nome digiattato nell'omonimo campo*/
    private JTextField cognome;
    /**
     * Conterrà lo username digitato nell'omonimo campo.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private JTextField userName;
    /**
     * Conterrà l'indirizzo mail digitato nell'omonimo campo.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private JTextField email;
    /**
     * Conterrà la password digitata nell'omonimo campo.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private JTextField password;
    /**
     * Conterrà il codice fiscale digitato nell'omonimo campo.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private JTextField codFiscale;
    /**
     * Conterrà gli eventuali errori di compilazione, presenti nel form di registrazione.
     * Dichiarato private così da essere visto solo dalla classe attuale
     */
    private String errori = "";
    /**
     * Metodo costruttore della finestra RegistrazioneUtente.
     */

    //metodo del costruttore che si occupa della finestra della registrazione utente
    public registrazioneUtente(){
        super("Registrazione");
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Jlabel
        JLabel registrazione = new JLabel("Registrazione");
        JLabel nomej = new JLabel("Nome: ");
        JLabel cognomej = new JLabel("Cognome: ");
        JLabel userNamej = new JLabel("UserName: ");
        JLabel emailj = new JLabel("Email: ");
        JLabel passwordj = new JLabel("Password: ");
        JLabel codFiscalej = new JLabel("CodiceFiscale: ");
        final JLabel avvisoFiscale = new JLabel();
        registrazione.setFont(new Font("Impact", 0, 25));
        registrazione.setForeground(new Color(24, 24, 24));
        avvisoFiscale.setForeground(new Color(24, 24, 24));
        //JtextFile
        this.nome = new JTextField(16);
        this.cognome = new JTextField(16);
        this.userName = new JTextField(16);
        this.email = new JTextField(16);
        this.password = new JPasswordField(16);
        this.codFiscale = new JTextField(16);

        //grafica
        nome.setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, new Color(24, 24, 24)));
        cognome.setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, new Color(24, 24, 24)));
        userName.setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, new Color(24, 24, 24)));
        email.setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, new Color(24, 24, 24)));
        password.setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, new Color(24, 24, 24)));
        codFiscale.setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, new Color(24, 24, 24)));
        //controllo valore inserito per codiceFiscale
        this.codFiscale.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent k){
                String value = registrazioneUtente.this.codFiscale.getText();
                if (value.length() < 16){
                    registrazioneUtente.this.codFiscale.setEditable(true);
                } else if (k.getKeyChar() == '\b') {
                    registrazioneUtente.this.codFiscale.setEditable(true);

                }else {
                    registrazioneUtente.this.codFiscale.setEditable(false);
                    avvisoFiscale.setText("Liminte di 16 caratteri");


                }

            }

        });

        //JBotton
        JButton indietro = new JButton("Indietro");
        JButton conferma = new JButton("Conferma");
        indietro.addActionListener(event -> actionListenerIndietro(event));
        conferma.addActionListener(event -> {
            try{
                actionListenerConferma(event);

            }catch (IOException e) {
                e.printStackTrace();
            }
        });
        //Panel
        JPanel pnTitolo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel pnBottoni = new JPanel(new GridLayout());
        JPanel pnCentro = new JPanel(new GridLayout(13, 1));
        JPanel pnSinistra = new JPanel(new GridLayout(13, 1));
        JPanel[] pnlabel = new JPanel[12];
        JPanel[] pnField = new JPanel[12];

        for (int i = 0; i < 12; i++){
            pnlabel[i] = new JPanel(new FlowLayout(0));
            pnField[i] = new JPanel(new FlowLayout(0));
        }
        pnTitolo.add(registrazione);
        pnlabel[0].add(nomej);
        pnlabel[1].add(cognomej);
        pnlabel[2].add(userNamej);
        pnlabel[3].add(emailj);
        pnlabel[4].add(passwordj);
        pnlabel[5].add(codFiscalej);
        pnField[0].add(this.nome);
        pnField[1].add(this.cognome);
        pnField[2].add(this.userName);
        pnField[3].add(this.email);
        pnField[4].add(this.password);
        pnField[5].add(this.codFiscale);
        pnBottoni.add(indietro);
        pnBottoni.add(conferma);

        for(int i = 0; i < 12; i++){
            pnSinistra.add(pnlabel[i]);
            pnCentro.add((pnField[i]));
        }
        Container principale = getContentPane();
        principale.setLayout(new BorderLayout());
        principale.add(pnTitolo, BorderLayout.NORTH);
        principale.add(pnSinistra, BorderLayout.WEST);
        principale.add(pnCentro, BorderLayout.CENTER);
        principale.add(pnBottoni, BorderLayout.SOUTH);
        
        setVisible(true);


    }

    /**
     * Al clic del bottone indietro verrà creata una nuova finestra Principale
     * e verrà chiusa la finestra RegistraUtente in esecuzione.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazioni sul clic del bottone indietro
     * @see SchermataAvvio
     */
    private void actionListenerIndietro(ActionEvent e){
        setVisible(false);
       (new SchermataAvvio()).setVisible(true);
        dispose();
    }
    /**
     * Al clic del bottone conferma verrà effettuato un check sul contenuto di tutti i campi compilati nella form.
     * Nel caso in cui non vi fossero presenti degli errori, verrà registrato il cliente nel file "UtentiRegistrati.dati" tramite
     * il richiamo del metodo scriviNuovoUtente.
     * Successivamente verrà creata una nuova finestra Principale
     * e verrà chiusa la finestra RegistraUtente in esecuzione.
     * Nel caso opposto, verrà creata una finestra contenente gli errore riscontrati nell'inserimento dei campi.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazioni sul clic del bottone conferma
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo ScriviNuovoUtente
     * richiamato nel corpo di questo metodo.
     * @see SchermataAvvio
     */
    private void actionListenerConferma(ActionEvent e) throws IOException{
        if (this.nome.getText().equals(""))
            this.errori = String.valueOf(this.errori) + "Il campo Nome è vuoto \n";
        if (this.cognome.getText().equals(""))
            this.errori = String.valueOf(this.errori) + "Il campo Cognome è vuoto \n";
        if (this.userName.getText().equals(""))
            this.errori = String.valueOf(this.errori) + "Il campo UserName è vuoto \n";
        if (this.email.getText().equals("")){
            this.errori = String.valueOf(this.errori) + "Il campo email è vuoto \n";

        }else{
            
            Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher matcher = pattern.matcher(this.email.getText());
            boolean matchFound = matcher.matches();
            System.out.println(matchFound);
            StringTokenizer stringTokenizer = new StringTokenizer(this.email.getText(), ".");
            String lastToken = stringTokenizer.nextToken();
            while(stringTokenizer.hasMoreElements()){
               
                lastToken = stringTokenizer.nextToken();
               
                if (!matchFound || (lastToken.length() != 2 && lastToken.length() != 3))
                    this.errori = String.valueOf(this.errori) + "Email non valida\n";
            }   
            }
        
            
        if (this.password.getText().equals(""))
            this.errori = String.valueOf(this.errori) + "Il campo Password è vuoto \n";
        if (this.codFiscale.getText().equals(""))
            this.errori = String.valueOf(this.errori) + "Il campo CodFiscale è vuoto \n";
        if (!this.errori.contentEquals("")){
            JOptionPane.showMessageDialog(this, this.errori, "Attenzione ", 2);
            this.errori = "";
        }else{
            Registra();
            setVisible(false);
            //(new SchermataAvvio().setVisibale(true));
            dispose();
        }
    }
    /**
     * Effettua il controllo del nickname richiamando il metodo controlloNickname a cui
     * verrà passato come parametro una stringa che corrisponde allo username.
     * Se quest'ultimo ritorna come valore:
     * 1) true, Tutti i campi compilati nel form vengono scritti all'interno del file "UtentiRegistrati.dati".
     * 2) false, verrà visualizzato un messaggio d'errore.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     */
    private void Registra() throws IOException{
        try{
            boolean login = controlloUserName(this.userName.getText());
            if(login){
                String separatore = ":";
                String aCapo = "\n";
                File f = new File("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\UtentiRegistrati.txt");
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f,true));
                        if(!f.exists())
                            f.createNewFile();
                            bufferedWriter.append("nome" + separatore);
                                String tempMaiuscolo = String.valueOf(this.nome.getText().substring(0,1).toUpperCase())+ this.nome.getText().substring(1,this.nome.getText().length()).toLowerCase();
                                bufferedWriter.append(String.valueOf(tempMaiuscolo)+aCapo);
                            bufferedWriter.append("cognome" + separatore);
                                 tempMaiuscolo = String.valueOf(this.cognome.getText().substring(0,1).toUpperCase())+ this.cognome.getText().substring(1,this.cognome.getText().length()).toLowerCase();
                                 bufferedWriter.append(String.valueOf(tempMaiuscolo) + aCapo);
                            bufferedWriter.append("userName" + separatore);
                                 tempMaiuscolo = String.valueOf(this.userName.getText().substring(0,1).toUpperCase())+ this.userName.getText().substring(1,this.userName.getText().length()).toLowerCase();
                                     bufferedWriter.append(String.valueOf(tempMaiuscolo)+aCapo);
                            bufferedWriter.append("email" + separatore);
                                tempMaiuscolo = String.valueOf(this.email.getText().substring(0,1).toUpperCase())+ this.email.getText().substring(1,this.email.getText().length()).toLowerCase();
                                bufferedWriter.append(String.valueOf(tempMaiuscolo)+aCapo);
                                    bufferedWriter.append("password" + separatore);
                                    tempMaiuscolo = String.valueOf(this.password.getText().substring(0,1).toUpperCase())+ this.password.getText().substring(1,this.password.getText().length()).toLowerCase();
                                     bufferedWriter.append(String.valueOf(tempMaiuscolo)+aCapo);
                            bufferedWriter.append("codFiscale" + separatore);
                                 tempMaiuscolo = String.valueOf(this.codFiscale.getText().substring(0,1).toUpperCase())+ this.codFiscale.getText().substring(1,this.codFiscale.getText().length()).toLowerCase();
                                    bufferedWriter.append(String.valueOf(tempMaiuscolo)+aCapo);
                                    bufferedWriter.close();
                         JOptionPane.showMessageDialog(this,"Registrazione effettuata con successo", "Registrazione", 1);











            }else{
                JOptionPane.showMessageDialog(this,"UserName già esistente");
                setVisible(false);
                setVisible(true);
            }


        }catch (EOFException eof){
            eof.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
    /**
     * Il metodo verifica se lo username che si vuole registrare è presente nel file "UtentiRegistrati.dati".
     * 1) Se è presente il metodo restituisce false.
     * 2) Se non è presente il metodo restituisce true
     * @param username Oggetto di tipo String contenente lo username da controllare.
     * @return ritorna una variabile di tipo booleano per il controllo della presenza dello username inserito dall'utente.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     * @throws EOFException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo EOFException che può essere sollevata dal metodo.
     */

    boolean controlloUserName(String username) throws IOException, EOFException{
        boolean esiste = true;

        String tmp = "";
        File a = new File("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\UtentiRegistrati.txt");
        if (!a.exists())
            a.createNewFile();

        FileReader f = new FileReader(a);
        BufferedReader br = new BufferedReader(f);
        while((tmp = br.readLine())!= null){
            if (tmp.substring(0,8).equals("userName") && tmp.substring(9).equals(username)){
                esiste = false;
            }

        }
        br.close();
        return esiste;

    }







}
