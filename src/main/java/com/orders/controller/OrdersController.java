package com.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orders.model.Orders;
import com.orders.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrdersController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/save")
    public String saveOrders(@RequestBody  Orders orders) {
		if(orders !=null) {
			return orderService.saveOrders(orders);
		}else {
			return "Invalid input for saving orders...!!";
		}
    }
	
	 @PatchMapping("/update/{orderId}")
	 public String updateOrders(@RequestBody Orders orders , @PathVariable Integer orderId) {
		 if(orders!=null) {
			 return orderService.updateOrders(orders, orderId);
		 }else {
			return "Invalid input for updating orders...!!";
		 }      
	 }

	 @DeleteMapping("/delete/{orderId}")
	 public String deleteOrders(@PathVariable Integer orderId) {
		 if(orderId!=null) {
			 return orderService.deleteOrders(orderId);
		 }else {
			return "No Id Found for deletion...!!";
		 }   
	        
     }

	 @GetMapping("/get/{orderId}")
	    public String getOneOrders(@PathVariable Integer orderId) {
	        if (orderId!= null) {
	        	 return orderService.getOneOrders(orderId);
			} else {
				return "Invalid orderId No Record Found...!!";
			}
	    }

	    @GetMapping("/getAll")
	    public String getAllOrders() {
	        List<Orders> orders = orderService.getAllOrders();
	        if (!orders.isEmpty()) {
	        	 return "All Records Found Successfully...!!";
			} else {
				return "No Records Found...!!";
			}
	    }
//	@PostMapping("/save")
//    public String saveProduct(@RequestBody Orders  orders) {
//        Orders o1=orderService.saveOrders(orders);
//        String m1 = null;
//        if (o1!=null){
//            m1="Orders Data Saved Successfully";
//        }else {
//            m1="Data is failed";
//        }
//        return m1;
//    }
//
//	@PutMapping("/update/{id}")
//    public String updateOrders(@RequestBody Orders orders, @PathVariable Integer id) {
//		Orders o2=orderService.updateOrders(orders, id);
//        String m2 = null;
//        if (o2!=null){
//            m2="Orders Data Updated Successfully";
//        }else {
//            m2="Data is failed to Update";
//        }
//        return m2;
//    }
//
// 
// 	@DeleteMapping("/delete/{id}")
//    public String deleteOrders(@PathVariable Integer id) {
// 		Orders o3=orderService.deleteOrders(id);
//        String m3 = null;
//        if (o3!=null){
//            m3="Orders Data Deleted Successfully";
//        }else {
//            m3="Data is failed to Deleted";
//        }
//        return m3;
//    }
//	
//	@GetMapping("/get/{id}")
//    public Orders getOneOrder(@PathVariable Integer id) {
//        return orderService.getOneOrders(id);
//    }
//	
//
// 	@GetMapping("/getAll")
//    public List<Orders> getAllOrders() {
//        return orderService.getAllOrders();
//    }
}