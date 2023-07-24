package com.poly.DAOImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.poly.bean.MyItem;
import com.poly.dao.ReportDAO;
import com.poly.util.HibernateUtil;
@Repository
public class ReportDAOImpl implements ReportDAO {

    @Override
    public List<MyItem> reportReceipt(Date date, int limit) {
        List<MyItem> list = new ArrayList<>();
        for (int i = limit - 1; i >= 0; i--) {
            Date d = subDays(date, i);
            MyItem myItem = new MyItem();
            myItem.setTime(covertD2S(d));
            myItem.setValue(countItemByDate(d));
            list.add(myItem);
        }
        return list;
    }

    private int countItemByDate(Date date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT count(*) FROM receipt WHERE DATE_FORMAT(receiptDate,'%Y-%m-%d') = :date");
            query.setDate("date", date);
            Long obj = (Long) query.uniqueResult();
            transaction.commit();
            return obj.intValue();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return 0;
    }

    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date subDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    private String covertD2S(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyy");
        return df.format(date);
    }

}
