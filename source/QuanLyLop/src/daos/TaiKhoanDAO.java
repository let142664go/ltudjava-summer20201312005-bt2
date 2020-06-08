package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.SinhVien;
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

    public static boolean capNhatMatKhau(String uc, String ps) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Boolean result = true;
        try {
            Transaction tx = session.beginTransaction();
            String hqlUpdate = "UPDATE public.\"TAI_KHOAN\" SET \"MAT_KHAU\"='" + ps + "' WHERE \"MA\" = '" + uc + "';";
            int updatedEntities = session.createNativeQuery(hqlUpdate).executeUpdate();
            result = 1 == updatedEntities;
            tx.commit();
        } catch (HibernateException ex) {
            // Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean themTaiKhoan(ArrayList<SinhVien> svs) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Boolean result = true;
        try {
            Transaction tx = session.beginTransaction();
            String keyInfo = "INSERT INTO public.\"TAI_KHOAN\"(\"MA\", \"MAT_KHAU\", \"LICENSE_ID\") VALUES (':masv', ':masv', 'SV');";
            String hqlInsert = "";
            for (int i = 0; i < svs.size(); i++) {
                SinhVien sv = svs.get(i);
                String row = keyInfo.replace(":masv", sv.getMa());
                hqlInsert = hqlInsert+ row;
            }
            int updatedEntities = session.createNativeQuery(hqlInsert).executeUpdate();
            result = updatedEntities > 0;
            tx.commit();
        } catch (HibernateException ex) {
            // Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;
    }
}
