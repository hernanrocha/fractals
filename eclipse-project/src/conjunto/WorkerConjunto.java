package conjunto;

import java.util.concurrent.Semaphore;

import javax.swing.SwingWorker;

public class WorkerConjunto extends SwingWorker<String, Integer> {

	private Semaphore s;
	private int threads;
	private GraficoConjuntos graficoConjuntos;

	public WorkerConjunto(GraficoConjuntos graficoConjuntos, Semaphore s, int threads) {
		this.s = s;
		this.threads = threads;
		this.graficoConjuntos = graficoConjuntos;
	}

	@Override
	protected String doInBackground() throws Exception {
		// Esperar a que todos los threads terminen
//		System.out.println("Esperando a threads.");
		s.acquire(threads);
//		System.out.println("A pintar..");
		graficoConjuntos.pintar();
		return null;
	}
	
	@Override
	protected void done() {
		graficoConjuntos.getFrame().resetProgress();
//		System.out.println("Termine");
	}

}
