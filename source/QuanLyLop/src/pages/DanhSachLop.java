package pages;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import daos.LopDAO;

public class DanhSachLop {
    private JFrame frame = new JFrame("Danh sách lớp");
    DanhSachLop(String lop) {
        frame.setSize(450, 450);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        var list = LopDAO.layDanhSachSinhVien(lop);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);
        String data[][] = { { "17CTT1", "1312005", "Nguyễn Đức Anh" }, { "17CTT1", "1413555", "Nguyễn Đức Tín" },
                { "17CTT1", "17110124", "Nguyễn Đức Thiện" } };
        String column[] = { "Mã lớp", "Mã sinh viên", "Tên sinh viên" };
        JTable jt = new JTable(data, column);
        jt.setEnabled(false);
        jt.setBounds(10, 50, 400, 300);
        JScrollPane sp = new JScrollPane(jt);
        frame.add(sp);
        frame.setVisible(true);
    }
}
