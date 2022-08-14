package helper;

import business.*;


/**
 * 
 * @author Maximilian Jaesch
 *
 *	<p>Source: </p><a href="https://stackoverflow.com/questions/3422673/how-to-evaluate-a-math-expression-given-in-string-form"> Quelle </a>
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
    
    /**
     * 
     * 
     * @author Maximilian Jaesch
     * @param x
     * @param input
     * @return
     */
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
     * 
     * @author Maximilian Jaesch
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
    
    /**
     * @author Maximilian
     * @param str
     * @return
     */
    public double parse(String str) {
        this.str = str;
        nextChar();
        double x = parseStrichrechnung();
        if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
        return x;
    }

    /**
     *  @author Maximilian
     */
    private void nextChar() {
        //gucken ob die nächste pos kleiner als length ist, wir wollen ja im string/char array bleiben
    	if(++pos < str.length()) {
    		ch = str.charAt(pos);
    	} else {
			ch = -1;
		}
    }

    /**
     * es wird geguckt ob der parameter als nächstes ist, wenn ja dann weiter
     * wenn nein, dann einfach false und die position bleibt dieselbe
     * 
     * aus Quelle übernommen
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
     * funktioniert genau so wie eat() aber ohne die position von ch tatsächlich zu verändern
     * @author Maximilian Jaesch
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

    

    /**
     * es wird als erstes parsePunkrechnung aufgerufen, damit Punkt vor Strich gewährleistet ist!!!
     * @author Maximilian Jaesch
     * @return
     */
    private double parseStrichrechnung() {
        double x = parsePunktrechnung();
        while (checkNext('+') || checkNext('-')) {
            if (eat('+')) {
                x = new Plus().execute(x, parsePunktrechnung()); // addition
            } else if (eat('-')) {
                x = new Minus().execute(x, parsePunktrechnung()); // subtraction
            }
        }
        return x;
    }

    /**
     * @author Maximilian Jaesch
     * @return
     */
    private double parsePunktrechnung() {
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
     * 
     * @author Maximilian Jaesch
     */
    private double parseFactor() {
        if (eat('+')) return +parseFactor(); // unary plus
        if (eat('-')) return -parseFactor(); // unary minus

        double x;
        int startPos = this.pos;
        if (eat('(')) { // parentheses
            x = parseStrichrechnung();  //hier drin wird der gesamte Klammerausdruck ausgewertet
            if (!eat(')')) throw new RuntimeException("Missing ')'");
        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
            // hier wird eine zusammenhÃ¤ngende zahl von einem String zu einem double gemacht
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(str.substring(startPos, this.pos));
        } else if (ch >= 'a' && ch <= 'z') { // functions
            while (ch >= 'a' && ch <= 'z') nextChar();
            String func = str.substring(startPos, this.pos);
            if (eat('(')) {
                x = parseStrichrechnung();
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
