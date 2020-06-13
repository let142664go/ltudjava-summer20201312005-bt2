package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Lop;
import resources.HibernateUtil;

public class LopDAO {
    public static Object[] layDanhSachLop() {
        Object[] ds = {};
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT DISTINCT \"MA_LOP\" FROM public.\"SINH_VIEN_LOP\" union select ''";
            SQLQuery query = session.createSQLQuery(hql);
            List<String> rows = query.list();
            ds = rows.toArray();
        } catch (HibernateException ex) {
            // Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static List<Lop> layDanhSachSinhVien(String lop) {
        List<Lop> ds = new ArrayList<Lop>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT \"SINH_VIEN_LOP\".\"MA_LOP\"";
            hql += ", \"SINH_VIEN_LOP\".\"MA_SINH_VIEN\"";
            hql += ", \"SINH_VIEN\".\"TEN\"";
            hql += "FROM public.\"SINH_VIEN_LOP\" left join public.\"SINH_VIEN\"";
            hql += "ON \"SINH_VIEN_LOP\".\"MA_SINH_VIEN\" = \"SINH_VIEN\".\"MA\"";
            hql += "WHERE \"SINH_VIEN_LOP\".\"MA_LOP\" = '" + lop + "' OR '" + lop + "' = '';";
            SQLQuery query = session.createSQLQuery(hql);
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                Lop emp = new Lop(row[1].toString(), row[2].toString(), row[0].toString(), 0, 0.0, 0.0, 0.0, 0.0);
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
                String hql = "SELECT 1 FROM public.\"SINH_VIEN_LOP\" WHERE \"MA_SINH_VIEN\" = '" + sv.getMaSV()
                        + "' AND \"MA_LOP\" = '" + sv.getMaLop() + "'";
                List<Object> rows = session.createSQLQuery(hql).list();
                for (Object row : rows) {
                    if ("1" == row.toString()) {
                        tx.rollback();
                        return -1;
                    }
                }
                String row = keyInfo.replace(":masv", sv.getMaSV()).replace(":malop", sv.getMaLop());
                hqlInsert = hqlInsert + row;
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