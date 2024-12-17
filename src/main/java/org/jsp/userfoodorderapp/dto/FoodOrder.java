package org.jsp.userfoodorderapp.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@UpdateTimestamp
	private LocalDateTime del_time;
	@CreationTimestamp
	private LocalDateTime ordered_time;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String item_name;
	@ManyToOne
	@JoinColumn
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDel_time() {
		return del_time;
	}

	public void setDel_time(LocalDateTime del_time) {
		this.del_time = del_time;
	}

	public LocalDateTime getOrdered_time() {
		return ordered_time;
	}

	public void setOrdered_time(LocalDateTime ordered_time) {
		this.ordered_time = ordered_time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "FoodOrder [id=" + id + ", del_time=" + del_time + ", ordered_time=" + ordered_time + ", price=" + price
				+ ", address=" + address + ", item_name=" + item_name + "]";
	}
}
