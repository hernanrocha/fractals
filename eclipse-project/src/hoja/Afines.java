/**
 * 
 */
package hoja;

import java.util.Vector;
import java.awt.Point;

public class Afines{
	private Vector<Afin> v = new Vector<Afin>();
	private Vector<Double> probAcum = new Vector<Double>();

	private int indice; 
	private double x = 0;
	private double y = 0;
	
	public static Afines HELECHO;
	public static Afines ARCE;
	public static Afines ARBOL;
	public static Afines ARBOL_2;
	public static Afines SIERPINSKI;
	
	public static void cargarAfines(){
		// Helecho
		HELECHO = new Afines();
		HELECHO.agregarAfin(new Afin( 0   , 0	, 0	  , 0.20, 0,  10), 0.01);
		HELECHO.agregarAfin(new Afin( 0.85, 0.04,-0.04, 0.85, 0, 100), 0.85);
		HELECHO.agregarAfin(new Afin( 0.20,-0.26, 0.23, 0.22, 0, 100), 0.07);
		HELECHO.agregarAfin(new Afin(-0.15, 0.30, 0.26, 0.24, 0,  28), 0.07);
		
		ARCE = new Afines();
		ARCE.agregarAfin(new Afin(0.14	,0.1	,0		,0.51	,-0.08 	,-1.31)	,0.10);
		ARCE.agregarAfin(new Afin(0.43	,0.52	,-0.45	,0.50 	,1.49	,-0.75)	,0.35);
		ARCE.agregarAfin(new Afin(0.45	,-0.49	,0.47	,0.47	,-1.62	,-0.74)	,0.35);
		ARCE.agregarAfin(new Afin(0.49	,0		,0.0	,0.51	,0.02	,1.62)	,0.20);

		ARBOL = new Afines();
		ARBOL.agregarAfin(new Afin(0		,0		,0		,0.50	,0 	,0)		,0.05);
		ARBOL.agregarAfin(new Afin(0.42	,-0.42	,0.42	,0.42	,0	,0.2)	,0.4);
		ARBOL.agregarAfin(new Afin(0.42	,0.42	,-0.42	,0.42	,0	,0.2)	,0.4);
		ARBOL.agregarAfin(new Afin(0.10	,0		,0		,0.10	,0	,0.2)	,0.15);

		ARBOL_2 = new Afines();
		ARBOL_2.agregarAfin(new Afin(0.195	,-0.488	,0.344	,0.443	,0.4431 	,0.2452)	,0.2);
		ARBOL_2.agregarAfin(new Afin(0.462	, 0.414	,-0.252 ,0.361	,0.2511 	,0.5692)	,0.2);
		ARBOL_2.agregarAfin(new Afin(-0.058,-0.070	,0.453	,-0.111 ,0.5976 	,0.0969)	,0.2);
		ARBOL_2.agregarAfin(new Afin(-0.035, 0.070	,-0.469 ,-0.022 ,0.4431 	,0.5069)	,0.2);
		ARBOL_2.agregarAfin(new Afin(-0.637, 0.0  	,0.0  	,0.501	,0.8562 	,0.2513)	,0.2);

		SIERPINSKI = new Afines();
		SIERPINSKI.agregarAfin(new Afin(0.50, 0, 0, 0.50,  150,  10), 0.33);
		SIERPINSKI.agregarAfin(new Afin(0.50, 0, 0, 0.50,    1, 300), 0.33);
		SIERPINSKI.agregarAfin(new Afin(0.50, 0, 0, 0.50, -150,  10), 0.34);
	}
	
	public Afines(){
		v.add(null);
		probAcum.add(0.0);
	}

	public void agregarAfin(Afin a, Double prob) {
		v.addElement(a); // Agregar Transformacion Afin
		probAcum.addElement(probAcum.lastElement() + prob); // Agregar probabilidad acumulada
	}

	public Point aplicar(Point p) {
//		System.out.println("Aplicar a (" + p.x + ", " + p.y + ")");
		
		// Determinar la transformacion y aplicar
		indice = execMontecarlo();
		Afin a = v.elementAt(indice);
		Point result = a.aplicar(p);
		
		// Actualizar punto actual
		x = a.getX();
		y = a.getY();
		
		return result;
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

	public void itera() {
		double f = Math.random();
		double counter=probAcum.elementAt(0);
		int i=0;

		while (counter < f && i<probAcum.size()) {
			i++;
			counter += probAcum.elementAt(i);
		}
		if (i==probAcum.size()) i--;
		indice = i;

		Afin a = v.elementAt(i);
		a.aplica(x,y);
		x = a.getX();
		y = a.getY();
	}


	// X,y de la última aplicación en formato double. Util para escalar
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
}
