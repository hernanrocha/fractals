package basic;

public class Fraccion {
	private double num;
	private double den;
	
	public Fraccion() {
		num=0;
		den=1;
	}
	
	public Fraccion(double x, double y) {
		num=x;
		den=y;
	}
	
	public static Fraccion sumar(Fraccion a, Fraccion b){
		Fraccion c=new Fraccion();
		c.num=a.num*b.den+b.num*a.den;
		c.den=a.den*b.den;
		return c;
	}
	
	public static Fraccion restar(Fraccion a, Fraccion b){
		Fraccion c=new Fraccion();
		c.num=a.num*b.den-b.num*a.den;
		c.den=a.den*b.den;
		return c;
	}

	public static Fraccion multiplicar(Fraccion a, Fraccion b){
		return new Fraccion(a.num*b.num, a.den*b.den);
	}
	
	public static Fraccion multiplicar(Fraccion a, double b){
		return new Fraccion(a.num*b, a.den);
	}
	
	public static Fraccion inversa(Fraccion a){
		return new Fraccion(a.den, a.num);
	}
	
	public static Fraccion opuesto(Fraccion a){
		return new Fraccion(-a.num, a.den);
	}
	
	public static Fraccion dividir(Fraccion a, Fraccion b){
		return multiplicar(a, inversa(b));
	}
	
	public static Fraccion dividir(Fraccion a, double b){
		return new Fraccion(a.num, a.den * b);
	}
	
	public static Fraccion cuadrado(Fraccion a){
		return new Fraccion(a.num*a.num, a.den*a.den);
	}
	
	public static Fraccion sqrt(Fraccion a){
		return new Fraccion(Math.sqrt(a.num), Math.sqrt(a.den));
	}

	private double mcd(){
		double u=Math.abs(num);
		double v=Math.abs(den);
		if(v==0){
			return u;
		}
		double r;
		while(v!=0){
			r=u%v;
			u=v;
			v=r;
		}
		return u;
	}

	public Fraccion simplificar(){
		double dividir=mcd();
		num/=dividir;
		den/=dividir;
		return this;
	}
	
	public double get(){
		return num/den;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(den);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(num);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		Fraccion other = (Fraccion) obj;
		return ( num * other.den == den * other.num );
	}

	public String toString(){
		String texto=num+" / "+den;
		return texto;
	}
 
}
