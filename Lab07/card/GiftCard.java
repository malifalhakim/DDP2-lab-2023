package card;

import item.Item;

public class GiftCard extends Card{
    public GiftCard(String companyName,double balance){
        super(companyName,balance,"GIFT");
    }

    /**
     * Method untuk membayar item
     * Harga item yang dibayar diberi diskon 10%
     * @param item -> Object Item yang ingin dibayar
     */
    @Override
    public void pay(Item item) {
        double hargaDibayar = 0.9 * item.getPrice();
        this.setBalance(this.getBalance() - hargaDibayar);
    }
}
