package br.com.limocs.customerapi.exception;

import br.com.limocs.customerapi.constants.MessagesConst;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String id) {
		super(MessagesConst.CUSTOMER_NOT_FOUND + id);
	}

}
