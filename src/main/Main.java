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

import Plotter.PlotController;
import gui.Interface;
import gui.MatrixCalc;
import helper.Parser;
import math.Matrix;

/**
 * 
 * 
 * @author Abdurrahman
 *
 */
public class Main {
    public static void main(String[] args) {
        initialize();
    	new Interface();
    }

    public static void initialize() {
    	JPL.init();
        Query q1 = new Query("consult", new Term[]{new Atom("lib/calc.pl")});
        System.out.println(q1.hasSolution());
        q1.close();
    }
}
