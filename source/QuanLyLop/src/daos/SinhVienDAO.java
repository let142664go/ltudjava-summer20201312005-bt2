package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public static int themSinhVien(ArrayList<SinhVien> svs) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int result = 1;
        try {
            Transaction tx = session.beginTransaction();
            String keyInfo = "INSERT INTO public.\"SINH_VIEN\"(\"MA\", \"TEN\", \"GIOI_TINH\", \"CMND\") VALUES (':masv', ':tensv', ':gioitinh', ':cmnd');";
            String hqlInsert = "";
            for (int i = 0; i < svs.size(); i++) {
                SinhVien sv = svs.get(i);
                String hql = "SELECT 1 FROM public.\"SINH_VIEN\" WHERE \"MA\" = '" + sv.getMa() + "'";
                List<Object> rows = session.createSQLQuery(hql).list();
                for (Object row : rows) {
                    if ("1" == row.toString()) {
                        tx.rollback();
                        return -1;
                    }
                }
                String row = keyInfo.replace(":masv", sv.getMa()).replace(":tensv", sv.getTen()).replace(":gioitinh", sv.getGioiTinh()).replace(":cmnd", sv.getCMND());
                hqlInsert = hqlInsert+ row;
            }
            int updatedEntities = session.createNativeQuery(hqlInsert).executeUpdate();
            result = updatedEntities > 0 ? 0 : 1;
            if (result == 0) {
                tx.rollback();
                return result;
            }
            result = TaiKhoanDAO.themTaiKhoan(svs);
            if (result == 0) {
                tx.rollback();
                return result;
            }
            tx.commit();
        } catch (HibernateException ex) {
            // Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result == 0 ? 0 : 1;
    }
}
