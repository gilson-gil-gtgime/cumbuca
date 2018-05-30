package com.cumbuca.web.dao;

import java.util.List;

import com.cumbuca.web.entity.FoodRecord;
import com.cumbuca.web.entity.FoodRecordIdentifier;

public interface FoodRecordDAO extends GenericDAO<FoodRecord, FoodRecordIdentifier>{
	public List<FoodRecord> getFoodRecordsWithUserId(int userId);
}
