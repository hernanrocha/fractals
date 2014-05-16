package conjunto;

import basic.Complejo;

public abstract class Conjunto {

	public static final int MAX_ITERACIONES = 100;
	public static final double RADIO_ESCAPE = 2;
	
	public int iterar(Complejo c){
		Complejo Zn = getFirst(c);

		for(int i = 0; i < Conjunto.MAX_ITERACIONES; i++){
			Zn = getNext(Zn, c);

			if (Zn.modulo() > Conjunto.RADIO_ESCAPE){
				return i;
			}
		}

		return Conjunto.MAX_ITERACIONES;

	}

	public abstract Complejo getFirst(Complejo c);
	
	public abstract Complejo getNext(Complejo Zn, Complejo c);
	
}
