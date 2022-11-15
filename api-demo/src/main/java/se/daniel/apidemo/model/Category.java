package se.daniel.apidemo.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="name", unique = true)
    private String name;



    /// If you wanna delete a category you need to fix so that it removes relation to items, the other way around already works.
    @ManyToMany(cascade = CascadeType.REFRESH , fetch = FetchType.EAGER)
    @JoinTable
    @JsonBackReference(value="category_items")
    private Set <Item> items = null;

    public Category() {}

    public Category(String name, Set<Item> items) {
        this.name = name;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
        }
    
    public void setName(String name) {
            this.name = name;
    
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Item item) {
        items.add(item);
    }
}


// each item can have a list of categories or null
// each category can have a list of items with that category or null

// /categories/"clothes" 
// @GET will give you the category "clothes" and its List<Item> which is all the items with the category "clothes"

// /categories 
// @GET will show all categories
// @POST will add a new category
// @DELETE will remove a category

// /items
// @PUT can add a category, or multiple? if (!category) {category does not exist} else item.categories.add(reqbody.categories)
// ??? @POST, no categories from start? Have to use put to add categories?

// maybe? /categories/items will show all categories and all items in each category


// Category will have id, name and List <Item> @ManyToMany
// Items will have to add List <Category> @ManyToMany
// if one item is deleted that will only remove the item from the List<Item> in Category
// if one category is deleted that will only remove the category from List<Category> in Item


/// join data table probablly only has to contain itemId --> categoryName??