package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VictimForm extends JFrame {
    private JTextField victimNameJText;
    private JTextField decryptionKey;
    private JTextField encryptedKey;
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

    }

    public void goToMenuForm (){
        setVisible(false);
        this.menuForm.setVisible(true);
    }

}

