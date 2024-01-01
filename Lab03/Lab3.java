import java.util.Scanner;

class Lab3 {
    private static final Scanner input = new Scanner(System.in);
    private static String[] kumpulanNamaMahasiswa;
    private static String[] kumpulanNamaMatkul;
    private static int[][] score;
    private static int banyakMatkul;

    public static void main(String[] args) {
        init();

        while (true) {
            printMenu();
            System.out.print("Masukkan pilihan: ");
            int pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1:
                    menambahMahasiswa();
                    break;
                case 2:
                    menghapusMahasiswa();
                    break;
                case 3:
                    mencetakTabel();
                    break;
                case 4:
                    mencetakNilai();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan BeJayNG!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }

    public static int[][] insertRow (int[] insertedRow, String namaMahasiswa) {
        // Menginput data nilai mahasiswa baru ke array score dan array daftar nama mahasiswa

        // Menginput nilai baru
        int[][] newArrayScore = new int[score.length + 1][banyakMatkul];
        System.arraycopy(score,0,newArrayScore,0,score.length);
        newArrayScore[newArrayScore.length - 1] = insertedRow;

        // Menginput nama mahasiswa baru
        String[] namaMahasiswaBaru = new String[kumpulanNamaMahasiswa.length + 1];
        System.arraycopy(kumpulanNamaMahasiswa,0,namaMahasiswaBaru,0,kumpulanNamaMahasiswa.length);
        namaMahasiswaBaru[namaMahasiswaBaru.length - 1] = namaMahasiswa;
        kumpulanNamaMahasiswa = namaMahasiswaBaru;

        return newArrayScore;
    }

    static void init() {
        // Meminta initial input untuk menginisialisasi array score,kumpulanNamaMatkul, dan kumpulanNamaMahasiswa
        System.out.println("Selamat datang di BeJayNG!");
        System.out.println("==============Initial Input==============");

        System.out.print("Masukkan jumlah mata kuliah : ");
        banyakMatkul = input.nextInt();
        kumpulanNamaMatkul = new String[banyakMatkul];
        input.nextLine();

        // Mengisi array mata kuliah
        for (int i = 0 ; i < banyakMatkul ; i++){
            System.out.print("Masukkan nama mata kuliah : ");
            String namaMataKuliah = input.nextLine();
            kumpulanNamaMatkul[i] = namaMataKuliah;
        }

        score = new int[0][banyakMatkul];
        kumpulanNamaMahasiswa = new String[0];
        return;
    }

    static void printMenu() {
        // Method untuk memprint pilihan menu
        System.out.println("==============Menu==============");
        System.out.println("[1] Menambah Mahasiswa");
        System.out.println("[2] Menghapus Mahasiswa");
        System.out.println("[3] Mencetak Tabel");
        System.out.println("[4] Mencetak Nilai");
        System.out.println("[0] Keluar");
    }

    static void menambahMahasiswa() {
        // Method yang menentukan alur program ketika menambah mahasiswa
        System.out.println("==============Menambah Mahasiswa==============");

        System.out.print("Masukkan Nama Mahasiswa : ");
        String namaMahasiswa = input.nextLine();

        // Mengisi nilai mahasiswa baru ke suatu array
        int[] insertedRow = new int[banyakMatkul];
        for (int i = 0; i < banyakMatkul ; i++){
            System.out.print("Masukkan nilai " + kumpulanNamaMatkul[i] + " : ");
            int nilaiMatkul = input.nextInt();
            input.nextLine();
            insertedRow[i] = nilaiMatkul;
        }

        // Memasukkan array nilai mahasiswa baru ke array score
        score = insertRow(insertedRow,namaMahasiswa);
        System.out.printf("Nilai mahasiswa bernama %s berhasil diinput ke BeJayNG\n",namaMahasiswa);
        return;
    }

    static void menghapusMahasiswa() {
        // Method yang menentukan alur program ketika menghapus mahasiswa
        System.out.println("==============Menghapus Mahasiswa==============");

        System.out.print("Masukkan nama mahasiswa : ");
        String namaMahasiswa = input.nextLine();

        // Menemukan index mahasiswa yang dicari
        int index = -1;
        for (int i = 0; i < kumpulanNamaMahasiswa.length ; i++){
            if (kumpulanNamaMahasiswa[i].equals(namaMahasiswa))
                index = i;
        }

        score = removeElement(index);
        System.out.printf("Mahasiswa bernama %s telah dihapus dari BeJayNG\n",namaMahasiswa);
        return;
    }

    public static int[][] removeElement(int index) {
        // Method untuk menghapus elemen nilai dan nama mahasiswa di array score dan kumpulanNamaMahasiswa
        int[][] nilaiBaru = new int[score.length - 1][banyakMatkul];
        String[] namaMahasiswaBaru = new String[kumpulanNamaMahasiswa.length - 1];

        for (int i = 0, k = 0; i < score.length ; i++){
            if (i == index) // Men-skip pencopy-an ke array baru untuk index yang ingin dihapus
                continue;
            nilaiBaru[k] = score[i];
            namaMahasiswaBaru[k++] = kumpulanNamaMahasiswa[i];
        }

        kumpulanNamaMahasiswa = namaMahasiswaBaru;
        return nilaiBaru;
    }

    static void mencetakTabel() {
        // Mencetak Tabel nama dan score mahasiswa
        System.out.println("==============Mencetak Tabel==============");

        // Mencetak Header Tabel
        System.out.print("Nama                ");
        for (int i = 0; i < banyakMatkul ; i++){
            System.out.printf("%-10s",kumpulanNamaMatkul[i]);
        }
        System.out.println();

        // Mencetak isi tabel berupa nama dan score
        for (int i = 0; i < score.length ; i++){
            System.out.printf("%-20s",kumpulanNamaMahasiswa[i]);
            for (int j = 0; j < kumpulanNamaMatkul.length; j++){
                System.out.printf("%-10d",score[i][j]);
            }
            System.out.println();
        }

        return;
    }

    static void mencetakNilai() {
        // Mencetak Nilai Matkul seorang mahasiswa
        System.out.println("==============Mencetak Nilai==============");

        System.out.print("Masukkan nama mahasiswa : ");
        String namaMahasiswa = input.nextLine();

        System.out.print("Masukkan nama mata kuliah : ");
        String namaMatkul = input.nextLine();

        // Mencari index namaMahasiswa di kumpulanNamaMahasiswa
        int indexNama = -1;
        for (int i = 0;i < kumpulanNamaMahasiswa.length; i++){
            if (namaMahasiswa.equals(kumpulanNamaMahasiswa[i]))
                indexNama = i;
        }

        // Mencari index namaMatkul di kumpulanNamaMatkul
        int indexMatkul = -1;
        for (int i = 0; i < kumpulanNamaMatkul.length; i++){
            if (namaMatkul.equals(kumpulanNamaMatkul[i]))
                indexMatkul = i;
        }

        System.out.printf("Nilai %s di mata kuliah %s adalah %d\n",namaMahasiswa,namaMatkul,score[indexNama][indexMatkul]);
        return;
    }
}