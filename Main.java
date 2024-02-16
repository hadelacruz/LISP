import java.util.List;

public class Main{
    public static void main(String[] args) {
        String lispCode = "(defun suma(hola))";
        List<Token> tokens = LispTokenizer.tokenize(lispCode);
        tokens.forEach(System.out::println);

    }
}