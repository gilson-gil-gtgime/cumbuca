package com.cumbuca.web.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FoodRecordIdentifier implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@NotNull
    private int userId;

    @NotNull
    private int foodId;

    public FoodRecordIdentifier() {
    }

    public FoodRecordIdentifier(int userId, int foodId) {
        this.userId = userId;
        this.foodId = foodId;
    }
    
    public FoodRecordIdentifier(int hashCode) {
    	this.foodId = hashCode % 1000;
    	this.userId = (hashCode - this.foodId) / 1000;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodRecordIdentifier that = (FoodRecordIdentifier) o;

        if (userId != that.userId) return false;
        return foodId == that.foodId;
    }

    @Override
    public int hashCode() {
        return 1000 * userId + foodId;
    }

}