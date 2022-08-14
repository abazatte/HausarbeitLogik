package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

/**
 * Ausgelagerter MouseListener vom Interface
 *
 * @author Berkan Yildiz
 */
public class CalculatorHelpMenuMouseListener implements MouseListener{
	
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
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Plotten:  wenn ein x  im Ausdruck verwendet wird, dann  wird bei druck auf \"=\" automatisch geplottet"
				+ " um die X werte festzulegen, können die Felder verwendet werden, diese Werden mit druck auf OK button bestätigt \n" + "Matrix Knopf drücken um den Matrix "
						+ "rechner zu öffnen\n" + "Round ist eine funktion, die das innere rundet auf die eingestellten nachkommastellen (standard 5 nach dem komma) "
								+ "bsp: \"round(sin(π))\"");
	}
}