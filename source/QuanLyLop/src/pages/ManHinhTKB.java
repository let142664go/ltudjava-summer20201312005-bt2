package pages;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import daos.LopDAO;
import daos.MonHocDAO;
import pojo.MonLop;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ManHinhTKB {
    private String uc = "";
    private JFrame frame = new JFrame("Thời khóa biểu");
    private JButton importButton = new JButton("Import Thời khóa biểu");
    private JButton seachButton = new JButton("Xem Thời khóa biểu");
    private JComboBox petList = new JComboBox();

    public ManHinhTKB(String uCode) {
        uc = uCode;
        frame.setSize(300, 320);
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

        ActionListener button_Search_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lop = petList.getSelectedItem().toString();
                if (lop.isEmpty() || lop.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lớp!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                new ManHinhChiTietTKB(lop);
                // frame.dispose();
            }
        };

        importButton.setBounds(10, 10, 160, 30);
        importButton.addActionListener(button_Import_CLick);
        panel.add(importButton);

        var petStrings = LopDAO.layDanhSachLop();
        petList = new JComboBox(petStrings);
        petList.setBounds(10, 40, 80, 30);
        panel.add(petList);

        seachButton.setBounds(90, 40, 160, 30);
        seachButton.addActionListener(button_Search_CLick);
        panel.add(seachButton);

        frame.setVisible(true);
    }
}