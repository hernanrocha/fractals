package swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import thread.ImageGenerator;

public class PanelImagen extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final int NONE = 0;
	public static final int MOVER = 1;
	public static final int ZOOM = 2;
	
	boolean cargado;
	float alpha = 0;

	private Image imagen, imagenVieja = null;

	private boolean mover;

	private boolean zoom;

	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private int action;
	
	 public PanelImagen() {
	        setBorder(BorderFactory.createLineBorder(Color.black));
	    }

	    public Dimension getPreferredSize() {
	        return new Dimension(600,550);
	    }

	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);  
	        
	        Graphics2D g2d = (Graphics2D) g;

	        if (cargado){
	        	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	        	g.drawImage(imagen, 0, 0, null);
	        	if(imagenVieja != null){
		        	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - alpha));
		        	g.drawImage(imagenVieja, 0, 0, null);
	        	}
		        
	        	// Dibujar linea
	        	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	        	g.setColor(Color.GREEN);
	        	
	        	if(action == MOVER){
		        	g.drawLine(xPos, yPos, width, height);
		        	g.setColor(Color.RED);
		        	g.drawLine(xPos + 1, yPos + 1, width + 1, height + 1);
	        	}else if(action == ZOOM){
		        	g.drawRect(xPos, yPos, width, height);
		        	g.setColor(Color.RED); 
		        	g.drawRect(xPos - 1, yPos - 1, width +2, height + 2);
	        	}
	        }
	    }

	    // TODO Sacar transicion para movimiento
		public void setImagen(Image imagen, boolean interpolar) {
			imagenVieja = this.imagen;
			this.imagen = imagen;
			cargado = true;
			
			if(interpolar){
				alpha = (float) 0.0;
				ImageGenerator worker = new ImageGenerator(this);
				worker.execute();
			}else{
				alpha = (float) 1.0;
				updateUI();
			}
		}
		
		public void setAlpha(float alpha){
			this.alpha = alpha;
		}

		public void setParametros(int xPos, int yPos, int width, int height, int action) {
			this.xPos = xPos;
			this.yPos = yPos;
			this.width = width;
			this.height = height;
			this.action = action;
			
			updateUI();
		}
}
