import java.awt.List;
import java.util.ArrayList;

public class libriConsiglaiti {
    private ArrayList<Libro> libriConsiglaitiL;
    private String titolo;
    private String autore;
    private int[] valutazione = new int[0];


    public libriConsiglaiti(ArrayList libriConsigliatiL){
    
        this.libriConsiglaitiL = new ArrayList<>();
    }

    //metodo per aggiungere un libro alla lista dei libri consigliati fino ad un massimo di 3
    public void consigliaMaxLibri(Libro libro){
        if(libriConsiglaitiL.size() < 3){
            libriConsiglaitiL.add(libro);
        }else{
            System.out.println("Non puoi consigliare piu di 3 libri");
        }
    }

    // metodo per stampare le info sul libro
    public String toString() {
            return titolo + "di" + autore + "(Valutazione: " + valutazione + ")";
    }
  
    //metodo per ottenere la lista dei libri consigliati
    public void mostraLibriConsigliati(){
        if(libriConsiglaitiL.isEmpty()){
            System.out.println("Nessun libro consigliato");
        }else{
            System.out.println("Libri consigliati per " + titolo + ":");
            for(Libro libro : libriConsiglaitiL){
                System.out.println(libro);
            }
        }
    }

    
}
