package SteppingStones.Model;

/**
 * Enum that define standard cooking measurements
 * 
 * @author Chris Huckins
 */
public enum MeasurementUnitType {
    TEASPOON(0, "tsp."),
    TABLESPOON(1, "tbsp."),
    CUP(2, "c."),
    PINT(3, "pt."),
    QUART(4, "qt"),
    GALLON(5, "gal."),
    NONE (6, "");

    private final int value;
    private final String abbrName;
    
    // Table of conversions between measurements
    private final double[][] conversionTable = 
        //TSP   TBSP    CUP     PT      QT      GAL             
        {{1d,   1d/3,   1d/48,  1d/96,  1d/192, 1d/768}, // TSP
         {3d,   1d,     1d/16,  1d/32,  1d/64,  1d/256}, // TBSP
         {48d,  16d,    1d,     1d/2,   1d/4,   1d/16},  // CUP
         {96d,  32d,    2d,     1d,     1d/2,   1d/8},   // PT
         {192d, 64d,    4d,     2d,     1d,     1d/4},   // QT
         {768d, 256d,   16d,    8d,     4d,     1d}};    // GAL

    /**
     * Constructor used to setup the measurement objects.
     * 
     * @param value
     * @param abbrName 
     */
    private MeasurementUnitType(int value, String abbrName) {
        this.value = value;
        this.abbrName = abbrName;
    }

    /**
     * Returns the multiplier value needed to convert the first measurement
     * to the second.
     * 
     * @param toType The type to be converted to.
     * @return 
     */
    public double getConversionMultiplierTo(MeasurementUnitType toType) {
        if (toType == null || this == NONE || toType == NONE) {
            return 1d;
        }
        return conversionTable[toType.value][this.value];
    }
    
    /**
     * Return the measurements short name.
     * 
     * @return 
     */
    public String getAbbrName() {
        return abbrName;
    } 
}
