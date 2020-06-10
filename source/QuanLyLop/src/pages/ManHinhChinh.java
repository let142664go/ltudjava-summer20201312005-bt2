package pages;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import daos.MonHocDAO;
import pojo.MonLop;

public class ManHinhChinh {
    private JFrame frame = new JFrame("Màn hình chính");
    private JPanel panel = new JPanel();
    private String uc = "";
    // Creating Sinh Vien button
    private JButton studentButton = new JButton("Sinh viên");
    // import thoi khoa bieu button
    private JButton importTKBButton = new JButton("Import thời khóa biểu");
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
        ActionListener button_TKB_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
                fc.addChoosableFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
                fc.showOpenDialog(importTKBButton);
                try {
                    Scanner scanner = new Scanner(fc.getSelectedFile());
                    scanner.useDelimiter("\n");
                    ArrayList<MonLop> lst = new ArrayList<MonLop>();
                    while (scanner.hasNext()) {
                        String line = scanner.next();
                        String[] items = line.split(",");
                        if (items.length != 4) {
                            JOptionPane.showMessageDialog(null, "Format file không đúng!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        // kiem tra bat buoc
                        if (items[0].toString().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Mã lớp không được rỗng!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (items[1].toString().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Mã môn học không được rỗng!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (items[2].toString().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Tên môn học không được rỗng!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        // kiem tra length
                        if (items[0].toString().length() > 20) {
                            JOptionPane.showMessageDialog(null, "Mã lớp không vượt quá 20 ký tự!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (items[1].toString().length() > 20) {
                            JOptionPane.showMessageDialog(null, "Mã môn học không vượt quá 20 ký tự!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (items[2].toString().length() > 100) {
                            JOptionPane.showMessageDialog(null, "Tên môn học không vượt quá 100 ký tự!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (items[3].toString().length() > 20) {
                            JOptionPane.showMessageDialog(null, "Phòng học không vượt quá 20 ký tự!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        MonLop sv = new MonLop(items[0].toString(), items[1].toString(), items[2].toString(), items[3].toString());
                        lst.add(sv);
                    }
                    scanner.close();
                    var rs = MonHocDAO.themThoiKhoaBieu(lst);
                    if (rs > 0) {
                        JOptionPane.showMessageDialog(null, "Import thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Import thất bại!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
        ActionListener button_Class_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhLop(uc);
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

        studentButton.setBounds(10, 20, 80, 25);
        studentButton.addActionListener(button_Student_CLick);
        panel.add(studentButton);

        classButton.setBounds(10, 50, 80, 25);
        classButton.addActionListener(button_Class_CLick);
        panel.add(classButton);

        subjectButton.setBounds(10, 80, 80, 25);
        subjectButton.addActionListener(button_Subject_CLick);
        panel.add(subjectButton);

        importTKBButton.setBounds(10, 110, 80, 25);
        importTKBButton.addActionListener(button_TKB_CLick);
        panel.add(importTKBButton);

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