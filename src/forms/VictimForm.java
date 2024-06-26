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
    private JList victimsJList;
    private JButton getVictimsJButton;
    private MenuForm menuForm;

    private DefaultListModel listModel;
    public VictimForm(MenuForm menuForm){
        super("Ratona Zoe");

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        this.listModel = new DefaultListModel();
        this.menuForm = menuForm;
        victimsJList.setModel(listModel);


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
                fillList();
            }
        });
        getVictimsJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillList();
            }
        });
    }

    public void goToMenuForm (){
        setVisible(false);
        this.menuForm.setVisible(true);
    }

    private void registerVictim () {
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

    private void cleanInputs () {
        victimNameJText.setText("");
        victimEmailJText.setText("");
        victimIpJText.setText("");
        encryptedKeyJText.setText("");
        decryptionKeyJText.setText("");
    }

    private void fillList () {
        listModel.clear();
        var victims = menuForm.getVictims();
        for (Victim victim: victims) {
            listModel.addElement(victim.getId() + " " + victim.getName());
        }
    }
}