import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Starcil {
  private static InputReader in;
  private static PrintWriter out;
  private static Beverage[] daftarMinuman = new Beverage[0];

  private static void masukkanKeDaftarMinuman(Beverage beverage) {
    Beverage[] newDaftarMinuman = new Beverage[daftarMinuman.length + 1];

    for (int i = 0; i < daftarMinuman.length; i++) {
      newDaftarMinuman[i] = daftarMinuman[i];
    }
    daftarMinuman = newDaftarMinuman;

    newDaftarMinuman[daftarMinuman.length - 1] = beverage;

  }

  private static Beverage getMinuman(String namaMinuman) {
    for (Beverage drink : daftarMinuman) {
      if (drink.getName().equalsIgnoreCase(namaMinuman)) {
        return drink;
      }
    }
    return null;
  }

  public void mainProgram() {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    int N;
    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      String event = in.next();

      if (event.equals("ADD")) {
        String jenisMinuman = in.next();
        String nama = in.next();
        String size = in.next();
        boolean isCold = in.next().equals("YES");

        if (jenisMinuman.equals("COFFEE")) {
          // Membuat object Coffee dan memasukkannya ke array daftarMinuman
          Coffee coffeeOrder = new Coffee(nama,size,isCold);
          masukkanKeDaftarMinuman(coffeeOrder);
        } else {
          // Membuat object Tea dan memasukkannya ke array daftarMinuman
          Tea teaOrder = new Tea(nama,size,isCold);
          masukkanKeDaftarMinuman(teaOrder);
        }
      }

      else if (event.equals("TOPPING")) {
        String namaMinuman = in.next();
        Beverage minuman = getMinuman(namaMinuman);

        // Menambahkan topping pada minuman sesuai dengan jenisnya
        if (minuman instanceof Tea)
          ((Tea) minuman).addMilk();
        else if (minuman instanceof Coffee)
          ((Coffee) minuman).addWhipCream();
      }

      else if (event.equals("ORDERAN")) {
        String jenisMinuman = in.next();
        if (jenisMinuman.equals("COFFEE")) {
          // Memprint semua minuman berjenis Coffee saja
          for (Beverage minuman: daftarMinuman){
            if (minuman instanceof Coffee)
              out.println(minuman);
          }

        } else {
          // Memprint semua minuman berjenis Tea saja
          for (Beverage minuman: daftarMinuman){
            if (minuman instanceof Tea)
              out.println(minuman);
          }
        }
      }

      else {
        out.println("PERINTAH TIDAK DITEMUKAN");
      }

    }
    out.flush();
  }

  public static void main(String[] args) {
    Starcil cafe = new Starcil();
    cafe.mainProgram();
  }

  // taken from https://codeforces.com/submissions/Petr
  // together with PrintWriter, these input-output (IO) is much faster than the
  // usual Scanner(System.in) and System.out
  // please use these classes to avoid your fast algorithm gets Time Limit
  // Exceeded caused by slow input-output (IO)
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
  }
}