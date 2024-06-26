import backend.DbConnection;
import backend.Victim;
import forms.MenuForm;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        JFrame frame = new MenuForm(dbConnection);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}