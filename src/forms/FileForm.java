package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileForm extends JFrame{
    private JButton decryptFileJButton;
    private JPanel panel;
    private JButton backJButton;
    private JButton cifrarButton;
    private JButton descifrarButton;

    private MenuForm menuForm;

    public FileForm(MenuForm menuForm){
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