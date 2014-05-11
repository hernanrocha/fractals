package conjunto;
import grafico.Grafico;

import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.util.concurrent.Semaphore;

import paleta.Paleta;
import paleta.PaletaArchivo;
import swing.Fractales;
import thread.GraficadorSegmento;
import basic.Complejo;


public class GraficoConjuntos extends Grafico{

	private static final int FACTOR = 10;
	
	// Variables Screen
	public int[][] matrizVal, matrizNueva;
	
	private Conjunto conjunto;
	private Paleta coloreo;
	
	private long tiempoProcesamiento = 0;
	private int threads = 1;
	
	// DEBUG
	public boolean aceleracionMover = true;
	
	public GraficoConjuntos(Conjunto conjunto, Paleta coloreo){
		this.conjunto = conjunto;
		this.coloreo = coloreo;
		
		calcularLimites();
	}
	
	public GraficoConjuntos(){
//		xMin = min.getReal();
//		xMax = max.getReal();
//		yMin = min.getImag();
//		yMax = max.getImag();
		
		matrizVal = new int[height][width];
		
		// TODO Arreglar
		this.coloreo = new PaletaArchivo(new File("paletas/default.pml"), true);
		this.conjunto = new ConjuntoMandelbrot(2);
		
		calcularLimites();
	}
	


	@Override
	public void calcular(){
		
		long begin = System.currentTimeMillis();

		Semaphore s = new Semaphore(0);
		
		int div = height / threads;
		int resto = height % threads;
		
		System.out.println(div + " y sobran " + resto);
		System.out.println("ALERTA: DEBUG Aceleracion Mover");
		matrizNueva = new int[height][width]; // DEBUG
		
		int min = 0;
		for (int i = 0; i < threads; i++){
			// Calcular salto
			int salto = div;
			if (i < resto){
				salto++;
			}
			
			// Calcular extremos del segmento
			int max = min + salto - 1;			
			
			// Lanzar thread para calcular el segmento
			GraficadorSegmento seg = new GraficadorSegmento(min, max, this, s);
			(new Thread(seg)).start();
			
			// Actualizar minimo
			min += salto;
		}
		
		
		try {
			// Esperar a que todos los threads terminen
			s.acquire(threads);
			matrizVal = matrizNueva.clone(); // DEBUG
			moviendo = false; // DEBUG
			long end = System.currentTimeMillis();
			tiempoProcesamiento = end - begin;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Image generarImagen(){
		int[] imagen = new int[width*height];
		
		for (int f = 0; f < height; f++){
			for (int c = 0; c < width; c++){
				int valor = matrizVal[f][c];
				imagen[f * width + c] = coloreo.getColor(valor * FACTOR).getRGB();
			}
		}
		
	    Image imagenNueva = frame.createImage(new MemoryImageSource(width, height, imagen, 0, width ));
	    
	    return imagenNueva;
	}
	
	public Conjunto getConjunto() {
		return conjunto;
	}

	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}

	public long getTiempoProcesamiento() {
		return tiempoProcesamiento;
	}

	public void setThreads(int threads) {
		this.threads = threads;		
	}
	
}
