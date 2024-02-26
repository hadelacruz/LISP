import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String input = "";
        while(!input.equals("exit")){
            System.out.print("> ");
            input = sc.nextLine();
            if (Interprete.validarParentesis(input)) {
                List<Token> tokens = LispTokenizer.tokenize(input);
                Interprete.interpreteListTokens(tokens);
                
            }else{
                System.out.println("¡Sintaxis inválida!");
            }
        }
    }
}