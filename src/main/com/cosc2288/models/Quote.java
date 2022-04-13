/**
 * QuoteMessage
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import javax.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {

    @column(name = "message")
	private String message;

	/**
	 * @return the quote
	 */
    public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
