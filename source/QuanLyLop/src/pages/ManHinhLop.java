package pages;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import daos.SinhVienDAO;
import daos.TaiKhoanDAO;
import pojo.SinhVien;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ManHinhLop {
    private String uc = "";
    private JFrame frame = new JFrame("Sinh viên");
    private JButton importButton = new JButton("Import Sinh viên vào lớp");
    private JButton insertButton = new JButton("Thêm Sinh viên vào lớp");

    public ManHinhLop(String uCode) {
        uc = uCode;
        frame.setSize(300, 300);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

        ActionListener button_Import_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(importButton);
                try {
                    Scanner scanner = new Scanner(fc.getSelectedFile());
                    scanner.useDelimiter("\n");
                    ArrayList<SinhVien> lst = new ArrayList<SinhVien>();
                    while (scanner.hasNext()) {
                        SinhVien sv = new SinhVien();
                        String line = scanner.next();
                        String[] items = line.split(",");
                        sv.setMa(items[0]);
                        sv.setTen(items[1]);
                        sv.setGioiTinh(items[2]);
                        sv.setCMND(items[3]);
                        lst.add(sv);
                    }
                    scanner.close();
                    var rs = SinhVienDAO.themSinhVien(lst);
                    if (rs > 0) {
                        JOptionPane.showMessageDialog(null, "Import thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Import thất bại!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                // } finally {
                //     frame.dispose();
                }
            }
        };

        ActionListener button_Insert_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DangKySinhVien(uc, true);
                // frame.dispose();
            }
        };

        importButton.setBounds(10, 30, 200, 30);
        importButton.addActionListener(button_Import_CLick);
        panel.add(importButton);

        insertButton.setBounds(10, 60, 200, 30);
        insertButton.addActionListener(button_Insert_CLick);
        panel.add(insertButton);

        frame.setVisible(true);
    }
    
}