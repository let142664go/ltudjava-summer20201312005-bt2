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

public class DangKySinhVien {
    private String uc = "";
    private JFrame frame = new JFrame("Quản lý sinh viên");
    private JTextField codeText = new JTextField(20);
    private JTextField nameText = new JTextField(100);
    private JTextField genText = new JTextField(20);
    private JTextField identText = new JTextField(20);

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
                lst.add(sv);
                boolean rs = SinhVienDAO.themSinhVien(lst);
                if (rs) {
                    JOptionPane.showMessageDialog(null, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Lưu thất bại!!!", "Thông tin lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            // } finally {
            //     frame.dispose();
            }
        }
    };

    public DangKySinhVien(String uCode){
        uc = uCode;
        frame.setSize(350, 400);
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

        JButton saveButton = new JButton("Lưu");
        saveButton.setBounds(10, 140, 80, 25);
        saveButton.addActionListener(button_Login_CLick);
        panel.add(saveButton);
    }
}