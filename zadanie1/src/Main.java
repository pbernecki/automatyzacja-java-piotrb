import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Witaj świecie!");
        Scanner odczyt = new Scanner(System.in);
        String input;

        do{
        	System.out.println("Aby zamknąć aplikację, trzeba wpisać exit i nacisnąć klawisz enter");
            input=odczyt.nextLine();
        }
        while(!input.equals("exit"));
        System.out.println("Koniec");
    }
}
