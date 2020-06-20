package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.MonLop;
import resources.HibernateUtil;

public class MonHocDAO {
    public static List<MonLop> layTKB(String lop) {
        List<MonLop> ds = new ArrayList<MonLop>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT \"LOP_MON_HOC\".\"MA_LOP\",";
            hql += " \"LOP_MON_HOC\".\"MA_MON_HOC\",";
            hql += " \"MON_HOC\".\"TEN\",";
            hql += " \"LOP_MON_HOC\".\"PHONG_HOC\"";
            hql += " FROM public.\"LOP_MON_HOC\" left join public.\"MON_HOC\"";
            hql += " ON \"LOP_MON_HOC\".\"MA_MON_HOC\" = \"MON_HOC\".\"MA\"";
            hql += " WHERE \"LOP_MON_HOC\".\"MA_LOP\" = '" + lop + "' OR '" + lop + "' = ''";
            hql += " ORDER BY \"LOP_MON_HOC\".\"MA_LOP\";";
            SQLQuery query = session.createSQLQuery(hql);
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                MonLop tkb = new MonLop(row[0].toString(), row[1].toString(), row[2].toString(), row[3].toString());
                ds.add(tkb);
            }
        } catch (HibernateException ex) {
            // Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
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
                String rowMonHoc = "";
                if (rows.size() > 0) {
                    rowMonHoc = keyUpdateMonHoc.replace(":mamonhoc", sv.getMaMonHoc()).replace(":tenmonhoc", sv.getTenMonHoc());
                } else {
                    rowMonHoc = keyInsertMonHoc.replace(":mamonhoc", sv.getMaMonHoc()).replace(":tenmonhoc", sv.getTenMonHoc());
                }
                hqlInsertMonHoc = hqlInsertMonHoc+ rowMonHoc;
                hql = "SELECT '1' FROM public.\"LOP_MON_HOC\" WHERE \"MA_LOP\" = '" + sv.getMaMonHoc() + "' AND \"MA_MON_HOC\"='"+ sv.getTenMonHoc()+"'";
                rows = session.createSQLQuery(hql).list();
                String rowQuery = "";
                if (rows.size() > 0) {
                    rowQuery = keyInsertLopMonHoc.replace(":malop", sv.getMaLop()).replace(":mamonhoc", sv.getMaMonHoc()).replace(":phonghoc", sv.getPhongHoc());
                } else {
                    rowQuery = keyUpdateLopMonHoc.replace(":malop", sv.getMaLop()).replace(":mamonhoc", sv.getMaMonHoc()).replace(":phonghoc", sv.getPhongHoc());
                }
                hqlInsert = hqlInsert+ rowQuery;
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