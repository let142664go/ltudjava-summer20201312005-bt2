package pages;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import daos.MonHocDAO;
import pojo.MonLop;

public class ManHinhChiTietTKB {
    ManHinhChiTietTKB(String lop) {
        JFrame frame = new JFrame("Thời khóa biểu lớp: " + lop);
        frame.setSize(450, 450);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        String column[] = { "STT", "Mã môn", "Tên môn", "Phòng học" };
        var list = MonHocDAO.layTKB(lop);
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        int stt = 1;
        for (MonLop lop2 : list) {
            Object[] rowData = { stt, lop2.getMaMonHoc(), lop2.getTenMonHoc(), lop2.getPhongHoc() };
            tableModel.addRow(rowData);
            stt++;
        }
        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);
        JTable jt = new JTable(tableModel);
        jt.setEnabled(false);
        jt.setBounds(10, 50, 400, 300);
        JScrollPane sp = new JScrollPane(jt);
        frame.add(sp);
        frame.setVisible(true);
    }
}
