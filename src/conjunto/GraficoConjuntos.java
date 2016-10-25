package conjunto;

import grafico.Grafico;

import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.util.concurrent.Semaphore;

import paleta.PaletaDefault;
import thread.GraficadorSegmento;


public class GraficoConjuntos extends Grafico{

	private static final int FACTOR = 8;
	
	// Variables Screen (PUBLIC para permitir acelerar el procesamiento por parte de los threads)
	public int[][] matrizVal, matrizNueva;
	
	private Conjunto conjunto;	
	Semaphore calculador = new Semaphore(1);
	private long tiempoProcesamiento = 0;
	private int threads = 1;

	private long begin;
	
	public GraficoConjuntos(){
		
		// Inicializar matriz de Valores
		matrizVal = new int[height][width];
		
		// Paleta por defecto
		paleta = new PaletaDefault();
//		paleta = new PaletaArchivo(new File("paletas/b&w.pml"), true);
		
	}
	
	@Override
	public void calcular(){
		try {
			calculador.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		begin = System.currentTimeMillis();

		final Semaphore s = new Semaphore(0);
		int div = height / threads;
		int resto = height % threads;
		
		// Generar nueva matriz para guardar el resultado
		matrizNueva = new int[height][width];
		
		// Worker encargado de esperar a que los Threads realicen la iteracion, y luego realizar el calculo de la imagen
		WorkerConjunto worker = new WorkerConjunto(this, s, threads);
		worker.execute();
		
		// Lanzar Threads
		int min = 0;
		for (int i = 0; i < threads; i++){
			// Calcular salto (Cantidad de lineas que procesa el thread)
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
	}
	
	@Override
	public Image generarImagen(){		
		int[] imagen = new int[width*height];
		
		for (int f = 0; f < height; f++){
			for (int c = 0; c < width; c++){
				int valor = matrizVal[f][c];
				imagen[f * width + c] = paleta.getColor(valor * FACTOR).getRGB();
			}
		}
		
	    Image imagenNueva = frame.createImage(new MemoryImageSource(width, height, imagen, 0, width));
	    
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
	
	public void pintar(){		
		matrizVal = matrizNueva.clone();
		moviendo = false;
		frame.resetProgress();
		long end = System.currentTimeMillis();
		tiempoProcesamiento = end - begin;
		
		frame.actualizarInterfaz();
		
		calculador.release();
	}
	
}
