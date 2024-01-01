public class Tea extends Beverage {
    // Data Fields
    private boolean hasMilk;

    // Constructor
    public Tea(String nama, String size, boolean isCold) {
        super(nama, size, isCold);
        this.calculatePrice();
    }

    // Methods

    /*
     * Methods untuk menetapkan harga tea berdasarkan ukuran yang dimasukkan
     */
    @Override
    public void calculatePrice() {
        if (this.size.equalsIgnoreCase("Tall"))
            this.price = 15000;
        else if (this.size.equalsIgnoreCase("Grande"))
            this.price = 20000;
        else if (this.size.equalsIgnoreCase("Venti"))
            this.price = 25000;
    
    }

    /*
     * Method untuk menambahkan susu pada tea sekaligus mengupdate harganya
     */
    public void addMilk() {
        if (!this.hasMilk){
            this.hasMilk = true;
            this.price += 7000;
        }
    }

    @Override
    public String toString() {
        String res = super.toString();
        if (hasMilk) {
          res += " with Milk";
        }
    
        res += " Rp. " + this.price + ",-";
        return res;
    }
}