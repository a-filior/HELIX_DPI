/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACASABLE;

/**
 *
 * @author antoi
 */
public class CalculatorImpl implements Calculator{
    @Override
    public int add(int a, int b) {
      int res = a;
      if (b > 0) {
          while(b-- != 0) {
                            res++;
                    }
            }
      else if (b < 0) {
          while(b++ != 0) {
                            res--;
                    }
            }
      return res;
    }

    @Override
    public int multiply(int a, int b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int divide(int a, int b) {
      if (b == 0) {
          throw new ArithmeticException();
            }
      boolean resEstNegatif = false;
      int res = 0;
      if ( a < 0) {
          resEstNegatif = !resEstNegatif;
          a = -a;
      }
      if ( b < 0) {
          resEstNegatif = !resEstNegatif;
          b = -b;
      }
      while (a > 0) {
              a = substract(a, b);
          res++;
      }
      if (resEstNegatif) {
          res = -res;
            }
      return res;
    }
    
    @Override
    public int substract(int a, int b) {
        return (a-b);
    }
}
