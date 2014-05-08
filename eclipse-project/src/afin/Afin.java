/**
 * 
 */
package afin;

import java.awt.Point;

public class Afin {
	private double a,b,c,d,e,f;
	private double factor; 
	private double x,y;

	public Afin (double a, double b, double c, double d, double e, double f) {
		this.a = a; 
		this.b = b; 
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;

		this.factor = 1.0;
	}

	public Point aplicar(Point punto) {  	
		x =  (a*punto.x + b*punto.y + e);
		y =  (c*punto.x + d*punto.y + f);

		Point puntoNuevo = new Point((int) x, (int) y);

		return puntoNuevo;
	}

	public void aplica(double x2, double y2) {
		x =  (a*x2+b*y2+e);
		y =  (c*x2+d*y2+f);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setFactor(double f) {  	
		this.factor = f;
	}
}