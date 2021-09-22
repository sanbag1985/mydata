package com.intuit.craft.challenge.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.intuit.craft.challenge.common.MyDataConstants;
import com.intuit.craft.challenge.entity.MyData;
import com.intuit.craft.challenge.exception.ConstraintsViolationException;

@Component
public class MyDataRequestValidator {

	public Optional<List<ConstraintsViolationException>> validateRequest(MyData myDataReq) {
		List<ConstraintsViolationException> errorsList = new ArrayList<>();
		idValidator(myDataReq, errorsList);
		maxLengthValidator(myDataReq, errorsList);
		return errorsList.size() > 0 ? Optional.of(errorsList) : Optional.empty();

	}
	
	private void idValidator(MyData myDataReq, List<ConstraintsViolationException> errorsList) {
		if(Objects.nonNull(myDataReq.getId())) {
			errorsList.add(new ConstraintsViolationException.Builder().setField(MyDataConstants.ID)
					.setMessage(MyDataConstants.NO_UPDATE_MSG).build());
		}
	}

	private void maxLengthValidator(MyData myDataReq, List<ConstraintsViolationException> errorsList) {

		if (Objects.nonNull(myDataReq.getAttributes())) {
			if (myDataReq.getAttributes().stream().map(attr -> attr.getKey()).anyMatch(key -> isMaxLength(key))) {
				errorsList.add(new ConstraintsViolationException.Builder().setField(MyDataConstants.KEY)
						.setMessage(MyDataConstants.MAX_LENGTH_MSG + MyDataConstants.MAX_LENGTH).build());
			}

			if (myDataReq.getAttributes().stream().map(attr -> attr.getValue()).anyMatch(value -> isMaxLength(value))) {
				errorsList.add(new ConstraintsViolationException.Builder().setField(MyDataConstants.VALUE)
						.setMessage(MyDataConstants.MAX_LENGTH_MSG + MyDataConstants.MAX_LENGTH).build());

			}
		}
	}

	private boolean isMaxLength(String value) {
		return StringUtils.isNotEmpty(value) && value.length() > MyDataConstants.MAX_LENGTH;
	}
}
