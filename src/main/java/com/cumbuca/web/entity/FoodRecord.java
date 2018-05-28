package com.cumbuca.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_food_record")
public class FoodRecord {

	@Id
	@SequenceGenerator(name="food_record", sequenceName="sq_tb_food_record", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_food_record")
	private int id_food_record;
	
	@ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="id_food", nullable=false)
    private Food food;
    
    @Column(name="nr_total")
    private int total;
    
    @Column(name="ds_unit")
    private String unit;
    
    @Column(name="dt_expire")
    private Date expire;

	public FoodRecord() {
		super();
	}

	public FoodRecord(User user, Food food, int total, String unit, Date expire) {
		super();
		this.user = user;
		this.food = food;
		this.total = total;
		this.unit = unit;
		this.expire = expire;
	}

	public User getUser() {
		return user;
	}

	public Food getFood() {
		return food;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}
}
