import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentSignUp {
    private JTextField txtCreateUsername;
    private JTextField txtCreatePassword;
    private JTextField txtConfirmPassword;
    private JButton btnCreateAcc;
    private JButton btnSignUpReturn;
    private JPanel studentSignUpPanel;

    public StudentSignUp() {
        btnSignUpReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new JFrame("STUDENT / STAFF LOGIN");
                    studentLogIn form = new studentLogIn();

                    JPanel panel = form.getStudentLoginPanel();

                    if (panel == null) {
                        JOptionPane.showMessageDialog(null,
                                "Error: getStudentLoginPanel returned null. Check studentLogIn.java!",
                                "Null Panel Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    frame.setContentPane(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(studentSignUpPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getStudentSignUpPanel() {
        return studentSignUpPanel;
    }
}
