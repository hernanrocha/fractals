package swing;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;


import basic.Punto;


public class MouseHandler extends MouseAdapter {
	private static final int REFRESH_PERIOD = 100;
	private int xPos;
	private int yPos;
	private Fractales frame;
	private long oldTime = 0;
	
	private boolean mover, zoom;
	private static final int MIN_PIXELS = 1;

	/**
	 * @param Fractales
	 */
	public MouseHandler(Fractales frame) {
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
//					frame.zoomOut(new Punto(xPos, yPos));
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
//					frame.zoomIn(new Punto(xPos, yPos));
				}
			};
			SwingUtilities.invokeLater(addIt);
			titulo += " con el botón izquierdo del ratón";
		}
		
//		System.out.println(titulo);
//		System.out.println(xPos + " - " + yPos);
	}
	
	@Override
	public void mousePressed(MouseEvent evento){
		xPos = evento.getX();
		yPos = evento.getY();
		
		System.out.println("Presionado " + xPos + " - " + yPos);
	}
	
	@Override
	public void mouseReleased(MouseEvent evento){
		if (zoom){
			int xPosRelease = evento.getX();
			int yPosRelease = evento.getY();
			
			if ( (Math.abs(xPos - xPosRelease) < MIN_PIXELS ) || (Math.abs(yPos - yPosRelease) < MIN_PIXELS) ){
				return;
			}
			
			System.out.println("Soltado " + xPos + " - " + yPos);
			
			if(xPosRelease > xPos){
				if (yPosRelease > yPos){
					frame.zoom(yPos, yPosRelease, xPos, xPosRelease);
				}else{
					frame.zoom(yPosRelease, yPos, xPos, xPosRelease);
				}
			}else{
				if (yPosRelease > yPos){
					frame.zoom(yPos, yPosRelease, xPosRelease, xPos);
				}else{
					frame.zoom(yPosRelease, yPos, xPosRelease, xPos);
				}
			}
			
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent evento){
		
		long newTime = System.currentTimeMillis();
		
		if(mover && newTime - oldTime  > REFRESH_PERIOD){
			oldTime = newTime;
			
			int difX = evento.getX() - xPos;
			int difY = evento.getY() - yPos;
			
			frame.mover(difY, difX);
			System.out.println("Movido: " + difX + " - " + difY);
			
			xPos = evento.getX();
			yPos = evento.getY();
		}
		
	}
	
	@Override
	public void mouseMoved(MouseEvent evento){
		frame.setPosicionActual(evento.getY(), evento.getX());
	}

//	public boolean isMover() {
//		return mover;
//	}

	public void setMover(boolean mover) {
		System.out.println("Set mover " + mover);
		this.mover = mover;
	}

//	public boolean isZoom() {
//		return zoom;
//	}

	public void setZoom(boolean zoom) {
		System.out.println("Set zoom " + zoom);
		this.zoom = zoom;
	}
	
}
