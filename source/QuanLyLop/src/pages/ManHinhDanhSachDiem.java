package pages;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import daos.LopDAO;
import pojo.Lop;

public class ManHinhDanhSachDiem {
    ManHinhDanhSachDiem(String lop) {
        JFrame frame = new JFrame("Bảng điểm lớp: " + lop);
        frame.setSize(900, 450);
        frame.setLocationRelativeTo(null);
        String column[] = { "STT", "Mã sinh viên", "Tên sinh viên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Đậu/Rớt" };
        var list = LopDAO.layDanhSachSinhVien(lop);
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        int i = 1;
        for (Lop lop2 : list) {
            Object[] rowData = { i, lop2.getMaSV(), lop2.getTenSV(), lop2.getDiemGK(), lop2.getDiemCK(), lop2.getDiemKhac(), lop2.getDiemTong(), lop2.getDau() };
            tableModel.addRow(rowData);
            i++;
        }
        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);
        JTable jt = new JTable(tableModel);
        jt.setEnabled(false);
        jt.setBounds(10, 50, 600, 300);
        JScrollPane sp = new JScrollPane(jt);
        frame.add(sp);
        frame.setVisible(true);
    }
}
