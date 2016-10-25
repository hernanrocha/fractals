package basic;
public class Complejo {
	
	private double real;
	private double imag;

	public Complejo() {
		real=0.0;
		imag=0.0;
	}
	
	public Complejo(double real, double imag){
		this.real=real;
		this.imag=imag;
	}
	
	public double getReal() {
		return real;
	}

	public double getImag() {
		return imag;
	}

	public static Complejo conjugado(Complejo c){
		return new Complejo(c.real, -c.imag);
	}
	
	public static Complejo opuesto(Complejo c){
		return new Complejo(-c.real, -c.imag);
	}
	
	public double modulo(){
		return Math.sqrt(real*real + imag*imag);
	}
	
	//devuelve el ángulo en grados
	public double argumento(){
		double angulo = Math.atan2(imag, real);     //devuelve el ángulo entre -PI y +PI
		
		if (angulo < 0){
			angulo = 2 * Math.PI + angulo;
		}
		
		return angulo*180/Math.PI;
	}
	
	//suma de dos números complejos
	public static Complejo suma(Complejo c1, Complejo c2){
		return new Complejo(
				c1.real + c2.real, 
				c1.imag + c2.imag);
	}
	
	//producto de dos números complejos
	public static Complejo producto(Complejo c1, Complejo c2){
		return new Complejo(
				c1.real * c2.real - c1.imag * c2.imag,
				c1.real * c2.imag + c1.imag * c2.real);
	}
	
	//producto de un complejo por un número real
	public static Complejo producto(Complejo c, double d){
		return new Complejo(
				c.real * d,
				c.imag * d);
	}
	
	//producto de un número real  por un complejo
	public static Complejo producto(double d, Complejo c){
		return new Complejo(
				c.real * d,
				c.imag * d);
	}
	
	//cociente de dos números complejos
	//excepción cuando el complejo denominador es cero
	public static Complejo cociente(Complejo c1, Complejo c2) throws ExcepcionDivideCero{
		double aux1, aux2, aux3;
		
		if(c2.modulo()==0.0){
			throw new ExcepcionDivideCero("Divide entre cero");
		}
			
		aux1 = c2.real * c2.real + c2.imag * c2.imag;
		aux2 = c1.real * c2.real;
		aux3 = c1.imag * c2.imag;
		
		return new Complejo(
				(aux2 + aux3) / aux1,
				(aux2 - aux3) / aux1);
	}
	
	//cociente entre un número complejo y un número real
	public static Complejo cociente(Complejo c, double d) throws ExcepcionDivideCero{
		if(d == 0.0){
			throw new ExcepcionDivideCero("Divide entre cero");
		}
		
		return new Complejo(
				c.real/d, 
				c.imag/d);
	}
	
	//el número e elevado a un número complejo
	public static Complejo exponencial(Complejo c){
		double exp = Math.exp(c.real);

		return new Complejo(
				exp * Math.cos(c.imag), 
				exp * Math.sin(c.imag));
	}
	
	//raíz cuadrada de un número positivo o negativo
	public static Complejo csqrt(double d){
		if( d >= 0){
			return new Complejo(Math.sqrt(d), 0);
		}
		
		return new Complejo(0, Math.sqrt(-d));
	}
	
	//función auxiliar  para la potencia de un número complejo
	private static double potencia(double base, int exponente){
		double resultado=1.0;
		
		for(int i=0; i<exponente; i++){
			resultado *= base;
		}
		
		return resultado;
	}
	
	//función auxiliar para la potencia de un número complejo
	private static double combinatorio(int m, int n){
		long num=1;
		long den=1;
		
		for(int i=m; i>m-n; i--){
			num *= i;
		}
		
		for(int i=2; i<=n; i++){
			den *= i;
		}
		
		return num/den;
	}
	
	//potencia de un número complejo
	public static Complejo potencia(Complejo c, int exponente){
		double x=0.0, y=0.0;
		int signo;
		
		for(int i=0; i <= exponente; i++){
			signo = (i%2==0)? +1 : -1;
			
			//parte real
			x += combinatorio(exponente, 2*i)*potencia(c.real, exponente-2*i)*potencia(c.imag, 2*i)*signo;
			
			if(exponente==2*i){
				break;
			}
			
			//parte imaginaria
			y += combinatorio(exponente, 2*i+1)*potencia(c.real, exponente-(2*i+1))*potencia(c.imag, 2*i+1)*signo;
		}
		
		return new Complejo(x, y);
	}
	
	@Override
	public String toString(){
		if(imag>0){
			return (double)Math.round(10000*real)/10000 + " + " + (double)Math.round(10000*imag)/10000 + "*i";
		}
		
		return (double)Math.round(10000*real)/10000 + " - " + (double)Math.round(-10000*imag)/10000 + "*i";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Complejo))
			return false;
		
		Complejo other = (Complejo) obj;
		if (imag != other.imag)
			return false;
		if (real != other.real)
			return false;
		
		return true;
	}
	
	
}

class ExcepcionDivideCero extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcepcionDivideCero() {
		super();
	}
	
	public ExcepcionDivideCero(String s) {
		super(s);
	}
	
}