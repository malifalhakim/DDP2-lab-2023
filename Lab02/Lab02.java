import java.util.Scanner;

public class Lab02 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Menampilkan halaman utama
        showPilihan();
        System.out.print("Pilihan : ");
        String pilihan = input.nextLine();

        // Program dijalankan terus hingga dipilih 3 (Keluar)
        while (!pilihan.equals("3")){

            // Jika dipilih pilihan buat kupon
            if (pilihan.equals("1")) {
                System.out.print("Nama kupon : ");
                String namaKupon = input.nextLine();
                String kodeKupon = generateKode(namaKupon);
                System.out.printf("Kode kupon adalah: %s \n", kodeKupon);
            }
            // Jika dipilih pilihan validasi kupon
            else if (pilihan.equals("2")) {
                System.out.print("Kupon : ");
                String kupon = input.nextLine();
                boolean statusKupon = validasiKupon(kupon);
                if (statusKupon){
                    System.out.println("Kupon yang diberikan valid");
                } else {
                    System.out.println("Kupon yang diberikan tidak valid");
                }
            }

            // Menu untuk meminta pilihan operasi selanjutnya
            System.out.println();
            showPilihan();
            System.out.print("Pilihan : ");
            pilihan = input.nextLine();
        }
        input.close();
    }

    public static void showPilihan(){
        // Method untuk menampilkan halaman pilihan operasi yang diinginkan

        System.out.println("""
                Halo! Apa yang ingin kamu lakukan?
                [1] Buat kupon
                [2] Validasi kupon
                [3] Keluar""");
    }

    public static String generateKode(String namaKupon){
        // Method untuk menghasilkan kode kupon

        for (int i = 0; i < 2; i++){
            int intOfChar = calculateIntCheckSum(namaKupon) % 26;
            char charCheckSum = (char) (intOfChar + 65);
            namaKupon += charCheckSum;
        }
        return namaKupon;
    }

    public static boolean validasiKupon(String kupon){
        // Method untuk mengecek apakah kupon yang diberikan valid atau tidak
        // valid ketika checksumnya sudah benar, sesuai dengan nama kupon di kupon

        String namaKupon = kupon.substring(0,kupon.length()-2);
        String kuponValid = generateKode(namaKupon);
        return kuponValid.equals(kupon);
    }

    public static int calculateIntCheckSum(String namaKupon){
        // Method untuk menghitung jumlah nilai dari semua huruf

        if (namaKupon.isEmpty())
            return 0;
        else {
            return (namaKupon.charAt(0) - 65) + calculateIntCheckSum(namaKupon.substring(1));
        }
    }
}
