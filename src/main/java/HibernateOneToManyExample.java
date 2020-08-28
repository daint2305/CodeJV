
import com.learning.model.A;
import com.learning.model.B;
import com.learning.model.Person;
import com.pj.media.util.MysqlHibernateUtil;
import java.util.ArrayList;
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
public class HibernateOneToManyExample {
    
    public void saveParent() {
        Session session = null;
        Transaction tx = null;
        
        try {
            
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            A parent = new A();
            parent.setName("M");
            
            Set<B> childLst = new HashSet<>();
            
            B child = new B();
            child.setA(parent);
            child.setName("N");
            childLst.add(child);
            
            parent.setChildLst(childLst);
            
            session.save(parent);
            
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
    
    public void saveChild() {
        Session session = null;
        Transaction tx = null;
        
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            A parent = new A();
            parent.setId(33L);
            
            B child = new B();
            child.setA(parent);
            child.setName("33 Child");
            
            session.save(child);
            
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
    
    public A findParentByChildName(String childName) {
        Session session = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(A.class);
            
            criteria.createAlias("childLst", "child");
            criteria.add(Restrictions.eq("child.name", childName).ignoreCase());
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
    
    public List<B> findChildByParentName(String parentName) {
        Session session = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(B.class);
            
            criteria.createAlias("a", "parent");
            criteria.add(Restrictions.eq("parent.name", parentName).ignoreCase());
            List<B> lst = criteria.list();
            
            return lst;
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return null;
    }
    
    public void deleteParentWithChild(Long parentId) {
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
    
    public void deleteParentWithOutChild(Long parentId) {
        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            A parent = new A();
            parent.setId(parentId);
            
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
        HibernateOneToManyExample example = new HibernateOneToManyExample();
        //example.saveParent();
        //example.saveChild();

        /*
        A parent = example.findParent(1L);
        if (parent != null) {
            Set<B> childs = parent.getChildLst();
            for (B child : childs) {
                System.out.println("Id : "+child.getId()+" - Name : "+child.getName());
            }
        }
         */
 /*
        A parent = example.findParentByChildName("Bứ");
        if (parent != null) {
            System.out.println("parent Name " + parent.getName());
        }
         */
 /*
        List<B> lst = example.findChildByParentName("long");
        if (lst != null) {
            for (B child : lst) {
                System.out.println("Id : "+child.getId()+" - Name : "+child.getName());
            }
        }
         */
        //example.deleteParentWithChild(2L);
        
        example.deleteParentWithOutChild(3L);
    }
    
}
