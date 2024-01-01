import java.util.ArrayList;

public class VideoList{
    private ArrayList<Video> videoList; // Atribut collections untuk menyimpan video

    public VideoList(){
        this.videoList = new ArrayList<Video>(); // Inisialisasi ArrayList kosong untuk object VideoList yang baru dibuat
    }

    // Mengembalikan list video
    public ArrayList<Video> getVideoList() {
        return this.videoList;
    }

    // Mengembalikan object video paling depan
    public Video getFirst(){
        return this.videoList.get(0);
    }

    // Menambahkan object video di paling depan atau paling belakang
    public void insertVideo(Video video,boolean isFront){
        if (isFront){
            this.videoList.add(0,video);
        } else {
            this.videoList.add(video);
        }
    }

    // Menghapus object video di paling depan
    public void deleteVideo(){
        this.videoList.remove(0);
    }
}
