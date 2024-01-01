package card;

import item.Item;

public class ElectronicCard extends Card implements Topupable{
    public ElectronicCard(String companyName,double balance){
        super(companyName,balance,"Electronic");
    }


    /**
     * Method untuk membayar item dengan harga item yang dibayar adalah harga normal
     * @param item -> Object Item yang ingin dibayar
     */
    @Override
    public void pay(Item item) {
        this.setBalance(this.getBalance() - item.getPrice());
    }

    /**
     * Method untuk menambah balance Electronic Card sebesar amount
     * @param amount
     */
    @Override
    public void topup(double amount) {
        this.setBalance(this.getBalance() + amount);
    }
}
