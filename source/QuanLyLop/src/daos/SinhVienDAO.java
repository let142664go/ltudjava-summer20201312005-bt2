package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.SinhVien;
import resources.HibernateUtil;

public class SinhVienDAO {
    public static List<SinhVien> layDanhSachSinhVien() {
        List<SinhVien> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT MA, TEN, GIOI_TINH, CMND FROM public.\"SINH_VIEN\"";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
}
