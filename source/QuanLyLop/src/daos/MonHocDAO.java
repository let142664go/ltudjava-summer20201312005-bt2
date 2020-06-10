package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.MonLop;
import resources.HibernateUtil;

public class MonHocDAO {
    public static int themThoiKhoaBieu(ArrayList<MonLop> svs) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int result = 1;
        try {
            Transaction tx = session.beginTransaction();
            String keyInsertLopMonHoc = "INSERT INTO public.\"LOP_MON_HOC\"(\"MA_LOP\", \"MA_MON_HOC\", \"PHONG_HOC\") VALUES (':malop', ':mamonhoc', ':phonghoc');";
            String keyUpdateLopMonHoc = "UPDATE public.\"LOP_MON_HOC\" SET \"PHONG_HOC\"=':phonghoc' WHERE \"MA_LOP\"=':malop' AND \"MA_MON_HOC\"=':mamonhoc';";
            String hqlInsert = "";
            String keyInsertMonHoc = "INSERT INTO public.\"MON_HOC\"(\"MA\", \"TEN\") VALUES (':mamonhoc', ':tenmonhoc');";
            String keyUpdateMonHoc = "UPDATE public.\"MON_HOC\" SET \"TEN\"=':tenmonhoc' WHERE \"MA\" = ':mamonhoc';";
            String hqlInsertMonHoc = "";
            for (int i = 0; i < svs.size(); i++) {
                MonLop sv = svs.get(i);
                String hql = "SELECT '1' FROM public.\"MON_HOC\" WHERE \"MA\" = '" + sv.getMaMonHoc() + "'";
                List<String> rows = session.createSQLQuery(hql).list();
                for (String row : rows) {
                    String rowMonHoc = "";
                    if ("1" == row) {
                        rowMonHoc = keyUpdateMonHoc.replace(":mamonhoc", sv.getMaMonHoc()).replace(":tenmonhoc", sv.getTenMonHoc());
                    } else {
                        rowMonHoc = keyInsertMonHoc.replace(":mamonhoc", sv.getMaMonHoc()).replace(":tenmonhoc", sv.getTenMonHoc());
                    }
                    hqlInsertMonHoc = hqlInsertMonHoc+ rowMonHoc;
                }
                hql = "SELECT '1' FROM public.\"LOP_MON_HOC\" WHERE \"MA_LOP\" = '" + sv.getMaMonHoc() + "' AND \"MA_MON_HOC\"='"+ sv.getTenMonHoc()+"'";
                rows = session.createSQLQuery(hql).list();
                for (String row : rows) {
                    String rowQuery = "";
                    if ("1" == row) {
                        rowQuery = keyInsertLopMonHoc.replace(":malop", sv.getMaLop()).replace(":mamonhoc", sv.getMaMonHoc()).replace(":phonghoc", sv.getPhongHoc());
                    } else {
                        rowQuery = keyUpdateLopMonHoc.replace(":malop", sv.getMaLop()).replace(":mamonhoc", sv.getMaMonHoc()).replace(":phonghoc", sv.getPhongHoc());
                    }
                    hqlInsert = hqlInsert+ rowQuery;
                }
            }
            int updatedEntities = session.createNativeQuery(hqlInsert).executeUpdate();
            result = updatedEntities;
            if (result == 0) {
                tx.rollback();
                return result;
            }
            updatedEntities = session.createNativeQuery(hqlInsertMonHoc).executeUpdate();
            result = updatedEntities;
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