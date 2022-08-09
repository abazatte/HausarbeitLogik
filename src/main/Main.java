package main;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jpl7.Atom;
import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import gui.Interface;


public class Main {
    public static void main(String[] args) {
        initialize();
    	new Interface();
    }
    
    /*public static void main(String[] args) throws PythonExecutionException, IOException {

        List<Double> x = NumpyUtils.linspace(-Math.PI, Math.PI, 256);
        List<Double> C = x.stream().map(Math::cos).collect(Collectors.toList());
        List<Double> S = x.stream().map(Math::sin).collect(Collectors.toList());

        Plot plt = Plot.create();
        plt.plot()
               // .add(C)
                .add(S)
                .label("label")
                .linestyle("-");
        plt.xlabel("xlabel");
        plt.ylabel("ylabel");
        plt.text(0.5, 0.2, "text");
        plt.title("Title!");
        plt.legend();
        plt.show();
    }*/
	
    private static void initialize() {
    	JPL.init();
        Query q1 = new Query("consult", new Term[]{new Atom("lib/calc.pl")});
        System.out.println(q1.hasSolution());
        q1.close();
    }
}