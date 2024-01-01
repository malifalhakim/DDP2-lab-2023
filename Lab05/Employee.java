public class Employee {
    // Data Fields
    private String nama;
    private int pengalamanKerja;
    private boolean status;
    private double netWorth;
    private String jabatan;
    private double gaji;

    // Constructor
    Employee(String nama){
        this.nama = nama;
        this.pengalamanKerja = 0;
        this.status = true;
        this.jabatan = "Junior";
        this.netWorth = 0;
        this.gaji = 0;
    }

    // Methods

    /*
     * Method untuk mengupdate pengalaman kerja sebanyak tahun yang diinginkan
     */
    public void nextYear(int tahun) {
       this.pengalamanKerja += tahun;
    }
  
    public String getNama() {
        return this.nama;
    }
  
    public void setJabatan(String jabatan){
        this.jabatan = jabatan;
    }

    public String getJabatan() {
        return this.jabatan;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
  
    public int getPengalamanKerja(){
        return this.pengalamanKerja;
    }

    public void setPengalamanKerja(int pengalamanKerjaBaru){
        this.pengalamanKerja = pengalamanKerjaBaru;
    }
  
    public double getNetWorth(){
        return this.netWorth;
    }
    
    public void setNetWorth(double n){
        this.netWorth = n;
    }
  
    public void setGaji(double gaji) {
        this.gaji = gaji;
    }
  
    public double getGaji() {
        return this.gaji;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}