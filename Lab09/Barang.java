public class Barang {
    // Class untuk object pada list comboBox

    // Atribute
    private String nama;
    private double harga;

    // Constructor
    public Barang(String nama,double harga){
        this.nama = nama;
        this.harga = harga;
    }

    // Methods
    public double getHarga() {
        return this.harga;
    }

    public String getNama() {
        return this.nama;
    }

    @Override
    public String toString() {
        return this.nama;
    }
}
