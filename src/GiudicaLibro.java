//Perri Christian matricola: 754702 VA
//De Felice Lorenzo  matricola: 757074 VA
//Bilora Davide  matricola: 757011 VA
//Mariani Amati Federico matricola: 756811 VA

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * Questa classe consente di giudicare il libro selezionato nella finestra RisultatoRicerca o nella finestra VisualizzaLibreria.
 * Se il libro è già stato giudicato permette di sovrascriverne il giudizio
 * @author Perri Christian matricola: 754702
 * @author De Felice Lorenzo  matricola: 757074
 * @author Bilora Davide  matricola: 757011
 * @author Mariani Amati Federico matricola: 756811
 */

public class GiudicaLibro extends javax.swing.JFrame {
    /**
     * Contiene il riferimento della finestra "padre" RisultatiRicerca.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private RisultatiRicerca risultatiRicerca;
    /**
     * Contiene il riferimento della finestra "padre" VisualizzaLibreria.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private VisualizzaLibreria libriLibreria;
    /**
     * Conterrà un numero da 1 a 5, in base alla valutazione assegnata dal cliente per ogni criterio.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private int[] valutazione = new int[0];
    /**
     * Contiene il nome del libro selezionato nella finestra "padre" RisultatiRicerca.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private String titolo;
    /**
     * Contiene lo username dell'utente che ha eseguito l'accesso.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private String username;
    /**
     * Contiene il valore di ritorno associato al tipo di instanza di r.
     * Permette il ritorno alla finestra "padre" corretta.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private int ritorno;
    /**
     * Contiene l'id del libro da giudicare .
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private String id;
    /**
     * Conterrà l'oggetto libreria a cui appartiene il libro.
     * Se viene passato da RisultatiRicerca risulta null.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private Libreria lb;
    /**
     * Variabile boolean che permette di annulare o salvare il giudizio
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private boolean nomEssaggio;
    /**
     * Array di stringhe contenenti i nomi dei criteri.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private String[] criterio = {"Stile:", "Contenuto:", "Grandezza:", "Originalita:", "Edizione:"};
    /**
     * Array di stringhe contenenti le colonne presenti.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private String[] colonne = {"Criterio valutazione:", "Spiegazione:", "Punteggio:", "Note:(250 max caratteri)"};
    /**
     * Array di bottoni. Sono necessari per assegnare la valutazione.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private JButton[] stelle = new JButton[45];
    /**
     * Icona stella piena.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private ImageIcon stellaFull = new ImageIcon("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\ICONE\\starFull.png");

    /**
     * Icona stella vuota.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private ImageIcon stellaUnfilled = new ImageIcon("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\ICONE\\starUnfilled.png");
    /**
     * Area in cui l'utente dovrà inserire il commento sul libro.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */

    private JTextArea[] areaCommento = new JTextArea[9];
    /**
     * Permette di visualizzare tutti i commenti.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */
    private JScrollPane[] js = new JScrollPane[9];
    /**
     * Contiene "true" se è stata assegnata una valutazione o "false" in caso contrario.
     * Dichiaraato private così da essere visto solo dalla classe attuale
     */


