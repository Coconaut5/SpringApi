package se.daniel.apidemo.model;

import java.util.Date;

public class UpdateUser {

	private long id;
	private String firstName = null;
	private String lastName = null;
	private String phoneNumber = null;
	private Date createdAt = null;

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

}