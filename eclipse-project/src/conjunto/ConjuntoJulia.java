package conjunto;
import basic.Complejo;

/**
 * 
 */

/**
 * @author hernan
 *
 */
public class ConjuntoJulia extends Conjunto{

	private Funcion funcion;
	
	public ConjuntoJulia(Funcion f){
		super();
		
		funcion = f;
	}

	public int iterar(Complejo c){		
		Complejo Zn = c;
		
		for(int i = 0; i < Conjunto.MAX_ITERACIONES; i++){
			Zn = funcion.evaluar(Zn);
			
			double modulo = Zn.modulo();
			
			if (modulo > Conjunto.RADIO_ESCAPE){
				return i;
			}
		}
		
		return Conjunto.MAX_ITERACIONES;
	}
}
