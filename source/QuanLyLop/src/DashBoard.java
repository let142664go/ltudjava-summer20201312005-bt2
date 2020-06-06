import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DashBoard {
    private JFrame frame = new JFrame("Màn hình chính");
    private String uc = "";

    DashBoard(String uc) {
        this.uc = uc;
        // Setting the width and height of frame
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        ActionListener button_Student_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("xxxxxxxxx");
                frame.dispose();
                new DashBoard(uc);
            }
        };
        ActionListener button_Class_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("xxxxxxxxx");
                frame.dispose();
                new DashBoard(uc);
            }
        };
        ActionListener button_Subject_CLick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("xxxxxxxxx");
                frame.dispose();
                new DashBoard(uc);
            }
        };
        // Creating Sinh Vien button
        JButton studentButton = new JButton("Sinh viên");
        studentButton.setBounds(10, 80, 80, 25);
        studentButton.addActionListener(button_Student_CLick);
        panel.add(studentButton);
        // Creating Lop button
        JButton classButton = new JButton("Lớp");
        classButton.setBounds(40, 80, 80, 25);
        classButton.addActionListener(button_Class_CLick);
        panel.add(classButton);
        // Creating Mon Hoc button
        JButton subjectButton = new JButton("Môn học");
        subjectButton.setBounds(70, 80, 80, 25);
        subjectButton.addActionListener(button_Subject_CLick);
        panel.add(subjectButton);
        frame.add(panel);
        frame.setVisible(true);
    }
}