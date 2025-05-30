package SteppingStones.Model;

import java.util.ArrayList;

/**
 * Class used to contain the information about the recipe.
 * 
 * @author Chris Huckins
 */
public class Recipe {
    private String name;
    private int servings;
    private ArrayList<Ingredient> ingredients;
    private double totalCalories;

    //********************************
    // Constructors
    //********************************
   
    /**
     * All Args Constructor
     * 
     * @param name
     * @param servings
     * @param ingredients
     * @param totalCalories 
     */
    public Recipe(String name, int servings, ArrayList<Ingredient> ingredients, double totalCalories) {
        this.name = name;
        this.servings = servings;
        this.ingredients = ingredients;
        this.totalCalories = totalCalories;
    }

    /**
     * No Args Constructor
     */
    public Recipe() {
        this.name = "";
        this.servings = 1;
        this.ingredients = null;
        this.totalCalories = 0d;
    }

    //********************************
    // Accessors
    //********************************
    
    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return 
     */
    public int getServings() {
        return servings;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    
    /**
     * 
     * @return 
     */
    public double getTotalCalories() {
        return totalCalories;
    }

    //********************************
    // Mutators
    //********************************
    
    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @param servings 
     */
    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     * 
     * @param ingredients 
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.totalCalories = calculateTotalCalories(ingredients);
    }
    
    /**
     * 
     * @param totalCalories 
     */
    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
    
    //********************************
    // Additional Methods
    //********************************
    
    /**
     * Creates a new recipe.
     * 
     * @param name The name of the recipe.
     * @param servings The number of serving the recipe makes.
     * @param ingredients A list of ingredients in the recipe.
     * @param totalCalories The total amount of calories in the complete dish.
     * @return 
     */
    static public Recipe addNewRecipe(String name, int servings, ArrayList<Ingredient> ingredients, double totalCalories) {
        return new Recipe(name, servings, ingredients, totalCalories);
    }
    
    /**
     * Creates a new recipe. Calculates the recipe's total calories from the 
     * provided ingredients list.
     * 
     * @param name
     * @param servings
     * @param ingredients
     * @return 
     */
    static public Recipe addNewRecipe(String name, int servings, ArrayList<Ingredient> ingredients) {
        return new Recipe(name, servings, ingredients, calculateTotalCalories(ingredients));
    }
    
    /**
     * Creates a new recipe with an empty ingredient list and 0 calories.
     * 
     * @param name
     * @param servings
     * @return 
     */
    static public Recipe addNewRecipe(String name, int servings) {
        return new Recipe(name, servings, new ArrayList<>(), 0d);
    }
    
    /**
     * Creates a recipe with only a name. Servings is set to 1 with an empty 
     * ingredient list and 0 calories.
     * 
     * @param name
     * @return 
     */
    static public Recipe addNewRecipe(String name) {
        return new Recipe(name, 1, new ArrayList<>(), 0d);
    }
    
    /**
     * Calculates the total calories for the recipe based on the 
     * ingredients.
     * 
     * @param ingredients
     * @return 
     */
    static public double calculateTotalCalories(ArrayList<Ingredient> ingredients) {
        double totalCalories = 0d;
        for (Ingredient ingredient : ingredients) {
            totalCalories += ingredient.getTotalCalories();
        } 
        return totalCalories;
    }
    
    /**
     * Calculates and returns the calories per serving.
     * 
     * @return 
     */
    public int getCaloriesPerServing() {
        return (int)Math.round(totalCalories / servings);
    }
    
    /**
     * Prints the recipe to the console.
     */
    public void printRecipe() {
        System.out.println(getDiplayString());
    }
    
    /**
     * Generates a print ready string of the recipe.
     * 
     * @return 
     */
    public String getDiplayString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe: "); sb.append(name);
        sb.append("\nServes: "); sb.append(servings);
        sb.append("\n\nIngredients:\n");
        for (Ingredient ingredient : ingredients) {
            sb.append(ingredient.getRecipeFormatedString()); 
            sb.append("\n");
        }
        sb.append("\nEach serving has ");
        sb.append(getCaloriesPerServing());
        sb.append(" calories.");
        return sb.toString();
    }
}
