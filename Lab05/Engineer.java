public class Engineer extends Employee{
    // Data Fields
    private int banyakSideJobs;

    // Constructor
    Engineer (String nama, int banyakSideJobs){
        super(nama);
        this.banyakSideJobs = banyakSideJobs;
        this.setGaji(4000000);  // Set gaji awal engineer
    }

    // Methods

    @Override
    public String toString() {
        String line1 = String.format("Nama: %s",this.getNama());
        String line2 = String.format("Pengalaman Kerja: %d",this.getPengalamanKerja());
        String line3 = String.format("Status: %b",this.isStatus());
        String line4 = String.format("NetWorth: Rp%.2f",this.getNetWorth());
        String line5 = String.format("Jabatan: %s",this.getJabatan());
        String line6 = "Role: Engineer";
        String line7 = String.format("Banyak SideJobs: %d",this.banyakSideJobs);

        return line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7;
    }
    
    /*
     * Method yang mengoverride method nextYear pada class Employee
     * Mengupdate pengalamanKerja,Gaji,Jabatan,Status,dan NetWorth
     */
    @Override
    public void nextYear(int n){
        for (int i = 0; i < n;i++){
            this.setPengalamanKerja(this.getPengalamanKerja() + 1);
            this.pembagianGajiDanJabatanTetap(this.getPengalamanKerja());
            if (this.isStatus())
                this.setNetWorth(this.getNetWorth() + (this.banyakSideJobs * 500000) + this.getGaji());
        }
    }

    /*
     * Method yang digunakan untuk mengupdate Gaji,Jabatan, dan Status
     * berdasarkan banyak tahun bekerja
     */
    public void pembagianGajiDanJabatanTetap(int tahunBekerja){
        if (tahunBekerja > 15){
            this.setJabatan("Pensiun");
            this.setGaji(0);
            this.setPengalamanKerja(16); // Engineer dengan pengalaman lebih dari 15 tahun akan tetap ditulis 16
            this.setStatus(false);
        } else if (tahunBekerja > 10){
            this.setJabatan("Expert");
            this.setGaji(12000000);
        } else if (tahunBekerja > 5){
            this.setJabatan("Senior");
            this.setGaji(8000000);
        } else{
            this.setJabatan("Junior");
            this.setGaji(4000000);
        }
    }
  }