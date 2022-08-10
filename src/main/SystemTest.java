package main;

import org.jpl7.PrologException;
import org.junit.*;

import helper.Parser;


public class SystemTest {
	Parser parser;
	
	@Before
	public void setup() {
		Main.initialize();
		parser = new Parser();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void additionTest() {
		try {
			Assert.assertEquals(6.0, parser.parse("3.0+3.0"),0.0);
		} catch (PrologException e) {
			System.out.println("moin exceptio");
			e.printStackTrace();
		}
	}
	
}
