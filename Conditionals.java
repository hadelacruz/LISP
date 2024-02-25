import java.util.List;
import java.util.function.Predicate;
import java.lang.Object;


/**
 * Referencia de: http://arantxa.ii.uam.es/~rcobos/teaching/esp/ia/lisp0.pdf
 * Referencia de: https://www.manualweb.net/java/operadores-condicionales-java/
 */

 /**
public class Cond extends AbstractFuncion{

    
     * @param comparacion compara la operacion aritmetica
     * @param a entero representativo del primer valor en comparar
     * @param b entero representativo del segundo valor en comparar
     * @return retorna un objeto String
     
    public static String cond(String comparacion, int a, int b) {
        switch(comparacion) {
            case ">":
                if (a > b) {
                    return "mayor";
                } else {
                    return "menor";
                }
            case "<":
                if (a < b) {
                    return "mayor";
                } else {
                    return "menor";
                }
            case ">=":
                if (a >= b) {
                    return "mayor";
                } else {
                    return "menor";
                }
            case "<=":
                if (a <= b) {
                    return "mayor";
                } else {
                    return "menor";
                }
            case "=":
                if (a == b) {
                    return "mayor";
                } else {
                    return "menor";
                }
            default:
                return "Error";
        }
    }

    /**
     * @param line recibe una linea de codigo
     * @return regresa una cadena de texto con el resultao
     
    public String ejecutar(String line){
        String example = "(cond > 5 3)";
        line = line.substring(1, line.length()-1);
        line = line.replace("cond ","");
        String[] things = line.split(" ");
        return cond(things[0],Integer.parseInt(things[1]),Integer.parseInt(things[2]));
    }
}
*/