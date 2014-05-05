package fractal;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import paleta.Paleta;
import paleta.PaletaArchivo;

import swing.Fractales;
import thread.GraficadorSegmento;
import basic.Complejo;
import conjunto.Conjunto;
import conjunto.ConjuntoMandelbrot;


public class Grafico {

	// Variables Screen
	private int width;
	private int height;
	public int[][] matriz;
	
	// Variables Grafico
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	
	private Conjunto conjunto;
	private Paleta coloreo;	
	private Fractales frame;
	
	private long tiempoProcesamiento = 0;
	private int threads = 1;
	
	public Grafico(Fractales frame, Conjunto conjunto, Paleta coloreo){
		this.frame = frame;
		this.conjunto = conjunto;
		this.coloreo = coloreo;
		
		calcularLimites();
	}
	
	public Grafico(Fractales frame, Complejo min, Complejo max, int width, int height){
		xMin = min.getReal();
		xMax = max.getReal();
		yMin = min.getImag();
		yMax = max.getImag();
		
		this.width = width;
		this.height = height;
		matriz = new int[height][width];
		
		// TODO Arreglar
		this.frame = frame;
		this.coloreo = new PaletaArchivo(new File("paletas/default.pml"), true);
		this.conjunto = new ConjuntoMandelbrot(2);
		
		calcularLimites();
	}
	
	private void calcularLimites(){
		
		double imgRatio = (double) width / height;
		
		double difX = xMax - xMin;
		double difY = yMax - yMin;
		double coordRatio = difX / difY;
				
		if (coordRatio < imgRatio){
			// Agrandar rango en eje X
			System.out.println("Agrandar eje X");
			double difXNuevo = imgRatio * difY;
			xMin = xMin - (difXNuevo - difX) / 2;
			xMax = xMin + difXNuevo;
		}else if (coordRatio > imgRatio){
			// TODO Agrandar rango en eje Y
			System.out.println("Agrandar eje Y (No implementado)");
		}
		
		System.out.println("Extremos X: " + xMax + " y " + xMin);
		System.out.println("Extremos Y: " + yMax + " y " + yMin);

	}

	public void calcular(){
		
		long begin = System.currentTimeMillis();

		Semaphore s = new Semaphore(0);
		
		int div = height / threads;
		int resto = height % threads;
		
		System.out.println(div + " y sobran " + resto);
		
		int min = 0;
		for (int i = 0; i < threads; i++){
			// Calcular salto
			int salto = div;
			if (i < resto){
				salto++;
			}
			
			// Calcular extremos del segmento
			int max = min + salto - 1;			
//			System.out.println("Desde " + min + " a " + max);
			
			// Lanzar thread para calcular el segmento
			GraficadorSegmento seg = new GraficadorSegmento(min, max, this, s);
			(new Thread(seg)).start();
			
			// Actualizar minimo
			min += salto;
		}
		
		try {
			// Esperar a que todos los threads terminen
			s.acquire(threads);
			long end = System.currentTimeMillis();
			tiempoProcesamiento = end - begin;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
//		for (int f = 0; f < height; f++){
//			for (int c = 0; c < width; c++){
//				double xTemp = xMin + c * (xMax - xMin) / width;
//				double yTemp = yMin + (height - f) * (yMax - yMin) / height;
//				matriz[f][c] = conjunto.iterar(new Complejo(xTemp, yTemp));
//			}
//		}
	}
	
	public void graficar(JLabel lblImagen){
		int[] imagen = new int[width*height];
		
		for (int f = 0; f < height; f++){
			for (int c = 0; c < width; c++){
				int valor = matriz[f][c];
				imagen[f * width + c] = coloreo.getColor(valor * 15).getRGB();
			}
		}
		
	    Image imagenNueva = frame.createImage(new MemoryImageSource(width, height, imagen, 0, width ));
	    lblImagen.setIcon(new ImageIcon(imagenNueva));
	    lblImagen.setMaximumSize(new Dimension(width, height));
	}

	public void mover(int f, int c) {
		double xMove = c * (xMax - xMin) / width;
		double yMove = f * (yMax - yMin) / height;
		
		xMin -= xMove;
		xMax -= xMove;
		yMin += yMove;
		yMax += yMove;
		
		calcular();
		
	}
	
	public void zoom(int fMin, int fMax, int cMin, int cMax){

		double xMin = getCtoX(cMin);
		double xMax = getCtoX(cMax);
		double yMin = getFtoY(fMax);
		double yMax = getFtoY(fMin);
		
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;

		calcularLimites();
		calcular();
	}
		
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
	
	public Conjunto getConjunto() {
		return conjunto;
	}

	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	public double getCtoX(int c) {
		return xMin + c * (xMax - xMin) / width;
	}

	public double getFtoY(int f) {
		return yMin + (height - f) * (yMax - yMin) / height;
	}

	public long getTiempoProcesamiento() {
		return tiempoProcesamiento;
	}

	public void setThreads(int threads) {
		this.threads = threads;		
	}
	
}
