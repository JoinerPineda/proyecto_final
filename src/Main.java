import forms.MenuForm;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new MenuForm();
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}