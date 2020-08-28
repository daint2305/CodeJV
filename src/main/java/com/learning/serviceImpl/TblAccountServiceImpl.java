/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.serviceImpl;

import com.learning.model.BillDetail;
import com.learning.model.StaffDetail;
import com.learning.model.TblAccount;
import com.learning.model.TblRole;
import com.learning.service.TblAccountService;
import com.pj.media.util.MysqlHibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Thanh_Long
 */
@Repository
public class TblAccountServiceImpl implements TblAccountService {

    @Override
    public TblAccount getAccountByUser(String username) {
        Session session = null;
        Transaction tx = null;
        TblAccount o = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TblAccount.class);
            criteria.add(Restrictions.eq("username", username));
            criteria.setMaxResults(1);
            o = (TblAccount) criteria.uniqueResult();
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
        return o;
    }

    @Override
    public List<TblRole> getRoleList(Long groupId) {
        Session session = null;
        Transaction tx = null;
        List<TblRole> lst = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            StringBuilder sql = new StringBuilder();
            sql.append(" select b.role_id as roleId, b.role_code as roleCode ,b.role_name as roleName from tbl_group_role a ");     
            sql.append(" INNER JOIN tbl_role b on a.role_id = b.role_id ");
            sql.append(" where a.group_id = :groupId");

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.setLong("groupId", groupId);       
            sqlQuery.addScalar("roleId", new LongType());
            sqlQuery.addScalar("roleCode", new StringType());
            sqlQuery.addScalar("roleName", new StringType());
        
            
            sqlQuery.setResultTransformer(Transformers.aliasToBean(TblRole.class));
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

}
