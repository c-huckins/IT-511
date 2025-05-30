package SteppingStones.Veiw;

import SteppingStones.Controller.RecipeBox;
import SteppingStones.Model.Recipe;
import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Chris Huckins
 */
public class MainView extends JFrame {
    private JList list;
    private RecipeBox recipeColntroller;
    private JTextField nameField;
    private JCheckBox exactCheckBox;
    
    public MainView(RecipeBox recipeController) throws HeadlessException {
        this.recipeColntroller = recipeController;
        
        // Set up the window properties.
        setSize(580, 630);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Recipes");
        
        // Add the recipe name search field and label.
        JLabel nameJLabel = new JLabel("Recipe Name:");
        nameJLabel.setBounds(30, 30, 80, 30);
        add(nameJLabel);
        
        nameField = new JTextField();
        nameField.setBounds(120, 30, 410, 30);
        add(nameField);
        
        // Add the chebox and label to restrict searches to exact matches.
        exactCheckBox = new JCheckBox();
        exactCheckBox.setBounds(30, 70, 20, 30);
        add(exactCheckBox);
        
        JLabel exactJLabel = new JLabel("Exact Match");
        exactJLabel.setBounds(60, 70, 150, 30);
        add(exactJLabel);
        
        // Add the search button
        JButton searchRecipesButton = new JButton("Search");
        searchRecipesButton.setBounds(380, 70, 150, 30);
        searchRecipesButton.addActionListener((ActionEvent e) -> {
            System.out.println("Search button clicked");
            if (nameField.getText().isBlank()) {
                loadAllRecipes();
            }
            if (exactCheckBox.isSelected()) {
                loadRecipe();
            } else {
                loadMatchingRecipes();
            }
        });
        add(searchRecipesButton);
        
        // Add the component to hold the recipe list.
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 110, 500, 420);
        add(scrollPane);
        
        ArrayList<String> recipeNames = (ArrayList<String>) recipeController.getAllRecipeNames();
        
        // Create the list for the resipes and add it the the scroll component.
        list = new JList(recipeNames.toArray());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(list);
        
        // Add a button to add recipes.
        JButton addRecipesButton = new JButton("Add");
        addRecipesButton.setBounds(30, 540, 100, 30);
        addRecipesButton.addActionListener((ActionEvent e) -> {
            AddRecipeView addRecipeView = new AddRecipeView(recipeController);
            addRecipeView.setLocation(getLocation());
            addRecipeView.setCallingView(this);
            setVisible(false);
            addRecipeView.setVisible(true);
        });
        add(addRecipesButton);
        
        // A button to delete a given recipe.
        JButton deleteRecipesButton = new JButton("Delete");
        deleteRecipesButton.setBounds(164, 540, 100, 30);
        deleteRecipesButton.addActionListener((ActionEvent e) -> {
            if (list.getSelectedIndex() == -1) {
                return;
            }
            Object[] options = {"Yes", "No"};
            int option = JOptionPane.showOptionDialog(this, "This action cannot be undone. Proceed?", "Removal Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (option == 0) {
                String recipeName = (String)list.getSelectedValue();
                recipeController.deleteRecipe(recipeName);
                updateRecipeList();
            }
        });
        add(deleteRecipesButton);
      
        // Button to view the selected recipe.
        JButton viewRecipesButton = new JButton("View");
        viewRecipesButton.setBounds(297, 540, 100, 30);
        viewRecipesButton.addActionListener((ActionEvent e) -> {
            if (list.getSelectedIndex() != -1) {
                Recipe recipe = recipeController.getRecipe((String)list.getSelectedValue());
                if (recipe != null) {
                    ViewRecipesView viewRecipesView = new ViewRecipesView(recipe);
                    viewRecipesView.setLocation(getLocation());
                    viewRecipesView.setVisible(true);
                }
            }
        });
        add(viewRecipesButton);
        
        // A button that allow for updating the selected recipe. 
        JButton updateRecipesButton = new JButton("Update");
        updateRecipesButton.setBounds(430, 540, 100, 30);
        updateRecipesButton.addActionListener((ActionEvent e) -> {
            if (list.getSelectedIndex() != -1) {
                Recipe recipe = recipeController.getRecipe((String)list.getSelectedValue());
                if (recipe == null) {
                    return;
                }
                UpdateRecipeView updateRecipeView = new UpdateRecipeView(recipe, recipeColntroller);
                updateRecipeView.setLocation(getLocation());
                updateRecipeView.setCallingView(this);
                setVisible(false);
                updateRecipeView.setVisible(true);
            }
        });
        add(updateRecipesButton);
    }
    
    /**
     * Loads all recipes from the recipe box into the list.
     */
    private void loadAllRecipes() {
        list.setListData(recipeColntroller.getAllRecipeNames().toArray());
    }
    
    /**
     * Loads a the matching recipe to the list.
    */
    private void loadRecipe() {
        list.setListData(recipeColntroller.findRecipe(nameField.getText()).toArray());
    }
    
    /**
     * Loads all the recipes that contain the search text to the list.
    */
    private void loadMatchingRecipes() {
        list.setListData(recipeColntroller.findRecipesContainingText(nameField.getText()).toArray());
    }
    
    /**
     * Updates the list object with current recipe names based on the search field settings.
     */
    public void updateRecipeList() {
        if (nameField.getText().isBlank()) {
                loadAllRecipes();
            }
            if (exactCheckBox.isSelected()) {
                loadRecipe();
            } else {
                loadMatchingRecipes();
            }
    }
}
