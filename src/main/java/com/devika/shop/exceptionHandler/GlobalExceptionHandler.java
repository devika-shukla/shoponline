package com.devika.shop.exceptionHandler;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devika.shop.exception.ApiException;
import com.devika.shop.util.messages.error.ErrorCodes;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler implements ErrorController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ApiException.class)
	public String handleApiException(ApiException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public String handleException(HttpMessageNotReadableException e) {
		return ErrorCodes.JSON_NOT_READABLE;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return e.getBindingResult().getFieldError().getDefaultMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e) {
		return e.getMessage();
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String error() {
		return ErrorCodes.NO_HANDLER_FOUND_EXCEPTION;
	}

}
