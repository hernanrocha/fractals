package thread;

import java.util.List;

import javax.swing.SwingWorker;

import swing.PanelImagen;

public class ImageGenerator extends SwingWorker<String, Integer> {

	private static final long SLEEP_TIME = 50;
	
	private PanelImagen panelImagen;
	
	public ImageGenerator(PanelImagen panelImagen){
		this.panelImagen = panelImagen;
	}

	@Override
	protected String doInBackground() throws Exception {
		for(int i = 0; i <= 10; i++){
			publish(i*10);
			Thread.sleep(SLEEP_TIME);
		}
		
		return "Finished";
	}

	protected void process(List<Integer> chunks){
		float alpha = chunks.get(0);
		alpha /= 100;
		panelImagen.setAlpha(alpha);
		panelImagen.updateUI();
	}
	
}
