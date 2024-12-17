package org.jsp.userfoodorderapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userfoodorderapp.dao.FoodOrderDao;
import org.jsp.userfoodorderapp.dto.FoodOrder;

public class FoodOrderController {

	public static void main(String[] args) {

		FoodOrderDao foodOrderDao = new FoodOrderDao();
		Scanner sc = new Scanner(System.in);
	
		System.out.println("1. Add the FoodOrder.\n2. Update FoodOrder.\n3. Fetch FoodOrder By User By Id.\n4. Fetch FoodOrder By User Email And Password.\n5. Delete the FoodOrder");

		switch (sc.nextInt()) {
		case 1:{
			System.out.println("Enter User Id to add food order: ");
			int user_id = sc.nextInt();
			
			FoodOrder order = new FoodOrder();
			System.out.println("Enter the FoodOrder item_name, price and address : ");
			
			order.setItem_name(sc.next());
			order.setPrice(sc.nextDouble());
			order.setAddress(sc.next());
			
			order = foodOrderDao.addFoodOrder(order, user_id);
			
			System.out.println("FoodOrder saved with id : " + order.getId());
			
			break;
		}
		case 2:{
			FoodOrder order = new FoodOrder();
			
			System.out.println("Enter the FoodOrder id, item_name, price and address to update foodorder: ");
			order.setId(sc.nextInt());
			order.setItem_name(sc.next());
			order.setPrice(sc.nextDouble());
			order.setAddress(sc.next());
			
			order = foodOrderDao.updateFoodOrder(order);
			
			if (order != null) {
				System.out.println("FoodOrder with id " + order.getId() + " updated");
			} else {
				System.err.println("Cannot update FoodOrder as id is invalid");
			}
			break;
		}
		case 3:{
		
			System.out.println("Enter the User id to fetch food orders :");
			int user_id = sc.nextInt();
			
			List<FoodOrder> orders = foodOrderDao.fetchFoodOrderByUserId(user_id);
						
			if( orders.size() > 0) {
				for(FoodOrder o : orders) {
					System.out.println(o);
				}
			}
			else {
				System.err.println("No FoodOrder details found as entered User id is invalid");
			}
			break;
		}
		case 4:{
			
			System.out.println("Enter the User email and password to fetch food orders :");
			String email = sc.next();
			String password = sc.next();
			
			List<FoodOrder> orders = foodOrderDao.fetchFoodOrderByUserEmailAndPassword(email,password);
						
			if( orders.size() > 0) {
				for(FoodOrder o : orders) {
					System.out.println(o);
				}
			}
			else {
				System.err.println("No FoodOrder details found as entered User email or password is invalid");
			}
			
			break;
		}
		case 5:{
			
			System.out.println("Enter the FoodOrder id to delete the food order :");
			int id = sc.nextInt();
			
			boolean flag = foodOrderDao.deleteFoodOrder(id);
			
			if(flag) {
				System.out.println("FoodOrder with Id : " +id + " is deleted" );
			}
			else {
				System.out.println("Invalid FoodOrder id.");
			}
			break;
		}
		default:
			System.out.println("Invalid Choice.");
			sc.close();
			break;
		}
	}

}
