package main;

import org.jpl7.PrologException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import helper.Parser;

public class SystemTest {
    Parser parser;

    @Before
    public void setUp() {
        parser = new Parser();
    	Main.initialize();
    }

//mal, geteilt, punkt vor strich, sinus cosinus tangens  
    
    /**
     * Author: Abdu
     */
	@Test
    public void additionTest() {
        Assert.assertEquals(12.0, parser.parse("3+3+3+3"), 0.0);
    }
	
	/**
     * Author: Abdu
     */
	
	@Test
	public void multiplikationTest() {
		Assert.assertEquals(81.0, parser.parse("3*3*3*3"), 0.0);
	}
	
	/**
     * Author: Abdu
     */
	
	@Test
	public void subtraktionTest() {
		Assert.assertEquals(-6.0, parser.parse("3-3-3-3"), 0.0);
	}
	
	/**
     * Author: Abdu
     */
	
	@Test
	public void divisionTest() {
		Assert.assertEquals(2.0, parser.parse("10/5"), 0.0);
	}
	
	/**
     * Author: Abdu
     */
	
	@Test
	public void punktVorStrich() {
		Assert.assertEquals(15.0, parser.parse("3+3*3+3"), 0.0);
	}
	
	/**
     * Author: Abdu
     */
	
	@Test
	public void klammerTest() {
		Assert.assertEquals(36.0, parser.parse("(3+3)*(3+3)"), 0.0);
	}
	
	/**
     * Author: Abdu
     */
	
	@Test
	public void sinusTest() {
		Assert.assertEquals(0.0, parser.parse("sin(0)"), 0.0);
	}
	
	/**
     * Author: Abdu
     */
	@Test
	public void sinusKlammerTest() {
		Assert.assertEquals(0.0, parser.parse("sin((1-1)*(1+1))"), 0.0);
	}
	
	/**
     * Author: Abdu
     */
	@Test
	public void cosinusTest() {
		Assert.assertEquals(1.0, parser.parse("cos(0)"), 0.0);
	}
	
	
	/**
     * Author: Abdu
     */
	@Test
	public void tangensTest() {
		Assert.assertEquals(0.0, parser.parse("tan(0)"), 0.0);
	}
	
	
	
	/** Grenzfall Test
     * Author: Berkan
     */
	
	/* Das "E" von Double.MAX wird nicht erkannt. 1.7976931348623157E308
	@Test 
	public void maxUeberlaufTest() {
		double d = Double.MAX_VALUE;
		Assert.assertEquals(0.0, parser.parse(d + "+1"), 0.0);
	}
	
	// Author: Berkan
	
	@Test Das "E" von Double.MIN wird nicht erkannt. 4.9E-324
	public void minUeberlaufTest() {
		double d = Double.MIN_VALUE;
		Assert.assertEquals(0.0, parser.parse(d + "-1"), 0.0);
	}
	*/
	
	/**
     * Author: Berkan
     */
	@Test
	public void teilenDurchNullTest() {
		try {
			
			parser.parse("1/0");
		} catch (PrologException e) {
			// TODO: handle exception

		}
	}
}
