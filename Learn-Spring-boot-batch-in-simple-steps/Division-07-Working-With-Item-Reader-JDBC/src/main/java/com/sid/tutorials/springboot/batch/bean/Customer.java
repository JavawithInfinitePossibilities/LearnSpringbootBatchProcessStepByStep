/**
 * 
 */
package com.sid.tutorials.springboot.batch.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author Lenovo
 *
 */
@Component
public class Customer implements GeneralBean {

	private long id;
	private String firstName;
	private String lastName;
	private Date birthDate;

	public Customer() {
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 */
	public Customer(long id, String firstName, String lastName, Date birthDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ "]";
	}

}