    private boolean boolValutazione = false;
    /**
     * Metodo costruttore della classe GiudicaLibro
     * @param username In fase fi costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo username della classe.
     * @param r In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo RisultatiRicerca o VisualizzaLibreria della classe.
     * @param l In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo Libro.
     * @param lb In fase di costruzione dell'oggetto, questo parametro verrà utilizzato per valorizzare
     * l'attributo Libreria.
     */

    
        public GiudicaLibro(String username, Object r, Libro l, Libreria lb
        ) {
            super("Giudica: " + l.getTitolo());
            this.pack();
            this.setSize(1150, 620);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            

        //Attributi della classe
        this.lb = lb;
        this.nomEssaggio = false;
        this.titolo = l.getTitolo();
        this.id = l.getId();
        this.username = username;

        if (r instanceof RisultatiRicerca) {
            this.risultatiRicerca = (RisultatiRicerca) r;
            this.ritorno = 0;
        } else {
            this.libriLibreria = (VisualizzaLibreria) r;
            this.ritorno = 1;
        }
//JLABEL
        JLabel giudicaL = new JLabel("Giudica");
        JLabel[] commentoL = new JLabel[9];
        for (int y = 0; y < 9; y++) {
            commentoL[y] = new JLabel("Commento: ");
        }
        JLabel criterioL;
        JLabel titoloL = new JLabel(titolo);
        titoloL.setFont(new Font("Impact", Font.PLAIN, 25));
        titoloL.setForeground(new Color(24, 24, 24));
//JBUTTON
        JButton conferma = new JButton("Conferma");
        JButton annulla = new JButton("Annulla");
        conferma.addActionListener(this::actionListenerConferma);
        annulla.addActionListener(this::actionListenerAnnulla);

        for (int y = 0; y < 9; y++) {
            areaCommento[y] = new JTextArea(12, 20);
            areaCommento[y].setLineWrap(true);
            areaCommento[y].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            js[y] = new JScrollPane(areaCommento[y]);
        }
        //JPANEL

        JPanel pCommento = new JPanel(new FlowLayout(0));
        JPanel pText = new JPanel(new FlowLayout(0));
        JPanel pCentro = new JPanel(new GridLayout(9, 4));
        JPanel pCorpo = new JPanel();
        JPanel pSinistra = new JPanel();
        JPanel pDestra = new JPanel();
        JPanel pIntestazione = new JPanel();
        pCorpo.setLayout(new BoxLayout(pCorpo, BoxLayout.X_AXIS));
        pSinistra.setLayout(new BoxLayout(pSinistra, BoxLayout.X_AXIS));
        pDestra.setLayout(new BoxLayout(pDestra, BoxLayout.X_AXIS));
        pIntestazione.setLayout(new BoxLayout(pIntestazione, BoxLayout.X_AXIS));

        JPanel pTitolo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JPanel pStelle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel pBottoni = new JPanel(new FlowLayout(FlowLayout.CENTER));
//JLABEL
        JLabel descCritL = new JLabel("CRITERIO:");
        JLabel spiegCritL = new JLabel("DESCRIZIONE CRITERIO:");
        JLabel valutazioneL = new JLabel("VALUTAZIONE:");
        JLabel commL = new JLabel("COMMENTO:");

        JLabel stileL = new JLabel("()");
        JLabel contenutoL = new JLabel("()");
        JLabel grandezzaL = new JLabel("()");
        JLabel originalitaL = new JLabel("()");
        JLabel edizioneL = new JLabel("()");
//JPANEL
        JPanel[] pLabel = new JPanel[9];
        JPanel[] pFstelle = new JPanel[9];
        JPanel[] pDescrizione = new JPanel[9];
        JPanel pCriterio = new JPanel(new GridLayout(9, 1));
        JPanel pSpiegazioneCriterio = new JPanel(new GridLayout(9, 1));
        int k;
        for (k = 0; k < criterio.length; k++) {
            pLabel[k] = new JPanel(new FlowLayout(0));
            pLabel[k].setForeground(Color.red);
            pFstelle[k] = new JPanel(new GridLayout(1, 5));
            pDescrizione[k] = new JPanel(new FlowLayout(1, 0, 20));
        }

        pTitolo.add(titoloL);
        pIntestazione.add(descCritL);
        pIntestazione.add(spiegCritL);
        pIntestazione.add(valutazioneL);
        pIntestazione.add(commL);

        int i = 0;
        int j = 0;
        String name;
        for (int z = 0; z < criterio.length; z++) {
            criterioL = new JLabel(criterio[z]);
            pLabel[z].add(criterioL);
        }
        pDescrizione[j].add(commentoL[j]);

        while (i < stelle.length && j < criterio.length) {
            if (i == 5 || i == 10 || i == 15 || i == 20 || i == 25 || i == 30 || i == 35 || i == 40) {
                j++;
                pDescrizione[j].add(commentoL[j]);
            }
            stelle[i] = new JButton(stellaUnfilled);
            stelle[i].setBorderPainted(false);
            stelle[i].setName("stella:" + i);
            stelle[i].setContentAreaFilled(false);
            stelle[i].setFocusPainted(false);
            stelle[i].setPreferredSize(new Dimension(45, 27));
            stelle[i].addActionListener(this::listenerStelle);
            pFstelle[j].add(stelle[i]);
            i++;
        }

        for (k = 0; k < criterio.length; k++) {
            pCriterio.add(pLabel[k]);
            pCentro.add(pFstelle[k]);
            pCentro.add(pDescrizione[k]);
            pCentro.add(js[k]);
        }

        pBottoni.add(annulla);
        pBottoni.add(conferma);
        pSpiegazioneCriterio.add(stileL);
        pSpiegazioneCriterio.add(contenutoL);
        pSpiegazioneCriterio.add(grandezzaL);
        pSpiegazioneCriterio.add(originalitaL);
        pSpiegazioneCriterio.add(edizioneL);
        pSinistra.add(pSinistra);
        pDestra.add(pDestra);
        pCorpo.add(pSinistra);
        pCorpo.add(pDestra);
        pCorpo.add(pCentro);

        Container principale = this.getContentPane();
        principale.setLayout(new BorderLayout());
        principale.add(pTitolo, BorderLayout.NORTH);
        principale.add(pCorpo, BorderLayout.CENTER);
        principale.add(pBottoni, BorderLayout.SOUTH);

        setVisible(true);
    }
    /**
     * Al clic del bottone annulla viene settata la visibilità della schermata "padre"
     * RisultatiRicerca oppure VisualizzaLibreria e successivamente la schermata GiudicaLibro in esecuzione
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone indietro.
     * @see RisultatiRicerca
     * @see VisualizzaLibreria
     */

