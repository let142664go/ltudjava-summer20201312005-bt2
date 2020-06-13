package pages;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import daos.LopDAO;
import daos.SinhVienDAO;
import pojo.SinhVien;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ManHinhLop {
    private String uc = "";
    private JFrame frame = new JFrame("Lớp");
    private JButton importButton = new JButton("Import Sinh viên vào lớp");
    private JButton insertButton = new JButton("Thêm Sinh viên vào lớp");
    private JButton searchButton = new JButton("Xem danh sách lớp");
    private JTextField searchText = new JTextField(20);

    public ManHinhLop(String uCode) {
        uc = uCode;
        frame.setSize(450, 450);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

        ActionListener button_Import_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
                fc.addChoosableFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
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
                        JOptionPane.showMessageDialog(null, "Import thành công!!!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Import thất bại!!!", "Thông tin lỗi",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };

        ActionListener button_Search_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lop = searchText.getText();
                    new DanhSachLop(lop);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
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

        importButton.setBounds(10, 10, 200, 30);
        importButton.addActionListener(button_Import_CLick);
        panel.add(importButton);

        var petStrings = LopDAO.layDanhSachLop();
        JComboBox petList = new JComboBox(petStrings);

        petList.setBounds(10, 40, 200, 30);
        panel.add(petList);

        insertButton.setBounds(210, 10, 200, 30);
        insertButton.addActionListener(button_Insert_CLick);
        panel.add(insertButton);

        searchButton.setBounds(210, 40, 200, 30);
        searchButton.addActionListener(button_Search_CLick);
        panel.add(searchButton);

        frame.setVisible(true);
    }

}