package helper;

import business.*;


/**
 * 
 * @author Maxi
 *
 */
public class Parser {
    private int pos;
    private int ch;
    private String str;
    private int roundBy; //standard: 5

    public Parser() {
        this.pos = -1;
        str = "";
        this.roundBy = 5; 
    }
    
    public Parser(int roundBy) {
    	this.pos = -1;
        str = "";
        this.roundBy = roundBy; 
    }

    void nextChar() {
        //gucken ob die nÃ¤chste pos kleiner als length ist, wir wollen ja im string/char array bleiben
        ch = (++pos < str.length()) ? str.charAt(pos) : -1;
    }

    /**
     * es wird geguckt ob der parameter drin ist?, wenn ja dann weiter
     * wenn nein, dann einfach false und die position bleibt dieselbe
     *
     * @param charToEat
     * @return
     */
    private boolean eat(int charToEat) {
        //spaces werden ignoriert ' ' == 32
        while (ch == ' ') {
            nextChar();
        }
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    /**
     * funktioniert genau so wie eat() aber ohne die position von ch tatsÃ¤chlich zu verÃ¤ndern
     *
     * @param charToCheck
     * @return
     */
    private boolean checkNext(int charToCheck) {
        int offset = 0;
        while ((ch + offset) == ' ') {
            offset++;
        }
        return ch + offset == charToCheck;
    }

    public String xInStringMitDoubleErsetzen(Double x, String input) {
        String result = "";
        if (input.contains("x")) {
            result = input.replace("x", "(" + Double.toString(x) + ")");
        }

        return result;
    }
    
    /**
     * e =  2.7182818284
     * grosses e wird ignoriert!
     * pi = 3.1415926535
     * @param input
     * @return
     */
    public String mathKonstantenErsetzen(String input) {
    	String result = input;
    	if(input.contains("e")) {
    		result = result.replace("e", "(" + Math.E + ")");
    	}
    	if(input.contains("π")) {
    		result = result.replace("π", "(" + Math.PI + ")");
    	}
    	return result;
    }


    public double parse(String str) {
        this.str = str;
        nextChar();
        double x = parseExpression();
        if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
        return x;
    }

    // Grammar:
    // expression = term | expression `+` term | expression `-` term
    // term = factor | term `*` factor | term `/` factor
    // factor = `+` factor | `-` factor | `(` expression `)` | number
    //        | functionName `(` expression `)` | functionName factor
    //        | factor `^` factor

    double parseExpression() {
        //es wird erst parseTerm aufgerufen damit wir erst multiplikation und division machen!!!
        double x = parseTerm();
        while (checkNext('+') || checkNext('-')) {
            if (eat('+')) {
                x = new Plus().execute(x, parseTerm()); // addition
            } else if (eat('-')) {
                x = new Minus().execute(x, parseTerm()); // subtraction
            }
        }
        return x;
    }

    double parseTerm() {
        double x = parseFactor();
        while (checkNext('*') || checkNext('/')) {
            if (eat('*')) {
                x = new Multiplication().execute(x, parseFactor()); // multiplication

            } else if (eat('/')) {
                x = new Division().execute(x, parseFactor()); // division
            } else if (eat('%')) {
            	x = new Modulo().execute(x, parseFactor()); //modulo
            }
        }
        return x;
    }


    /**
     * Generelle Idee: statements werden so case by case aufgenommen, und je nachdem mit was wir anfangen
     * zb: zahl oder vorzeichen machen wir was anderes
     */
    double parseFactor() {
        if (eat('+')) return +parseFactor(); // unary plus
        if (eat('-')) return -parseFactor(); // unary minus

        double x;
        int startPos = this.pos;
        if (eat('(')) { // parentheses
            x = parseExpression();  //hier drin wird der gesamte Klammerausdruck ausgewertet
            if (!eat(')')) throw new RuntimeException("Missing ')'");
        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
            // hier wird eine zusammenhÃ¤ngende zahl von einem String zu einem double gemacht
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(str.substring(startPos, this.pos));
        } else if (ch >= 'a' && ch <= 'z') { // functions
            while (ch >= 'a' && ch <= 'z') nextChar();
            String func = str.substring(startPos, this.pos);
            if (eat('(')) {
                x = parseExpression();
                if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
            } else {
                x = parseFactor();
            }
            if (func.equals("sqrt")) {
                x = new SquareRoot().execute(x, 0);
            } else if (func.equals("sin")) {
                x = new Sinus().execute(x, 0);
            } else if (func.equals("cos")) {
                x = new Cosinus().execute(x, 0);
            } else if (func.equals("tan")) {
                x = new Tangens().execute(x, 0);
            } else if (func.equals("fak")) {
            	x = new Factorial().execute(x, 0);
            } else if (func.equals("ln")) {
            	x = new NaturalLogarithm().execute(x, 0);
            } else if (func.equals("round")) {
            	x = new Round().execute(x, roundBy);
            }
            else throw new RuntimeException("Unknown function: " + func);
        } else {
            throw new RuntimeException("Unexpected: " + (char) ch);
        }

        if (eat('^')) {
            x = new Power().execute(x, parseFactor()); // Potenzrechnung
        }

        return x;
    }
}
