package vn.hiuz.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.hiuz.configs.JPAConfig;
import vn.hiuz.dao.ICategoryDao;
import vn.hiuz.entity.Category;


public class CategoryDao implements ICategoryDao {
	@Override
	public void insert(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			 trans.begin();
			 enma.persist(category);
			 trans.commit();
		} catch (Exception e) {
			 e.printStackTrace();
			 trans.rollback();
			 throw e;
		}finally {
			 enma.close();
			 }		 
	}
	@Override
	public void update(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			 trans.begin();
			 enma.merge(category);
			 trans.commit();
		} catch (Exception e) {
			 e.printStackTrace();
			 trans.rollback();
			 throw e;
		}finally {
			 enma.close();
			 }		 
	}
	@Override
	public void delete(int cateid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			 trans.begin();
			 //
			 Category category = enma.find(Category.class, cateid);
			 if(category != null) {
				 enma.remove(category);
			 }else {
				 throw new Exception(" không nhìn thấy");
			 }
			 trans.commit();
		} catch (Exception e) {
			 e.printStackTrace();
			 trans.rollback();
			 throw e;
		}finally {
			 enma.close();
			 }		 
	}
	@Override
	public Category findById(int cateid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Category category = enma.find(Category.class,cateid);
		return category;
	}
	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll",Category.class);
		return query.getResultList();
	}
	@Override
	public List<Category> findByCategoryname(String catname){
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "Select c From Category c Where c.catname like : catname";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("catname","%" + catname+ "%");
		return query.getResultList();
	}
	
}
