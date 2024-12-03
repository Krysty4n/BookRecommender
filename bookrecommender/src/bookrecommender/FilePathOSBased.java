package bookrecommender;

public class FilePaosthOSBased {
    public String getfilePath(){
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            return "";//inserire path del pc

        }else if (os.contains("nix") || os.contains("nux")){
            return null; //inserire path del pc

        } else if (os.contains("mac")) {
           // rimpiazzare con absolute path della directory "DATA" sul vostro computer.
          //  return ;
        }
        else {
            return null;
        }
    }
    public FilePaosthOSBased(){
        getfilePath();
    }
}
