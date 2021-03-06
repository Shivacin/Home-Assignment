package com.shiva.ebay.assessment.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

	public NotFoundException(String msg) {
        super(msg);
    }

	public NotFoundException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }
}
