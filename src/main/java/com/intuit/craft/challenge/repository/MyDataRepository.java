package com.intuit.craft.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.craft.challenge.entity.MyData;

public interface MyDataRepository extends JpaRepository<MyData, Integer> {
	Page<MyData> findByTimestampAndAttributesKeyAndAttributesValue(Long timestamp, String key, String value,
			Pageable pageable);
}
