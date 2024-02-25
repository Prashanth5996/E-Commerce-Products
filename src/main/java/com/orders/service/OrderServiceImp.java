package com.orders.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.orders.model.Customer;
import com.orders.model.Orders;
import com.orders.model.Products;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private WebClient webClient;
    
    @Autowired
    public JdbcTemplate jt;
    
    private final String SQL_FIND_ORDER = "SELECT * FROM orders WHERE orderId = ?";
    private final String SQL_DELETE_ORDER = "DELETE FROM orders WHERE orderId = ?";
    private final String SQL_UPDATE_ORDERS = "UPDATE orders SET customerId=?, productId=?, quantity=?, totalPrice=?, status=?, address=? WHERE orderId=?";
    private final String SQL_GET_ALL = "SELECT * FROM orders";
    private final String SQL_INSERT_ORDER = "INSERT INTO orders(orderId, customerId, productId, quantity, totalPrice, status, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public String saveOrders(Orders orders) {
       try {
    	   Products product = webClient.get().uri("http://localhost:5001/product/get/" + orders.getProductId())
                   .retrieve().bodyToMono(Products.class).block();
           Customer customer = webClient.get().uri("http://localhost:5003/customer/get/" + orders.getCustomerId())
                 .retrieve().bodyToMono(Customer.class).block();
           if (product != null && customer != null) {
           	orders.setProduct(product);
           	orders.setCustomer(customer);
               orders.setAddress(customer.getAddress());
               Double totalQuantity = (double) orders.getQuantity();
               Double totalPrice = product.getPrice() * totalQuantity;
               orders.setTotalPrice(totalPrice);
               orders.setStatus("1");
               jt.update(SQL_INSERT_ORDER, orders.getOrderId(), orders.getCustomerId(), orders.getProductId(),
                       orders.getQuantity(), orders.getTotalPrice(), orders.getStatus(), orders.getAddress());
               return "Orders Data Saved Successfully...!";
           } else {
               orders.setStatus("0");
               return "Failed to Save Orders Data...!";
           }
       } catch (Exception e) {
    	   e.printStackTrace();
    	   return "Failed to Save Orders Data...!";
       }
    }

    @Override
    public String updateOrders(Orders orders, int orderId) {
        try {
            Orders existingOrder = webClient.get().uri("http://localhost:5002/order/get/" + orderId)
                    .retrieve().bodyToMono(Orders.class).block();
            if (existingOrder != null) {
                Products eProduct = existingOrder.getProduct();
                Customer eCustomer = existingOrder.getCustomer();
                Products uproduct = webClient.get().uri("http://localhost:5001/product/get/" + orders.getProductId())
                        .retrieve().bodyToMono(Products.class).block();
                Customer ucustomer = webClient.get().uri("http://localhost:5003/customer/get/" + orders.getCustomerId())
                        .retrieve().bodyToMono(Customer.class).block();
                if (uproduct != null && ucustomer != null) {
                    orders.setAddress(ucustomer.getAddress());
                    Double totalQuantity = (double) orders.getQuantity();
                    Double totalPrice = uproduct.getPrice() * totalQuantity;
                    orders.setTotalPrice(totalPrice);
                    orders.setStatus("1");

                    orders.setProduct(uproduct);
                    orders.setCustomer(ucustomer);
                    jt.update(SQL_UPDATE_ORDERS, ucustomer.getCustomerId(), uproduct.getProductId(),
                            orders.getQuantity(), orders.getTotalPrice(), orders.getStatus(), orders.getAddress(),
                            orderId);
                    return "Orders Data Updated Successfully...!";
                } else {
                    orders.setStatus("0");
                    return "Failed to Update Orders Data...!";
                }
            } else {
                orders.setStatus("0");
                return "Failed to Fetch existingOrder Data...!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to Update Orders Data...!";
        }
    }

	@Override
	public String deleteOrders(int orderId) {
		try {
			int result = jt.update(SQL_DELETE_ORDER, orderId);
			if(result >0) {
				return "Orders Data Deleted Successfully...!";
			}else {
				return "Failed to Delete Orders Id Not Found...!";
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
			 return "Failed to Delete Orders Id Not Found...!";
		}
		
	}

	@Override
	public String getOneOrders(int orderId) {
		try {
	        Orders orders = jt.queryForObject(SQL_FIND_ORDER, new BeanPropertyRowMapper<>(Orders.class), orderId);
	        if (orders != null) {
	            Products product = webClient.get().uri("http://localhost:5001/product/get/" + orders.getProductId())
	                    .retrieve().bodyToMono(Products.class).block();
	            Customer customer = webClient.get().uri("http://localhost:5003/customer/get/" + orders.getCustomerId())
	                    .retrieve().bodyToMono(Customer.class).block();
	            if (product != null && customer != null) {
	                orders.setProduct(product);
	                orders.setCustomer(customer);
	            }
	        }
	        return " "+orders;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Invalid orderId No Record Found...!!";
	    }
    }
	

	@Override
    public List<Orders> getAllOrders() {
		try {
			List<Orders> ordersList = jt.query(SQL_GET_ALL, new BeanPropertyRowMapper<>(Orders.class));
	        for (Orders orders : ordersList) {
	        	 Products product = webClient.get().uri("http://localhost:5001/product/get/" + orders.getProductId())
	                     .retrieve().bodyToMono(Products.class).block();
	             Customer customer = webClient.get().uri("http://localhost:5003/customer/get/" + orders.getCustomerId())
	                     .retrieve().bodyToMono(Customer.class).block();
	             if (product != null && customer != null) {
	                 orders.setProduct(product);
	                 orders.setCustomer(customer);
	             }
	        }
	        return ordersList;
		} catch (Exception e) {
			e.printStackTrace();
            return new ArrayList<>();
		}
    }

    
//    List<Orders> orderlist = new ArrayList<Orders>();
//
//    @Override
//    public Orders saveOrders(Orders orders) {
//        Products product = webClient.get().uri("http://localhost:5001/product/get/" + orders.getOrderId())
//                .retrieve().bodyToMono(Products.class).block();
//        Customer customer = webClient.get().uri("http://localhost:5003/customer/get/" + orders.getOrderId())
//                .retrieve().bodyToMono(Customer.class).block();
//        if (product != null && customer != null) {
//            orders.setProduct(product);
//            orders.setCustomer(customer);
//            orders.setAddress(customer.getAddress());
//            Double totalQuantity = (double) orders.getQuantity();
//            Double totalPrice = product.getPrice() * totalQuantity;
//            orders.setTotalPrice(totalPrice);
//            orders.setStatus("1");
//            orderlist.add(orders);
//            return orders;
//        } else {
//            orders.setStatus("0");
//            return orders;
//        }
//    }
//
//    @Override
//    public Orders updateOrders(Orders orders, Integer id) {
//        for (Orders order : orderlist) {
//            if (order.getOrderId().equals(id)) {
//                order.setQuantity(orders.getQuantity());
//                order.setTotalPrice(orders.getTotalPrice());
//                order.setAddress(orders.getAddress());
//                order.setStatus(orders.getStatus());
//                return order;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Orders deleteOrders(Integer id) {
//        Orders o = null;
//        for (Orders order : orderlist) {
//            if (order.getOrderId().equals(id)) {
//                o = order;
//                orderlist.remove(order);
//                break;
//            }
//        }
//        return o;
//    }
//
//    @Override
//    public Orders getOneOrders(Integer id) {
//        Products product = webClient.get().uri("http://localhost:5001/product/get/" + id)
//                .retrieve().bodyToMono(Products.class).block();
//        Customer customer = webClient.get().uri("http://localhost:5003/customer/get/" + id)
//                .retrieve().bodyToMono(Customer.class).block();
//        Orders order = orderlist.stream()
//                .filter(o -> o.getOrderId().equals(id))
//                .findFirst()
//                .orElse(null);
//        if (order != null && product != null && customer != null) {
//            order.setProduct(product);
//            order.setCustomer(customer);
//            return order;
//        }
//        return null;
//    }
//
//    @Override
//    public List<Orders> getAllOrders() {
//        return orderlist;
//    }
}