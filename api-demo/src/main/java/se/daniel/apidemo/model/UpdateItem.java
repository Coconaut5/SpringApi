package se.daniel.apidemo.model;

public class UpdateItem {

    private Long id;
    private String name = null;
    private String description = null;
    private Integer price = null;
    private User user = null;
    private Category category = null;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    } 

    public String getDescription () {
        return description;
    }

    public Integer getPrice () {
        return price;
    }

    public User getUser() {
        return user;
        }

    
    public Category getCategory() {
        return category;
    }
    
}
