package com.terrapay.hm.exception;

public class DataAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataAlreadyExistException(String msg) {
		super(msg);
	}

}
