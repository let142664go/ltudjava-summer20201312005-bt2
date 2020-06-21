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
    // import thoi khoa bieu button
    private JButton TKBButton = new JButton("Thời khóa biểu");
    // Creating Lop button
    private JButton classButton = new JButton("Lớp");
    // Creating Mon Hoc button
    private JButton subjectButton = new JButton("Môn học");
    // Creating Danh sach yeu cau button
    private JButton requestButton = new JButton("Danh sách chờ duyệt");
    // Creating Dang Xuat button
    private JButton logoutButton = new JButton("Đăng xuất");
    // Creating Doi Mat Khau button
    private JButton changePassButton = new JButton("Đổi mật khẩu");
    // Creating Xem Diem Khau button
    private JButton searchScoreButton = new JButton("Xem điểm");

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
        ActionListener button_TKB_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhTKB(uc);
            }
        };
        ActionListener button_Class_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhLop(uc);
            }
        };
        ActionListener button_Score_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhLop(uc);
            }
        };
        ActionListener button_Request_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhDanhSachYeuCau(uc);
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

        if (uc.equals("giaovu")) {
            studentButton.setBounds(10, 20, 80, 25);
            studentButton.addActionListener(button_Student_CLick);
            panel.add(studentButton);
    
            classButton.setBounds(10, 50, 80, 25);
            classButton.addActionListener(button_Class_CLick);
            panel.add(classButton);
    
            subjectButton.setBounds(10, 80, 80, 25);
            subjectButton.addActionListener(button_Subject_CLick);
            panel.add(subjectButton);
    
            TKBButton.setBounds(10, 110, 80, 25);
            TKBButton.addActionListener(button_TKB_CLick);
            panel.add(TKBButton);
    
            requestButton.setBounds(10, 140, 80, 25);
            requestButton.addActionListener(button_Request_CLick);
            panel.add(requestButton);
        } else {
            searchScoreButton.setBounds(10, 140, 80, 25);
            searchScoreButton.addActionListener(button_Score_CLick);
            panel.add(searchScoreButton);
        }

        logoutButton.setBounds(100, 20, 80, 25);
        logoutButton.addActionListener(button_Logout_CLick);
        panel.add(logoutButton);

        changePassButton.setBounds(100, 50, 80, 25);
        changePassButton.addActionListener(button_ChangePass_CLick);
        panel.add(changePassButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}