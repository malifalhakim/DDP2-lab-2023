public class Secretary extends Employee{
    // Data Fields
    private double tunjangan;

    // Constructor
    Secretary (String nama, double tunjangan){
        super(nama);
        this.tunjangan = tunjangan;
        this.setGaji(3000000); // Set gaji awal secretary
    }
    
    // Methods

    @Override
    public String toString() {
        String line1 = String.format("Nama: %s",this.getNama());
        String line2 = String.format("Pengalaman Kerja: %d",this.getPengalamanKerja());
        String line3 = String.format("Status: %b",this.isStatus());
        String line4 = String.format("NetWorth: Rp%.2f",this.getNetWorth());
        String line5 = String.format("Jabatan: %s",this.getJabatan());
        String line6 = "Role: Secretary";
        String line7 = String.format("Banyak Tunjangan: %.2f",this.tunjangan);

        return line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7;
    }
    
    /*
     * Method yang mengoverride method nextYear di class Employee
     * Mengupdate pengalamanKerja,Gaji,Jabatan,Status,dan NetWorth dari Secretary
     */
    @Override
    public void nextYear(int n){
        for (int i = 0; i < n; i++){
            this.setPengalamanKerja(this.getPengalamanKerja() + 1);
            this.pembagianGajiDanJabatanTetap(this.getPengalamanKerja());
            if (this.isStatus())
                this.setNetWorth(this.getNetWorth() + this.getGaji() + this.tunjangan);
        }
    }
    
    /*
     * Method yang mengupdate Gaji,Jabatan,dan Status Secretary berdasarkan tahun bekerja
     */
    public void pembagianGajiDanJabatanTetap(int tahunBekerja){
        if (tahunBekerja > 15){
            this.setPengalamanKerja(16); // Manager dengan pengalaman lebih dari 15 tahun akan tetap ditulis 16
            this.setStatus(false);
            this.setJabatan("Pensiun");
            this.setGaji(0);
        } else if (tahunBekerja > 10){
            this.setJabatan("Expert");
            this.setGaji(9000000);
        } else if (tahunBekerja > 5){
            this.setJabatan("Senior");
            this.setGaji(6000000);
        } else{
            this.setJabatan("Junior");
            this.setGaji(3000000);
        }
    }
  
  }