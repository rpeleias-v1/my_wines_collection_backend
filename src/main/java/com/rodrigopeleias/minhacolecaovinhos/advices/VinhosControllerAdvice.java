package com.rodrigopeleias.minhacolecaovinhos.advices;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rodrigopeleias.minhacolecaovinhos.advices.mensagens.ConstraintViolationMessage;
import com.rodrigopeleias.minhacolecaovinhos.advices.mensagens.MessagemErroCustomizada;

@ControllerAdvice
public class VinhosControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	ResponseEntity<?> handleException(HttpServletRequest request, ConstraintViolationException exception) {
		HttpStatus status = getStatus(request);
		MessagemErroCustomizada mensagem = new MessagemErroCustomizada(status.value(),
				exception.getClass().getSimpleName(), request.getRequestURI());
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			ConstraintViolationMessage constraintViolationMessage = new ConstraintViolationMessage(
					constraintViolation.getMessage(), constraintViolation.getInvalidValue());
			mensagem.addConstraintViolationMessage(constraintViolationMessage);
		}
		return new ResponseEntity<>(mensagem, status);
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}

}
