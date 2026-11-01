import javax.swing.*;

public class main {
    private JButton btnStudentStaff;
    private JButton btnDriver;
    private JButton btnGuest;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");

        main form = new main();
        frame.setContentPane(form.mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 640);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
