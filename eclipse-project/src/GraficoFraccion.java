/**
 * 
 */

/**
 * @author hernan
 *
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import coloreo.Coloreo;

import conjunto.Conjunto;

import basic.Complejo;
import basic.ComplejoFraccion;
import basic.Fraccion;
import basic.MatrizHash;
import basic.Punto;


public class GraficoFraccion {

	
	private int pixelsX = 100;
	private int pixelsY = 100;
	private int[][] matriz = new int[pixelsX][pixelsY];
	
	private ComplejoFraccion min = new ComplejoFraccion(-2, 2);
	private ComplejoFraccion max = new ComplejoFraccion(2, -2);
	private ComplejoFraccion centro = new ComplejoFraccion(0, 0);
	private double radio = 2;
	private Fraccion deltaX;	
	private Fraccion deltaY;
	private MatrizHash cache = new MatrizHash();
	
	Conjunto conjunto;
	Coloreo coloreo;
	
	private miFrame frame;
	
	public GraficoFraccion(miFrame frame, Conjunto conjunto, Coloreo coloreo){
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
		
		centro = ComplejoFraccion.suma(min, new ComplejoFraccion( Fraccion.multiplicar(deltaX, p.getX()), Fraccion.multiplicar(deltaY, p.getY())));
		radio = radio / 2;
		
		calcularLimites();
	}
	
	public void zoomOut(Punto p){
				
		centro = ComplejoFraccion.suma(min, new ComplejoFraccion( Fraccion.multiplicar(deltaX, p.getX()), Fraccion.multiplicar(deltaY, p.getY())));
		radio = radio * 2;
		
		calcularLimites();
	}
	
	private void calcularLimites(){		
		min = ComplejoFraccion.suma(centro, new ComplejoFraccion(-radio, radio));
		max = ComplejoFraccion.suma(centro, new ComplejoFraccion(radio, -radio));
		
//		deltaX = ( max.getReal() - min.getReal() ) / (pixelsX);
//		deltaY = ( max.getImag() - min.getImag() ) / (pixelsY);
		
		deltaX = Fraccion.dividir(Fraccion.restar(max.getFraccionReal(), min.getFraccionReal()), pixelsX);
		deltaY = Fraccion.dividir(Fraccion.restar(max.getFraccionImag(), min.getFraccionImag()), pixelsY);
		
		deltaX.simplificar();
		deltaY.simplificar();
		
//		System.out.println(deltaY);
//		deltaY = -deltaY;
		
//		deltaY = (double) Math.round(deltaX * 1000000000) / 1000000000;
//		deltaY = (double) Math.round(deltaY * 100000) / 100000;
	}

	public void calcular(){
		
		System.out.println("Deltas: " + deltaX + " - " + deltaY);
		
		for (int f = 0; f < pixelsX; f++){
			for (int c = 0; c < pixelsY; c++){
				ComplejoFraccion temp = ComplejoFraccion.suma(min, new ComplejoFraccion( Fraccion.multiplicar(deltaX, f) , Fraccion.multiplicar(deltaY, c)));
				Integer it;
				
				// Buscar en cache
//				System.out.println("buscando " + temp);
				it = cache.get(temp);
				
				// Fallo de cache
				if (it == null){
//					it = conjunto.iterar(temp);
					cache.put(temp, it);
				}else{
//					System.out.println("Acierto de cache");
				}
				
				matriz[f][c] = it;
//				System.out.println("f - c:" + f + " - " + c + " (" + temp + ")");
			}
		}
		
		System.out.println("Aciertos de cache: " + cache.getAciertos());
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