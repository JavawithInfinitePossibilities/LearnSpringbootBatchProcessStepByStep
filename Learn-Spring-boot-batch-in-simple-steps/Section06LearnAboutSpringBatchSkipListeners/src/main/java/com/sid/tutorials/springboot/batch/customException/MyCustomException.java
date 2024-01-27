/**
 * 
 */
package com.sid.tutorials.springboot.batch.customException;

import org.springframework.stereotype.Component;

/**
 * @author kunmu
 *
 */
public class MyCustomException extends Exception {

	private String message;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyCustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
