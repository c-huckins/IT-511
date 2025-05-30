package SteppingStones;

import SteppingStones.Controller.RecipeBox;
import SteppingStones.Model.Recipe;
import SteppingStones.Veiw.MainView;
import java.util.ArrayList;

/**
 *
 * @author Chris Huckins
 */
public class RecipeManager {
    
    public static void main(String[] args) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        RecipeBox recipeBox = new RecipeBox(recipes);
        MainView mainMenu = new MainView(recipeBox);
        mainMenu.setVisible(true);
    }
}
