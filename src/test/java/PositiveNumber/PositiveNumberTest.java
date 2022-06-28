package PositiveNumber;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.Iterator;

public class PositiveNumberTest {
    @DataProvider(name="numbers")
    public Object [][] numbers(){
        return new Object[][]{
                {-1, false}, {3, true}, {0, false}
        };
    }


    @Test(dataProvider = "numbers")
    public void testIsNumberPositive(int num, boolean isPositive) {
        assertEquals(PositiveNumber.isNumberPositive(num), isPositive);
    }
}