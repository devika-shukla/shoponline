package com.idealo.shop.util.messages.error;

public interface ErrorCodes {

	String CATEGORY_NAME_EMPTY_EXCEPTION = "Category Name can not be empty.";
	String CATEGORY_DOES_NOT_EXIST = "Category Id does not exist. please chose a valid Category Id.";
	String CATEGORY_ALREADY_EXIST = "Category already exists with this name.";

	String NO_HANDLER_FOUND_EXCEPTION = "The url is not supported by the application. Please use valid url: ";
	String JSON_NOT_READABLE = "Please enter the data in correct format.";
	String METHOD_ARGUMENT_INVALID = "Please enter values.";
}
