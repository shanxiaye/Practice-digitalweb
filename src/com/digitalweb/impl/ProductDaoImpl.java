package com.digitalweb.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.digitalweb.dao.ProductDao;
import com.digitalweb.model.Product;

public class ProductDaoImpl extends SuperOpr implements ProductDao {
	public boolean add(Product p) {
		sql = "insert into product_info(code,name,type,brand,pic,num,price,sale,intro)values(?,?,?,?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getCode());
			psmt.setString(2, p.getName());
			psmt.setString(3, p.getType());
			psmt.setString(4, p.getBrand());
			psmt.setString(5, p.getPic());
			psmt.setDouble(6, p.getNum());
			psmt.setDouble(7, p.getPrice());
			psmt.setDouble(8, p.getSale());
			psmt.setString(9, p.getIntro());
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}

	public boolean update(Product p) {
		sql = "update product_info set code=?, name=?,type=?,brand=?,pic=?,num=?,price=?,sale=?,intro=? where id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getCode());
			psmt.setString(2, p.getName());
			psmt.setString(3, p.getType());
			psmt.setString(4, p.getBrand());
			psmt.setString(5, p.getPic());
			psmt.setDouble(6, p.getNum());
			psmt.setDouble(7, p.getPrice());
			psmt.setDouble(8, p.getSale());
			psmt.setString(9, p.getIntro());
			psmt.setInt(10, p.getId());
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}

	public boolean delete(int id) {
		sql = "update product_info set status = 0 where id = "+id;
		try {
			psmt = con.prepareStatement(sql);
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}

	public ArrayList<Product> list() {
		sql = "select * from product_info where status = 1";
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				Product p = new Product();
				p.setBrand(rs.getString("brand"));
				p.setCode(rs.getString("code"));
				p.setId(rs.getInt("id"));
				p.setIntro(rs.getString("intro"));
				p.setName(rs.getString("name"));
				p.setNum(rs.getInt("num"));
				p.setPic(rs.getString("pic"));
				p.setPrice(rs.getDouble("price"));
				p.setSale(rs.getDouble("sale"));
				p.setStatus(1);
				p.setType(rs.getString("type"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Product getProductById(int id) {
		sql = "select * from product_info where status = 1 and id = "+id;
		Product p = new Product();
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				p.setBrand(rs.getString("brand"));
				p.setCode(rs.getString("code"));
				p.setId(rs.getInt("id"));
				p.setIntro(rs.getString("intro"));
				p.setName(rs.getString("name"));
				p.setNum(rs.getInt("num"));
				p.setPic(rs.getString("pic"));
				p.setPrice(rs.getDouble("price"));
				p.setSale(rs.getDouble("sale"));
				p.setStatus(1);
				p.setType(rs.getString("type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public List<Product> search(String sql) {
		List<Product> list = new ArrayList<Product>();
		try {
			System.out.println(sql);
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				Product p = new Product();
				p.setBrand(rs.getString("brand"));
				p.setCode(rs.getString("code"));
				p.setId(rs.getInt("id"));
				p.setIntro(rs.getString("intro"));
				p.setName(rs.getString("name"));
				p.setNum(rs.getInt("num"));
				p.setPic(rs.getString("pic"));
				p.setPrice(rs.getDouble("price"));
				p.setSale(rs.getDouble("sale"));
				p.setStatus(1);
				p.setType(rs.getString("type"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean importProduct(List<Product> pList) {
		sql = "insert into product_info(code,name,type,brand,pic,num,price,sale,intro)values(?,?,?,?,?,?,?,?,?)";
		try {
			con.setAutoCommit(false);
			psmt = con.prepareStatement(sql);
			for(Product p:pList){
				psmt.setString(1, p.getCode());
				psmt.setString(2, p.getName());
				psmt.setString(3, p.getType());
				psmt.setString(4, p.getBrand());
				psmt.setString(5, p.getPic());
				psmt.setDouble(6, p.getNum());
				psmt.setDouble(7, p.getPrice());
				psmt.setDouble(8, p.getSale());
				psmt.setString(9, p.getIntro());
				psmt.addBatch();
			}
			int[] batchRow = psmt.executeBatch();
			for(int i:batchRow){
				row += i;
			}
			if(row==pList.size())con.commit();
			con.setAutoCommit(true);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row==pList.size()?true:false;
	}
}
