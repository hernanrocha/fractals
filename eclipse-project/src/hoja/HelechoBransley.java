/**
 * 
 */
package hoja;


import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.MemoryImageSource;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import swing.Fractales;

public class HelechoBransley {
	
	private Fractales frame;
	
	private Point punto;
	private int width = 1000;
	private int height = 700;
	private int[] matriz = new int[width * height];
	private Afines afines = Afines.SIERPINSKI;
//	private Afines afines = Afines.HELECHO;
	private static int PROFUNDIDAD = 3;
	private Vector<Integer> indices = new Vector<Integer>();

	private int lastIndex;
	
	public HelechoBransley(Fractales frame){
		this.frame = frame;		
	}

	// Calcular el fractal por IFS
	public void calcular(){
		
		// Punto inicial
		punto = new Point(0, 0);
		
		// Realizar IFS
		for (int it=0 ; it < 100000; it++) {
			nuevoPunto();
		}
		
	}
	
	// Obtener nuevo punto a partir del anterior
	private void nuevoPunto() {
		Point nuevoPunto = afines.aplicar(punto);

		int index = afines.getIndice();
		indices.add(index);
		
		// Color
		int r = 0, g = 0;
		
		
		
		if (indices.size() > PROFUNDIDAD){
			r = (indices.get(indices.size() - PROFUNDIDAD) - 1) * 80;
		}
//		r = ( indices.lastElement() - 1) * 80;
		
//		switch (index) {
//		case 1:
//			r = 0;
//			break;
//		case 2:
//			r = 85;
//			break;		
//		case 3:
//			r = 170;
//			break;		
//		case 4:
//			r = 255;
//			break;
//		default:
//			break;
//		}
//		
//		switch (lastIndex) {
//		case 1:
//			g = 0;
//			break;
//		case 2:
//			g = 85;
//			break;		
//		case 3:
//			g = 170;
//			break;		
//		case 4:
//			g = 255;
//			break;
//		default:
//			break;
//		}

		setPixel(nuevoPunto.x + 400, nuevoPunto.y, new Color(r, g, 128).getRGB());

		punto = nuevoPunto;
		

		lastIndex = index;

	}
	
	// Colorear un pixel en la matriz
	private void setPixel(int c, int f, int color) {
		if (c>=0 && f>=0 && c<width && f<height){
			matriz[(height - f + 1)*width + c] = color;
		}else{
			System.out.println("Advertencia: Pixeles fuera de la imagen");
		}
		
	}

	// Graficar en un JLabel a partir de la matriz almacenada
	public void graficar(JLabel lblImagen){
	    
	    Image imagenNueva = frame.createImage(new MemoryImageSource(width, height, matriz, 0, width ));
	    ImageIcon icon = new ImageIcon(imagenNueva);
	    lblImagen.setIcon(icon);
	
	}   
	    
        //Escalar Imagen
//	    ImageIcon icon = new ImageIcon(imagenNueva);
//      ImageIcon tmpIcon = new ImageIcon(icon.getImage().getScaledInstance(pixelsX, pixelsY, Image.SCALE_DEFAULT));
//	    ImageIcon tmpIcon = new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
//	    lblImagen.setIcon(icon);
//	    lblImagen.setMaximumSize(new Dimension(pixelsX, pixelsY));	  
//	}
	
}