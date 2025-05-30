package SteppingStones.Model;

/**
 * Class that defines the recipe ingredient data.
 * 
 * @author Chris Huckins
 */
public class Ingredient {
    private String name;
    private double amount;
    private MeasurementUnitType unitOfMeasurement;
    private int caloriesPerMeasurementUnit;
    private MeasurementUnitType calorieMeasurementType;

    /**
     * All Arg Constructor
     * 
     * @param name The name of the ingredient.
     * @param amount Numeric value of how much of the ingredient is needed.
     * @param unitOfMeasurement The unit the ingredient is measured in.
     * @param caloriesPerMeasurementUnit The number of calories in a given unit of measurement.
     * @param calorieMeasurementType The unit of measurement for the calories.
     * 
     */

    public Ingredient(String name, double amount, MeasurementUnitType unitOfMeasurement, int caloriesPerMeasurementUnit, MeasurementUnitType calorieMeasurementType) {
        this.name = name;
        this.amount = amount;
        this.unitOfMeasurement = unitOfMeasurement;
        this.caloriesPerMeasurementUnit = caloriesPerMeasurementUnit;
        this.calorieMeasurementType = calorieMeasurementType;
    }
    
    /**
     * No Arg constructor
     */
    public Ingredient() {
        this.name = "";
        this.amount = 1d;
        this.unitOfMeasurement = null;
        this.caloriesPerMeasurementUnit = 0;
        this.calorieMeasurementType = null;
    }
    /////////////////////
    // Setters
    /////////////////////

    /**
     * @param name The name of the ingredient.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param amount The amount of the ingredient.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
    /**
     * @param unitOfMeasurement The unit the ingredient is measured in.
     */
    public void setUnitOfMeasurement(MeasurementUnitType unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
    /**
     * @param caloriesPerMeasurementUnit The number of calories in a given unit of measurement.
     */
    public void setCaloriesPerMeasurementUnit(int caloriesPerMeasurementUnit) {
        this.caloriesPerMeasurementUnit = caloriesPerMeasurementUnit;
    }
    /**
     * @param calorieMeasurementType The unit of measurement for the calories.
     */
    public void setCalorieMeasurementType(MeasurementUnitType calorieMeasurementType) {
        this.calorieMeasurementType = calorieMeasurementType;
    }

    /////////////////////
    // Getters
    /////////////////////

    /**
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * @return 
     */
    public double getAmount() {
        return amount;
    }
    /**
     * @return 
     */
    public MeasurementUnitType getUnitOfMeasurement() {
        return unitOfMeasurement;
    }
    /**
     * @return 
     */
    public int getCaloriesPerMeasurementUnit() {
        return caloriesPerMeasurementUnit;
    }
    /**
     * @return 
     */
    public MeasurementUnitType getCalorieMeasurementType() {
        return calorieMeasurementType;
    }

    /////////////////////
    // Additional Methods
    /////////////////////

    /**
     * @return 
     */
    public double getTotalCalories() {
        if (calorieMeasurementType == null) {
            return amount * caloriesPerMeasurementUnit;
        } else {
            // Adjust ingredient calories to match the recipies measurement unit
            double convertedCalories = caloriesPerMeasurementUnit * calorieMeasurementType.getConversionMultiplierTo(unitOfMeasurement);
            // Calculate calories based on the adjust calories multiplied by the amount needed.
            return convertedCalories * amount;
        }
    }
    
    /**
     * Creates a new ingredient.
     * 
     * @param name
     * @return 
     */
    public Ingredient addNewIngredient(String name) {
        setName(name);
        return this;
    }
    
    /**
     * 
     * @param name
     * @param amount
     * @return 
     */
    public Ingredient addNewIngredient(String name, double amount) {
        setName(name);
        setAmount(amount);
        return this;
    }
    
    /**
     * 
     * @param name
     * @param amount
     * @param measurementUnitType
     * @return 
     */
    public Ingredient addNewIngredient(String name, double amount, MeasurementUnitType measurementUnitType) {
        setName(name);
        setAmount(amount);
        setUnitOfMeasurement(unitOfMeasurement);
        return this;
    }
    
    /**
     * 
     * @param name
     * @param amount
     * @param measurementUnitType
     * @return 
     */
    public Ingredient addNewIngredient(String name, double amount, String measurementUnitType) {
        setName(name);
        setAmount(amount);
        setUnitOfMeasurement(MeasurementUnitType.valueOf(measurementUnitType.toUpperCase()));
        return this;
    }
    
    /**
     * 
     * @param name
     * @param amount
     * @param measurementUnitType
     * @param calories
     * @return 
     */
    public Ingredient addNewIngredient(String name,
                                       double amount,
                                       MeasurementUnitType measurementUnitType,
                                       int calories){
        setName(name);
        setAmount(amount);
        setUnitOfMeasurement(unitOfMeasurement);
        setCaloriesPerMeasurementUnit(calories);
        return this;
    }
    
    /**
     * 
     * @param name
     * @param amount
     * @param measurementUnitType
     * @param calories
     * @return 
     */
    public Ingredient addNewIngredient(String name,
                                       double amount,
                                       String measurementUnitType,
                                       int calories){
        setName(name);
        setAmount(amount);
        setUnitOfMeasurement(MeasurementUnitType.valueOf(measurementUnitType.toUpperCase()));
        setCaloriesPerMeasurementUnit(calories);
        return this;
    }
    
    /**
     * 
     * @param name
     * @param amount
     * @param measurementUnitType
     * @param calories
     * @param calorieMeasurementUnitType
     * @return 
     */
    public Ingredient addNewIngredient(String name,
                                       double amount,
                                       MeasurementUnitType measurementUnitType,
                                       int calories,
                                       MeasurementUnitType calorieMeasurementUnitType){
        setName(name);
        setAmount(amount);
        setUnitOfMeasurement(measurementUnitType);
        setCaloriesPerMeasurementUnit(calories);
        setCalorieMeasurementType(calorieMeasurementUnitType);
        return this;
    }
    
    /**
     * 
     * @param name
     * @param amount
     * @param measurementUnitType
     * @param calories
     * @param calorieMeasurementUnitType
     * @return 
     */
    public Ingredient addNewIngredient(String name,
                                       double amount,
                                       String measurementUnitType,
                                       int calories,
                                       String calorieMeasurementUnitType){
        setName(name);
        setAmount(amount);
        setUnitOfMeasurement(MeasurementUnitType.valueOf(measurementUnitType.toUpperCase()));
        setCaloriesPerMeasurementUnit(calories);
        setCalorieMeasurementType(MeasurementUnitType.valueOf(calorieMeasurementUnitType.toUpperCase()));
        return this;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        String s;
        s = "Ingredient Name: " + name + "\n";
        s += "Ingredient Amount: " + amount + " " + unitOfMeasurement.getAbbrName() +"\n";
        s += "Ingredient Calories: " + getTotalCalories() + " (" + caloriesPerMeasurementUnit + " calories per " + calorieMeasurementType.getAbbrName() + ")";
        return s;
    }
    
    /**
     * Creates a formatted string for displaying as part of a recipe. 
     * 
     * @return 
     */
    public String getRecipeFormatedString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(amount);
        sb.append(" ");
        if (unitOfMeasurement != null || !unitOfMeasurement.equals(MeasurementUnitType.NONE)) {
            sb.append(unitOfMeasurement.getAbbrName());
            sb.append(" ");
        }
        sb.append(name);
        return sb.toString();
    }
}
