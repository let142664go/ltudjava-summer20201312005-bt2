package pages;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import daos.SinhVienDAO;
import pojo.SinhVien;

import java.awt.event.*;
import java.util.ArrayList;

public class ManHinhDangKySinhVien {
    private String uc = "";
    private Boolean isClass = false;
    private JFrame frame = null;
    private JTextField codeText = new JTextField(20);
    private JTextField nameText = new JTextField(100);
    private JTextField genText = new JTextField(20);
    private JTextField identText = new JTextField(20);
    private JTextField classText = new JTextField(20);

    private ActionListener button_Login_CLick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<SinhVien> lst = new ArrayList<SinhVien>();
                SinhVien sv = new SinhVien();
                sv.setMa(codeText.getText());
                sv.setTen(nameText.getText());
                sv.setGioiTinh(genText.getText());
                sv.setCMND(identText.getText());
                String lop = classText.getText();
                if (isClass && lop.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mã lớp chưa được nhập!!!", "Thông tin lỗi",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                if (sv.getMa().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mã sinh viên chưa được nhập!!!", "Thông tin lỗi",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                if (sv.getTen().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tên sinh viên chưa được nhập!!!", "Thông tin lỗi",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                lst.add(sv);
                var rs = SinhVienDAO.themSinhVien(lst);
                if (rs > 0) {
                    JOptionPane.showMessageDialog(null, "Lưu thành công!!!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Lưu thất bại!!!", "Thông tin lỗi",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // } finally {
                // frame.dispose();
            }
        }
    };

    public ManHinhDangKySinhVien(String uCode, Boolean isCl) {
        uc = uCode;
        isClass = isCl;
        if (isCl) {
            frame = new JFrame("Quản lý lớp");
        } else {
            frame = new JFrame("Quản lý sinh viên");
        }
        frame.setSize(350, 350);
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
        panel.setLayout(null);

        JLabel codeLabel = new JLabel("Mã");
        codeLabel.setBounds(10, 20, 80, 25);
        panel.add(codeLabel);

        codeText.setBounds(100, 20, 165, 25);
        panel.add(codeText);

        JLabel nameLabel = new JLabel("Tên");
        nameLabel.setBounds(10, 50, 80, 25);
        panel.add(nameLabel);

        nameText.setBounds(100, 50, 165, 25);
        panel.add(nameText);

        JLabel genLabel = new JLabel("Giới tính");
        genLabel.setBounds(10, 80, 80, 25);
        panel.add(genLabel);

        genText.setBounds(100, 80, 165, 25);
        panel.add(genText);

        JLabel identLabel = new JLabel("CMND");
        identLabel.setBounds(10, 110, 80, 25);
        panel.add(identLabel);

        identText.setBounds(100, 110, 165, 25);
        panel.add(identText);

        if (this.isClass) {
            JLabel classLabel = new JLabel("Lớp");
            classLabel.setBounds(10, 140, 80, 25);
            panel.add(classLabel);

            classText.setBounds(100, 140, 165, 25);
            panel.add(classText);
        }

        JButton saveButton = new JButton("Lưu");
        saveButton.setBounds(10, 170, 80, 25);
        saveButton.addActionListener(button_Login_CLick);
        panel.add(saveButton);
    }
}