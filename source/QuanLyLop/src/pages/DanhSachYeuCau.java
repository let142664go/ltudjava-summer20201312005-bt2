package pages;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class DanhSachYeuCau {
    private JFrame frame = new JFrame("Danh sách yêu cầu");
    DanhSachYeuCau(String lop) {
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
