package SteppingStones;

import SteppingStones.Model.MeasurementUnitType;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Chris Huckins
 */
public class MeasurementUnitTypeNGTest {
    @DataProvider(name = "test-data")
    public Object[][] measurementConversions() {
        return new Object[][] 
                {{MeasurementUnitType.TEASPOON, MeasurementUnitType.TEASPOON, 1d},
                 {MeasurementUnitType.TEASPOON, MeasurementUnitType.TABLESPOON, 3d},
                 {MeasurementUnitType.TEASPOON, MeasurementUnitType.CUP, 48d},
                 {MeasurementUnitType.TEASPOON, MeasurementUnitType.PINT, 96d},
                 {MeasurementUnitType.TEASPOON, MeasurementUnitType.QUART, 192d},
                 {MeasurementUnitType.TEASPOON, MeasurementUnitType.GALLON, 768d},
                 
                 {MeasurementUnitType.TABLESPOON, MeasurementUnitType.TEASPOON, 1d/3},
                 {MeasurementUnitType.TABLESPOON, MeasurementUnitType.TABLESPOON, 1d},
                 {MeasurementUnitType.TABLESPOON, MeasurementUnitType.CUP, 16d},
                 {MeasurementUnitType.TABLESPOON, MeasurementUnitType.PINT, 32d},
                 {MeasurementUnitType.TABLESPOON, MeasurementUnitType.QUART, 64d},
                 {MeasurementUnitType.TABLESPOON, MeasurementUnitType.GALLON, 256d},
                 
                 {MeasurementUnitType.CUP, MeasurementUnitType.TEASPOON, 1d/48},
                 {MeasurementUnitType.CUP, MeasurementUnitType.TABLESPOON, 1d/16},
                 {MeasurementUnitType.CUP, MeasurementUnitType.CUP, 1d},
                 {MeasurementUnitType.CUP, MeasurementUnitType.PINT, 2d},
                 {MeasurementUnitType.CUP, MeasurementUnitType.QUART, 4d},
                 {MeasurementUnitType.CUP, MeasurementUnitType.GALLON, 16d},
                 
                 {MeasurementUnitType.PINT, MeasurementUnitType.TEASPOON, 1d/96},
                 {MeasurementUnitType.PINT, MeasurementUnitType.TABLESPOON, 1d/32},
                 {MeasurementUnitType.PINT, MeasurementUnitType.CUP, 1d/2},
                 {MeasurementUnitType.PINT, MeasurementUnitType.PINT, 1d},
                 {MeasurementUnitType.PINT, MeasurementUnitType.QUART, 2d},
                 {MeasurementUnitType.PINT, MeasurementUnitType.GALLON, 8d},
                 
                 {MeasurementUnitType.QUART, MeasurementUnitType.TEASPOON, 1d/192},
                 {MeasurementUnitType.QUART, MeasurementUnitType.TABLESPOON, 1d/64},
                 {MeasurementUnitType.QUART, MeasurementUnitType.CUP, 1d/4},
                 {MeasurementUnitType.QUART, MeasurementUnitType.PINT, 1d/2},
                 {MeasurementUnitType.QUART, MeasurementUnitType.QUART, 1d},
                 {MeasurementUnitType.QUART, MeasurementUnitType.GALLON, 4d},
                 
                 {MeasurementUnitType.GALLON, MeasurementUnitType.TEASPOON, 1d/768},
                 {MeasurementUnitType.GALLON, MeasurementUnitType.TABLESPOON, 1d/256},
                 {MeasurementUnitType.GALLON, MeasurementUnitType.CUP, 1d/16},
                 {MeasurementUnitType.GALLON, MeasurementUnitType.PINT, 1d/8},
                 {MeasurementUnitType.GALLON, MeasurementUnitType.QUART, 1d/4},
                 {MeasurementUnitType.GALLON, MeasurementUnitType.GALLON, 1d}};
    }

    @Test (dataProvider = "test-data")
    public void ValidateConversionMultipliers(MeasurementUnitType from, MeasurementUnitType to, double expected) {
        double conversion = from.getConversionMultiplierTo(to);
        assertEquals(conversion, expected);
    }
}
