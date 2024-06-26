package forms;

import backend.Victim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VictimForm extends JFrame {
    private JTextField victimNameJText;
    private JTextField decryptionKeyJText;
    private JTextField encryptedKeyJText;
    private JTextField victimEmailJText;
    private JTextField victimIpJText;
    private JPanel panel;
    private JButton registerJButton;
    private JButton backJButton;

    private MenuForm menuForm;

    public VictimForm(MenuForm menuForm){
        super("Ratona Zoe");

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        this.menuForm = menuForm;

        backJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            goToMenuForm();
            }
        });

        registerJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerVictim();
            }
        });
    }

    public void goToMenuForm (){
        setVisible(false);
        this.menuForm.setVisible(true);
    }

    public void registerVictim () {
        String name = victimNameJText.getText().trim();
        String email = victimEmailJText.getText().trim();
        String ip = victimIpJText.getText().trim();
        String encryptKey = encryptedKeyJText.getText().trim();
        String decryptKey = decryptionKeyJText.getText().trim();

        boolean isIncomplete = name.isEmpty() || email.isEmpty() || ip.isEmpty() || encryptKey.isEmpty() || decryptKey.isEmpty();
        if (isIncomplete) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            return;
        }

        Victim victim = new Victim(name, email, ip, encryptKey, decryptKey);

        boolean isSuccess = menuForm.saveVictim(victim);

        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "Victima guardada");
            cleanInputs();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo guardar a la victima");
        }
    }

    public void cleanInputs () {
        victimNameJText.setText("");
        victimEmailJText.setText("");
        victimIpJText.setText("");
        encryptedKeyJText.setText("");
        decryptionKeyJText.setText("");
    }
}