package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//@DisplayName("Kocurek") - potencjalnie niebezpieczne bo wprowadza zamęt przy wyszukiwaniu co nie działa
class CalculatorAppTest {

    @Test
    public void addTwoPositiveIntegers() {
        //arrange
        CalculatorApp calculator = new CalculatorApp();
        //act
        int suma = calculator.add(1, 2);
        //assert
        Assertions.assertEquals(3, suma, "Sprawdź czy dodawanie liczb całkowitych działa");

    }

    @Test
    public void addTwoPositiveDoubles() {
        //arrange
        CalculatorApp calculator = new CalculatorApp();
        //act
        double suma = calculator.add(2.1, 4.1);
        //assert
        Assertions.assertEquals(6.2, suma, 0.00000001, "Sprawdź czy dodawanie liczb zmiennoprzecinkowych działa");
        //tu delta musi być mniejsza niż ilośc miejsc po przecinku w wyniku
        // assertEquals(6.2 , suma,"Sprawdź czy dodawanie liczb zmiennoprzecinkowych działa"); dla Assertions.* w polu import static

    }

    @Test
    public void divideTwoPositiveDouble(){
        CalculatorApp calculator = new CalculatorApp();
        double wynikDziel = calculator.divide(2.0, 3.0);
        Assertions.assertEquals(0.66666, wynikDziel, 0.0001, "Sprawdź czy dzielenie dwóch liczb zmiennoprzecinkowych działa");
        //tu delta musi być większa niż ilośc miejsc po przecinku w wyniku
    }
}