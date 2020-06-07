package pages;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.event.*;

import daos.TaiKhoanDAO;


public class ManHinhDoiMatKhau {
    private JFrame frame = new JFrame("Đổi mật khẩu");
    private JPasswordField oldPassText = new JPasswordField(20);
    private JPasswordField newPassText = new JPasswordField(20);
    private JPasswordField confirmPassText = new JPasswordField(20);
    private String uCode = "";

    private ActionListener button_Save_CLick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String oldPw = oldPassText.getText();
            String newPw = newPassText.getText();
            String cofPw = confirmPassText.getText();
            if (newPw.isBlank() || newPw.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (!newPw.equals(cofPw)) {
                JOptionPane.showMessageDialog(null, "Mật khẩu mới không khớp!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String uc = TaiKhoanDAO.kiemTraTaiKhoan(uCode, oldPw);
            if (!uCode.equals(uc)) {
                JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                boolean rs = TaiKhoanDAO.capNhatMatKhau(uCode, newPw);
                if (rs) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        }
    };

    public ManHinhDoiMatKhau(String uc){
        this.uCode = uc;
        System.out.println("Hello, World!");
        // Creating instance of JFrame
        // Setting the width and height of frame
        frame.setSize(350, 200);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        JLabel oldPaLabel = new JLabel("Mật khẩu cũ");
        oldPaLabel.setBounds(10, 20, 120, 25);
        panel.add(oldPaLabel);

        oldPassText.setBounds(100, 20, 165, 25);
        panel.add(oldPassText);

        JLabel newPaLabel = new JLabel("Mật khẩu mới");
        newPaLabel.setBounds(10, 50, 120, 25);
        panel.add(newPaLabel);

        newPassText.setBounds(100, 50, 165, 25);
        panel.add(newPassText);

        JLabel confirmPaLabel = new JLabel("Xác nhận mật khẩu");
        confirmPaLabel.setBounds(10, 80, 120, 25);
        panel.add(confirmPaLabel);

        confirmPassText.setBounds(100, 80, 165, 25);
        panel.add(confirmPassText);

        // Creating save button
        JButton loginButton = new JButton("Lưu");
        loginButton.setBounds(10, 110, 80, 25);
        loginButton.addActionListener(button_Save_CLick);
        panel.add(loginButton);
    }
}