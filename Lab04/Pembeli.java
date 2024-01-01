import java.util.ArrayList;

class Pembeli {

    // DataFields
    private String nama;
    private long jumlahUang;
    private ArrayList<Pesanan> listPesanan = new ArrayList<>();
    private final int MAKS_JUMLAH_BARANG = 20;

    // Constructor
    public Pembeli(String nama,long jumlahUang){
        this.nama = nama;
        this.jumlahUang = jumlahUang;
        this.listPesanan = listPesanan;
    }

    // Methods

    /*
     * Method yang akan mengembalikan sebuah String yang merupakan pesan hasil dari
     * query BELI.
     */
    public String tambahPesanan(Barang barang, int jumlah){
        boolean barangTersedia = barang.getStok() - jumlah >= 0;
        int jumlahBarang = this.totalBarang();
        long jumlahHarga = this.totalHarga();

        if (!barangTersedia)
            return String.format("Tidak bisa memesan %s sebanyak %d buah. Stok barang tidak cukup",barang.getNama(),jumlah);

        boolean kurangDariMaxPesanan = (jumlahBarang+jumlah) <= MAKS_JUMLAH_BARANG;
        if (!kurangDariMaxPesanan)
            return String.format("Tidak bisa memesan %s sebanyak %d buah. List pesanan %s melebihi kapasitas",barang.getNama(),jumlah,this.nama);

        boolean uangCukup = (jumlahHarga+ barang.getHarga() * jumlah) <= this.jumlahUang;
        if (!uangCukup)
            return String.format("Tidak bisa memesan %s sebanyak %d buah. Uang %s tidak cukup",barang.getNama(),jumlah,this.nama);

        Pesanan pesananPembeli = new Pesanan(barang,jumlah);
        boolean isBelumDipesan = true;
        for (Pesanan pesanan:this.listPesanan){
            if (pesanan.isPesananSama(pesananPembeli)){
                Barang barangDibeli = pesanan.getBarang();
                pesanan.setJumlahBarang(pesanan.getJumlahBarang() + jumlah);
                barangDibeli.setStok(barangDibeli.getStok() - jumlah);
                isBelumDipesan = false;
            }
        }
        if (isBelumDipesan){
            this.listPesanan.add(pesananPembeli);
            Barang barangDibeli = pesananPembeli.getBarang();
            barangDibeli.setStok(barangDibeli.getStok() - jumlah);
        }

        return String.format("%s berhasil memesan %s sebanyak %d buah",this.nama,barang.getNama(),jumlah);
    }

    /*
     * Menghitung total harga seluruh pesanan pembeli.
     */
    public long totalHarga(){
        long total = 0;
        for (Pesanan pesanan:this.listPesanan){
                total = total + pesanan.totalHarga();
        }
        return total;
    }

    /*
     * Menghitung total Barang seluruh pesanan pembeli tersebut
     */

    public int totalBarang(){
        int total = 0;
        for (Pesanan pesanan:this.listPesanan){
                total = total + pesanan.getJumlahBarang();
        }
        return total;
    }

    public long getJumlahUang(){
        return this.jumlahUang;
    }

    public void setJumlahUang(long jumlahUang) {
        this.jumlahUang = jumlahUang;
    }

    public ArrayList<Pesanan> getListPesanan() {
        return this.listPesanan;
    }

    /*
     * Method untuk mengosongkan list pesanan
     */
    public void resetPesanan(){
        this.listPesanan.clear();
    }

    public String getNama() {
        return this.nama;
    }

}
