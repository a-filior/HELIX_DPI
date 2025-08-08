package TEST_UNITAIRE;


import BACASABLE.Calculator;
import BACASABLE.CalculatorImpl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import static org.junit.Assert.*;

import org.junit.Test;
/**
 *
 * @author antoi
 */
public class CalculatorTest {
    
    @Test(expected = ArithmeticException.class)
    public final void testDivide() {
        Calculator calc = new CalculatorImpl();      

        assertEquals("Division A et B positifs", 1, calc.divide(5, 5));        
        assertEquals("Division A = 0", 0, calc.divide(0, 5));        
        assertEquals("Division A négatif", -1, calc.divide(-5, 5));
        assertEquals("Division B négatif", -1, calc.divide(5, -5));
        assertEquals("Division A et B négatifs", 1, calc.divide(-5, -5));
        assertEquals("Division B = 0", new ArithmeticException(), calc.divide(5, 0));
        assertEquals("Division A et B = 0", new ArithmeticException(), calc.divide(0, 0));
        

        
    }   
            
    @Test
    public final void testAdd() {
        Calculator calc = new CalculatorImpl();

        assertTrue("a et b positif", calc.add(5, 5) == 10);
        assertTrue("a nul", calc.add(0, 5) == 5);
        assertTrue("b nul", calc.add(5, 0) == 5);
        assertTrue("a et b nuls", calc.add(0, 0) == 0);
        assertTrue("a negatif", calc.add(-5, 5) == 0);
        assertTrue("b negatif", calc.add(5, -5) == 0);
        assertTrue("a et b negatif", calc.add(-5, -5) == -10);
    }
}
