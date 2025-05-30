/**
 *
 * @author Chris Huckins
 */

package SteppingStones;

import SteppingStones.Model.MeasurementUnitType;
import SteppingStones.Model.Recipe;
import SteppingStones.Model.Ingredient;
import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecipeTestNG {
    
    private ArrayList<Ingredient> ingredients;
    
    public RecipeTestNG() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient().addNewIngredient("ingredient 1", 
                1, 
                MeasurementUnitType.TEASPOON, 
                100, 
                MeasurementUnitType.TEASPOON));
        ingredients.add(new Ingredient().addNewIngredient("ingredient 2", 
                2, 
                MeasurementUnitType.TEASPOON, 
                100, 
                MeasurementUnitType.TEASPOON));
        ingredients.add(new Ingredient().addNewIngredient("ingredient 3", 
                1d, 
                (MeasurementUnitType)null, 
                100, 
                null));
    }

    @Test
    public void testAddNewRecipeWithName() {
        Recipe recipe = Recipe.addNewRecipe("test recipe");
        assertEquals(recipe.getName(), "test recipe");
        assertEquals(recipe.getServings(), 1);
        assertTrue(recipe.getIngredients().isEmpty());
        assertEquals(recipe.getTotalCalories(), 0d);
        recipe.setIngredients(ingredients);
        assertEquals(recipe.getTotalCalories(), 400d);
    }
    
    @Test
    public void testAddNewRecipeWithNameServings() {
        Recipe recipe = Recipe.addNewRecipe("test recipe", 3);
        assertEquals(recipe.getName(), "test recipe");
        assertEquals(recipe.getServings(), 3);
        assertTrue(recipe.getIngredients().isEmpty());
        assertEquals(recipe.getTotalCalories(), 0d);
        
    }
    
    @Test
    public void testAddNewRecipeWithNameServingsIngredients() {
        Recipe recipe = Recipe.addNewRecipe("test recipe", 3, ingredients);
        assertEquals(recipe.getName(), "test recipe");
        assertEquals(recipe.getServings(), 3);
        assertEquals(recipe.getIngredients().size(), 3);
        assertEquals(recipe.getTotalCalories(), 400d);
    }
    
    @Test
    public void testAddNewRecipeWithNameServingsIngredientsCalories() {
        Recipe recipe = Recipe.addNewRecipe("test recipe", 3, ingredients, 450);
        assertEquals(recipe.getName(), "test recipe");
        assertEquals(recipe.getServings(), 3);
        assertEquals(recipe.getIngredients().size(), 3);
        assertEquals(recipe.getTotalCalories(), 450d);
        recipe.setIngredients(ingredients);
        assertEquals(recipe.getTotalCalories(), 400d);
    }
    
    @Test
    public void testGetCaloriesPerServing() {
        Recipe recipe = Recipe.addNewRecipe("test recipe", 2, ingredients);
        assertEquals(recipe.getCaloriesPerServing(), 200);
        recipe.setTotalCalories(900);
        recipe.setServings(3);
        assertEquals(recipe.getCaloriesPerServing(), 300);
    }
}
