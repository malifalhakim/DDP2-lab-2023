public class Coffee extends Beverage {
    // Data Fields
    private boolean hasWhipCream = false;

    // Constructor
    public Coffee(String nama, String size, boolean isCold) {
        super(nama, size, isCold);
        this.calculatePrice();
    }

    // Methods

    /*
     * Methods untuk menetapkan harga coffee berdasarkan ukuran yang dimasukkan
     */
    @Override
    public void calculatePrice() {
        if (this.size.equalsIgnoreCase("Tall"))
            this.price = 20000;
        else if (this.size.equalsIgnoreCase("Grande"))
            this.price = 25000;
        else if (this.size.equalsIgnoreCase("Venti"))
            this.price = 30000;
    }

    /*
     * Method untuk menambahkan whip cream pada coffee sekaligus mengupdate harganya
     */
    public void addWhipCream() {
        if (!this.hasWhipCream){
            this.hasWhipCream = true;
            this.price += 5000;
        }
    }

    @Override
    public String toString() {
        String res = super.toString();
        if (hasWhipCream) {
            res += " with Whip Cream";
        }

        res += " Rp. " + this.price + ",-";
        return res;
    }
  }