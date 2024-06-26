package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileForm extends JFrame{
    private JButton selectFileJButton;
    private JPanel panel;
    private JButton backJButton;
    private JButton encryptJButton;
    private JButton decryptJButton;
    private JLabel fileNameJLabel;

    private MenuForm menuForm;

    private File file;

    public FileForm(MenuForm menuForm){
        super("FileForm");

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
        selectFileJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file = getFile();
                if (file != null) {
                    JOptionPane.showMessageDialog(null, "Documento cargado: " + file.getName());
                }
            }
        });
        encryptJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file == null) {
                    JOptionPane.showMessageDialog(null, "Seleccione un archivo");
                    return;
                }

                boolean isSuccess = menuForm.encrypt(file);

                if (isSuccess) {
                    JOptionPane.showMessageDialog(null, "Archivo encriptado");
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error. Intente de nuevo");
                }

            }
        });
        decryptJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file == null) {
                    JOptionPane.showMessageDialog(null, "Seleccione un archivo");
                    return;
                }

                boolean isSuccess = menuForm.decrypt(new File(file.getAbsolutePath()));

                if (isSuccess) {
                    JOptionPane.showMessageDialog(null, "Archivo descrifrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error. Intente de nuevo");
                }
            }
        });
    }

    public void goToMenuForm (){
        setVisible(false);
        this.menuForm.setVisible(true);
    }

    private File getFile () {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            fileNameJLabel.setText(file.getName());
            return file;
        }
        return null;
    }
}