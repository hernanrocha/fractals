package thread;

import java.util.concurrent.Semaphore;

import basic.Complejo;
import conjunto.Conjunto;
import conjunto.GraficoConjuntos;

public class GraficadorSegmento implements Runnable {
	
	private int min, max;
	private Semaphore s;
	private GraficoConjuntos g;
	private Conjunto conjunto;
	private int width, height;
	private double xMin, xMax, yMin, yMax;

	public GraficadorSegmento(int min, int max, GraficoConjuntos g, Semaphore s) {
		super();
		
		this.min = min;
		this.max = max;
		this.g = g;
		this.s = s;
		
		conjunto = g.getConjunto();
		width = g.getWidth();
		height = g.getHeight();
		xMin = g.getxMin();
		xMax = g.getxMax();
		yMin = g.getyMin();
		yMax = g.getyMax();
	}

	@Override
	public void run() {
		
		for (int f = min; f <= max; f++){
			for (int c = 0; c < width; c++){
				if (g.moviendo && f-g.fDelta >= 0 && f-g.fDelta < height && c-g.cDelta >= 0 && c-g.cDelta < width){
					g.matrizNueva[f][c] = g.matrizVal[f-g.fDelta][c-g.cDelta];
				}else{
					double xTemp = xMin + c * (xMax - xMin) / width;
					double yTemp = yMin + (height - f) * (yMax - yMin) / height;
					g.matrizNueva[f][c] = conjunto.iterar(new Complejo(xTemp, yTemp));
				}
			}
			g.getFrame().addProgress(1);
		}
//		System.out.println("Termine. Soy " + Thread.currentThread().getName());
		s.release();

	}

}
