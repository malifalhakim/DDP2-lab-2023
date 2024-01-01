public class User {
    // Class untuk object user yang menggunakan program VendingMachine

    // Atribut yang dimiliki user
    private double jumlahUang;

    // Constructor
    public User(double uangAwal){
        this.jumlahUang = uangAwal;
    }

    // Methods
    public double getJumlahUang() {
        return this.jumlahUang;
    }

    public void setJumlahUang(double jumlahUang) {
        this.jumlahUang = jumlahUang;
    }

    public void addJumlahUang(double tambahanUang){
        this.jumlahUang += tambahanUang;
    }
}
