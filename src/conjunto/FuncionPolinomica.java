package conjunto;

import basic.Complejo;

public class FuncionPolinomica extends Funcion {

	private int grado;
	private Complejo cte;

	public FuncionPolinomica(int grado, Complejo cte){
		this.grado = grado;
		this.cte = cte;
	}
	
	@Override
	public Complejo evaluar(Complejo c) {
		return Complejo.suma(Complejo.potencia(c, grado), cte);
	}

}
