import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PruebasUnitarias {

    @BeforeAll
    public static void setup() {
        // Definimos las funciones aquí
        String aumento2_def = "(defun aumento2 (num) (cond ((<= num 1) num) ((> num 1) (* num num))))";
        String factorial_def = "(defun factorial (num2) (cond ((<= num2 1) 1) ((> num2 1) (* num2 (factorial (- num2 1))))))";
        String suma_def = "(defun suma (x y) (+ x y))";
        String resta_def = "(defun resta (x y) (- x y))";
        String multiplicacion_def = "(defun multiplicacion (x y) (* x y))";
        String division_def = "(defun division (x y) (/ x y))";
        String fibonacci_def = "(defun fibonacci (n) (cond ((< n 0) 0) ((= n 1) 1) ((>= n 2) (+ (fibonacci (- n 1)) (fibonacci (- n 2))))))";
        
        // Ejecutamos las definiciones de funciones en un entorno simulado
        Interprete.interpreteListTokens(LispTokenizer.tokenize(aumento2_def));
        Interprete.interpreteListTokens(LispTokenizer.tokenize(factorial_def));
        Interprete.interpreteListTokens(LispTokenizer.tokenize(suma_def));
        Interprete.interpreteListTokens(LispTokenizer.tokenize(resta_def));
        Interprete.interpreteListTokens(LispTokenizer.tokenize(multiplicacion_def));
        Interprete.interpreteListTokens(LispTokenizer.tokenize(division_def));
        Interprete.interpreteListTokens(LispTokenizer.tokenize(fibonacci_def));
    }

    @Test
    public void pruebaAumento2() {
        String input = "(aumento2 5)";
        String expectedOutput = "25"; // El resultado esperado de aumento2(5) es "25"
        String actualOutput = defun.executeFunction(LispTokenizer.tokenize(input));
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void pruebaFactorial() {
        String input = "(factorial 9)";
        String expectedOutput = "362880"; // El resultado esperado de factorial(5) es "120"
        String actualOutput = defun.executeFunction(LispTokenizer.tokenize(input));
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void pruebaSuma() {
        String input = "(suma 3 4)";
        String expectedOutput = "7"; // El resultado esperado de suma(3, 4) es "7"
        String actualOutput = defun.executeFunction(LispTokenizer.tokenize(input));
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void pruebaResta(){
        String input = "(resta 5 4)";
        String expectedOutput = "1";
        String actualOutput = defun.executeFunction(LispTokenizer.tokenize(input));
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void pruebaMultipicacion(){
        String input = "(multiplicacion 10 9)";
        String expectedOutput = "90";
        String actualOutput = defun.executeFunction(LispTokenizer.tokenize(input));
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void pruebaDivision(){
        String input = "(division 100 20)";
        String expectedOutput = "5";
        String actualOutput = defun.executeFunction(LispTokenizer.tokenize(input));
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void pruebaFibonacci(){
        String input = "(fibonacci 4)";
        String expectedOutput = "3";
        String actualOutput = defun.executeFunction(LispTokenizer.tokenize(input));
        assertEquals(expectedOutput, actualOutput);
    }
}
