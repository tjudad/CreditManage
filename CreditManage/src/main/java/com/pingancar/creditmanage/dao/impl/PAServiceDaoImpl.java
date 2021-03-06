package com.pingancar.creditmanage.dao.impl;

import com.pingancar.creditmanage.dao.PAServiceDao;
import com.pingancar.creditmanage.pojo.PAServicePojo;
import com.pingancar.creditmanage.util.myenum.PAServiceField;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;
/**
 *Created by system on Tue Sep 09 11:17:13 CST 2014
 */
public class PAServiceDaoImpl implements PAServiceDao {

	SessionFactory sessionFactory;
	HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public HibernateTemplate getHibernateTemplate() {
		if (null == hibernateTemplate) {
			hibernateTemplate = new HibernateTemplate(sessionFactory);
		}
		return hibernateTemplate;
	}

    @Override
    public List<PAServicePojo> queryPAService(List<PAServiceField> paServiceFieldsList, List<String> valuesList) {
        String query = "from PAServicePojo temp where ";
        for(int i = 0; i < paServiceFieldsList.size(); i++){
            if( i != 0 ){
                query += " and ";
            }
            if(valuesList.get(i).isEmpty())
                continue;
            query += "temp.";
            query += paServiceFieldsList.get(i).toString().toLowerCase();
            query += " = ";
            query += valuesList.get(i).toString();
        }

        return  (List<PAServicePojo>) getHibernateTemplate().find(query);
    }

    @Override
    public List<String> getAllTags(String paserviceinfoid) {
        List<String> tagList = new ArrayList<String>();
        List<PAServicePojo> paList = this.findByPaserviceinfoid(paserviceinfoid);
        if(paList != null){
              for(int i=0; i<paList.size(); i++){
                  if(!tagList.contains(paList.get(i).getTag1()))
                    tagList.add(paList.get(i).getTag1());
              }
        }
        return tagList;
    }

    public PAServicePojo findById(Integer id){
		return (PAServicePojo)getHibernateTemplate().get(PAServicePojo.class, id);
	}

	public List<PAServicePojo> findAll(){
		String query = "from PAServicePojo";
		return (List<PAServicePojo>)(getHibernateTemplate().find(query));
	}

	public Integer save(PAServicePojo paservice){
		return (Integer)getHibernateTemplate().save(paservice);
	}

	public void delete(PAServicePojo paservice){
		getHibernateTemplate().delete(paservice);
	}

	public void update(PAServicePojo paservice){

		getHibernateTemplate().update(paservice);
	}

	public List<PAServicePojo> findByPaserviceinfoid(String paserviceinfoid){
		String query = "from PAServicePojo temp where temp.paserviceinfoid = ?";
		return (List<PAServicePojo>)(getHibernateTemplate().find(query, paserviceinfoid));
	}

	public List<PAServicePojo> findByPaserviceid(String paserviceid){
		String query = "from PAServicePojo temp where temp.paserviceid = ?";
		return (List<PAServicePojo>)(getHibernateTemplate().find(query, paserviceid));
	}

	public List<PAServicePojo> findByType(String type){
		String query = "from PAServicePojo temp where temp.type = ?";
		return (List<PAServicePojo>)(getHibernateTemplate().find(query, type));
	}

	public List<PAServicePojo> findByTag1(String tag1){
		String query = "from PAServicePojo temp where temp.tag1 = ?";
		return (List<PAServicePojo>)(getHibernateTemplate().find(query, tag1));
	}

	public List<PAServicePojo> findByLeftnum(Integer leftnum){
		String query = "from PAServicePojo temp where temp.leftnum = ?";
		return (List<PAServicePojo>)(getHibernateTemplate().find(query, leftnum));
	}

	public List<PAServicePojo> findByAmount(Integer amount){
		String query = "from PAServicePojo temp where temp.amount = ?";
		return (List<PAServicePojo>)(getHibernateTemplate().find(query, amount));
	}

	public List<PAServicePojo> findByCreditcost(Double creditcost){
		String query = "from PAServicePojo temp where temp.creditcost = ?";
		return (List<PAServicePojo>)(getHibernateTemplate().find(query, creditcost));
	}

	public List findBySqlSentence(String queryString){
		return getHibernateTemplate().find(queryString);
	}
	public List findBySqlSentence(String queryString, Object value){
		return getHibernateTemplate().find(queryString, value);
	}

	public List findBySqlSentence(String queryString, Object... values){
		return getHibernateTemplate().find(queryString, values);
	}

}