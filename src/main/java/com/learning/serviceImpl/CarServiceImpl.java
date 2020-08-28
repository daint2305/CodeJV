package com.learning.serviceImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.learning.model.Oto;
import com.learning.service.CarService;
import com.pj.media.util.MysqlHibernateUtil;


@Component
public class CarServiceImpl implements CarService{
	public CarServiceImpl() {
		
	}

	@Override
	public void addData(Oto o) {
		Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
		
	}

	@Override
	public void delete(Oto o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Oto getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateData(Oto o) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		//Oto o1 = new Oto(1L, 1L, 9999,  , 1000000L);
	}
	
}
