import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import coloreo.Coloreo;

import conjunto.Conjunto;

import basic.Complejo;
import basic.MatrizHash;
import basic.Punto;


public class Grafico {

	
	private int pixelsX = 600;
	private int pixelsY = 600;
	private int[][] matriz = new int[pixelsX][pixelsY];
	
	private Complejo min = new Complejo(-2, 2);
	private Complejo max = new Complejo(2, -2);
	private Complejo centro = new Complejo(0, 0);
	private double radio = 2;
	private double deltaX = ( max.getReal() - min.getReal() ) / (pixelsX);	
	private double deltaY = ( max.getImag() - min.getImag() ) / (pixelsY);
	private MatrizHash cache = new MatrizHash();
	
	Conjunto conjunto;
	Coloreo coloreo;
	
	private miFrame frame;
	
	public Grafico(miFrame frame, Conjunto conjunto, Coloreo coloreo){
		this.frame = frame;
		this.conjunto = conjunto;
		this.coloreo = coloreo;
		
		calcularLimites();
	}
	
	public int getPixelsX() {
		return pixelsX;
	}

	public int getPixelsY() {
		return pixelsY;
	}
	
	public void zoomIn(Punto p){
		
		centro = Complejo.suma(min, new Complejo( deltaX*p.getX() , deltaY*p.getY()));
		radio = radio / 2;
		
		calcularLimites();
	}
	
	public void zoomOut(Punto p){
				
		centro = Complejo.suma(min, new Complejo( deltaX*p.getX() , deltaY*p.getY()));
		radio = radio * 2;
		
		calcularLimites();
	}
	
	private void calcularLimites(){		
		min = Complejo.suma(centro, new Complejo(-radio, radio));
		max = Complejo.suma(centro, new Complejo(radio, -radio));
		
		deltaX = ( max.getReal() - min.getReal() ) / (pixelsX);
		deltaY = ( max.getImag() - min.getImag() ) / (pixelsY);
//		System.out.println(deltaY);
//		deltaY = -deltaY;
		
//		deltaY = (double) Math.round(deltaX * 1000000000) / 1000000000;
//		deltaY = (double) Math.round(deltaY * 100000) / 100000;
	}

	public void calcular(){
		
		System.out.println("Deltas: " + deltaX + " - " + deltaY);
		
		for (int f = 0; f < pixelsX; f++){
			for (int c = 0; c < pixelsY; c++){
				Complejo temp = Complejo.suma(min, new Complejo( deltaX*f , deltaY*c));
				Integer it;
				
				// Buscar en cache
//				it = cache.get(temp);
				
				// Fallo de cache
//				if (it == null){
					it = conjunto.iterar(temp);
//					cache.put(temp, it);
//				}else{
//					System.out.println("Acierto de cache");
//				}
				
				matriz[f][c] = it;
//				System.out.println("f - c:" + f + " - " + c + " (" + temp + ")");
			}
		}
	}
	
	public void graficar(JLabel lblImagen){
		int[] imagen = new int[pixelsX*pixelsY];
		
		for (int c = 0; c < pixelsY; c++){
			for (int f = 0; f < pixelsX; f++){
				int valor = matriz[f][c];
//				System.out.println(f + " - " + c + ": " + valor);
//				imagen[c * pixelsY + f] = colores[valor % cantColores];
				imagen[c * pixelsY + f] = coloreo.colorear(valor);
			}
		}
		
	    int tam = (int) Math.sqrt(imagen.length);
	    
//	    System.out.println(tam);
	    
	    Image imagenNueva = frame.createImage(new MemoryImageSource(tam, tam, imagen, 0, tam ));
	    
        //Escalar Imagen
	    ImageIcon icon = new ImageIcon(imagenNueva);
//        ImageIcon tmpIcon = new ImageIcon(icon.getImage().getScaledInstance(pixelsX, pixelsY, Image.SCALE_DEFAULT));
	    ImageIcon tmpIcon = new ImageIcon(icon.getImage().getScaledInstance(pixelsX, pixelsY, Image.SCALE_DEFAULT));
	    lblImagen.setIcon(tmpIcon);
	    lblImagen.setMaximumSize(new Dimension(pixelsX, pixelsY));
	    
	}
	
}
