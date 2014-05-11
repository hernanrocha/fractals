package swing;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;





import basic.Punto;


public class MouseHandler extends MouseAdapter {
	private static final int REFRESH_PERIOD = 20;
	private static final int MIN_PIXELS = 2;

	private Fractales frame;
	
	private int xPos;
	private int yPos;
	private long oldTime = 0;
	
	private boolean mover, moverInteractivo, zoom;

	public MouseHandler(Fractales frame) {
		this.frame = frame;
	}

	public void mouseWheelMoved(MouseWheelEvent evento){
		System.out.println(evento.getScrollAmount());
		System.out.println(evento.getPreciseWheelRotation());
		System.out.println(evento.getScrollType());
	}
	
	// Manejar evento de clic del rat�n y determinar cu�l bot�n se oprimi�
	public void mouseClicked(MouseEvent evento){
		xPos = evento.getX();
		yPos = evento.getY();

		String titulo = "Se hizo clic " + evento.getClickCount() + " Veces";

		if ( evento.isMetaDown() ){
			// bot�n derecho del rat�n
			Runnable addIt = new Runnable() {
				public void run() {
//					frame.zoomOut(new Punto(xPos, yPos));
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
//					frame.zoomIn(new Punto(xPos, yPos));
				}
			};
			SwingUtilities.invokeLater(addIt);
			titulo += " con el bot�n izquierdo del rat�n";
		}
		
//		System.out.println(titulo);
//		System.out.println(xPos + " - " + yPos);
	}
	
	@Override
	public void mousePressed(MouseEvent evento){
		// Actualizar posicion
		xPos = evento.getX();
		yPos = evento.getY();
		
		System.out.println("Presionado " + xPos + " - " + yPos);
	}
	
	@Override
	public void mouseReleased(MouseEvent evento){
		int xPosRelease = evento.getX();
		int yPosRelease = evento.getY();
		frame.getImagen().setParametros(0, 0, 0, 0, PanelImagen.NONE);
		
		System.out.println("Soltado " + xPosRelease + " - " + yPosRelease);
		
		if (zoom){
			
			if ( (Math.abs(xPos - xPosRelease) < MIN_PIXELS ) || (Math.abs(yPos - yPosRelease) < MIN_PIXELS) ){
				return;
			}
			
			// Calcular rango de eje
			int xMin = Math.min(xPos, xPosRelease);
			int xMax = Math.max(xPos, xPosRelease);
			int yMin = Math.min(yPos, yPosRelease);
			int yMax = Math.max(yPos, yPosRelease);
			
			frame.zoom(yMin, yMax, xMin, xMax);
		
		}else if(mover && !moverInteractivo){
			// Calcular movimiento
			int difX = evento.getX() - xPos;
			int difY = evento.getY() - yPos;
			
			// Realizar movimiento
			System.out.println("Mover: " + difX + " - " + difY);
			frame.mover(difY, difX);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent evento){
		
		long newTime = System.currentTimeMillis();
		
		if(mover && moverInteractivo && newTime - oldTime  > REFRESH_PERIOD){
			oldTime = newTime;
			
			// Calcular movimiento
			int difX = evento.getX() - xPos;
			int difY = evento.getY() - yPos;
			
			// Calcular nueva posicion
			xPos = evento.getX();
			yPos = evento.getY();
			
			// Realizar movimiento
			System.out.println("Mover interactivo: " + difX + " - " + difY);
			frame.mover(difY, difX);
			
		}else if (mover){
			// Actualizar linea
			frame.getImagen().setParametros(xPos, yPos, evento.getX(), evento.getY(), PanelImagen.MOVER);
		}else if (zoom){
			// Actualizar rectangulo
			int x = Math.min(xPos, evento.getX());
			int y = Math.min(yPos, evento.getY());
			int w = Math.abs(xPos - evento.getX());
			int h = Math.abs(yPos - evento.getY());
			frame.getImagen().setParametros(x, y, w, h, PanelImagen.ZOOM);
		}
		
	}
	
	@Override
	public void mouseMoved(MouseEvent evento){
		frame.setPosicionActual(evento.getY(), evento.getX());
	}
	
	// Setters

	public void setMover(boolean mover) {
		System.out.println("Set mover " + mover);
		this.mover = mover;
	}
	
	public void setMoverInteractivo(boolean moverInteractivo) {
		System.out.println("Set mover interactivo " + moverInteractivo);
		this.moverInteractivo = moverInteractivo;
	}

	public void setZoom(boolean zoom) {
		System.out.println("Set zoom " + zoom);
		this.zoom = zoom;
	}
	
}
