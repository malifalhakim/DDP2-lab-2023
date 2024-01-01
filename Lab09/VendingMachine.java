import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendingMachine extends JFrame {
    // Data Fields / Atribut Frame Vending Machine
    private User pengguna = new User(0);
    private JLabel label = new JLabel("Please select an option");;
    private JButton addMoney = new JButton("add Money");
    private JButton purchase = new JButton("Purchase Product");

    // Constructor
    public VendingMachine(){
        Container contentPane = getContentPane();

        // Membuat Panel Utama (berisi semua panel lain di frame ini)
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        contentPane.add(panel);

        // Membuat Panel yang berisi text field
        JPanel textFieldPanel = new JPanel(new BorderLayout());
        textFieldPanel.add(label,BorderLayout.CENTER);
        label.setHorizontalAlignment(JTextField.CENTER);

        // Membuat panel yang berisi dua button (addMoney, dan Purchase)
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addMoney);
        buttonPanel.add(purchase);

        // Menambahkan textFieldPanel dan buttonPanel ke panel utama
        panel.add(textFieldPanel,BorderLayout.CENTER);
        panel.add(buttonPanel,BorderLayout.SOUTH);

        // Menambahkan listener pada button addMoney dan purchase
        addMoney.addActionListener(new AddMoneyListener(this));
        purchase.addActionListener(new PurchaseListener(this));

        // Configurasi awal dari frame VendingMachine
        pack();
        setTitle("Vending Machine");
        setLocationRelativeTo(null); // Center the frame
        setSize(350,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method main untuk mulai menjalankan program
    public static void main(String[] args) {
        VendingMachine frame = new VendingMachine();
    }

    // Listener untuk button addMoney. Berguna untuk menyembunyikan frame saat ini dan membuka frame MoneyInput
    private class AddMoneyListener implements ActionListener{
        private VendingMachine frame;

        public AddMoneyListener(VendingMachine frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            MoneyInput frame = new MoneyInput(pengguna,this.frame);
        }
    }

    // Method untuk mengupdate label pada frame VendingMachine sesuai dengan uang user
    public void updateLabel(){
        if (pengguna.getJumlahUang() >= 0)
            label.setText(String.format("Total Money : Rp.%.1f",pengguna.getJumlahUang()));
    }

    // Listener untuk purchase button. Berguna untuk menutup frame saat ini dan membuka frame PurchaseProduct
    private class PurchaseListener implements ActionListener{
        private VendingMachine frame;
        public PurchaseListener(VendingMachine frame){
            this.frame = frame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            PurchaseProduct frame = new PurchaseProduct(pengguna,this.frame);
        }
    }
}
