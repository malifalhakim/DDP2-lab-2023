import java.util.NoSuchElementException;
import java.util.Scanner;

public class VideoPlayer {
    private static VideoList movieList;
    private static VideoList ddpTubeVideoList;
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isFinish = false;

        //inisiasi movie list dan ddptubevideo list baru
        movieList = new VideoList();
        ddpTubeVideoList = new VideoList();

        // Meminta input menu yang diinginkan dari user
        System.out.println("Selamat datang di DEDEPE Player!");
        while(!isFinish){
            try{
                printMainMenu();
                int menu = Integer.parseInt(in.nextLine());
                switch (menu) {
                    case 1 -> addVideo();
                    case 2 -> nextVideo();
                    case 3 -> deleteVideo();
                    case 4 -> printVideoList();
                    default -> {
                        System.out.println("Terima kasih sudah menggunakan DEDEPE Player!");
                        isFinish = true;
                    }
                }
            }
            catch (IllegalArgumentException e){
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("WRONG INPUT FORMAT!!\nError code: 401");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            }
            catch (NoSuchElementException e){
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("NO VIDEO FOUND!!\nError code: 402");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            }
            catch (Exception e){
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("UNKNOWN ERROR!!\nError code: 444");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            }
        }

    }

    /**
     * Method untuk menambahkan Video baru ke dalam videoList sesuai dengan posisi yang diinginkan
     */
    public static void addVideo(){
        System.out.println("---------------TAMBAH VIDEO----------------");
        System.out.println("Tambah Video Baru");
        System.out.print("Masuk di (1) paling depan atau (2) paling belakang: ");
        int pos = Integer.parseInt(in.nextLine());
        System.out.print("Jenis: ");
        String type = in.nextLine();
        System.out.print("Judul: ");
        String title = in.nextLine();
        System.out.print("Durasi (dalam menit): ");
        int duration = Integer.parseInt(in.nextLine());

        if(duration<=0) throw new IllegalArgumentException();

        Video newVideo;

        if(type.equalsIgnoreCase("Movie")){
            System.out.print("Sutradara: ");
            String director = in.nextLine();
            System.out.print("Rating: ");
            Double rating = Double.valueOf(in.nextLine());

            if(rating > 5.0) throw new IllegalArgumentException();

            newVideo = new Movie(title,duration,director, rating);

            // memasukkan newVideo ke movie list sesuai dengan posisi yang diinginkan
            movieList.insertVideo(newVideo,pos == 1);

        }
        else if(type.equalsIgnoreCase("DDPTube")){
            System.out.print("Creator: ");
            String creator = in.nextLine();

            newVideo = new DDPTubeVideo(title,duration,creator);

            //masukkan ddptube video ke ddpTubeVideoList sesuai dengan posisi yang diinginkan
            ddpTubeVideoList.insertVideo(newVideo,pos == 1);

        }
        else{
            System.out.println("Tipe video tidak diketahui !!!");
        }
    }

    /**
     * Method untuk membuat Video Player memutar video selanjutnya sesuai dengan tipe video yang ingin
     * di-next
     */
    public static void nextVideo(){
        System.out.print("Putar (1) movie atau (2) DDPTube video selanjutnya? ");
        int type = Integer.parseInt(in.nextLine());

        // Mengambil object Video paling depan -> Menghapusnya -> Pindahkan object yang telah diambil ke belakang
        switch (type){
            case 1:
                // Jika yang dipilih tipe Movie
                Video movieTelahDiputar = movieList.getFirst();
                movieList.deleteVideo();
                movieList.insertVideo(movieTelahDiputar,false);
                break;
            case 2:
                // Jika dipilih tipe ddpTube
                Video ddpTubeTelahDiputar = ddpTubeVideoList.getFirst();
                ddpTubeVideoList.deleteVideo();
                ddpTubeVideoList.insertVideo(ddpTubeTelahDiputar,false);
                break;
        }

    }

    /**
     * Method untuk menghapus video yang ada di paling depan movieList atau ddpTubeVideoList
     */
    public static void deleteVideo(){
        System.out.println("---------------HAPUS VIDEO-----------------");

        System.out.print("Hapus (1) movie atau (2) DDPTube video? ");
        int type = Integer.parseInt(in.nextLine());

        Video video = null;
        switch (type){
            case 1:
                video = movieList.getFirst();  // Mengambil object video paling depan ( yang dihapus )
                movieList.deleteVideo();
                break;
            case 2:
                video = ddpTubeVideoList.getFirst();
                ddpTubeVideoList.deleteVideo();
                break;
        }
        System.out.println(video.getTitle() + " - " + video.getDuration() + " dihapus!");
        Video.videoAmount--;
    }

    /**
     * Method untuk mencetak semua Video yang ada di tiap video list dimulai dari video yang
     * paling depan
     */
    public static void printVideoList(){
        System.out.println("---------------DAFTAR VIDEO----------------");

        int counter = 0;

        System.out.println("Movie anda: ");
        for (Video video : movieList.getVideoList()){
            counter++;
            System.out.println(counter + ". " + video.getTitle() + " - " + video.getDuration());
        }
        if(counter<1){
            System.out.println("List movie anda kosong");
        }

        System.out.println();
        counter = 0;
        System.out.println("DDPTube Video anda: ");
        for (Video video : ddpTubeVideoList.getVideoList()){
            counter++;
            System.out.println(counter + ". " + video.getTitle() + " - " + video.getDuration());
        }
        if(counter<1){
            System.out.println("List DDPTube video anda kosong");
        }
    }

    /**
     * Method untuk mencetak menu dari Video Player beserta jumlah Video yang sudah terdaftar
     */
    public static void printMainMenu(){
        System.out.println("-------------------------------------------");
        System.out.println("Total jumlah video sekarang: " + Video.videoAmount);
        playMovie();
        playDdpTubeVideo();
        System.out.println("-------------------------------------------");
        System.out.print("""
                Silakan pilih menu:\s
                1. Tambah video
                2. Putar video selanjutnya
                3. Hapus video
                4. Lihat daftar video
                5. Keluar
                Pilihan:\s""");
    }

    /**
     * Method untuk memutar movie yang ada di paling depan movieList
     */
    public static void playMovie(){
        try{
            Video current = movieList.getFirst();
            System.out.println("Movie sekarang: \n" + current);
        }
        catch (Exception e){
            System.out.println("Movie sekarang: \nTidak ada");
        }
    }

    /**
     * Method untuk memutar DDPTube video yang ada di paling depan ddpTubeVideoList
     */
    public static void playDdpTubeVideo(){
        try{
            Video current = ddpTubeVideoList.getFirst();
            System.out.println("DDPTube video sekarang: \n" + current);
        }
        catch (Exception e){
            System.out.println("DDPTube video sekarang: \nTidak ada");
        }
    }
}
