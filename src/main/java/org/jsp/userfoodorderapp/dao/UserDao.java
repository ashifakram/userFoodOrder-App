package org.jsp.userfoodorderapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userfoodorderapp.dto.User;

public class UserDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("development");
	EntityManager manager = factory.createEntityManager();

	public User saveUser(User user) {

		EntityTransaction transaction = manager.getTransaction();

		manager.persist(user);
		transaction.begin();
		transaction.commit();

		return user;
	}

	public User updateUser(User user) {

		EntityTransaction transaction = manager.getTransaction();

		User dbUser = manager.find(User.class, user.getId());

		if (dbUser != null) {

			dbUser.setName(user.getName());
			dbUser.setGender(user.getGender());
			dbUser.setEmail(user.getEmail());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());

			transaction.begin();
			transaction.commit();
			return dbUser;
		}
		return null;
	}

	public User verifyUserByEmailAndPassword(String email, String password) {

		Query q = manager.createQuery("select u from User u where u.email = ?1 and u.password = ?2");
		q.setParameter(1, email);
		q.setParameter(2, password);

		return (User) q.getSingleResult();
	}

	public User verifyUserByPhoneAndPassword(long phone, String password) {

		Query q = manager.createQuery("select u from User u where u.phone = ?1 and u.password = ?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);

		return (User) q.getSingleResult();
	}

	public User verifyUserByIdAndPassword(int id, String password) {

		Query q = manager.createQuery("select u from User u where u.id = ?1 and u.password = ?2");
		q.setParameter(1, id);
		q.setParameter(2, password);

		return (User) q.getSingleResult();
	}
}
