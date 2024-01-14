package Controllers;

import Models.Author;
import Models.Category;
import Models.StandardViewResponse;

public class CategoryController {
    public void addCategory(Category c) {
        FileController.categories.add(c);
    }

    public Category createCategory(String categoryname) {
        for (Category c : FileController.categories) {
            if (c.getCategoryName().equals(categoryname))
                return null;
        }
        Category category = new Category(categoryname);
        addCategory(category);
        return category;
    }

    public Category findCategory(int id) {
        for (Category c : FileController.categories) {
            if (c.getID() == id)
                return c;
        }
        return null;
    }
    static boolean isSpecialChar(char c) {
        String specialChars = "!@#$%^&*()._";
        return specialChars.contains(String.valueOf(c));
    }
    static boolean isUniqueCategory(int ID, String categoryname) {
        for (Category category : FileController.categories) {
            if (category.getID()==ID && category.getCategoryName().equals(categoryname)) {
                return false;
            }
        }
        return true;
    }

    public boolean deleteCategory(int id) {
        var exists = findCategory(id);
        if (exists == null) {
            return false;
        }
        for (Category category : FileController.categories) {
            if (category.getID() == id) {
                FileController.categories.remove(category);
                return true;
            }
        }
        return false;
    }

    public StandardViewResponse editCategory( int ID, String categoryname){
        Category category = findCategory(ID);
        try {

            if (categoryname.isEmpty()) {
                return new StandardViewResponse<>(category, "Fields are empty!");
            }

            if (categoryname.length() < 3 || categoryname.length() > 20) {
                return new StandardViewResponse<>(category, "Name can't have this length!");
            } else if (categoryname.matches(".*\\d+.*")) {
                return new StandardViewResponse<>(category, "Name can't contain numbers!");
            }
            for (char c : categoryname.toCharArray()) {
                if (isSpecialChar(c)) {
                    return new StandardViewResponse<>(category, "Name can't contain special characters!");
                }
            }
            if (!Character.isUpperCase(categoryname.charAt(0))) {
                categoryname = Character.toUpperCase(categoryname.charAt(0)) + categoryname.substring(1);
            }
            if(!isUniqueCategory(ID, categoryname)) {
                return new StandardViewResponse<>(category, "There already exists a category with this name");
            }
            category.setCategoryName(categoryname);
            System.out.println("Category was successfully edited");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new StandardViewResponse<>(category, e.getMessage());
        }
        return new StandardViewResponse<>(category, "");
    }
}
