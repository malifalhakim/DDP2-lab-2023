import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class PurchaseProduct extends JFrame {
    // Data Fields / Atribut
    private JLabel productLabel = new JLabel("Product : ");
    private JLabel quantityLabel = new JLabel("Quantity : ");
    private JLabel priceLabel = new JLabel("Price : ");
    private JLabel totalLabel = new JLabel("Total Price : ");
    private JComboBox<Barang> productList = new JComboBox<Barang>();
    private JTextField quantityTextField = new JTextField();
    private JTextField priceTextField = new JTextField();
    private JTextField totalPriceTextField = new JTextField();
    private JButton purchaseButton = new JButton("Purchase");
    private VendingMachine frameVending;
    private User pengguna;

    // Constructor
    public PurchaseProduct(User pengguna,VendingMachine frameVending){
        this.frameVending = frameVending;
        this.pengguna = pengguna;

        // Menambahkan panel utama
        Container contentPane = getContentPane();
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        contentPane.add(panel);

        // Menambahkan sub-panel (userInputPanel dan button panel) pada panel utama
        JPanel userInputPanel = new JPanel(new GridLayout(4,2,10,10));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        panel.add(userInputPanel,BorderLayout.CENTER);
        panel.add(buttonPanel,BorderLayout.SOUTH);

        // Menambahkan object pada combo box
        productList.addItem(new Barang("Akua",5000));
        productList.addItem(new Barang("Fruti Apel",8000));
        productList.addItem(new Barang("Palpi Jeruk",7500));
        productList.addItem(new Barang("Neskafe Latte",11000));
        productList.addItem(new Barang("Koka Kola",9500));

        // Menambahkan setiap komponen pada userInputPanel
        userInputPanel.add(productLabel);
        userInputPanel.add(productList);

        userInputPanel.add(quantityLabel);
        userInputPanel.add(quantityTextField);

        userInputPanel.add(priceLabel);
        userInputPanel.add(priceTextField);
        priceTextField.setEditable(false);  // set price text field supaya tidak bisa diubah
        priceTextField.setText("Rp.5000.0"); // set nilai awal price text field jadi 5000

        userInputPanel.add(totalLabel);
        userInputPanel.add(totalPriceTextField);
        totalPriceTextField.setEditable(false); // set total price text supaya tidak bisa diubah

        // Tambahkan purchase button di panel kedua (buttonPanel)
        buttonPanel.add(purchaseButton);

        // Konfigurasi awal frame PurchaseProduct
        pack();
        setTitle("Purchase Product");
        setLocationRelativeTo(null); // Center the frame
        setSize(450,250);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);

        // Menambahkan listener untuk combo box, quantity text field,purchase button, dan window
        productList.addItemListener(new ItemChangeListener());
        quantityTextField.getDocument().addDocumentListener(new quantityListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Close frame saat ini dan kembali ke frame VendingMachine
                dispose();
                frameVending.updateLabel();
                frameVending.setVisible(true);
            }
        });
        purchaseButton.addActionListener(new PurchaseListener(pengguna,this));
    }

    // Listener untuk comboBox. Berguna untuk mengubah priceTextField ketika item yang dipilih di comboBox berubah
    private class ItemChangeListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED){
                Barang barangDipilih = (Barang) e.getItem();
                priceTextField.setText("Rp." + barangDipilih.getHarga());
                try{
                    if (Integer.parseInt(quantityTextField.getText()) > 0){
                        Double totalHarga = Double.parseDouble(priceTextField.getText().substring(3)) *
                                Long.parseLong(quantityTextField.getText());
                        totalPriceTextField.setText("Rp." + totalHarga);
                    } else
                        totalPriceTextField.setText("Rp.0.0");
                } catch (NumberFormatException numberFormatException){
                    totalPriceTextField.setText("");
                }

            }
        }
    }

    // Listener untuk quantityTextField. Berguna untuk mengubah totalPriceTextField ketika quantity berubah
    private class quantityListener implements DocumentListener{
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateLabel();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateLabel();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateLabel();
        }

        public void updateLabel(){
            try {
                if (Integer.parseInt(quantityTextField.getText()) > 0){
                    Double totalHarga = Double.parseDouble(priceTextField.getText().substring(3)) *
                            Long.parseLong(quantityTextField.getText());
                    totalPriceTextField.setText("Rp." + totalHarga);
                } else
                    totalPriceTextField.setText("Rp.0.0");
            } catch (NumberFormatException e){
                totalPriceTextField.setText("");
            }
        }
    }

    // Listener untuk purchase button. Berguna untuk menampilkan pesan ketika melakukan pembelian
    private class PurchaseListener implements ActionListener{
        private PurchaseProduct frame;
        private User pengguna;

        public PurchaseListener(User pengguna,PurchaseProduct frame){
            this.frame = frame;
            this.pengguna = pengguna;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (totalPriceTextField.getText().equals("") | totalPriceTextField.getText().equals("Rp.0.0")){
                // Jika quantity tidak valid
                JOptionPane.showMessageDialog(this.frame,"Maaf, jumlah barang yang Anda masukkan tidak valid!",
                        "Info",JOptionPane.ERROR_MESSAGE);
            } else{
                // Jika quantity valid
                Double hargaTotal = Double.parseDouble(totalPriceTextField.getText().substring(3));

                if (pengguna.getJumlahUang() >= hargaTotal){
                    // Jika uang pengguna cukup
                    this.pengguna.setJumlahUang(this.pengguna.getJumlahUang() - hargaTotal);
                    JOptionPane.showMessageDialog(this.frame,String.format("Berhasil!Kembalian Anda sebesar Rp.%.1f"
                                    ,(pengguna.getJumlahUang())),"Info",JOptionPane.INFORMATION_MESSAGE);
                    quantityTextField.setText("");

                } else{
                    // Jika uang pengguna tidak cukup
                    JOptionPane.showMessageDialog(this.frame,"Maaf,Uang Anda tidak cukup!",
                            "Info",JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }

}
