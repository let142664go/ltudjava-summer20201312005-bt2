package pages;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import daos.TaiKhoanDAO;

import java.awt.event.*;
import java.util.Scanner;

public class ManHinhSinhVien {
    private String uc = "";
    private JFrame frame = new JFrame("Sinh viên");
    private JButton importButton = new JButton("Import Sinh viên");
    private JButton insertButton = new JButton("Thêm Sinh viên");

    public ManHinhSinhVien(String uCode) {
        uc = uCode;
        frame.setSize(600, 300);
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
                    while (scanner.hasNext()) {
                        System.out.println(scanner.next());
                    }
                    scanner.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    frame.dispose();
                }
            }
        };

        ActionListener button_Insert_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        };

        importButton.setBounds(10, 30, 160, 25);
        importButton.addActionListener(button_Import_CLick);
        panel.add(importButton);

        insertButton.setBounds(180, 30, 160, 25);
        insertButton.addActionListener(button_Insert_CLick);
        panel.add(insertButton);

        frame.setVisible(true);
    }
}