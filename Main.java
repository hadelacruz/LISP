import java.util.List;
import java.util.Scanner;

/**
 * La clase {@code Main} es la clase principal del proyecto Lisp. Proporciona
 * una interfaz de línea de comandos para interactuar con el intérprete Lisp.
 */
public class Main {
    /**
     * Método principal que inicia la ejecución del intérprete Lisp en modo de línea
     * de comandos.
     *
     * @param args Argumentos de la línea de comandos (no utilizado).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        Environment environment;
        while (!input.equals("exit")) {
            System.out.print("> ");
            input = sc.nextLine();
            // Validar sintaxis de paréntesis y realizar la interpretación
            if (Interprete.validarParentesis(input)) {
                List<Token> tokens = LispTokenizer.tokenize(input);
                Interprete.interpreteListTokens(tokens);
                environment = defun.funciones;
            } else {
                System.out.println("¡Sintaxis inválida!");
            }
        }
    }
}