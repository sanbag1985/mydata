package com.intuit.craft.challenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.intuit.craft.challenge.entity.MyData;

public interface MyDataService {
	Page<MyData> getMyData(Long startTimestamp, Long endTimestamp, String key, String value, Pageable pageable);

	MyData createMyData(MyData myData);

}
