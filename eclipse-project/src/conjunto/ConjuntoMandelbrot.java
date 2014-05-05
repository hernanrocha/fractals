package conjunto;
import basic.Complejo;


public class ConjuntoMandelbrot extends Conjunto{

	private int exp;

	public ConjuntoMandelbrot(int exp){
		this.exp = exp;
	}
	
	@Override
	public int iterar(Complejo c){
		
		Complejo Zn = new Complejo();
		
		for(int i = 0; i < Conjunto.MAX_ITERACIONES; i++){
			Zn = Complejo.suma(Complejo.potencia(Zn, exp), c);			
			double modulo = Zn.modulo();
			
			if (modulo > Conjunto.RADIO_ESCAPE){
				return i;
			}
		}
		
		return Conjunto.MAX_ITERACIONES;
	}
	
}
