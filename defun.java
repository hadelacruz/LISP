import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class defun {
    public static Environment funciones = new Environment();

    public static <T> void saveDefun(List<Token> tokens) {
        String nombreFuncion = "";
        int contador = 0;
        int contadorPApertura = 0;
        int contadorPCierre = 0;
        boolean condicion = false;
        Function function = new Function();

        Stack<Token> stack = new Stack<>();
        Stack<Token> condicionStack = new Stack<>();
        List<Node> listaParametros = new ArrayList();
        List<Node> body = new ArrayList();

        List<Node> Lcondiciones = new ArrayList();

        try {
            for (Token elemento : tokens) {
                if (!elemento.value.equals(")")) {
                    if (elemento.type.equals(Token.TokenType.COND)) {
                        condicion = true;
                    }
                    if (elemento.value.equals("(") && condicion) {
                        contadorPApertura++;
                    }
                    stack.push(elemento);
                } else if (condicion) {
                    contadorPCierre++;
                    stack.add(elemento);
                    if ((contadorPApertura + 1) == contadorPCierre) {
                        // (cond ((<= n 1) 1) ((> n 1) (* n (factorial (- n 1)))))
                        condicion = false;
                        contadorPApertura = 0;
                        contadorPCierre = 0;
                        Lcondiciones.clear();
                        // Empieza a recorrer toda la condicion que armo.
                        for (Token tokenPila : stack) {
                            // (defun factorial (n) (cond ((<= n 1) 1) ((> n 1) (* n (factorial (- n 1))))))
                            if (tokenPila.value.equals(")")) {
                                contadorPCierre++;
                                if ((contadorPApertura - 1) == contadorPCierre) {
                                    if (condicionStack.size() == 4) {
                                        Node condicionNode = new Node();
                                        condicionNode.tipo = "COND_SIMPLE";
                                        condicionNode.operador = condicionStack.get(0).value;
                                        condicionNode.operando1 = condicionStack.get(1).value;
                                        condicionNode.operando2 = condicionStack.get(2).value;
                                        condicionNode.resultadoCond = condicionStack.get(3).value;
                                        Lcondiciones.add(condicionNode);
                                        // body.add(condicionNode);
                                        condicionStack.clear();
                                        contadorPApertura = 1;
                                        contadorPCierre = 0;
                                    } else {// (defun fibonacci (n)(cond ((< n 2) n) ((>= n 2) (+ (fibonacci (- n 1))
                                            // (fibonacci (- n 2)))) ))
                                        Node condicionNode = new Node();
                                        condicionNode.tipo = "COND_COMPUESTA";
                                        condicionNode.operador = condicionStack.get(0).value;
                                        condicionNode.operando1 = condicionStack.get(1).value;
                                        condicionNode.operando2 = condicionStack.get(2).value;

                                        // Resultado de la expresión compuesta
                                        Node resultadoCondicion = new Node();
                                        resultadoCondicion.tipo = "EXPRESION";
                                        resultadoCondicion.operador = condicionStack.get(3).value;
                                        if (condicionStack.get(4).type.equals(Token.TokenType.IDENTIFIER)
                                                || condicionStack.get(4).type.equals(Token.TokenType.NUMBER)) {
                                            if (condicionStack.get(5).type.equals(Token.TokenType.OPERADOR)) {
                                                resultadoCondicion.expresion1 = new Node();
                                                resultadoCondicion.expresion1.tipo = "CALL";
                                                resultadoCondicion.expresion1.nombreFuncion = condicionStack.get(4).value;
                                                resultadoCondicion.expresion1.operador = condicionStack.get(5).value;
                                                resultadoCondicion.expresion1.operando1 = condicionStack.get(6).value;
                                                resultadoCondicion.expresion1.operando2 = condicionStack.get(7).value;

                                                // Se obtiene la segunda expresión
                                                if (condicionStack.get(8).type.equals(Token.TokenType.IDENTIFIER)
                                                        || condicionStack.get(8).type.equals(Token.TokenType.NUMBER)) {
                                                    if (condicionStack.size() == 9) {
                                                        resultadoCondicion.expresion2 = new Node();
                                                        resultadoCondicion.expresion2.tipo = "VAR/NUM";
                                                        resultadoCondicion.expresion2.operando1 = condicionStack.get(8).value;
                                                    } else {
                                                        resultadoCondicion.expresion2 = new Node();
                                                        resultadoCondicion.expresion2.tipo = "CALL";
                                                        resultadoCondicion.expresion2.nombreFuncion = condicionStack.get(8).value;
                                                        resultadoCondicion.expresion2.operador = condicionStack.get(9).value;
                                                        resultadoCondicion.expresion2.operando1 = condicionStack.get(10).value;
                                                        resultadoCondicion.expresion2.operando2 = condicionStack.get(11).value;
                                                    }
                                                }

                                            } else {
                                                resultadoCondicion.expresion1 = new Node();
                                                resultadoCondicion.expresion1.tipo = "VAR/NUM";
                                                resultadoCondicion.expresion1.operando1 = condicionStack.get(4).value;
                                                // Se obtiene el segundo operador
                                                if (condicionStack.get(5).type.equals(Token.TokenType.IDENTIFIER)
                                                        || condicionStack.get(5).type.equals(Token.TokenType.NUMBER)) {

                                                    if (condicionStack.size() == 6) {
                                                        resultadoCondicion.expresion2 = new Node();
                                                        resultadoCondicion.expresion2.tipo = "VAR/NUM";
                                                        resultadoCondicion.expresion2.operando1 = condicionStack.get(5).value;

                                                    } else {
                                                        resultadoCondicion.expresion2 = new Node();
                                                        resultadoCondicion.expresion2.tipo = "CALL";
                                                        resultadoCondicion.expresion2.nombreFuncion = condicionStack.get(5).value;
                                                        resultadoCondicion.expresion2.operador = condicionStack.get(6).value;
                                                        resultadoCondicion.expresion2.operando1 = condicionStack.get(7).value;
                                                        resultadoCondicion.expresion2.operando2 = condicionStack.get(8).value;
                                                    }
                                                }

                                            }
                                        }
                                        condicionNode.resultadoExpre = resultadoCondicion;
                                        Lcondiciones.add(condicionNode);
                                        // body.add(condicionNode);
                                        condicionStack.clear();
                                        contadorPApertura = 1;
                                        contadorPCierre = 0;
                                    }

                                }
                            } else {
                                if (tokenPila.value.equals("(")) {
                                    contadorPApertura++;
                                } else {
                                    if (!tokenPila.type.equals(Token.TokenType.COND)) {
                                        condicionStack.add(tokenPila);

                                    }
                                }

                            }

                        }

                        if (Lcondiciones.size() > 1) {
                            Node condicionPadre = Lcondiciones.get(0);
                            condicionPadre.condicionesHijas = new ArrayList();

                            for (int i = 1; i < Lcondiciones.size(); i++) {
                                condicionPadre.condicionesHijas.add(Lcondiciones.get(i));
                            }

                            body.add(condicionPadre);

                        } else {
                            body.add(Lcondiciones.get(0));
                        }

                        Lcondiciones.clear();
                    }
                } else {
                    if (stack.size() > 0) {
                        if (stack.get(1).value.toUpperCase().equals("DEFUN")) {
                            nombreFuncion = stack.get(2).value;
                            // Extracción de parámetros
                            for (int i = 4; i < stack.size(); i++) {
                                if (!stack.get(i).value.equals(")")) {
                                    Node node = new Node();
                                    node.tipo = "PARAM";
                                    node.var = stack.get(i).value;
                                    listaParametros.add(node);
                                }
                            }
                            function.setParameters(listaParametros);
                            stack.clear();
                        } else if (stack.get(1).type.equals(Token.TokenType.OPERADOR)) {
                            Node node = new Node();
                            node.tipo = "EXP";
                            node.operador = stack.get(1).value;
                            node.operando1 = stack.get(2).value;
                            node.operando2 = stack.get(3).value;
                            body.add(node);
                            stack.clear();
                        }

                    }

                }
            }
            function.setBody(body);
            funciones.defineFunction(nombreFuncion, function);
            System.out.println("Funcion " + nombreFuncion + " guardada exitosamente.");
        } catch (Exception e) {
            System.out.println("¡Error al guardar la función!");
        }

    }

    public static String executeFunction(List<Token> tokens) {
        String nombreFuncion = "";
        String resultado = "";
        List<Token> Lparametros = new ArrayList();
        List<Node> LBodyFunction = new ArrayList();
        List<Node> LparametrosFunction = new ArrayList();

        try {
            // validar si la funcion existe
            if (tokens.size() > 0) {
                nombreFuncion = tokens.get(1).value;
                Function function = funciones.getFunction(nombreFuncion);

                if (function != null) {

                    for (int i = 2; i < tokens.size(); i++) {
                        if (tokens.get(i).type.equals(Token.TokenType.NUMBER)
                                || tokens.get(i).type.equals(Token.TokenType.IDENTIFIER)) { // acepta numero y variables como parametro
                            Lparametros.add(tokens.get(i));
                        }
                    }

                    if (function.getParameters().size() == Lparametros.size()) {

                        // Asignacion de parametros a la definicion de la funcion
                        for (int i = 0; i < function.getParameters().size(); i++) {
                            function.getParameters().get(i).value = Lparametros.get(i).value;
                            if(function.estado == 0){
                                function.getParameters().get(i).value2 = Lparametros.get(i).value;
                                function.getParameters().get(i).value3 = Lparametros.get(i).value;
                            }
                        }

                        // recorrer el cuerpo de la funcion
                        LBodyFunction = function.getBody();
                        LparametrosFunction = function.getParameters();
                        function.estado = 1;

                        for (int i = 0; i < LBodyFunction.size(); i++) {

                            switch (LBodyFunction.get(i).tipo) {
                                case "EXP":
                                    resultado = executeExp(LBodyFunction.get(i), LparametrosFunction);
                                    //System.out.println("El resulado de la función es: " + resultado);
                                    break;
                                case "COND_SIMPLE":
                                    resultado = executeCondSimple(LBodyFunction.get(i), LparametrosFunction);
                                    //System.out.println("El resulado de la función es: " + resultado);
                                    break;
                                default:
                                    break;
                            }
                        }
                        return resultado;
                    } else {
                        System.out.println("La cantidad de parametros enviadas no coincide con la cantidad definida en la función.");
                        return "N";
                    }

                } else {
                    System.out.println("funcion " + nombreFuncion + " no ha sido declarada!");
                    return "N";
                }
            } else {
                System.out.println("La lista de token viene incorrecta.");
                return "N";
            }

        } catch (Exception e) {
            System.out.println("¡Error al ejecutar la función!");
            return "NA";
        }

    }

    //Construye la cadena para ejecutar la expresion.
    private static String buildExp(Node exp, List<Node> Lparam){
        try {
            String cadenaExpresion = "(" + exp.operador;
            boolean parametroEncontrado = false;

            if(!exp.tipo.equals("EXPRESION")){
                if (esEntero(exp.operando1)) {
                    cadenaExpresion += " " + exp.operando1;
                } else {
                    for (Node node : Lparam) {
                        if (exp.operando1.equals(node.var)) {

                            if (esEntero(node.value)) {
                                cadenaExpresion += " " + node.value;
                                parametroEncontrado = true;
                            }else{
                                for (Variable variable : Instrucciones.listasDeVariables) {
                                    if (variable.getName().equals(node.value)) {
                                        cadenaExpresion += " " + variable.getValue();
                                        parametroEncontrado = true;
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }

                    if (!parametroEncontrado) {
                        return "La variable " + exp.operando1 + " no existe en ningun ambito";
                    } else {
                        // validacion del operando 2
                        if (esEntero(exp.operando2)) {
                            cadenaExpresion += " " + exp.operando2;
                        } else {
                            for (Node node : Lparam) {
                                if (exp.operando2.equals(node.var)) {

                                    if (esEntero(node.value)) {
                                        cadenaExpresion += " " + node.value;
                                        parametroEncontrado = true;
                                    }else{
                                        for (Variable variable : Instrucciones.listasDeVariables) {
                                            if (variable.getName().equals(node.value)) {
                                                cadenaExpresion += " " + variable.getValue();
                                                parametroEncontrado = true;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                            }

                            if (!parametroEncontrado) {
                                
                                return "La variable " + exp.operando2 + " no existe en ningun ambito";
                            } else {

                                cadenaExpresion += ")";
                                return cadenaExpresion;
                            }
                        }
                    }
                    parametroEncontrado = false;
                    cadenaExpresion += ")";
                    return cadenaExpresion;
                }
            }else{
                //*********************ES UN NODE EXPRESION QUE VIENE DE UNA CONDICION COMPUESTA

                if (exp.expresion1.tipo.equals("VAR/NUM")) {
                    if (esEntero(exp.expresion1.operando1)) {
                        cadenaExpresion += " " + exp.expresion1.operando1;
                    }else{
                        for (Node node : Lparam) {
                            if (exp.expresion1.operando1.equals(node.var)) {
    
                                if (esEntero(node.value)) {
                                    cadenaExpresion += " " + node.value;
                                    parametroEncontrado = true;
                                }else{
                                    for (Variable variable : Instrucciones.listasDeVariables) {
                                        if (variable.getName().equals(node.value)) {
                                            cadenaExpresion += " " + variable.getValue();
                                            parametroEncontrado = true;
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                        if (!parametroEncontrado) {
                            return "La variable " + exp.operando1 + " no existe en ningun ambito";
                        } else {
                            // validacion del operando 2
                            if (exp.expresion2.tipo.equals("VAR/NUM")) {
                                if (esEntero(exp.expresion2.operando1)) {
                                    cadenaExpresion += " " + exp.expresion2.operando1;
                                } else {
                                    for (Node node : Lparam) {
                                        if (exp.expresion2.operando1.equals(node.var)) {
        
                                            if (esEntero(node.value)) {
                                                cadenaExpresion += " " + node.value;
                                                parametroEncontrado = true;
                                            }else{
                                                for (Variable variable : Instrucciones.listasDeVariables) {
                                                    if (variable.getName().equals(node.value)) {
                                                        cadenaExpresion += " " + variable.getValue();
                                                        parametroEncontrado = true;
                                                        break;
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                    }
        
                                    if (!parametroEncontrado) {
                                        return "La variable " + exp.operando2 + " no existe en ningun ambito";
                                    } else {
        
                                        cadenaExpresion += ")";
                                        return cadenaExpresion;
                                    }
                                }

                                cadenaExpresion += ")";
                                return cadenaExpresion;
                            }else{
                                //EL OPERADOR 2 ES UNA FUNCION
                                Node invokeFunction = exp.expresion2;
                                String Funcion = "(" + invokeFunction.nombreFuncion + " ";
                                String cadenaExpresionParametro = "(" + invokeFunction.operador;
                                String operando1 = getVar(invokeFunction.operando1, Lparam);
                                String operando2= getVar(invokeFunction.operando2, Lparam);

                                if(operando1 != null){
                                    cadenaExpresionParametro += " " + operando1;
                                    if(operando2 != null){
                                        cadenaExpresionParametro += " " + operando2 + ")";
                                    }

                                    ArrayList<String> Lista = builStringToListExp(cadenaExpresionParametro);

                                    String resultadoParametros = Calculadora.calculadoraOperaciones(Lista);
                                    List<Token> newInput;

                                    Funcion += resultadoParametros + ")";
                                    newInput = LispTokenizer.tokenize(Funcion);

                                    String resCallFunction = executeFunction(newInput);
                                    cadenaExpresion += " " + resCallFunction + ")";
                                    return cadenaExpresion;

                                }else{
                                    return "El operando 1 del llamado de la funcion " + invokeFunction.nombreFuncion + " no existe";
                                }

                                
                            }
                        }

                    }
                }else{
                    //EL OPERADOR 1 ES UNA FUNCION
                    Node invokeFunction = exp.expresion1;
                    String Funcion = "(" + invokeFunction.nombreFuncion + " ";
                    Function function = funciones.getFunction(invokeFunction.nombreFuncion);
                    String cadenaExpresionParametro = "(" + invokeFunction.operador;
                    String operando1 = /*function.estado == 1 ? function.getParameters().get(0).value2:*/ getVar(invokeFunction.operando1, Lparam);
                    String operando2= getVar(invokeFunction.operando2, Lparam);

                    if(operando1 != null){
                        cadenaExpresionParametro += " " + operando1;
                        if(operando2 != null){
                            cadenaExpresionParametro += " " + operando2 + ")";
                        }

                        ArrayList<String> Lista = builStringToListExp(cadenaExpresionParametro);

                        String resultadoParametros = Calculadora.calculadoraOperaciones(Lista);
                        List<Token> newInput;

                        Funcion += resultadoParametros + ")";
                        newInput = LispTokenizer.tokenize(Funcion);

                        String resCallFunction=""; 
                        if(!invokeFunction.nombreFuncion.contains("fibonacci")){
                            resCallFunction = executeFunction(newInput);
                            cadenaExpresion += " "+ resCallFunction;
                        }else{
                            if(Integer.parseInt(resultadoParametros)<= 0){
                                cadenaExpresion += " 0";
                            }else{
                                resCallFunction = String.valueOf(executeSpecialRecursiveFuction(Integer.parseInt(resultadoParametros)));
                                cadenaExpresion += " "+ resCallFunction;
                            }
                        }
                    }
                    //----------------VALIDAR EL OPERADOR 2----------------
                    if (exp.expresion2.tipo.equals("VAR/NUM")) {
                        if (esEntero(exp.expresion2.operando1)) {
                            cadenaExpresion += " " + exp.expresion2.operando1;
                        } else {
                            for (Node node : Lparam) {
                                if (exp.expresion2.operando1.equals(node.var)) {

                                    if (esEntero(node.value)) {
                                        cadenaExpresion += " " + node.value;
                                        parametroEncontrado = true;
                                    }else{
                                        for (Variable variable : Instrucciones.listasDeVariables) {
                                            if (variable.getName().equals(node.value)) {
                                                cadenaExpresion += " " + variable.getValue();
                                                parametroEncontrado = true;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            if (!parametroEncontrado) {
                                return "La variable " + exp.operando2 + " no existe en ningun ambito";
                            } else {

                                cadenaExpresion += ")";
                                return cadenaExpresion;
                            }
                        }

                        cadenaExpresion += ")";
                        return cadenaExpresion;
                    }else{
                        //EL OPERADOR 2 ES UNA FUNCION
                        Node invokeFunction2 = exp.expresion2;
                        function = funciones.getFunction(invokeFunction2.nombreFuncion);
                        String Funcion2 = "(" + invokeFunction2.nombreFuncion + " ";
                        String cadenaExpresionParametro2 = "(" + invokeFunction2.operador;
                        String operando1_1 = function.estado == 1 ? function.getParameters().get(0).value3: getVar(invokeFunction2.operando1, Lparam);
                        String operando2_2 = getVar(invokeFunction2.operando2, Lparam);

                        if(operando1_1 != null){
                            cadenaExpresionParametro2 += " " + operando1_1;
                            if(operando2_2 != null){
                                cadenaExpresionParametro2 += " " + operando2_2 + ")";
                            }

                            ArrayList<String> Lista = builStringToListExp(cadenaExpresionParametro2);

                            String resultadoParametros = Calculadora.calculadoraOperaciones(Lista);
                            
                            if(function.estado == 1){
                                function.getParameters().get(0).value3 = resultadoParametros;
                            }

                            List<Token> newInput;

                            Funcion2 += resultadoParametros + ")";
                            newInput = LispTokenizer.tokenize(Funcion2);

                            String resCallFunction=""; 
                            if(!invokeFunction2.nombreFuncion.contains("fibonacci")){
                                resCallFunction = executeFunction(newInput);
                                cadenaExpresion += " "+ resCallFunction +")";
                            }else{
                                if(Integer.parseInt(resultadoParametros)<= 0){
                                    cadenaExpresion += " 0)";
                                }else{
                                    resCallFunction = String.valueOf(executeSpecialRecursiveFuction(Integer.parseInt(resultadoParametros)));
                                    cadenaExpresion += " "+ resCallFunction +")";
                                }
                            }
                            
                            function.estado = 0;
                            return cadenaExpresion;

                        }else{
                            return "El operandor 2 del llamado de la funcion " + invokeFunction2.nombreFuncion + " no existe";
                        }
                    }
                }
                
            }
            System.out.println("Error al construir la expresión.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al ejecutar la expresion desde la funcion.");
            return null;
        }
    }

    public static String executeExp(Node exp, List<Node> Lparam) {
        try {
            String cadenaExpresion = buildExp(exp, Lparam);

            if(cadenaExpresion != null){
                // Se realizar el casteo a la cadena expresión
                // Construir números completos
                ArrayList<String> arrayList = builStringToListExp(cadenaExpresion);

                return Calculadora.calculadoraOperaciones(arrayList);
            }
            
            return "Error al evaluar la expresion";
            
        } catch (Exception e) {
            System.out.println("Error al ejecutar la expresion desde la funcion.");
            return "";
        }
    }

    private static String getVar(String varB, List<Node> Lparam){
        if (esEntero(varB)) {
            return varB;
        } else {
            for (Node node : Lparam) {
                if (varB.equals(node.var)) {
                    if (esEntero(node.value)) {
                        return node.value;
                    }else{
                        for (Variable variable : Instrucciones.listasDeVariables) {
                            if (variable.getName().equals(node.value)) {
                                return variable.getValue().toString();
                            }
                        }
                    }
                    break;
                }
            }

            return null;
        }
    }

    public static String executeCondSimple(Node exp, List<Node> Lparam) {
        try {
            String cadenaExpresion = buildExp(exp, Lparam);
            String resCondicion = getVar(exp.resultadoCond, Lparam);
            String resEv = "";

            if(resCondicion != null){
                cadenaExpresion = "(cond "+ cadenaExpresion + " " + resCondicion +" )";
                List<Token> tokensCondicionSimple = LispTokenizer.tokenize(cadenaExpresion);

                Token resultEvauacion = Conditionals.sintaxisCond(tokensCondicionSimple);

                //verifico si la condicion se cumplio
                if(resultEvauacion == null){
                    //la condicion no se cumplio y valido si tiene hijos
                    if(exp.condicionesHijas.size() > 0){

                        for (Node condHija : exp.condicionesHijas) {
                            //Valido el tipo de condicion
                            if(condHija.tipo.equals("COND_SIMPLE")){
                                resEv = executeCondSimple(condHija, Lparam);
                                if(resEv != ""){
                                    return resEv;
                                }
                                
                            }else{
                                //es una condicion compuesta
                                resEv = executeCondCompuesta(condHija, Lparam, exp);
                                if(resEv != ""){
                                    return resEv;
                                }
                            }
                        }
                        
                        return "";
                        
                    }else{
                        return "La condicion no se cumplio y no hay mas casos que evaluar.";
                    }

                }else{
                    return resultEvauacion.value;
                }
            }else{

                //Validar si es una funcion la respuesta de condicion
                return "El resultado de la condicion no se pudo leer";
            }
            
        } catch (Exception e) {
            //System.out.println("Error al ejecutar la condicion simple desde la funcion.");
            return "";
        }
    }

    public static String executeCondCompuesta(Node exp, List<Node> Lparam, Node padre) {
        try {
            String cadenaExpresion = buildExp(exp, Lparam);
            String resCondicion = buildExp(exp.resultadoExpre, Lparam);
            String resEv ="";

            if(resCondicion != null){
                
                ArrayList<String> arrayList = builStringToListExp(resCondicion);
                resCondicion = Calculadora.calculadoraOperaciones(arrayList);

                cadenaExpresion = "(cond "+ cadenaExpresion + " " + resCondicion +" )";
                List<Token> tokensCondicionSimple = LispTokenizer.tokenize(cadenaExpresion);

                Token resultEvauacion = Conditionals.sintaxisCond(tokensCondicionSimple);

                //verifico si la condicion se cumplio
                if(resultEvauacion == null){
                    //la condicion no se cumplio y valido si tiene hijos
                    if(padre.condicionesHijas.size() > 0){

                        for (Node condHija : exp.condicionesHijas) {
                            //Valido el tipo de condicion
                            if(condHija.tipo.equals("COND_SIMPLE")){
                                
                                resEv = executeCondSimple(condHija, Lparam);
                                if(resEv != ""){
                                    return resEv;
                                }
                            }else{
                                //es una condicion compuesta
                                resEv = executeCondCompuesta(condHija, Lparam, padre);
                                if(resEv != ""){
                                    return resEv;
                                }
                            }
                        }
                        
                        return "";
                        
                    }else{
                        return "La condicion no se cumplio y no hay mas casos que evaluar.";
                    }

                }else{
                    return resultEvauacion.value;
                }
            }else{

                //Validar si es una funcion la respuesta de condicion
                return "El resultado de la condicion no se pudo leer";
            }

            //return "Hubo una incosistencia en la ejecución de la condicion compuesta.";
        } catch (Exception e) {
            System.out.println("Error al ejecutar la condicion compuesta desde la funcion.");
            return "";
        }
    }

    private static ArrayList<String> builStringToListExp(String cadenaExpresion){
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();
        for (char c : cadenaExpresion.toCharArray()) {
            if (Character.isDigit(c)) {
                // Si es un dígito, construir el número completo
                currentNumber.append(c);
            } else {
                // Si hay un número o carácter en proceso, agregarlo al ArrayList
                if (currentNumber.length() > 0) {
                    arrayList.add(currentNumber.toString());
                    currentNumber.setLength(0); // Reiniciar el StringBuilder
                }
                // Agregar paréntesis, operadores y otros caracteres al ArrayList
                if (c == '(' || c == ')' || !Character.isWhitespace(c)) {
                    arrayList.add(String.valueOf(c));
                }
            }
        }

        return arrayList;
    }

    public static int executeSpecialRecursiveFuction(int n) {
        if (n <= 1) {
            return n;
        } else {
            return executeSpecialRecursiveFuction(n - 1) + executeSpecialRecursiveFuction(n - 2);
        }
    }

    public static boolean esEntero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}