package org.jsp.userfoodorderapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userfoodorderapp.dto.FoodOrder;
import org.jsp.userfoodorderapp.dto.User;

public class FoodOrderDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("development");
	EntityManager manager = factory.createEntityManager();
	
	public FoodOrder addFoodOrder(FoodOrder order, int user_id) {
		
		EntityTransaction transaction = manager.getTransaction();
		
		User user = manager.find(User.class, user_id);
		
		if( user != null) {
			
			// List<User> users = order.getUser();
			//users.add(user);
			//order.setUser(users);
			
			
			order.setUser(user);
			
			List<FoodOrder> orders = user.getFoodorder();
			orders.add(order);
			user.setFoodorder(orders);
//			user.getFoodorder().add(order);
					
			manager.persist(order);
			
			transaction.begin();
			transaction.commit();
			
			return order;
		}	
		return null;
	}
	
	public FoodOrder updateFoodOrder(FoodOrder order) {
		
		FoodOrder dbOrder = manager.find(FoodOrder.class, order.getId());
		EntityTransaction transaction = manager.getTransaction();
		
		if( dbOrder != null) {
			
			dbOrder.setItem_name(order.getItem_name());
			dbOrder.setPrice(order.getPrice());
			dbOrder.setAddress(order.getAddress());
			
			transaction.begin();
			transaction.commit();
			
			return dbOrder;
		}
		return null;
	}
	
	public List<FoodOrder> fetchFoodOrderByUserId(int user_id) {
		
		//Query q = manager.createQuery("select u.foodorder from User u where u.id = ?1");
		Query q = manager.createQuery("select o from FoodOrder o where o.user.id = ?1");
		
		q.setParameter(1, user_id);

		return q.getResultList();	
	}
	
	public List<FoodOrder> fetchFoodOrderByUserEmailAndPassword(String email, String password){
		
		//Query q = manager.createQuery("select u.foodorder from User u where u.email = ?1 and u.password = ?2");
		Query q = manager.createQuery("select o from FoodOrder o where o.user.email = ?1 and o.user.password = ?2");
		
		q.setParameter(1, email);
		q.setParameter(2, password);
		
		return q.getResultList();
	}
	
	public boolean deleteFoodOrder(int order_id) {
		
		EntityTransaction transaction = manager.getTransaction();
		
		FoodOrder order = manager.find(FoodOrder.class, order_id);
		
		if(order != null) {
			
			manager.remove(order);
			transaction.begin();
			transaction.commit();
			
			return true;
		}
		
		return false;
	}
}
