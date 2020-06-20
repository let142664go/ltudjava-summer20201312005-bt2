package pages;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManHinhDanhSachYeuCau {
    private JFrame frame = new JFrame("Danh sách yêu cầu");
    ManHinhDanhSachYeuCau(String lop) {
        frame.setSize(450, 450);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        String column[] = { "Mã lớp", "Mã sinh viên", "Tên sinh viên" };
        // var list = LopDAO.layDanhSachSinhVien(lop);
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        // for (Lop lop2 : list) {
        //     Object[] rowData = { lop2.getMaLop(), lop2.getMaSV(), lop2.getTenSV() };
        //     tableModel.addRow(rowData);
        // }
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JTable jt = new JTable(tableModel);
        jt.setEnabled(false);
        jt.setBounds(10, 50, 400, 300);
        JScrollPane sp = new JScrollPane(jt);
        panel.add(sp);
        frame.add(panel);
        frame.setVisible(true);
    }
}
