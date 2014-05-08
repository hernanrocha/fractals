package conjunto;
import basic.Complejo;


public class ConjuntoMandelbrot extends Conjunto{

	public static final ConjuntoMandelbrot CUADRATICO = new ConjuntoMandelbrot(2);
	public static final ConjuntoMandelbrot CUBICO = new ConjuntoMandelbrot(3);
	public static final ConjuntoMandelbrot ORDEN_5 = new ConjuntoMandelbrot(5);
	public static final ConjuntoMandelbrot ORDEN_10 = new ConjuntoMandelbrot(10);
	public static final ConjuntoMandelbrot ORDEN_20 = new ConjuntoMandelbrot(20);
	
	private int exp;

	public ConjuntoMandelbrot(int exp){
		super();
		
		this.exp = exp;
	}
	
	@Override
	public Complejo getFirst(Complejo c){
		// Zo = 0 + 0i
		return new Complejo();
	}

	@Override
	public Complejo getNext(Complejo Zn, Complejo c){
		// Z(n+1) = Zn ^ exp + c
		return Complejo.suma(Complejo.potencia(Zn, exp), c);
	}
	
}
