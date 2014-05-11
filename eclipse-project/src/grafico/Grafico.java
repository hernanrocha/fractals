package grafico;

import java.awt.Image;

import swing.Fractales;

public abstract class Grafico {
	
	// Variables Screen	
	protected static Fractales frame;
	protected static int width;
	protected static int height;	

	// Variables Grafico
	protected double xMin;
	protected double xMax;
	protected double yMin;
	protected double yMax;

	// Variables de movimiento
	public boolean moviendo;
	public int fDelta, cDelta;

	public static void setFrame(Fractales frame, int width, int height) {
		Grafico.frame = frame;
		Grafico.width = width;
		Grafico.height = height;
	}
	
	protected void calcularLimites(){
		
		double imgRatio = (double) width / height;
		
		double difX = xMax - xMin;
		double difY = yMax - yMin;
		double coordRatio = difX / difY;
				
		if (coordRatio < imgRatio){
			// Agrandar rango en eje X
			double difXNuevo = imgRatio * difY;
			xMin = xMin - (difXNuevo - difX) / 2;
			xMax = xMin + difXNuevo;
		}else if (coordRatio > imgRatio){
			// Agrandar rango en eje Y
			double difYNuevo = difX / imgRatio;
			yMin = yMin - (difYNuevo - difY) / 2;
			yMax = yMin + difYNuevo;
		}
		
		System.out.println("Rango X: (" + xMax + ", " + xMin + ")");
		System.out.println("Rango Y: (" + yMax + ", " + yMin + ")");

	}
	
	// Mover a un rango especifico (necesita recalculo de limites)
	public void setRango(double xMin, double xMax, double yMin, double yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		
		calcularLimites();
		calcular();
	}
	
	// Mover una cantidad de filas y columnas determinada (no necesita recalculo de limites)
	public void mover(int f, int c) {
		
		moviendo = true;
		fDelta = f;
		cDelta = c;
		
		double xMove = c * (xMax - xMin) / width;
		double yMove = f * (yMax - yMin) / height;		

		xMin -= xMove;
		xMax -= xMove;
		yMin += yMove;
		yMax += yMove;

		calcular();
	}
	
	// Hacer zoom (necesita recalculo de limites)
	public void zoom(int fMin, int fMax, int cMin, int cMax){

		double xMin = getCtoX(cMin);
		double xMax = getCtoX(cMax);
		double yMin = getFtoY(fMax);
		double yMax = getFtoY(fMin);
		
		setRango(xMin, xMax, yMin, yMax);
	}
	
	// Convertir numero de columna a valor en X
	public double getCtoX(int c) {
		return xMin + c * (xMax - xMin) / width;
	}

	// Convertir numero de fila a valor en Y
	public double getFtoY(int f) {
		return yMin + (height - f) * (yMax - yMin) / height;
	}
	
	// Convertir valor de X a numero de columna
	public int getXtoC(double x){
		return (int) ((x - xMin) * width / (xMax - xMin));
	}
	
	// Convertir valor de Y a numero de fila
	public int getYtoF(double y){
		return (int) (height - (y - yMin) * height / (yMax - yMin));
	}

	// Metodos abstractos
	
	public abstract void calcular();
	
	public abstract Image generarImagen();
	
	
	// Getters
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public double getxMin() {
		return xMin;
	}

	public double getxMax() {
		return xMax;
	}

	public double getyMin() {
		return yMin;
	}

	public double getyMax() {
		return yMax;
	}

}
