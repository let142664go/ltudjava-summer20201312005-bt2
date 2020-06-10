package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Lop;
import pojo.SinhVien;
import resources.HibernateUtil;

public class LopDAO {
    public static List<SinhVien> layDanhSachSinhVien(String lop) {
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

    public static int themSinhVien(ArrayList<Lop> svs) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int result = 1;
        try {
            Transaction tx = session.beginTransaction();
            String keyInfo = "INSERT INTO public.\"SINH_VIEN_LOP\"(\"MA_SINH_VIEN\", \"MA_LOP\", \"TRANG_THAI\") VALUES (':masv', ':malop', '1');";
            String hqlInsert = "";
            for (int i = 0; i < svs.size(); i++) {
                Lop sv = svs.get(i);
                String hql = "SELECT 1 FROM public.\"SINH_VIEN_LOP\" WHERE \"MA_SINH_VIEN\" = '" + sv.getMaSV() + "' AND \"MA_LOP\" = '" + sv.getMaLop() + "'";
                List<Object> rows = session.createSQLQuery(hql).list();
                for (Object row : rows) {
                    if ("1" == row.toString()) {
                        tx.rollback();
                        return -1;
                    }
                }
                String row = keyInfo.replace(":masv", sv.getMaSV()).replace(":malop", sv.getMaLop());
                hqlInsert = hqlInsert+ row;
            }
            int updatedEntities = session.createNativeQuery(hqlInsert).executeUpdate();
            result = updatedEntities > 0 ? 0 : 1;
            if (result == 0) {
                tx.rollback();
                return result;
            }
            // result = TaiKhoanDAO.themTaiKhoan(svs);
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