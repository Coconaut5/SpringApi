package se.daniel.apidemo.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "items", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    public Item(String name, String description, Integer price, User user) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.user = user;
	}

    public Item() {

    }

    public User getUser() {
    return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
    return id;
    }

    public String getName() {
    return name;
    }

    public String getDescription() {
    return description;
    }

    public Integer getPrice() {
    return price;
    }

    public void setName(String name) {
		this.name = name;
	}

    public void setDescription(String description) {
		this.description = description;
	}

    public void setPrice(Integer price) {
		this.price = price;
	}

    
}


