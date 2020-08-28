
import com.learning.model.A;
import com.learning.model.B;
import com.learning.model.C;
import com.pj.media.util.MysqlHibernateUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thanhlong
 */
public class HibernateOneToOneExample {
    public void save(){
          Session session = null;
        Transaction tx = null;
        
        try {
            
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            A a = new A();
            a.setName("C parent");
            
            C detail = new C();
            detail.setDetail("Ozawa");
            
            a.setC(detail);
            detail.setA(a);
            
            session.save(a);
            
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
    
     public A findParent(Long parentId) {
        Session session = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(A.class);
            criteria.add(Restrictions.eq("id", parentId));
            criteria.setMaxResults(1);
            
            List<A> lst = criteria.list();
            
            if (lst != null && !lst.isEmpty()) {
                return lst.get(0);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return null;
    }
     
     public A findParentByDetail(String detail) {
        Session session = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(A.class);
          
            
            criteria.createAlias("c", "c");
            criteria.add(Restrictions.ilike("c.detail", detail,MatchMode.ANYWHERE));
            
            criteria.setMaxResults(1);
            
            List<A> lst = criteria.list();
            
            if (lst != null && !lst.isEmpty()) {
                return lst.get(0);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return null;
    }
     
     public void deleteParentWithDetail(Long parentId) {
        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            A parent = this.findParent(parentId);
            
            if (parent != null) {
                session.delete(parent);
            }
            
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
    
    public static void main(String[] args) {
        HibernateOneToOneExample example = new HibernateOneToOneExample();
        //example.save();
        
        /*
        A parent = example.findParent(5L);
        if (parent != null) {
            C detail = parent.getC();
            if(detail!=null){
                System.out.println("id : "+ detail.getId()+" - Detail : "+detail.getDetail());
            }
        }
        */
        
         /*
        A parent = example.findParentByDetail("ozawa");
        if (parent != null) {
            C detail = parent.getC();
            if(detail!=null){
                System.out.println("id : "+ detail.getId()+" - Detail : "+detail.getDetail());
            }
        }
        */
         
         example.deleteParentWithDetail(5L);
       
        
    }
    
}
