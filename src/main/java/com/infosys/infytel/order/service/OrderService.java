package com.infosys.infytel.order.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infosys.infytel.order.dto.OrdersDTO;
import com.infosys.infytel.order.entity.Orders;
import com.infosys.infytel.order.repository.OrderRepository;
import com.infosys.infytel.order.repository.OrderRepository2;
import com.infosys.infytel.order.entity.Productsorderd;
import com.infosys.infytel.order.dto.ProductsorderdDTO;

@Service
public class OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	OrderRepository2 orderRepo2;
	
	@Autowired
	OrderRepository orderRepo;

	public List<ProductsorderdDTO> getAllOrder(String buyerId) {
		List<Productsorderd> orders = orderRepo2.findAll();
		List<ProductsorderdDTO> orderDTOs = new ArrayList<ProductsorderdDTO>();
		for(Productsorderd order : orders) {
			ProductsorderdDTO orderDTO = ProductsorderdDTO.valueOf(order);
			if(orderDTO.getBuyerId().equals(buyerId))
				orderDTOs.add(orderDTO);
		}
		logger.info("order details : {}", orderDTOs);
		return orderDTOs;
	}

	public String getOrderAddress(String buyerId) {
		Orders orders = orderRepo.findById(buyerId).orElse(null);
		return orders.getAddress();
		
	}

	public String reOrder(String buyerId, String prodId) {
		Orders orders = orderRepo.findById(buyerId).orElse(null);
		if(orders.getStatus().equals("Delivered")) {
		List<Productsorderd> products=orderRepo2.findAll();
		for(Productsorderd product : products) {
			ProductsorderdDTO productDTO = ProductsorderdDTO.valueOf(product);
			if(productDTO.getBuyerId().equals(buyerId) && productDTO.getProdId().equals(prodId)) {
				orders.setStatus("Order Placed");
				orderRepo.save(orders);
				return "product reordered successfully..";
			}		
		}
		return "reorder failed...";	
	}
	return "buyer haven'tmade any orders...";							
	}

	public List<OrdersDTO> viewAllOrders(String sellerId) {
		Query query = entityManager.createQuery("SELECT o FROM Orders o JOIN Productsorderd p ON o.buyerId=p.buyerId WHERE p.sellerId=:sellerId");
		query.setParameter("sellerId",sellerId);
		List<OrdersDTO> orders = query.getResultList();
		logger.info("order details : {}", orders);
		return orders;
	}

	public void changeStatus(String orderId, String status) throws Exception {
		Orders order=orderRepo.findById(orderId).orElse(null);
		if(order==null)
			throw new Exception("Order Does not Exist");
		order.setStatus(status);
		orderRepo.save(order);
	}

	public void addOrder(OrdersDTO ordersDTO) throws Exception {
		Orders order=orderRepo.findById(ordersDTO.getOrderId()).orElse(null);
		if(order==null)
			throw new Exception("Order Does not Exist");
		Orders neworder = ordersDTO.createEntity();
		orderRepo.save(neworder);
	}
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
//		Optional<Orders> ord = ordersRepo.findById(orderId);
		orderRepo.deleteById(orderId);
	}
	

}
