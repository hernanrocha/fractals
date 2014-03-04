import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

import basic.Punto;


public class ManejadorClicsRaton extends MouseAdapter {
	private int xPos;
	private int yPos;
	private miFrame frame;

	/**
	 * @param miFrame
	 */
	public ManejadorClicsRaton(miFrame frame) {
		this.frame = frame;
	}

	public void mouseWheelMoved(MouseWheelEvent evento){
		System.out.println(evento.getScrollAmount());
		System.out.println(evento.getPreciseWheelRotation());
		System.out.println(evento.getScrollType());
	}
	
	// manejar evento de clic del ratón y determinar cuál botón se oprimió
	public void mouseClicked(MouseEvent evento){
		xPos = evento.getX();
		yPos = evento.getY();

		String titulo = "Se hizo clic " + evento.getClickCount() + " Veces";

		if ( evento.isMetaDown() ){
			// botón derecho del ratón
			Runnable addIt = new Runnable() {
				public void run() {
					frame.zoomOut(new Punto(xPos, yPos));
				}
			};
			SwingUtilities.invokeLater(addIt);
			titulo += " con el botón derecho del ratón";
		}else if ( evento.isAltDown() ){
			// botón de en medio del ratón
			titulo += " con el botón central del ratón";
		}else{  // botón izquierdo del ratón
			Runnable addIt = new Runnable() {
				public void run() {
					frame.zoomIn(new Punto(xPos, yPos));
				}
			};
			SwingUtilities.invokeLater(addIt);
			titulo += " con el botón izquierdo del ratón";
		}
		
		System.out.println(titulo);
		System.out.println(xPos + " - " + yPos);
	}
}