    private void actionListenerAnnulla(ActionEvent e) {
        if (ritorno == 0) {
            this.setVisible(false);
            risultatiRicerca.setVisible(true);
            this.dispose();
        } else {
            this.setVisible(false);
            libriLibreria.setVisible(true);
            this.dispose();
        }
    }
    /**
     * Al clic del bottone conferma viene valutata la presenza di errori,
     * se non ci sono, il giudizio scritto viene salvato sul file tramite il metodo inserisciLibro
     * e poi viene settata a "true" la visibilità della schermata "padre" RisultatiRicerca oppure VisualizzaLibreria
     * e succesivamente viene chiusa la schermata  GiudicaLibro in esecuzione;
     * Se invece ci sono errori viene visualizzata una finestra contenente il messaggio di errore.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone conferma
     * @see RisultatiRicerca
     * @see VisualizzaLibreria
     */


    private void actionListenerConferma(ActionEvent e) {
        String errori = "";
        if (!boolValutazione) {
            errori = errori + "E' necessario assegnare una valutazione\n";
        }
        for (int u = 0; u < 9; u++) {
            if (areaCommento[u].getText().length() > 256) {
                errori = errori + "Superato il limite massimo di caratteri (MAX 256)\n";
                break;
            }
        }
        System.out.println("Nomessaggio status: " + nomEssaggio);

        if (errori.equals("")) {
            try {
                inserisciCriteriLibro();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (errori.equals("") && nomEssaggio == false && lb.getNome().equals("")) {
            //avviso l'utente che il giudizio è stato inserito correttamente
            JOptionPane.showMessageDialog(this, "Il giudizio e' stato salvato correttamente", "Giudizio Salvato", JOptionPane.INFORMATION_MESSAGE);
            //torno alla schermata risultati ricerca
            risultatiRicerca.setVisible(true);
            nomEssaggio = false;
            this.dispose();
        } else if (errori.equals("") && nomEssaggio == true && lb.getNome().equals("")) {
            JOptionPane.showMessageDialog(this, "Operazione annullata correttamente", "Giudizio non Salvato", JOptionPane.INFORMATION_MESSAGE);
            //torno alla schermata risultati ricerca
            risultatiRicerca.setVisible(true);
            nomEssaggio = false;
            this.dispose();
        } else if (!lb.getNome().equals("")) {
            JOptionPane.showMessageDialog(this, "Il giudizio e' stato salvato correttamente", "Giudizio Salvato", JOptionPane.INFORMATION_MESSAGE);
            //torno alla schermata risultati ricerca
            new VisualizzaLibreria(this.lb, username).setVisible(true);
            nomEssaggio = false;
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "ATTENZIONE:\n" + errori, "Attenzione!", JOptionPane.WARNING_MESSAGE);

        }

    }
    /**
     * Al clic di uno dei 45 bottoni presenti nell'array di bottoni stelle,
     * in base al valore della valutazione, viene cambiata l'icona di tutti i bottoni,
     * in modo da rispettare il voto assegnato.
     * @param e Oggetto di tipo ActionEvent contenente tutte le informazionni sul clic del bottone presente
     * nell'array stelle
     */

    private void listenerStelle(ActionEvent e) {
        boolValutazione = true;
        JButton button = (JButton) e.getSource();
        String s = button.getName();
        String[] nStella = s.split(":");
        int stPremuta = Integer.parseInt(nStella[1]);
        if (stPremuta == 0 || stPremuta == 5 || stPremuta == 10 |
                stPremuta == 15 || stPremuta == 20 || stPremuta == 25 |
                stPremuta == 30 || stPremuta == 35 || stPremuta == 40) {
            stelle[stPremuta].setIcon(stellaFull);
            stelle[stPremuta + 1].setIcon(stellaUnfilled);
            stelle[stPremuta + 2].setIcon(stellaUnfilled);
            stelle[stPremuta + 3].setIcon(stellaUnfilled);
            stelle[stPremuta + 4].setIcon(stellaUnfilled);
            switch (stPremuta) {
                case 0:
                    valutazione[0] = 1;
                    break;
                case 5:
                    valutazione[1] = 1;
                    break;
                case 10:
                    valutazione[2] = 1;
                    break;
                case 15:
                    valutazione[3] = 1;
                    break;
                case 20:
                    valutazione[4] = 1;
                    break;
                case 25:
                    valutazione[5] = 1;
                    break;
                case 30:
                    valutazione[6] = 1;
                    break;
                case 35:
                    valutazione[7] = 1;
                    break;
                case 40:
                    valutazione[8] = 1;
                    break;
                default:
                    break;
            }
        } else if (stPremuta == 1 || stPremuta == 6 || stPremuta == 11 |
                stPremuta == 16 || stPremuta == 21 | stPremuta == 26 |
                stPremuta == 31 || stPremuta == 36 || stPremuta == 41) {
            stelle[stPremuta - 1].setIcon(stellaFull);
            stelle[stPremuta].setIcon(stellaFull);
            stelle[stPremuta + 1].setIcon(stellaUnfilled);
            stelle[stPremuta + 2].setIcon(stellaUnfilled);
            stelle[stPremuta + 3].setIcon(stellaUnfilled);
            switch (stPremuta) {
                case 1:
                    valutazione[0] = 2;
                    break;
                case 6:
                    valutazione[1] = 2;
                    break;
                case 11:
                    valutazione[2] = 2;
                    break;
                case 16:
                    valutazione[3] = 2;
                    break;
                case 21:
                    valutazione[4] = 2;
                    break;
                case 26:
                    valutazione[5] = 2;
                    break;
                case 31:
                    valutazione[6] = 2;
                    break;
                case 36:
                    valutazione[7] = 2;
                    break;
                case 41:
                    valutazione[8] = 2;
                    break;
                default:
                    break;
            }
        } else if (stPremuta == 2 || stPremuta == 7 || stPremuta == 12 |
                stPremuta == 17 || stPremuta == 22 | stPremuta == 27 |
                stPremuta == 32 || stPremuta == 37 || stPremuta == 42) {
            stelle[stPremuta - 2].setIcon(stellaFull);
            stelle[stPremuta - 1].setIcon(stellaFull);
            stelle[stPremuta].setIcon(stellaFull);
            stelle[stPremuta + 1].setIcon(stellaUnfilled);
            stelle[stPremuta + 2].setIcon(stellaUnfilled);
            switch (stPremuta) {
                case 2:
                    valutazione[0] = 3;
                    break;
                case 7:
                    valutazione[1] = 3;
                    break;
                case 12:
                    valutazione[2] = 3;
                    break;
                case 17:
                    valutazione[3] = 3;
                    break;
                case 22:
                    valutazione[4] = 3;
                    break;
                case 27:
                    valutazione[5] = 3;
                    break;
                case 32:
                    valutazione[6] = 3;
                    break;
                case 37:
                    valutazione[7] = 3;
                    break;
                case 42:
                    valutazione[8] = 3;
                    break;
                default:
                    break;
            }
        } else if (stPremuta == 3 || stPremuta == 8 || stPremuta == 13 |
                stPremuta == 18 || stPremuta == 23 | stPremuta == 28 |
                stPremuta == 33 || stPremuta == 38 || stPremuta == 43) {
            stelle[stPremuta - 3].setIcon(stellaFull);
            stelle[stPremuta - 2].setIcon(stellaFull);
            stelle[stPremuta - 1].setIcon(stellaFull);
            stelle[stPremuta].setIcon(stellaFull);
            stelle[stPremuta + 1].setIcon(stellaUnfilled);
            switch (stPremuta) {
                case 3:
                    valutazione[0] = 4;
                    break;
                case 8:
                    valutazione[1] = 4;
                case 13:
                    valutazione[2] = 4;
                    break;
                case 18:
                    valutazione[3] = 4;
                    break;
                case 23:
                    valutazione[4] = 4;
                    break;
                case 28:
                    valutazione[5] = 4;
                    break;
                case 33:
                    valutazione[6] = 4;
                    break;
                case 38:
                    valutazione[7] = 4;
                    break;
                case 43:
                    valutazione[8] = 4;
                    break;
                default:
                    break;
            }
        } else if (stPremuta == 4 || stPremuta == 9 || stPremuta == 14 |
                stPremuta == 19 | stPremuta == 24 | stPremuta == 29 ||
                stPremuta == 34 || stPremuta == 39 || stPremuta == 44) {
            stelle[stPremuta - 4].setIcon(stellaFull);
            stelle[stPremuta - 3].setIcon(stellaFull);
            stelle[stPremuta - 2].setIcon(stellaFull);
            stelle[stPremuta - 1].setIcon(stellaFull);
            stelle[stPremuta].setIcon(stellaFull);
            switch (stPremuta) {
                case 4:
                    valutazione[0] = 5;
                    break;
                case 9:
                    valutazione[1] = 5;
                    break;
                case 14:
                    valutazione[2] = 5;
                    break;
                case 19:
                    valutazione[3] = 5;
                    break;
                case 24:
                    valutazione[4] = 5;
                    break;
                case 29:
                    valutazione[5] = 5;
                    break;
                case 34:
                    valutazione[6] = 5;
                    break;
                case 39:
                    valutazione[7] = 5;
                    break;
                case 44:
                    valutazione[8] = 5;
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * Il metodo si occupa di effettuare la scrittura del giudizio all'interno del file "Giudizio.dati",
     * Se il giudizio è già presente viene visualizzata una nuova finestra con l'opzione di riscrittura del giudizio.
     * @throws IOException Dichiara che, in fase di richiamo di questo metodo, dovrà essere gestita un'eccezione
     * di tipo IOException che può essere sollevata dal metodo.
     */

    private void inserisciCriteriLibro() throws IOException {

        File a = new File("C:\\Users\\david\\Desktop\\Book- Recommender\\data\\Emozioni.txt");
        if (!a.exists())
            a.createNewFile();

        int conferma = 0;
        int contaRiga = 0;
        boolean esiste = false;
        String letto = "";
        String idSost = "";
        String nickSost = "";
        String titSost = "";
        String stringaDaSostituire = "";
        String vecchioContenuto = "";
        FileReader f = new FileReader(a);
        BufferedReader br = new BufferedReader(f);

        while ((letto = br.readLine()) != null) {
            contaRiga++;
            var coding = letto;
            String[] StringaSeparata = coding.split("\\$");
            if (StringaSeparata[0].equals(id) && StringaSeparata[2].equals(username)) {
                Object[] options = {"Sovrescrivi", "Annulla"};
                //0 = SI, 1 = NO, 2 = ANNULLA
                conferma = JOptionPane.showOptionDialog(null, "Vuoi sovrascrivere il tuo precedente commento?", "Aggiunta commento per " + titolo,
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                esiste = true;
                stringaDaSostituire = letto;
                idSost = id;
                nickSost = username;
                titSost = titolo;
            } else {
                if (!letto.isEmpty()) {
                    vecchioContenuto = vecchioContenuto + "\n" + letto;
                }
            }
        }
        br.close();

        if (conferma == 0 && esiste == true) {
            String s = (id + "$" + titolo + "$" + username);
            for (int i = 0; i < valutazione.length; i++) {
                s = s + "$" + valutazione[i] + ":" + areaCommento[i].getText().replace("\n", " ");
            }
            String nuovoContenuto = vecchioContenuto + "\n" + s;
            BufferedWriter bw = new BufferedWriter(new FileWriter(a, false));
            bw.write(nuovoContenuto + "\n");
            bw.close();
        } else if (conferma == 1 && esiste == true) {
            nomEssaggio = true;
            System.out.println("Nomessaggio Attivo ");
        }
        if (esiste == false) {
            System.out.println("Scrivo sotto");
            BufferedWriter bn = new BufferedWriter(new FileWriter(a, true));
            String s = (id + "$" + username);
            for (int i = 0; i < valutazione.length; i++) {
                s = s + "$" + valutazione[i] + ":" + areaCommento[i].getText().replace("\n", " ");
            }
            bn.append(s);
            bn.append("\n");
            bn.close();
        }
    }
}