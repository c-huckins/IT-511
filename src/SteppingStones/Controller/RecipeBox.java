package SteppingStones.Controller;

import SteppingStones.Model.Recipe;
import java.util.ArrayList;
import java.util.List;

/**
 *Class used to keep store a collection of recipes.
 * 
 * @author Chris Huckins
 */
public class RecipeBox {
        private ArrayList<Recipe> listOfRecipes = new ArrayList<>();;
    
    public RecipeBox() {
    }
    
    public RecipeBox(ArrayList<Recipe> listOfRecipes) 
    throws IllegalArgumentException {
        if (listOfRecipes == null) {
            throw new IllegalArgumentException("List of recipes cannot be null");
        }
        this.listOfRecipes = listOfRecipes;
    }

    /**
     * Gets all the recipes in the collection.
     * @return 
    */
    public ArrayList<Recipe> getListOfRecipes() {
        return listOfRecipes;
    }
    
    /**
     * Sets the recipe list
     * 
     * @param listOfRecipes 
     */
    public void setListOfRecipes(ArrayList<Recipe> listOfRecipes) {
        if (listOfRecipes == null) {
            throw new IllegalArgumentException("List of recipes cannot be null");
        }
        this.listOfRecipes = listOfRecipes;
    }
    
    /**
     * Get all the names of the recipes in the collection.
     * 
     * @return 
     */
    public List<String> getAllRecipeNames() {
        ArrayList<String> nameList = new ArrayList<>();
        for (Recipe recipe : listOfRecipes) {
            nameList.add(recipe.getName());
        }
        return nameList;
    }
    /**
     * Finds a recipe with a specific name.
     * 
     * @param recipeName
     * @return 
     */
    public ArrayList<String> findRecipe(String recipeName) {
        ArrayList<String> results = new ArrayList<>();
        for (Recipe recipe : listOfRecipes) {
            if (recipe.getName().toLowerCase().equals(recipeName.toLowerCase())) {
                results.add(recipe.getName());
                break;
            }
        }
        return results;
    }
    
    /**
     * Finds all the recipes names that contains specific text.
     * 
     * @param text
     * @return 
     */
    public ArrayList<String> findRecipesContainingText(String text) {
        ArrayList<String> results = new ArrayList<>();
        for (Recipe recipe : listOfRecipes) {
            if (recipe.getName().toLowerCase().contains(text.toLowerCase())) {
                results.add(recipe.getName());
            }
        }
        return results;
    }
    
    /**
     * Returns a recipe with a specific name.
     * 
     * @param recipeName
     * @return 
     */
    public Recipe getRecipe(String recipeName) {
        for (Recipe recipe : listOfRecipes) {
            if (recipe.getName().toLowerCase().equals(recipeName.toLowerCase())) {
                return recipe;
            }
        }
        return null;
    }
    
    /**
     * Adds a recipe to the existing list.
     * 
     * @param recipe 
     */
    public void addRecipe(Recipe recipe) {
        listOfRecipes.add(recipe);
    }
    
    /**
     * Deletes a recipe with a given name.
     * 
     * @param recipeName 
     */
    public void deleteRecipe(String recipeName) {
        Recipe recipeToDelte = null;
        for (Recipe recipe : listOfRecipes) {
            if (recipe.getName().toLowerCase().equals(recipeName.toLowerCase())) {
                recipeToDelte = recipe;
                break;
            }
        }
        if (recipeToDelte != null) {
            listOfRecipes.remove(recipeToDelte);
        }
    }
    
    /**
     * Prints the recipe that matches the name to the console.
     * 
     * @param recipeName 
     */
    public void printAllRecipeDetails(String recipeName) {
        System.out.println("");
        Recipe recipe = getRecipe(recipeName);
        if (!(recipe == null)) {
            recipe.printRecipe();
            System.out.println("\n");
            return;
        } 
        System.out.println("* There is no recipe named " + recipeName + ". *\n");
    }
    
    /**
     * Prints all recipes to the console.
     */
    public void printAllRecipeNames() {
        List<String> recipeNames = getAllRecipeNames();
        System.out.println("\nAll recipes in the collection:");
        if (recipeNames.isEmpty()) {
            System.out.println("There are no recipes in the recipe box.");
        } else {
            for (Recipe recipe : listOfRecipes) {
                System.out.println(recipe.getName());
            }
        }
        System.out.println("");
    }
}
