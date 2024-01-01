import java.io.*;
import java.util.StringTokenizer;

public class Diskonpedia {

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    private static Barang[] listBarang;
    private static Pembeli[] listPembeli;

    /*
     * Method utama program
     */
    public static void main(String[] args) {

        // Inisiasi Barang
        int jumlahBarang = in.nextInt();
        listBarang = new Barang[jumlahBarang];

        for(int i = 0; i < jumlahBarang; i++){
            String namaBarang = in.next();
            long harga = in.nextLong();
            int stok = in.nextInt();

            //Inisiasi objek barang baru dan masukkan ke listBarang
            Barang produk = new Barang(harga,namaBarang,stok);
            listBarang[i] = produk;

        }

        // Inisiasi Pembeli
        int jumlahPembeli = in.nextInt();
        listPembeli = new Pembeli[jumlahPembeli];

        for(int i = 0; i < jumlahPembeli; i++){
            String namaPembeli = in.next();
            long jumlahUang = in.nextLong();

            //Inisiasi objek pembeli baru dan masukkan ke listPembeli
            Pembeli pembeli = new Pembeli(namaPembeli,jumlahUang);
            listPembeli[i] = pembeli;

        }

        // Jalanin Query
        int jumlahPerintah = in.nextInt();

        for(int i = 0; i < jumlahPerintah; i++){
            String perintah = in.next();
            switch (perintah) {
                case "PESAN" -> {
                    String namaPembeli = in.next();
                    String namaBarang = in.next();
                    int jumlah = in.nextInt();
                    pesan(namaPembeli, namaBarang, jumlah);
                    break;
                }
                case "BAYAR" -> {
                    String namaPembeli = in.next();
                    bayar(namaPembeli);
                    break;
                }
                case "DISKON" -> {
                    String namaPembeli = in.next();
                    diskon(namaPembeli);
                    break;
                }
                case "RESTOCK" -> {
                    String namaBarang = in.next();
                    int jumlah = in.nextInt();
                    restock(namaBarang, jumlah);
                    break;
                }
            }
        }
        out.close();
    }

    /*
     * Method untuk perintah PESAN
     */
    public static void pesan(String namaPembeli, String namaBarang, int jumlah){
        Pembeli pembeli = cariPembeli(namaPembeli);
        Barang barangDibeli = cariBarang(namaBarang);

        // Menambahkan Pesanan dan Mencetak pesan terkait
        out.println(pembeli.tambahPesanan(barangDibeli,jumlah));
    }

    /*
     * Method untuk perintah BAYAR
     */
    public static void bayar(String namaPembeli){
        Pembeli pembeli = cariPembeli(namaPembeli);
        out.printf("%s berhasil melakukan pembelian barang dan pembayaran!\n",pembeli.getNama());
        out.println("########## Detail Pembayaran ##########");

        // Mencetak informasi mengenai list barang yang dipesan
        for (Pesanan pesanan:pembeli.getListPesanan()){
            Barang barang = pesanan.getBarang();
            out.printf("%s: %d * %d = %d\n",
                    barang.getNama(),barang.getHarga(), pesanan.getJumlahBarang(),pesanan.totalHarga());

        }

        long diskon = (hitungDiskon(pembeli) * pembeli.totalHarga())/100;
        long hargaBayar = pembeli.totalHarga() - diskon;
        out.println("_____________________________________");
        out.printf("Total harga = %d\n",pembeli.totalHarga());
        out.printf("Diskon = (%d) \n",diskon);
        out.printf("Harga bayar = %d\n",hargaBayar);

        pembeli.setJumlahUang(pembeli.getJumlahUang() - hargaBayar);
        out.printf("Sisa uang = %d \n",pembeli.getJumlahUang());
        out.println("#######################################");

        pembeli.resetPesanan();
    }

    /*
     * Method untuk perintah RESTOCK
     */
    public static void restock(String namaBarang, int jumlah){
        Barang barang = cariBarang(namaBarang);
        if (jumlah > 0){
            // Menambah stok barang dengan jumlah yang dimasukkan
            barang.setStok(barang.getStok() + jumlah);
            out.printf("Berhasil menambahkan stok barang %s. Sisa stok sekarang = %d.\n",barang.getNama(),barang.getStok());
        } else {
            out.println("Maaf, stok tambahan yang dimasukkan tidak valid");
        }


    }

    /*
     * Method untuk perintah DISKON.
     */
    public static void diskon(String namaPembeli){
        Pembeli pembeli = cariPembeli(namaPembeli);
        out.printf("%s mendapatkan diskon sebesar %d",pembeli.getNama(),hitungDiskon(pembeli));
        out.println("%");

    }

    /*
     * Method untuk mencari persentase diskon yang didapat oleh pembeli.
     * Method ini mengembalikan persentase diskon yang didapat.
     */
    public static int hitungDiskon(Pembeli pembeli){
        return pembeli.totalBarang();
    }

    /*
     * Method untuk mencari Barang berdasarkan nama
     */
    public static Barang cariBarang(String nama){
        for(Barang barang: listBarang){
            if(barang.getNama().equals(nama))
                return barang;
        }
        return null;
    }

    /*
     * Method untuk mencari Pembeli berdasarkan nama
     */
    public static Pembeli cariPembeli(String nama){
        for(Pembeli pembeli: listPembeli){
            if(pembeli.getNama().equals(nama))
                return pembeli;
        }
        return null;
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
