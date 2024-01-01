import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PacilfyMerger {
    public static void main(String[] args) {
        // Inisiasi scanner sebagai input user dan array list untuk menampung list lagu
        Scanner inputUser = new Scanner(System.in);
        ArrayList<String> listPlaylist1 = new ArrayList<>();
        ArrayList<String> listPlaylist2 = new ArrayList<>();
        ArrayList<String> listPlaylistMerge = new ArrayList<>();

        // Meminta file 1, mengecek kevalidan-nya, dan menambahkan ke list merge
        System.out.print("File playlist pertama: ");
        String filePlaylist1 = inputUser.nextLine();
        if (!isValidPlaylist(filePlaylist1, listPlaylist1))
            System.exit(0);
        for (String lagu:listPlaylist1){
            listPlaylistMerge.add(lagu);
        }

        // Meminta file 2, mengecek kevalidan-nya, dan menambahkan lagu baru ke list merge
        System.out.print("File playlist kedua: ");
        String filePlaylist2 = inputUser.nextLine();
        if (!isValidPlaylist(filePlaylist2, listPlaylist2))
            System.exit(0);
        for(String lagu:listPlaylist2){
            if(!listPlaylist1.contains(lagu)){
                listPlaylistMerge.add(lagu);
            }
        }

        // Membuat file merge yang berisi gabungan playlist dua file
        System.out.print("File playlist output: ");
        String filePlaylistOutput = inputUser.nextLine();
        try {
            buatFile(listPlaylistMerge,filePlaylistOutput);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Berhasil menimpa playlist, jumlah lagu adalah: " + listPlaylistMerge.size());
    }

    /**
     * Mengecek apakah file playlist valid atau tidak
     * jika valid return true dan sebaliknya
     * Serta memasukkan lagu ke dalam arraylist-nya masing-masing
     */
   public static boolean isValidPlaylist(String namaFile, ArrayList<String> listPlaylist){
        try(Scanner fileReader = new Scanner(new File(namaFile))){
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                masukkanLagu(line,listPlaylist);
            }
        } catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan!");
            return false;
        } catch (InvalidPlaylistException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
   }

    /**
     * Memasukkan setiap lagu di playlist sekaligus mengecek
     * setiap metadata apakah sesuai format atau tidak
     */
   public static void masukkanLagu(String data,ArrayList<String> listPlaylist) throws InvalidPlaylistException{
        String[] token = data.split("\\|\\|");
        if (token.length != 2)
            throw new InvalidPlaylistException();
        if (token[0].length() == 0 || token[1].length() == 0)
            throw new InvalidPlaylistException();
        if (token[0].startsWith("|") || token[0].endsWith("|"))
            throw new InvalidPlaylistException();
        if (token[1].startsWith("|") || token[1].endsWith("|"))
            throw new InvalidPlaylistException();
        listPlaylist.add(data);
   }

    /**
     * Membuat file playlist baru yang merupakan gabungan dari dua file playlist
     */
   public static void buatFile(ArrayList<String> listPlaylist,String namaFile) throws FileNotFoundException {
        try(PrintWriter pw = new PrintWriter(namaFile)) {
            for (String lagu : listPlaylist)
                pw.println(lagu);
        }
    }
}

// Class custom Exception yang menandakan playlist tidak valid
class InvalidPlaylistException extends Exception{
    public InvalidPlaylistException(){
        super("Playlist tidak valid!");
    }
}
