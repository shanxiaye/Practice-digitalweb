package com.digitalweb.dao;

import java.util.List;

import com.digitalweb.model.Product;

public interface ProductDao {
	public boolean add(Product p);
	public boolean update(Product p);
	public boolean delete(int id);
	public List<Product> list();
	public List<Product> search(String sql);
	public Product getProductById(int id);
}
