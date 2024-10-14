package vn.hiuz.services;

import java.util.List;

import vn.hiuz.entity.Category;

public interface ICategoryService {
	List<Category> findByCategoryname(String catname);

	List<Category> findAll();

	Category findById(int cateid);

	void delete(int cateid) throws Exception;

	void update(Category category);

	void insert(Category category);

}
