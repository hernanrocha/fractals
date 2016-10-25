package conjunto;

import basic.Complejo;

/** Conjuntos de Julia
 * Jc = Conjunto de Julia de parámetro c
 * Para todo z en el plano Complejo, iterar:
 *     Z(0) = z
 *         Z(n+1) = Z(n) ^ 2 + c
 *         
 * @author hernan
 *
 */

public class ConjuntoJulia extends Conjunto{
	
	// Conjuntos de Julia predeterminados
	public static final ConjuntoJulia DISCO_SIEGEL = new ConjuntoJulia(new FuncionPolinomica(2, new Complejo(-0.391, 0.587)));
	public static final ConjuntoJulia DENDRITA = new ConjuntoJulia(new FuncionPolinomica(2, new Complejo(0, 1)));
	public static final ConjuntoJulia CONEJO_DOUADY = new ConjuntoJulia(new FuncionPolinomica(2, new Complejo(-0.123, 0.745)));
	public static final ConjuntoJulia SAMPLE_1 = new ConjuntoJulia(new FuncionPolinomica(2, new Complejo(-0.022803, -0.672621)));
	public static final ConjuntoJulia SAMPLE_2 = new ConjuntoJulia(new FuncionPolinomica(2, new Complejo(0.3, 0.5)));
	public static final ConjuntoJulia SAMPLE_3 = new ConjuntoJulia(new FuncionPolinomica(2, new Complejo(0.279, 0)));
	public static final ConjuntoJulia SAMPLE_4 = new ConjuntoJulia(new FuncionPolinomica(3, new Complejo(0.4, 0)));
	public static final ConjuntoJulia SAMPLE_5 = new ConjuntoJulia(new FuncionPolinomica(3, new Complejo(-1, 0)));
	public static final ConjuntoJulia SAMPLE_6 = new ConjuntoJulia(new FuncionPolinomica(3, new Complejo(0.3, 0.6)));
	
	// Sitio: http://www.mat.iesvillalbahervas.org/index.php?option=com_content&view=article&id=120:3conjuntos-de-julia-y-mandelbrot&catid=60:fractales&Itemid=134
//	Funcion f = new FuncionPolinomica(2, new Complejo(0, 0.8));
//	Funcion f = new FuncionPolinomica(2, new Complejo(-0.8, -0.25));
//	Funcion f = new FuncionPolinomica(2, new Complejo(-0.8, 0));
	
	 // Fractal de San Marco
	
	// Otras funciones
//	·        z(n+1) = z’(n)^2 + c 
//			·        z(n+1) = z’(n)^3 + c 
//			·        z(n+1) = z’(n)^4 + c 
	// z(n+1) = sen (z/c) ; z(0) = c.

	// Mas Informacion
//	http://es.wikipedia.org/wiki/Fractal
	
	// Variables privadas
	private Funcion f;
	
	public ConjuntoJulia(Funcion f){
		super();
		
		this.f = f;
	}
	
	@Override
	public Complejo getFirst(Complejo c){
		// Zo = c
		return c;
	}

	@Override
	public Complejo getNext(Complejo Zn, Complejo c){
		// Z(n+1) = F (Zn)
		return f.evaluar(Zn);
	}
}
