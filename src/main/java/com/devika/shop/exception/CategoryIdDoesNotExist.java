package com.devika.shop.exception;

import com.devika.shop.util.messages.error.ErrorCodes;

public class CategoryIdDoesNotExist extends ApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -74458226736615307L;

	public CategoryIdDoesNotExist() {
		super(ErrorCodes.CATEGORY_DOES_NOT_EXIST);
	}

}
