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
import pojo.Lop;
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
    private JButton importScoreButton = new JButton("Import bảng điểm");
    private JButton searchScoreButton = new JButton("Xem bảng điểm");
    private JComboBox petList = new JComboBox();

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

        ActionListener button_ImportScore_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lop = petList.getSelectedItem().toString();
                if (lop.isEmpty() || lop.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập lớp!!!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
                fc.addChoosableFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
                fc.showOpenDialog(importButton);
                try {
                    Scanner scanner = new Scanner(fc.getSelectedFile());
                    scanner.useDelimiter("\n");
                    ArrayList<Lop> lst = new ArrayList<Lop>();
                    while (scanner.hasNext()) {
                        String line = scanner.next();
                        String[] items = line.split(",");
                        if (items.length != 5) {
                            JOptionPane.showMessageDialog(null, "Format file không đúng!!!", "Thông tin lỗi",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        // kiem tra bat buoc
                        if (items[0].toString().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Mã sinh viên không được rỗng!!!", "Thông tin lỗi",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        // kiem tra length
                        if (items[0].toString().length() > 20) {
                            JOptionPane.showMessageDialog(null, "Mã sinh viên không vượt quá 20 ký tự!!!",
                                    "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        String diemGK = items[1].toString();
                        if (!isDouble(diemGK)) {
                            JOptionPane.showMessageDialog(null, "Điểm GK không đúng format!!!", "Thông tin lỗi",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        } else {
                            if (Double.parseDouble(diemGK) > 10) {
                                JOptionPane.showMessageDialog(null, "Điểm GK không được vượt quá 10!!!",
                                        "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        }
                        String diemCK = items[2].toString();
                        if (!isDouble(diemCK)) {
                            JOptionPane.showMessageDialog(null, "Điểm CK không đúng format!!!", "Thông tin lỗi",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        } else {
                            if (Double.parseDouble(diemCK) > 10) {
                                JOptionPane.showMessageDialog(null, "Điểm CK không được vượt quá 10!!!",
                                        "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        }
                        String diemKhac = items[3].toString();
                        if (!isDouble(diemKhac)) {
                            JOptionPane.showMessageDialog(null, "Điểm khác không đúng format!!!", "Thông tin lỗi",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        } else {
                            if (Double.parseDouble(diemKhac) > 10) {
                                JOptionPane.showMessageDialog(null, "Điểm khác không được vượt quá 10!!!",
                                        "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        }
                        String diemTK = items[4].toString();
                        if (!isDouble(diemTK)) {
                            JOptionPane.showMessageDialog(null, "Điểm tổng không đúng format!!!", "Thông tin lỗi",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        } else {
                            if (Double.parseDouble(diemTK) > 10) {
                                JOptionPane.showMessageDialog(null, "Điểm tổng không được vượt quá 10!!!",
                                        "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        }
                        Lop sv = new Lop(items[0].toString(), "", lop, 0, Double.parseDouble(diemGK),
                                Double.parseDouble(diemCK), Double.parseDouble(diemKhac), Double.parseDouble(diemTK), "");
                        lst.add(sv);
                    }
                    scanner.close();
                    var rs = LopDAO.themDiem(lst);
                    if (rs > 0) {
                        JOptionPane.showMessageDialog(null, "Import thành công!!!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
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
                    String lop = petList.getSelectedItem().toString();
                    new ManHinhDanhSachLop(lop);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };

        ActionListener button_SearchScore_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lop = petList.getSelectedItem().toString();
                    if (lop.isEmpty() || lop.isBlank()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập lớp!!!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    new ManHinhDanhSachDiem(lop);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };

        ActionListener button_Insert_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManHinhDangKySinhVien(uc, true);
                // frame.dispose();
            }
        };

        importButton.setBounds(10, 10, 200, 30);
        importButton.addActionListener(button_Import_CLick);
        panel.add(importButton);

        var petStrings = LopDAO.layDanhSachLop();
        petList = new JComboBox(petStrings);
        petList.setBounds(10, 40, 200, 30);
        panel.add(petList);

        insertButton.setBounds(210, 10, 200, 30);
        insertButton.addActionListener(button_Insert_CLick);
        panel.add(insertButton);

        searchButton.setBounds(210, 40, 200, 30);
        searchButton.addActionListener(button_Search_CLick);
        panel.add(searchButton);

        importScoreButton.setBounds(10, 70, 200, 30);
        importScoreButton.addActionListener(button_ImportScore_CLick);
        panel.add(importScoreButton);

        searchScoreButton.setBounds(210, 70, 200, 30);
        searchScoreButton.addActionListener(button_SearchScore_CLick);
        panel.add(searchScoreButton);

        frame.setVisible(true);
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}