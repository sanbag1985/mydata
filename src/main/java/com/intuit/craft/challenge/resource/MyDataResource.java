package com.intuit.craft.challenge.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.challenge.common.MyDataConstants;
import com.intuit.craft.challenge.entity.MyData;
import com.intuit.craft.challenge.exception.ConstraintsViolationException;
import com.intuit.craft.challenge.exception.DocumentNotFoundException;
import com.intuit.craft.challenge.exception.MyDataResourceException;
import com.intuit.craft.challenge.exception.MyDataServiceException;
import com.intuit.craft.challenge.service.MyDataService;
import com.intuit.craft.challenge.validator.MyDataRequestValidator;

@RestController
@RequestMapping(value = "/mydata")
public class MyDataResource {

	@Autowired
	MyDataService myDataService;

	@Autowired
	MyDataRequestValidator validator;

	@GetMapping(value = "/{start-timestamp}/{end-timestamp}/{key}/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getMyData(@PathVariable("start-timestamp") Long startTimestamp,
			@PathVariable("end-timestamp") Long endTimestamp, @PathVariable("key") String key,
			@PathVariable("value") String value, @RequestParam(defaultValue = "0") int page) {
		try {
			Pageable pageable = PageRequest.of(page, MyDataConstants.PAGE_SIZE);
			Page<MyData> data = myDataService.getMyData(startTimestamp, endTimestamp, key, value, pageable);

			if (CollectionUtils.isEmpty(data.getContent())) {
				throw new DocumentNotFoundException(MyDataConstants.NOT_FOUND);
			} else {
				return new ResponseEntity<>(data.getContent().stream().map(d -> d.getId()).collect(Collectors.toList()),
						HttpStatus.OK);
			}
		} catch (MyDataServiceException ex) {
			throw new MyDataResourceException(MyDataConstants.FETCH_EROR, ex);

		}
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> createMyData(@RequestBody MyData myData) {
		try {
			ResponseEntity<?> result = null;

			Optional<List<ConstraintsViolationException>> error = validator.validateRequest(myData);
			if (error.isPresent()) {
				result = new ResponseEntity<Optional<List<ConstraintsViolationException>>>(error,
						HttpStatus.BAD_REQUEST);
			} else {
				result = new ResponseEntity<>(myDataService.createMyData(myData), HttpStatus.CREATED);
			}
			return result;
		} catch (MyDataServiceException ex) {
			throw new MyDataResourceException(MyDataConstants.SAVE_EROR, ex);

		}
	}
}
