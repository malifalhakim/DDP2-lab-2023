public class Beverage {
    // Data Fields
    private String name;
    protected String size;
    private boolean isCold;
    protected int price;

    // Constructor
    public Beverage(String name, String size, boolean isCold) {
      this.name = name;
      this.size = size;
      this.isCold = isCold;
    }

    // Methods

    /*
     * Method yang akan dioverride oleh sub-class. Berguna untuk Menghitung Harga Minuman
     */
    public void calculatePrice() {
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
      String output = "";
  
      if (isCold) {
        output += "COLD ";
      } else {
        output += "HOT ";
      }
  
      output += this.size + " " + this.name;
      return output;
    }
  }