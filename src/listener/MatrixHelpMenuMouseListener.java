package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

/**
 * 
 * @author Berkan Yildiz
 *
 */
public class MatrixHelpMenuMouseListener implements MouseListener{

	@Override
	public void mouseReleased(MouseEvent e) {
		// Nicht benoetigt
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// Nicht benoetigt
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// Nicht benoetigt
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// Nicht benoetigt
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		String message = "Willkommen im MatrixCalc \n "
				+ "(+) Linke Matrix + Rechte Matrix \n "
				+ "(-) Linke Matrix - Rechte Matrix \n "
				+ "(*) Linke Matrix * Rechte Matrix \n "
				+ "(L^t) Linke Matrix transponieren \n "
				+ "(R^t) Rechte Matrix transponieren \n";
		
		String hinweis = "Sei die Matrix M1 in Form (nxn) und die Matrix M2 in Form (nxm)\n"
				+ "Das Produkt der Matrix M1*M2 ist die Form (nxm)\n "
				+ "Das Produkt der Matrix M1*M1 ist die Form (nxn)\n" 
				+ "Das Produkt der Matrix M2*M2 ist nicht m�glich";
		
		JOptionPane.showMessageDialog(null, message + " " + hinweis);
	}

}
