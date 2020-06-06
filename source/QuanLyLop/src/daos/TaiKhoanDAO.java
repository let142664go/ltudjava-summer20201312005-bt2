package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import pojo.TaiKhoan;
import resources.HibernateUtil;

public class TaiKhoanDAO {
    public static List<TaiKhoan> layDanhSachTaiKhoan() {
        List<TaiKhoan> ds = new ArrayList<TaiKhoan>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT \"MA\", \"MAT_KHAU\", \"LICENSE_ID\" FROM public.\"TAI_KHOAN\";";
            SQLQuery query = session.createSQLQuery(hql);
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                TaiKhoan us = new TaiKhoan();
                us.setMa(row[0].toString());
                us.setPass(row[1].toString());
                us.setLicsen(row[2].toString());
                ds.add(us);
            }
        } catch (HibernateException ex) {
            // Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }

    public static String kiemTraTaiKhoan(String uc, String ps) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String val = "";
        try {
            String hql = "SELECT \"MA\" FROM public.\"TAI_KHOAN\" WHERE \"MA\" = '" + uc + "' AND \"MAT_KHAU\" = '" + ps
                    + "'";
            hql = String.format(hql, uc, ps);
            SQLQuery query = session.createSQLQuery(hql);
            List<Object> rows = query.list();
            for (Object row : rows) {
                val = row.toString();
            }
        } catch (HibernateException ex) {
            // Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return val;
    }
}
