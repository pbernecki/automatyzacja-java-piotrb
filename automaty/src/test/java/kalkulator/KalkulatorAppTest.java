package kalkulator;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//@DisplayName("Kocurek") - potencjalnie niebezpieczne bo wprowadza zamęt przy wyszukiwaniu co nie działa
class KalkulatorAppTest {

    @Test
    public void addTwoPositiveIntegers() {
        //arrange
        KalkulatorApp kalkulatorek = new KalkulatorApp();
        //act
        int suma = kalkulatorek.add(1, 2);
        //assert
        Assertions.assertEquals(3, suma, "Sprawdź czy dodawanie liczb całkowitych działa");

    }

    @Test
    public void addTwoPositiveDoubles() {
        //arrange
        KalkulatorApp kalkulatorek = new KalkulatorApp();
        //act
        double suma = kalkulatorek.add(2.1, 4.1);
        //assert
        Assertions.assertEquals(6.2, suma, 0.00000001, "Sprawdź czy dodawanie liczb zmiennoprzecinkowych działa");
        //tu delta musi być mniejsza niż ilośc miejsc po przecinku w wyniku
        // assertEquals(6.2 , suma,"Sprawdź czy dodawanie liczb zmiennoprzecinkowych działa"); dla Assertions.* w polu import static

    }

    @Test
    public void divideTwoPositiveDouble(){
        KalkulatorApp kalkulatorek = new KalkulatorApp();
        double wynikDziel = kalkulatorek.divide(2.0, 3.0);
        Assertions.assertEquals(0.66666, wynikDziel, 0.0001, "Sprawdź czy dzielenie dwóch liczb zmiennoprzecinkowych działa");
        //tu delta musi być większa niż ilośc miejsc po przecinku w wyniku
    }
}