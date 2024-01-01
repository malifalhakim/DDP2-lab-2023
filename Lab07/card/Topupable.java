package card;

public interface Topupable {
    /**
     * Akan menambah balance object Card sebesar amount
     * @param amount
     */
    void topup(double amount);
}
