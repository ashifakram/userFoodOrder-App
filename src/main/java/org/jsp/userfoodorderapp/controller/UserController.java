package org.jsp.userfoodorderapp.controller;

import java.util.Scanner;

import javax.persistence.NoResultException;

import org.jsp.userfoodorderapp.dao.UserDao;
import org.jsp.userfoodorderapp.dto.User;

public class UserController {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		Scanner sc = new Scanner(System.in);

		System.out.println(
				"1. Save User.\n2. Update User.\n3. Verify User By Email And Password.\n4. Verify User By Phone And Password.\n5. Verify User By Id And Password");

		switch (sc.nextInt()) {
		case 1: {
			User user = new User();
			System.out.println("Enter the user name, gender(Male/Female), email, phone and password : ");
			user.setName(sc.next());
			user.setGender(sc.next());
			user.setEmail(sc.next());
			user.setPhone(sc.nextLong());
			user.setPassword(sc.next());

			user = userDao.saveUser(user);
			System.out.println("User saved with id : " + user.getId());

			break;
		}
		case 2: {
			User user = new User();
			System.out.println("Enter the user id, name, gender(Male/Female), email, phone and password to update user : ");
			user.setId(sc.nextInt());
			user.setName(sc.next());
			user.setGender(sc.next());
			user.setEmail(sc.next());
			user.setPhone(sc.nextLong());
			user.setPassword(sc.next());
			
			user = userDao.updateUser(user);
			
			if (user != null) {
				System.out.println("User with id " + user.getId() + " updated");
			} else {
				System.err.println("Cannot update User as id is invalid");
			}

			break;
		}
		case 3: {
			System.out.println("Enter the user email and password to verify user:");
			String email = sc.next();
			String password = sc.next();
			
			try {
				User user = userDao.verifyUserByEmailAndPassword(email, password);
				System.out.println("Verification successfull");
				System.out.println(user);
			}catch(NoResultException e) {
				System.err.println("Invalid Email or Password.");
			}

			break;
		}
		case 4: {
			
			System.out.println("Enter the user phone number and password to verify user:");
			long phone = sc.nextLong();
			String password = sc.next();
			
			try {
				User user = userDao.verifyUserByPhoneAndPassword(phone, password);
				System.out.println("Verification successfull");
				System.out.println(user);
			}catch(NoResultException e) {
				System.err.println("Invalid Phone number or Password.");
			}
			break;
		}
		case 5:{
			System.out.println("Enter the user id and password to verify user:");
			int id = sc.nextInt();
			String password = sc.next();
			
			try {
				User user = userDao.verifyUserByIdAndPassword(id, password);
				System.out.println("Verification successfull");
				System.out.println(user);
			}catch(NoResultException e) {
				System.err.println("Invalid id or Password.");
			}
			break;
		}
		default:
			sc.close();
			break;
		}
	}
}
