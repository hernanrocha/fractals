/**
 * 
 */
package afin;

import grafico.Grafico;

import java.util.Vector;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.MemoryImageSource;

import swing.Fractales;

public class GraficoAfines extends Grafico{
	private Vector<Afin> v = new Vector<Afin>();
	private Vector<Double> probAcum = new Vector<Double>();

	private int indice; 
	private double x = 0;
	private double y = 0;
	
	public static GraficoAfines HELECHO;
	public static GraficoAfines ARCE;
	public static GraficoAfines ARBOL;
	public static GraficoAfines ARBOL_2;
	public static GraficoAfines SIERPINSKI;
	
	private Fractales frame;
	
	private Point punto;
	private int width = 1000;
	private int height = 700;
	private int[] matriz = new int[width * height];
	private static int PROFUNDIDAD = 3;
	private Vector<Integer> indices = new Vector<Integer>();

	public static void cargarAfines(){
		// Helecho
		HELECHO = new GraficoAfines();
		HELECHO.agregarAfin(new Afin( 0   , 0	, 0	  , 0.20, 0,  10), 0.01);
		HELECHO.agregarAfin(new Afin( 0.85, 0.04,-0.04, 0.85, 0, 100), 0.85);
		HELECHO.agregarAfin(new Afin( 0.20,-0.26, 0.23, 0.22, 0, 100), 0.07);
		HELECHO.agregarAfin(new Afin(-0.15, 0.30, 0.26, 0.24, 0,  28), 0.07);
		
		ARCE = new GraficoAfines();
		ARCE.agregarAfin(new Afin(0.14	,0.1	,0		,0.51	,-0.08 	,-1.31)	,0.10);
		ARCE.agregarAfin(new Afin(0.43	,0.52	,-0.45	,0.50 	,1.49	,-0.75)	,0.35);
		ARCE.agregarAfin(new Afin(0.45	,-0.49	,0.47	,0.47	,-1.62	,-0.74)	,0.35);
		ARCE.agregarAfin(new Afin(0.49	,0		,0.0	,0.51	,0.02	,1.62)	,0.20);

		ARBOL = new GraficoAfines();
		ARBOL.agregarAfin(new Afin(0		,0		,0		,0.50	,0 	,0)		,0.05);
		ARBOL.agregarAfin(new Afin(0.42	,-0.42	,0.42	,0.42	,0	,0.2)	,0.4);
		ARBOL.agregarAfin(new Afin(0.42	,0.42	,-0.42	,0.42	,0	,0.2)	,0.4);
		ARBOL.agregarAfin(new Afin(0.10	,0		,0		,0.10	,0	,0.2)	,0.15);

		ARBOL_2 = new GraficoAfines();
		ARBOL_2.agregarAfin(new Afin(0.195	,-0.488	,0.344	,0.443	,0.4431 	,0.2452)	,0.2);
		ARBOL_2.agregarAfin(new Afin(0.462	, 0.414	,-0.252 ,0.361	,0.2511 	,0.5692)	,0.2);
		ARBOL_2.agregarAfin(new Afin(-0.058,-0.070	,0.453	,-0.111 ,0.5976 	,0.0969)	,0.2);
		ARBOL_2.agregarAfin(new Afin(-0.035, 0.070	,-0.469 ,-0.022 ,0.4431 	,0.5069)	,0.2);
		ARBOL_2.agregarAfin(new Afin(-0.637, 0.0  	,0.0  	,0.501	,0.8562 	,0.2513)	,0.2);

		SIERPINSKI = new GraficoAfines();
		SIERPINSKI.agregarAfin(new Afin(0.50, 0, 0, 0.50,  150,  10), 0.33);
		SIERPINSKI.agregarAfin(new Afin(0.50, 0, 0, 0.50,    1, 300), 0.33);
		SIERPINSKI.agregarAfin(new Afin(0.50, 0, 0, 0.50, -150,  10), 0.34);
	}
	
	public GraficoAfines(){
		v.add(null);
		probAcum.add(0.0);	
	}
	
	public GraficoAfines(Fractales frame){
		v.add(null);
		probAcum.add(0.0);
		this.frame = frame;
	}
	
	public void setFrame(Fractales frame) {
		this.frame = frame;
	}

	public void agregarAfin(Afin a, Double prob) {
		v.addElement(a); // Agregar Transformacion Afin
		probAcum.addElement(probAcum.lastElement() + prob); // Agregar probabilidad acumulada
	}
	
	private int execMontecarlo() {
		double r = Math.random();
		
		for (int i = 1; i < probAcum.size(); i++){
			if (r < probAcum.get(i)){
				return i;
			}
		}
		
		return -1;
	}

	public void iterar() {
		double rand = Math.random();		
		double prob = probAcum.elementAt(0);
		int i = 0;

		while (prob < rand && i < probAcum.size()) {
			i++;
			prob += probAcum.elementAt(i);
		}
		
		if (i == probAcum.size()){
			i--;
		}
		
		indice = i;
		Afin a = v.elementAt(i);
		a.aplica(x,y);
		x = a.getX();
		y = a.getY();
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public int getIndice() {
		return indice;
	}

	public void factor(double k) {
		for (Afin a : v)
			a.setFactor(k);
	}
	
	
	// --------------------------------------------------------------------------------------
	
	// Calcular el fractal por IFS
	@Override
	public void calcular(){
		
		// Punto inicial
		punto = new Point(0, 0);
		
		// Realizar IFS
		for (int it=0 ; it < 100000; it++) {
			calcularNuevoPunto();
		}
		
	}
	
	// Obtener nuevo punto a partir del anterior
	private void calcularNuevoPunto() {
		// Obtener el indice de la transformacion seleccionada y el punto obtenido
		indice = execMontecarlo();
		Afin a = v.elementAt(indice);
		punto = a.aplicar(punto);
		
		 // Agregar indice a la lista
		indices.add(indice);
		
		// Calcular el valor del pixel
		int r = 0, g = 0;		
		if (indices.size() > PROFUNDIDAD){
			r = (indices.get(indices.size() - PROFUNDIDAD) - 1) * 80;
		}
		
		// Setear el valor del pixel
		setPixel(punto.x + 400, punto.y, new Color(r, g, 128).getRGB());
	}
	
	// Colorear un pixel en la matriz
	private void setPixel(int c, int f, int color) {
		if (c>=0 && f>=0 && c<width && f<height){
			matriz[(height - f + 1)*width + c] = color;
		}else{
			System.out.println("Advertencia: Pixeles fuera de la imagen");
		}
		
	}

	// Generar imagen a partir de la matriz almacenada
	@Override
	public Image generarImagen(){
	    
	    Image imagenNueva = frame.createImage(new MemoryImageSource(width, height, matriz, 0, width ));
	    
	    return imagenNueva;
	
	}
}
