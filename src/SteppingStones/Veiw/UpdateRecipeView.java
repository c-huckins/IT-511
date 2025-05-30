package SteppingStones.Veiw;

import SteppingStones.Controller.RecipeBox;
import SteppingStones.Model.Ingredient;
import SteppingStones.Model.MeasurementUnitType;
import SteppingStones.Model.Recipe;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Chris Huckins
 */
public class UpdateRecipeView extends JFrame {
    private final JButton backButton;
    private MainView callingView;
    private final JTextField recipeNameField;
    private final JTextField servingsField;
    private final JLabel recipeCaloriesLabel;
    private JList<Ingredient> ingredientList;
    private final JTextField ingedientNameField;
    private final JTextField amountField;
    private final JComboBox<MeasurementUnitType> amountComboBox;
    private final JButton cancelUpdateIngredientButton;
    private final JTextField caloriesField;
    private final JComboBox<MeasurementUnitType> caloriesComboBox;
    private final JTextField recipeCaloriesField = new JTextField();
    private DefaultListModel<Ingredient> ingredients;
    private double totalRecipeCalories = 0d;
    private int modifyIndex = -1;
    private final Recipe recipeToUpdate;
    private final RecipeBox recipeController;

    public UpdateRecipeView(Recipe recipeToUpdate, RecipeBox recipeController) throws HeadlessException {
        this.recipeToUpdate = recipeToUpdate;
        this.recipeController = recipeController;
        
        setLayout(null);
        setSize(580, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add Recipe");
        setResizable(false);
        
        JLabel nameJLabel = new JLabel("Recipe Name:");
        nameJLabel.setBounds(30, 30, 80, 30);
        add(nameJLabel);
        
        recipeNameField = new JTextField();
        recipeNameField.setBounds(150, 30, 380, 30);
        add(recipeNameField);
        
        JLabel servingsJLabel = new JLabel("Number Of Servings:");
        servingsJLabel.setBounds(30, 70, 120, 30);
        add(servingsJLabel);
        
        servingsField = new JTextField();
        servingsField.setBounds(150, 70, 130, 30);
        add(servingsField);
        
        recipeCaloriesLabel = new JLabel();
        recipeCaloriesLabel.setText("Total Calories: " + Math.round(totalRecipeCalories));
        recipeCaloriesLabel.setBounds(290, 70, 150, 30);
        add(recipeCaloriesLabel);
        
        recipeCaloriesField.setBounds(450, 70, 80, 30);
        add(recipeCaloriesField);
        
        JLabel calorieAdjustLabel = new JLabel("Adj. Calories");
        calorieAdjustLabel.setBounds(455, 90, 80, 30);
        add(calorieAdjustLabel);
        
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setBounds(30, 110, 150, 30);
        add(ingredientsLabel);
        
        ingredients = new DefaultListModel<>();
        ingredientList = new JList<>(ingredients);
        ingredientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ingredientList.setCellRenderer(new UpdateRecipeView.IngredientRenderer());
        
        ingredientList.addListSelectionListener(e -> {
            int value = ((JList<Ingredient>)(e.getSource())).getSelectedIndex();
            System.out.println("value: " + value);
            ingredientList.setSelectedIndex(value);
        });

        
        JScrollPane ingredientsPane =  new JScrollPane(ingredientList);
        ingredientsPane.setBounds(30, 140, 270, 400);
        add(ingredientsPane);
   
        ingredientList = new JList<>(ingredients);
        ingredientList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        ingredientsPane.add(ingredientList);
        
        JLabel ingredientNameLabel = new JLabel("Ingredient Name:");
        ingredientNameLabel.setBounds(310, 140, 150, 30);
        add(ingredientNameLabel);
        
        ingedientNameField = new JTextField();
        ingedientNameField.setBounds(310, 170, 220, 30);
        add(ingedientNameField);
        
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(310, 200, 150, 30);
        add(amountLabel);
        
        amountField = new JTextField();
        amountField.setBounds(310, 230, 105, 30);
        add(amountField);
        
        amountComboBox = new JComboBox<>();
        amountComboBox.setBounds(420, 230, 105, 30);
        amountComboBox.addItem(MeasurementUnitType.TEASPOON);
        amountComboBox.addItem(MeasurementUnitType.TABLESPOON);
        amountComboBox.addItem(MeasurementUnitType.CUP);
        amountComboBox.addItem(MeasurementUnitType.PINT);
        amountComboBox.addItem(MeasurementUnitType.QUART);
        amountComboBox.addItem(MeasurementUnitType.GALLON);
        amountComboBox.addItem(MeasurementUnitType.NONE);
        amountComboBox.setSelectedIndex(-1);
        add(amountComboBox);
        
        JLabel caloriesLabel = new JLabel("Calories:");
        caloriesLabel.setBounds(310, 260, 150, 30);
        add(caloriesLabel);
        
        JLabel caloriesPerLabel = new JLabel("Per:");
        caloriesPerLabel.setBounds(420, 260, 150, 30);
        add(caloriesPerLabel);
        
        caloriesField = new JTextField();
        caloriesField.setBounds(310, 290, 105, 30);
        add(caloriesField);
        
        caloriesComboBox = new JComboBox<>();
        caloriesComboBox.setBounds(420, 290, 105, 30);
        caloriesComboBox.addItem(MeasurementUnitType.TEASPOON);
        caloriesComboBox.addItem(MeasurementUnitType.TABLESPOON);
        caloriesComboBox.addItem(MeasurementUnitType.CUP);
        caloriesComboBox.addItem(MeasurementUnitType.PINT);
        caloriesComboBox.addItem(MeasurementUnitType.QUART);
        caloriesComboBox.addItem(MeasurementUnitType.GALLON);
        caloriesComboBox.addItem(MeasurementUnitType.NONE);
        caloriesComboBox.setSelectedIndex(-1);
        add(caloriesComboBox);
        
        JButton addIngredientButton = new JButton("Add");
        addIngredientButton.setBounds(375, 330, 150, 30);
        
        addIngredientButton.addActionListener((ActionEvent e) -> {
            Ingredient ingredient = buildIngredient();
            if (ingredient != null) {
                totalRecipeCalories += ingredient.getTotalCalories();
                recipeCaloriesLabel.setText("Total Calories: " + Math.round(totalRecipeCalories));
                ingredients.addElement(ingredient);
                clearIngredientFields();
            } 
        });
        add(addIngredientButton);
        
        JButton updateIngredientButton = new JButton("Update");
        updateIngredientButton.setBounds(375, 330, 150, 30);
        
        cancelUpdateIngredientButton = new JButton("Cancel");
        
        updateIngredientButton.addActionListener((ActionEvent e) -> {
            double originalCalories = ingredients.elementAt(modifyIndex).getTotalCalories();
            Ingredient ingredient = buildIngredient();
           
            if (ingredient != null) {
                totalRecipeCalories += ingredient.getTotalCalories() - originalCalories;
                recipeCaloriesLabel.setText("Total Calories: " + Math.round(totalRecipeCalories));
                ingredients.set(modifyIndex, ingredient);
                clearIngredientFields();
                updateIngredientButton.setVisible(false);
                cancelUpdateIngredientButton.setVisible(false);
                addIngredientButton.setVisible(true);
                modifyIndex = -1;
            } 
        });
        
        add(updateIngredientButton);
        updateIngredientButton.setVisible(false);
        
        JButton modifyIngredientButton = new JButton("Modify");
        modifyIngredientButton.setBounds(310, 470, 150, 30);
              
        add(modifyIngredientButton);
        
        JButton removeIngredientButton = new JButton("Remove");
        removeIngredientButton.setBounds(310, 510, 150, 30);
        
        removeIngredientButton.addActionListener((ActionEvent e) -> {
            int index = ingredientList.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "No ingredient selected.", "Ingredient Selection Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object[] options = {"Yes", "No"};
                int option = JOptionPane.showOptionDialog(this, "This action cannot be undone. Proceed?", "Removal Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (option == 0) {
                    totalRecipeCalories -= ingredientList.getSelectedValue().getTotalCalories();
                    recipeCaloriesLabel.setText("Total Calories: " + Math.round(totalRecipeCalories));
                    ingredients.remove(index);
                }
            }   
        });
        
        add(removeIngredientButton);
        
        
        cancelUpdateIngredientButton.setBounds(375, 370, 150, 30);
        
        cancelUpdateIngredientButton.addActionListener((ActionEvent e) -> {
            clearIngredientFields();
            updateIngredientButton.setVisible(false);
            cancelUpdateIngredientButton.setVisible(false);
            addIngredientButton.setVisible(true);
        });
        
        modifyIngredientButton.addActionListener((ActionEvent e) -> {
            modifyIndex = ingredientList.getSelectedIndex();
            System.out.println(ingredientList.getSelectedIndex());
            if (modifyIndex == -1) {
                JOptionPane.showMessageDialog(this, "No ingredient selected.", "Ingredient Selection Error", JOptionPane.INFORMATION_MESSAGE);

            } else {
                addIngredientButton.setVisible(false);
                updateIngredientButton.setVisible(true);
                cancelUpdateIngredientButton.setVisible(true);
                Ingredient selectedIngredient = ingredientList.getSelectedValue();
                ingedientNameField.setText(selectedIngredient.getName());
                amountField.setText("" + selectedIngredient.getAmount());
                amountComboBox.setSelectedItem(selectedIngredient.getUnitOfMeasurement());
                caloriesField.setText("" + selectedIngredient.getCaloriesPerMeasurementUnit());
                caloriesComboBox.setSelectedItem(selectedIngredient.getCalorieMeasurementType());  
            }
        });
        
        add(cancelUpdateIngredientButton);
        cancelUpdateIngredientButton.setVisible(false);
        
        JButton saveRecipeButton = new JButton("Save");
        saveRecipeButton.setBounds(30, 550, 75, 30);
     
        saveRecipeButton.addActionListener((ActionEvent e) -> {
            Recipe recipe = buildRecipe();
            if (recipe != null) {
                recipeToUpdate.setName(recipe.getName());
                recipeToUpdate.setServings(recipe.getServings());
                recipeToUpdate.setIngredients(recipe.getIngredients());
                if (!recipeCaloriesField.getText().isBlank()) {
                    recipeToUpdate.setTotalCalories(recipe.getTotalCalories());
                }
                callingView.updateRecipeList();
                callingView.setVisible(true);
                dispose();
            }
        });
        
        add(saveRecipeButton);     
        
        backButton = new JButton("Back");
        backButton.setBounds(470, 550, 75, 30);
        
        backButton.addActionListener((ActionEvent e) -> {
            System.out.println("Add back button clicked");
            if (callingView != null) {
                setVisible(false);
                callingView.setVisible(true);
            }
        });
        add(backButton);
        
        setRecipeData();
        
    }  
    
