package card;

import item.Item;

public abstract class Card implements Comparable<Card> {
    private static int idCounter = 0;
    private double balance;
    private String companyName;
    private int id;
    private String type;

    protected Card(String companyName, double balance, String type) {
        this.companyName = companyName;
        this.balance = balance;
        this.type = type;
        id = idCounter++;
    }

    /**
     * Method untuk membayar Item yang dipilih menggunakan card
     * @param item -> Object Item yang ingin dibayar
     */
    public abstract void pay(Item item);

    /**
     * Method untuk membandingkan dua buah object Card
     * @param o -> Object Card yang ingin dibandingkan
     */
    @Override
    public int compareTo(Card o) {
        if (this.balance > o.getBalance())
            return -1;
        else if (this.balance == o.getBalance()){
            if (this.id > o.getId())
                return 1;
            else if (this.id == o.getId())
                return 0;
            else
                return -1;
        } else
            return 1;
    }

    @Override
    public String toString() {
        return String.format("Card %s %s - id: %d, balance: %.2f",
                companyName, type, id, balance);
    }

    // Getter dan Setter
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
