package com.cumbuca.web.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cumbuca.web.dao.FoodRecordDAO;
import com.cumbuca.web.entity.FoodRecord;
import com.cumbuca.web.entity.FoodRecordIdentifier;

@Repository
public class FoodRecordDAOImpl extends GenericDAOImpl<FoodRecord, FoodRecordIdentifier> implements FoodRecordDAO{

	public List<FoodRecord> getFoodRecordsWithUserId(int userId) {
		@SuppressWarnings("unchecked")
		List<FoodRecord> foods = em.createQuery("SELECT f FROM FoodRecord f WHERE f.user.id = :userId")
				.setParameter("userId", userId)
				.getResultList();
		if (foods.isEmpty()) {
			return null;
		} else {
			return foods;
		}
	}
	
}