    private void setRecipeData() {
        recipeNameField.setText(recipeToUpdate.getName());
        servingsField.setText("" + recipeToUpdate.getServings());
        ingredients.addAll(recipeToUpdate.getIngredients());
        recipeCaloriesLabel.setText("Total Calories: " + Math.round(recipeToUpdate.getTotalCalories()));
    }
    
    
    public void setCallingView(MainView callingView) {
        this.callingView = callingView;
    }
    
    private Recipe buildRecipe() {
        String errorString = "";
        String recipeName = recipeNameField.getText();
        if (recipeName.isBlank()) {
            errorString += "Recipe must have a name.\n";
        } else if (!recipeController.findRecipe(recipeName).isEmpty()) {
            errorString += "Recipe name already used. Please enter a differnt name.\n";
        }
        int servings = 0;
        try {
            servings = Integer.parseInt(servingsField.getText());
            if (servings <= 0) {
                errorString += "Servings must be greater than 0.\n";
            }
        } catch (NumberFormatException exception) {
            errorString += "Servings must be a number.\n";
        }
        ArrayList<Ingredient> recipeIngredients = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            recipeIngredients.add(ingredients.getElementAt(i));
        }
        if (!errorString.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please correct the following errors:\n\n" + errorString, "Recipe Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            
            if (recipeCaloriesField.getText().isBlank()) {
                return Recipe.addNewRecipe(recipeName, servings, recipeIngredients);
            } else {
                try {
                    double recipeCalories = Double.parseDouble(recipeCaloriesField.getName());
                    if (recipeCalories < 0d) {
                        JOptionPane.showMessageDialog(this, "Recipe Calories cannot be negative." + errorString, "Recipe Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                    } else {
                        return Recipe.addNewRecipe(recipeName, servings, recipeIngredients, recipeCalories);
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(this, "Recipe Calories must be a number." + errorString, "Recipe Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            }
        }
    }
    
    private Ingredient buildIngredient() {
        String errorString = "";
        String ingredientName = ingedientNameField.getText();
        if (ingredientName.isBlank()) {
            errorString += "Ingredient name cannot be blank.\n";
        }
        double ingredientAmount = 0d;
        try {
            ingredientAmount = Double.parseDouble(amountField.getText());
            if (ingredientAmount <= 0d) {
                errorString += "Ingredient amount must be greater than 0.\n";
            }
        } catch (NumberFormatException exception) {
            errorString += "Ingredient amount must be a number.\n";
        }
        MeasurementUnitType amountType = (MeasurementUnitType)amountComboBox.getSelectedItem();
        if (amountType == null) {
            errorString += "A selection for the amount type is missing.\n";
        }
        int calories = 0;
        try {
            calories = Integer.parseInt(caloriesField.getText());
            if (calories < 0) {
                errorString += "Calories cannot be negative.\n";
            }
        } catch (NumberFormatException exception) {
            errorString += "Calories for the ingredient must be a number.\n";
        }
        MeasurementUnitType calorieType = (MeasurementUnitType)caloriesComboBox.getSelectedItem();
        if (calorieType == null) {
            errorString += "A selection for the calorie type is missing.\n";
        }
        if (errorString.isBlank()) {
            return(new Ingredient().addNewIngredient(ingredientName, ingredientAmount, amountType, calories, calorieType));
        } else {
            JOptionPane.showMessageDialog(this, "Please correct the following errors:\n\n" + errorString, "Ingredient Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void clearIngredientFields() {
        ingedientNameField.setText("");
        amountField.setText("");
        caloriesField.setText("");
        amountComboBox.setSelectedIndex(-1);
        caloriesComboBox.setSelectedIndex(-1);
    }    
    
    
    public class IngredientRenderer extends JLabel implements ListCellRenderer<Ingredient> {
 
        @Override
        public Component getListCellRendererComponent(JList<? extends Ingredient> list, Ingredient ingredient, int index,
            boolean isSelected, boolean cellHasFocus) {
            
            setIcon(null);
            setText(ingredient.getRecipeFormatedString()+ "; " + Math.round(ingredient.getTotalCalories()) + " cal. (" + ingredient.getCaloriesPerMeasurementUnit() + " cal. per " + ingredient.getCalorieMeasurementType().getAbbrName() + ")");
            
            Color foregroundColor;
            Color backgroundColor;
            
            if (isSelected) {
                foregroundColor = Color.WHITE;
                backgroundColor = Color.BLUE;
            } else {
                foregroundColor = Color.BLACK;
                backgroundColor = Color.WHITE;
            }
            
            setForeground(foregroundColor);
            setBackground(backgroundColor);
            setOpaque(true);
            
            return this;
        }
    }
}
