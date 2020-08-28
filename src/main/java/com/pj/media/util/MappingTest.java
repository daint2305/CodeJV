/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.util;

import com.learning.model.Bill;
import com.learning.model.BillDetail;
import com.learning.model.Goods;
import com.learning.model.Shop;
import com.learning.model.Staff;
import com.learning.model.StaffDetail;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

/**
 *
 * @author thanhlong
 */
public class MappingTest {

    public static void testCreateStaff() {
        Session session = null;
        Transaction tx = null;
        Staff o = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Staff f = new Staff();
            f.setStaffCode("S8888");
            f.setStaffName("Name Test");

            StaffDetail detail = new StaffDetail();
            detail.setAcademicLevel("trung cap");
            detail.setAge(23);
            detail.setCountry("VN");
            detail.setHobby("eat");

            Shop shop = new Shop();
            shop.setId(1l);

            detail.setStaff(f);
            f.setDetail(detail);
            f.setShop(shop);
            session.save(f);

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

    public static void testDeletetaff(long staffId) {
        Session session = null;
        Transaction tx = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Staff.class);
            criteria.add(Restrictions.eq("id", staffId));

            Staff f = (Staff) criteria.uniqueResult();

            if (f != null) {
                session.delete(f);
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

    public static void testCreateBill() {
        Session session = null;
        Transaction tx = null;
        Staff o = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Bill bill = new Bill();
            bill.setShopId(1L);
            bill.setStaffId(1L);
            bill.setTotalPrice(26000L);
            bill.setCreateTime(new Date());

            Set<BillDetail> detail = new HashSet<>();
            BillDetail a = new BillDetail();

            Goods mytom = new Goods();
            mytom.setId(1l);

            a.setGoods(mytom);
            a.setGoodsCount(1);
            a.setPriceDetail(6000L);
            a.setBill(bill);

            detail.add(a);

            BillDetail b = new BillDetail();

            Goods xucxich = new Goods();
            xucxich.setId(2l);
            b.setGoods(xucxich);

            b.setGoodsCount(2);
            b.setPriceDetail(10000L);
            b.setBill(bill);

            detail.add(b);
            bill.setBillDetail(detail);

            session.save(bill);

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

    public static void testDeleteBill(long billId) {
        Session session = null;
        Transaction tx = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Bill.class);
            criteria.add(Restrictions.eq("id", billId));

            Bill f = (Bill) criteria.uniqueResult();

            if (f != null) {
                session.delete(f);
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

    public static void testdOneToOne() {
        Session session = null;
        Transaction tx = null;
        Staff o = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Staff.class);
            criteria.add(Restrictions.eq("id", 1L));
            criteria.setMaxResults(1);
            o = (Staff) criteria.uniqueResult();
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

        if (o != null) {
            if (o.getShop() != null) {
                System.out.println("Shop name : " + o.getShop().getShopName());
            }

            if (o.getDetail() != null) {
                System.out.println("Hobby : " + o.getDetail().getHobby());
            }
        }

    }

    public static void testOneToMany() {
        Session session = null;
        Transaction tx = null;
        Shop shop = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Shop.class);
            criteria.add(Restrictions.eq("id", 1L));
            List<Shop> shops = criteria.list();
            if (shops != null && !shops.isEmpty()) {
                shop = shops.get(0);
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

        if (shop != null) {
            Set<Staff> staffs = shop.getStaffs();
            if (staffs != null) {
                for (Staff o : staffs) {
                    System.out.println(" Staff " + o.getStaffName());

                }

            }
        } else {
            System.out.println("staffs null");
        }

    }

    public static void testManyToMany(Long billId) {
        Session session = null;
        Transaction tx = null;
        Bill bill = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Bill.class);
            criteria.add(Restrictions.eq("id", billId));
            List<Bill> lst = criteria.list();
            if (lst != null && !lst.isEmpty()) {
                bill = lst.get(0);
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

        if (bill != null) {
            Set<Goods> set = bill.getGoodsLst();
            if (set != null && !set.isEmpty()) {
               for(Goods goods : set){
                   System.out.println(goods.getGoodsName());
               }
            }

        }

    }

    public static List<BillDetail> getBillDEtail(Long billId) {

        List<BillDetail> lst = new ArrayList<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            StringBuilder sql = new StringBuilder();
            sql.append(" select a.bill_id  billId ,a.BILL_DETAIL_ID billDetailId ");
            sql.append(" , a.GOODS_ID goodsId , b.goods_name goodsName ,b.goods_code  goodsCode "
                    + ", a.GOODS_COUNT goodsCount , a.PRICE_DETAIL priceDetail ");
            sql.append(" from BILL_DETAIL a ");
            sql.append(" left join goods b on a.goods_id =b.id ");
            sql.append(" where a.bill_id = :billId ");
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.setLong("billId", billId);
            sqlQuery.addScalar("billId", new LongType());
            sqlQuery.addScalar("billDetailId", new LongType());
            sqlQuery.addScalar("goodsId", new LongType());
            sqlQuery.addScalar("goodsName", new StringType());
            sqlQuery.addScalar("goodsCode", new StringType());
            sqlQuery.addScalar("goodsCount", new IntegerType());
            sqlQuery.addScalar("priceDetail", new LongType());
            sqlQuery.setResultTransformer(Transformers.aliasToBean(BillDetail.class));
            lst = sqlQuery.list();
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
        return lst;
    }

    public static void main(String[] args) {

        //testdOneToOne();
         testOneToMany();
        //testManyToMany(2L);
        //getBillDEtail(1L);

        //testCreateStaff();
        //testDeletetaff(23L);
        //testCreateBill();
        //testDeleteBill(5L);
    }

}
