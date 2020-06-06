package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import pojo.SinhVien;
import resources.HibernateUtil;

public class SinhVienDAO {
    public static List<SinhVien> layDanhSachSinhVien() {
        List<SinhVien> ds = new ArrayList<SinhVien>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT \"MA\", \"TEN\", \"GIOI_TINH\", \"CMND\" FROM public.\"SINH_VIEN\"";
            SQLQuery query = session.createSQLQuery(hql);
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                SinhVien emp = new SinhVien();
                emp.setMa(row[0].toString());
                emp.setTen(row[1].toString());
                emp.setGioiTinh(row[2].toString());
                emp.setCMND(row[3].toString());
                ds.add(emp);
            }
        } catch (HibernateException ex) {
            // Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
}
