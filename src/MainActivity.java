import resources.MainOperation;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class MainActivity {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            new MainOperation();
        } catch (Exception e) {
            int result = JOptionPane.showConfirmDialog(null, "Error while loading Main Activity" + e.getMessage(),
                    "Confirm to exit",
                    JOptionPane.DEFAULT_OPTION);
            if (result == 0)
                System.exit(0);
        }
    }
}