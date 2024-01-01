import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MoneyInput extends JFrame {
    // Data Fields / Atribut
    private JLabel labelInputUang = new JLabel("Enter the amount of money: ");
    private JTextField textFieldUang = new JTextField();
    private JButton submitButton = new JButton("Submit");
    private User pengguna;
    private VendingMachine frameVending;

    // Constructor
    public MoneyInput(User pengguna,VendingMachine frameVending){
        Container contentPane= getContentPane();
        this.pengguna = pengguna;
        this.frameVending = frameVending;

        // Membuat Panel utama yang berisi semua panel atau component lain
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(panel);

        // Menambahkan setiap komponen ke panel utama
        panel.add(labelInputUang,BorderLayout.NORTH);
        panel.add(textFieldUang,BorderLayout.CENTER);
        panel.add(submitButton,BorderLayout.SOUTH);

        // Menambahkan listener pada submit button
        submitButton.addActionListener(new SubmitListener());

        // Konfigurasi awal frame MoneyInput
        pack();
        setTitle("Money Input");
        setLocationRelativeTo(null);
        setSize(350,250);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                frameVending.setVisible(true);
            }
        });
        setVisible(true);
    }

    // Listener untuk submitButton. Berguna untuk menambah uang milik user sekaligus berpindah ke frame VendingMachine
    private class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pengguna.addJumlahUang(Double.parseDouble(textFieldUang.getText()));
            dispose();
            frameVending.updateLabel();   // Mengubah label frameVending machine sesuai uang milik user setelah ditambah
            frameVending.setVisible(true);

        }
    }
}
