package pages;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.*;

import daos.TaiKhoanDAO;
public class ManHinhDangNhap {
    private JFrame frame = new JFrame("Quản lý sinh viên");
    private JTextField userText = new JTextField(20);
    private JPasswordField passText = new JPasswordField(20);

    private ActionListener button_Login_CLick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String uCo = userText.getText();
            String uPw = passText.getText();
            String uc = TaiKhoanDAO.kiemTraTaiKhoan(uCo, uPw);
            if (!uCo.equals(uc)) {
                JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng", "title", JOptionPane.INFORMATION_MESSAGE);
            } else {
                frame.dispose();
                new ManHinhChinh(uc);
            }
        }
    };

    public ManHinhDangNhap(){
        System.out.println("Hello, World!");
        // Creating instance of JFrame
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        /*
         * Creating panel. This is same as a div tag in HTML We can create several
         * panels and add them to specific positions in a JFrame. Inside panels we can
         * add text fields, buttons and other components.
         */
        JPanel panel = new JPanel();
        // adding panel to frame
        frame.add(panel);
        /*
         * calling user defined method for adding components to the panel.
         */
        placeComponents(panel);

        // Setting the frame visibility to true
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        /*
         * We will discuss about layouts in the later sections of this tutorial. For now
         * we are setting the layout to null
         */
        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("User");
        /*
         * This method specifies the location and size of component. setBounds(x, y,
         * width, height) here (x,y) are cordinates from the top left corner and
         * remaining two arguments are the width and height of the component.
         */
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        /*
         * Creating text field where user is supposed to enter user name.
         */
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        // Same process for password label and text field.
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        /*
         * This is similar to text field but it hides the user entered data and displays
         * dots instead to protect the password like we normally see on login screens.
         */
        passText.setBounds(100, 50, 165, 25);
        panel.add(passText);

        // Creating login button
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(button_Login_CLick);
        panel.add(loginButton);
    }    
}
