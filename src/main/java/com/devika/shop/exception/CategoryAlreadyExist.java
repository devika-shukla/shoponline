package com.devika.shop.exception;

import com.devika.shop.util.messages.error.ErrorCodes;

public class CategoryAlreadyExist extends ApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8518695629424889982L;

	public CategoryAlreadyExist() {
		super(ErrorCodes.CATEGORY_ALREADY_EXIST);
	}

}
