package com.digitalweb.model;

import java.io.Serializable;

public class Rank implements Serializable{
	private int id;
	private String name;
	private int total;
	private double money;
	public Rank() {	}
	public Rank(int id, String name, int total, double money) {
		super();
		this.id = id;
		this.name = name;
		this.total = total;
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
}
