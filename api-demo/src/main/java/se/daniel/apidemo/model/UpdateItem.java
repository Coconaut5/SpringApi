package se.daniel.apidemo.model;

public class UpdateItem {

    private long id;
    private String name = null;
    private String description = null;
    private Integer price = null;
    private Phonebook user = null;

    public long getId() {
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

    public Phonebook getUser() {
        return user;
        }
    
}