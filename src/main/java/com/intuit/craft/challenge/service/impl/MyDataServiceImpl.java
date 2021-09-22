package com.intuit.craft.challenge.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.intuit.craft.challenge.common.MyDataConstants;
import com.intuit.craft.challenge.entity.MyData;
import com.intuit.craft.challenge.exception.MyDataServiceException;
import com.intuit.craft.challenge.repository.MyDataRepository;
import com.intuit.craft.challenge.service.MyDataService;

@Service
public class MyDataServiceImpl implements MyDataService {
	static Logger logger = Logger.getLogger(MyDataServiceImpl.class.getName());

	@Autowired
	MyDataRepository myDataRepository;

	@Override
	@Transactional
	public Page<MyData> getMyData(Long timestamp, String key, String value, Pageable pageable) {
		try {
			return myDataRepository.findByTimestampAndAttributesKeyAndAttributesValue(timestamp, key, value, pageable);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, MyDataConstants.FETCH_EROR, ex);
			throw new MyDataServiceException(MyDataConstants.FETCH_EROR);
		}
	}

	@Override
	@Transactional
	public MyData createMyData(MyData myData) {
		try {
			return myDataRepository.save(myData);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, MyDataConstants.SAVE_EROR, ex);
			throw new MyDataServiceException(MyDataConstants.SAVE_EROR);
		}
	}

	@Override
	@Transactional
	public MyData updateMyData(MyData myData) {
		try {
			return myDataRepository.save(myData);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, MyDataConstants.UPDATE_EROR, ex);
			throw new MyDataServiceException(MyDataConstants.UPDATE_EROR);
		}
	}

}
