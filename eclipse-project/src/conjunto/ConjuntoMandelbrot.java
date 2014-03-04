package conjunto;
import basic.Complejo;


public class ConjuntoMandelbrot extends Conjunto{

	@Override
	public int iterar(Complejo c){
//		System.out.println("Nueva iteracion");
		
		Complejo Zn = new Complejo();
		
		for(int i = 0; i < Conjunto.MAX_ITERACIONES; i++){
			Zn = Complejo.suma(Complejo.potencia(Zn, 2), c);			
			double modulo = Zn.modulo();
			
//			System.out.println(modulo);
			
			if (modulo > Conjunto.RADIO_ESCAPE){
				return i;
			}
		}
		
		return Conjunto.MAX_ITERACIONES;
	}
	
}
