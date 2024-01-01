import java.util.Scanner;

public class Lab01 {
    public static int getDiscount(double ipk){
        // Method untuk memilih discount berdasarkan IPK
        int discount;
        if (ipk > 3.5)
            discount = 50;
        else if (ipk > 3.0)
            discount = 35;
        else if (ipk > 2.5)
            discount = 25;
        else
            discount = 10;
        return discount;
    }

    public static double getPembayaran(int banyakLembar, int discount){
        // Method untuk menghitung jumlah yang harus di bayar
        final double HARGA_PER_LEMBAR = 555.00;
        return (banyakLembar * HARGA_PER_LEMBAR * ((100.00 - discount)/100.00));
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Selamat datang di Toko Fotokopi Dek Depe!");
        System.out.println("--------------------------------------------------------");

        System.out.print("Masukkan jumlah mahasiswa yang ingin melakukan fotokopi: ");
        int banyakMahasiswa = Integer.parseInt(input.nextLine());
        double jumlahPendapatan = 0; // Variable untuk menampung jumlah pendapatan

        for (int i = 1;i <= banyakMahasiswa;i++){
            System.out.printf("--------------------DATA MAHASISWA %d--------------------\n",i);

            System.out.print("Nama: ");
            String namaMahasiswa = input.nextLine();

            System.out.print("IPK: ");
            double ipk = Double.parseDouble(input.nextLine());

            System.out.print("Jumlah lembar: ");
            int jumlahLembar = Integer.parseInt(input.nextLine());

            int discount = getDiscount(ipk); // cek diskon yang didapat dari method getDiscount
            double jumlahBayar = getPembayaran(jumlahLembar,discount); // hitung jumlah yang perlu dibayar dari method getPembayaran
            jumlahPendapatan += jumlahBayar; // update jumlah pendapatan

            System.out.printf("%s membayar seharga %.2f dengan diskon sebesar %d",namaMahasiswa,jumlahBayar,discount);
            System.out.println("%");
        }

        System.out.println("---------------------RINGKASAN DATA---------------------");
        System.out.printf("Hasil pendapatan yang diperoleh Toko Fotokopi dari %d mahasiswa adalah %.2f",banyakMahasiswa,jumlahPendapatan);
        input.close();

    }
}
