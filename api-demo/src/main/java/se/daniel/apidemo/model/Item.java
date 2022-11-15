package se.daniel.apidemo.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "items", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="user_id", nullable = true)
    @JsonBackReference(value="user_items")
    private User user;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonManagedReference(value="category_items")
    private Set<Category> categories;

    public Item(String name, String description, Integer price, User user, Set<Category> categories) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.user = user;
    this.categories = categories;
	}

    public Item() {

    }

    public User getUser() {
    return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
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

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategory(Category category) {
    categories.add(category);
  }
    
}


