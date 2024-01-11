package Controllers;

import Models.Category;


public class CategoryController {
    public void addCategory(Category c){
        FileController.categories.add(c);
    }
    public Category createCategory(String categoryname) {
        for(Category c:FileController.categories){
            if(c.getCategoryName().equals(categoryname))
                return null;
        }
        Category category=new Category (categoryname);
        addCategory(category);
        return category;
    }
    public Category findCategory(int id) {
        for(Category c:FileController.categories){
            if(c.getID()==id)
                return c;
        }
        return null;
    }
    public boolean CategoryNameValidation(){
        return false;
    }
}
