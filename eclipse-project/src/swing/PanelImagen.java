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

	// Accion actual (Mover, Zoom, Otra)
	public static final int NONE = 0;
	public static final int MOVER = 1;
	public static final int ZOOM = 2;	

	private Image imagen, imagenVieja = null;
	private boolean cargado;
	private float alpha = 0;

	private int p1;
	private int p2;
	private int p3;
	private int p4;
	private int action;

	public PanelImagen() {
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public Dimension getPreferredSize() {
		return new Dimension(600,600);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);  

		Graphics2D g2d = (Graphics2D) g;

		if (cargado){
//			System.out.println("Mostrando en pantalla");
			
			int posX = getMinX();
			int posY = getMinY();
			
//			System.out.println(posX + " - " + posY);
			
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g.drawImage(imagen, posX, posY, null);
			if(imagenVieja != null){
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - alpha));
				g.drawImage(imagenVieja, posX, posY, null);
			}

			// Dibujar linea
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			g.setColor(Color.GREEN);

			if(action == MOVER){
				g.drawLine(p1 + posX, p2 + posY, p3 + posX, p4 + posY);
				g.setColor(Color.RED);
				g.drawLine(p1 + 1 + posX, p2 + 1 + posY, p3 + 1 + posX, p4 + 1 + posY);
			}else if(action == ZOOM){
				g.drawRect(p1 + posX, p2 + posY, p3, p4);
				g.setColor(Color.RED); 
				g.drawRect(p1 - 1 + posX, p2 - 1 + posY, p3 +2, p4 + 2);
			}
		}
	}

	// Sacar transicion para movimiento
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

	public void setParametros(int p1, int p2, int p3, int p4, int action) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.action = action;

		updateUI();
	}

	public int getMinX() {
		if (imagen == null)
			return 0;
		
		return ( getWidth() - imagen.getWidth(null) ) / 2;
	}

	public int getMinY() {
		if (imagen == null)
			return 0;
		
		return ( getHeight() - imagen.getHeight(null) ) / 2;
	}
}
