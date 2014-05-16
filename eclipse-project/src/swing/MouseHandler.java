package swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	@Override
	public void mousePressed(MouseEvent evento){
		if (!frame.isCargado()){
			return;
		}
		
		// Actualizar posicion
		xPos = evento.getX() - frame.getImagen().getMinX();
		yPos = evento.getY() - frame.getImagen().getMinY();
		
//		System.out.println("Presionado " + xPos + " - " + yPos);
	}
	
	@Override
	public void mouseReleased(MouseEvent evento){
		if (!frame.isCargado()){
			return;
		}
		
		int xPosRelease = evento.getX() - frame.getImagen().getMinX();
		int yPosRelease = evento.getY() - frame.getImagen().getMinY();
		
		frame.getImagen().setParametros(0, 0, 0, 0, PanelImagen.NONE);
		
//		System.out.println("Soltado " + xPosRelease + " - " + yPosRelease);
		
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
			int difX = xPosRelease - xPos;
			int difY = yPosRelease - yPos;
			
			// Realizar movimiento
//			System.out.println("Mover: " + difX + " - " + difY);
			frame.mover(difY, difX);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent evento){
		if (!frame.isCargado()){
			return;
		}
		
		int xDrag = evento.getX() - frame.getImagen().getMinX();
		int yDrag = evento.getY() - frame.getImagen().getMinY();
		
		long newTime = System.currentTimeMillis();
		
		if(mover && moverInteractivo && newTime - oldTime  > REFRESH_PERIOD){
			oldTime = newTime;
			
			// Calcular movimiento
			int difX = xDrag - xPos;
			int difY = yDrag - yPos;
			
			// Calcular nueva posicion
			xPos = xDrag;
			yPos = yDrag;
			
			// Realizar movimiento
//			System.out.println("Mover interactivo: " + difX + " - " + difY);
			frame.mover(difY, difX);
			
		}else if (mover){
			// Actualizar linea
			frame.getImagen().setParametros(xPos, yPos, xDrag, yDrag, PanelImagen.MOVER);
		}else if (zoom){
			// Actualizar rectangulo
			int x = Math.min(xPos, xDrag);
			int y = Math.min(yPos, yDrag);
			int w = Math.abs(xPos - xDrag);
			int h = Math.abs(yPos - yDrag);
			frame.getImagen().setParametros(x, y, w, h, PanelImagen.ZOOM);
		}
		
	}
	
	@Override
	public void mouseMoved(MouseEvent evento){
		if (!frame.isCargado()){
			return;
		}
		
		frame.setPosicionActual(evento.getY() - frame.getImagen().getMinY(), evento.getX() - frame.getImagen().getMinX());

	}
	
	// Setters

	public void setMover(boolean mover) {
		this.mover = mover;
	}
	
	public void setMoverInteractivo(boolean moverInteractivo) {
		this.moverInteractivo = moverInteractivo;
	}

	public void setZoom(boolean zoom) {
		this.zoom = zoom;
	}
	
}
