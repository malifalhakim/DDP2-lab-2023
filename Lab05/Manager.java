public class Manager extends Employee{
    // Data Fields
    private double raise;

    // Constructor
    Manager (String nama, double raise){
        super(nama);
        this.raise = raise;
        this.setGaji(2000000); // Set gaji awal Manager
    }
    
    // Methods

    @Override
    public String toString() {
        String line1 = String.format("Nama: %s",this.getNama());
        String line2 = String.format("Pengalaman Kerja: %d",this.getPengalamanKerja());
        String line3 = String.format("Status: %b",this.isStatus());
        String line4 = String.format("NetWorth: Rp%.2f",this.getNetWorth());
        String line5 = String.format("Jabatan: %s",this.getJabatan());
        String line6 = "Role: Manager";

        return line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6;
    }
    
    
    /*
     * Method yang mengoverride method nextYear di class Employee
     * Mengupdate pengalamanKerja,Gaji,Jabatan,Status dan NetWorth dari Manager
     */
    @Override
    public void nextYear(int n){
        for (int i = 0; i < n; i++){
            this.setPengalamanKerja(this.getPengalamanKerja() + 1);
            this.setGaji(this.raise * this.getGaji());
            this.pembagianJabatanTetap(this.getPengalamanKerja());
            this.setNetWorth(this.getNetWorth() + this.getGaji());
        }
    }
  
    /*
     * Method untuk mengupdate Status,Jabatan dan Gaji dari Manager berdasarkan tahun bekerja
     */
    public void pembagianJabatanTetap (int tahunBekerja){
        if (tahunBekerja > 15){
            this.setPengalamanKerja(16); // Manager dengan pengalaman lebih dari 15 tahun akan tetap ditulis 16
            this.setStatus(false);
            this.setJabatan("Pensiun");
            this.setGaji(0);
        } else if (tahunBekerja > 10){
            this.setJabatan("Expert");
        } else if (tahunBekerja > 5){
            this.setJabatan("Senior");
        } else{
            this.setJabatan("Junior");
        }
    }
  
  }