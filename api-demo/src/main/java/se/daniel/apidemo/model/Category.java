package se.daniel.apidemo.model;

public class Category {
    
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