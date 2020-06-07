package pages;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManHinhChinh {
    private JFrame frame = new JFrame("Màn hình chính");
    private JPanel panel = new JPanel();
    private String uc = "";
    // Creating Sinh Vien button
    private JButton studentButton = new JButton("Sinh viên");
    // Creating Lop button
    private JButton classButton = new JButton("Lớp");
    // Creating Mon Hoc button
    private JButton subjectButton = new JButton("Môn học");
    // Creating Dang Xuat button
    private JButton logoutButton = new JButton("Đăng xuất");
    // Creating Doi Mat Khau button
    private JButton changePassButton = new JButton("Đổi mật khẩu");

    public ManHinhChinh(String ucode) {
        uc = ucode;
        // Setting the width and height of frame
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        ActionListener button_Student_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhSinhVien(uc);
            }
        };
        ActionListener button_Class_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhChinh(uc);
            }
        };
        ActionListener button_Subject_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhChinh(uc);
            }
        };
        ActionListener button_Logout_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ManHinhDangNhap();
            }
        };
        ActionListener button_ChangePass_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhDoiMatKhau(uc);
            }
        };

        studentButton.setBounds(10, 120, 80, 25);
        studentButton.addActionListener(button_Student_CLick);
        panel.add(studentButton);

        classButton.setBounds(40, 120, 80, 25);
        classButton.addActionListener(button_Class_CLick);
        panel.add(classButton);

        subjectButton.setBounds(70, 120, 80, 25);
        subjectButton.addActionListener(button_Subject_CLick);
        panel.add(subjectButton);

        logoutButton.setBounds(100, 120, 80, 25);
        logoutButton.addActionListener(button_Logout_CLick);
        panel.add(logoutButton);

        changePassButton.setBounds(130, 120, 80, 25);
        changePassButton.addActionListener(button_ChangePass_CLick);
        panel.add(changePassButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}