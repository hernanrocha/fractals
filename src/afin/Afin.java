package afin;

import java.awt.Point;

public class Afin {
	private double a,b,c,d,e,f;
	private double x,y;

	public Afin (double a, double b, double c, double d, double e, double f) {
		this.a = a; 
		this.b = b; 
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	}

	public Point aplicar(Point punto) {  	
		x =  (a*punto.x + b*punto.y + e);
		y =  (c*punto.x + d*punto.y + f);

		return new Point((int) x, (int) y);
	}
	
}