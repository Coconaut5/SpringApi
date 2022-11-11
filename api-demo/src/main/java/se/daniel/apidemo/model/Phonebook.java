package se.daniel.apidemo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "phonebook", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Phonebook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "createdAt")
	private Date createdAt;

	@OneToMany(targetEntity = Item.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Column(name = "items", nullable = true)
	@JsonManagedReference
	private List <Item> items = null;


	public Phonebook() {

	}

	public Phonebook(String firstName, String lastName, String phoneNumber, Date createdAt, List<Item> items) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.createdAt = createdAt;
		this.items = items;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	

	public void setCreatedAt() {
		this.createdAt = new Date();
	}

	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(Item item) {
		items.add(item);
	}
	

}
