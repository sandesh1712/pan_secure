package com.pan_secure.advice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pan_secure.dto.ErrorReponse;
import com.pan_secure.exceptions.NotFoundException;
import com.pan_secure.exceptions.UnauthorizedException;

@ControllerAdvice
public class GenericAdvice {

	@ExceptionHandler
	public ResponseEntity<ErrorReponse> exceptionHandler(Exception ex) {
		ErrorReponse errorResponse = new ErrorReponse();
		errorResponse.setMessage(ex.getLocalizedMessage());
		LocalDateTime nowDateTime = LocalDateTime.now();
		errorResponse.setTimeStampString(nowDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

		if (ex.getClass().equals(NotFoundException.class))
			errorResponse.setStatus(HttpStatus.NOT_FOUND);

		if (errorResponse.getStatus() == null)
			errorResponse.setStatus(HttpStatus.BAD_REQUEST);

		if (ex.getClass().equals(UnauthorizedException.class))
			errorResponse.setStatus(HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<ErrorReponse>(errorResponse, errorResponse.getStatus());
	}
}
