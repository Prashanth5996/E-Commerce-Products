package com.orders.service;

import java.util.List;

import com.orders.model.Orders;

public interface OrderService {
	
	public String saveOrders(Orders orders);
	
	public String updateOrders(Orders orders,int orderId);
	
	public String deleteOrders(int orderId);
	
	public String  getOneOrders(int orderId);
	
	public List<Orders> getAllOrders();
	
//public Orders saveOrders(Orders orders);
//	
//	public Orders updateOrders(Orders orders,Integer id);
//	
//	public Orders deleteOrders(Integer id);
//	
//	public Orders getOneOrders(Integer id);
//	
//	public List<Orders> getAllOrders();
}
