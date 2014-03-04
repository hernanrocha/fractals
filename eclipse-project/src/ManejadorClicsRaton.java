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
	
	// manejar evento de clic del rat�n y determinar cu�l bot�n se oprimi�
	public void mouseClicked(MouseEvent evento){
		xPos = evento.getX();
		yPos = evento.getY();

		String titulo = "Se hizo clic " + evento.getClickCount() + " Veces";

		if ( evento.isMetaDown() ){
			// bot�n derecho del rat�n
			Runnable addIt = new Runnable() {
				public void run() {
					frame.zoomOut(new Punto(xPos, yPos));
				}
			};
			SwingUtilities.invokeLater(addIt);
			titulo += " con el bot�n derecho del rat�n";
		}else if ( evento.isAltDown() ){
			// bot�n de en medio del rat�n
			titulo += " con el bot�n central del rat�n";
		}else{  // bot�n izquierdo del rat�n
			Runnable addIt = new Runnable() {
				public void run() {
					frame.zoomIn(new Punto(xPos, yPos));
				}
			};
			SwingUtilities.invokeLater(addIt);
			titulo += " con el bot�n izquierdo del rat�n";
		}
		
		System.out.println(titulo);
		System.out.println(xPos + " - " + yPos);
	}
}
