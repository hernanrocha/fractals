package afin;

import grafico.Grafico;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.MemoryImageSource;

import paleta.PaletaAfin;

public class GraficoAfines extends Grafico{
	
	// Transformaciones Afines Predeterminadas
	
	// Helecho de Bransley
	public static GraficoAfines HELECHO = new GraficoAfines(
			new Afin[]{new Afin( 0   , 0	, 0	  , 0.20, 0,  10),
			           new Afin( 0.85, 0.04,-0.04, 0.85, 0, 100),
			           new Afin( 0.20,-0.26, 0.23, 0.22, 0, 100),
			           new Afin(-0.15, 0.30, 0.26, 0.24, 0,  28)},
			new Double[]{0.01, 0.85, 0.07, 0.07}
	);
	
	// Alfombra de Sierpinski
	public static GraficoAfines SIERPINSKI = new GraficoAfines(
			new Afin[]{new Afin(0.50, 0, 0, 0.50,  150,  10),
					   new Afin(0.50, 0, 0, 0.50,    1, 300),
					   new Afin(0.50, 0, 0, 0.50, -150,  10)},
		    new Double[]{0.33, 0.33, 0.34}
	);
	
	public static GraficoAfines HOJA = new GraficoAfines(
			new Afin[]{new Afin(0.14, 0.01, 0, 0.51, -0.08, -1.31),
					   new Afin(0.43, 0.52, -0.45, 0.50, 1.49, -0.75),
					   new Afin(0.45, -0.49, 0.47, 0.47, -1.62, -0.74),
					   new Afin(0.49, 0, 0, 0.51, 0.02, 1.62)},
			new Double[]{0.10, 0.35, 0.35, 0.20}
	);
	
	// Profundidad de pintado
	private static int PROFUNDIDAD = 1;
	
	// Transformaciones y probabilidades
	private Vector<Afin> afines = new Vector<Afin>();
	private Vector<Double> probAcum = new Vector<Double>();
	
	// Indices y valores obtenidos
	private Vector<Integer> indices = new Vector<Integer>();
	private HashMap<Point, Integer> matrizVal = new HashMap<Point, Integer>();

	private int indice;
	private Point punto;
	
	public GraficoAfines(Afin[] afines, Double[] probabilidades){
		this();
		
		// Cargar vectores
		for(int i = 0; i < afines.length; i++){
			agregarAfin(afines[i], probabilidades[i]);
		}
	}
	
	public GraficoAfines(){
		// Cargar datos iniciales
		afines.add(null);
		probAcum.add(0.0);
		
		// Cargar paleta
		paleta = new PaletaAfin();
	}

	public void agregarAfin(Afin a, Double prob) {
		afines.addElement(a); // Agregar Transformacion Afin
		probAcum.addElement(probAcum.lastElement() + prob); // Agregar probabilidad acumulada
	}
	
	private int execMontecarlo() {
		double r = Math.random();
		
		for (int i = 1; i < probAcum.size(); i++){
			if (r < probAcum.get(i)){
				return i;
			}
		}
		
		return -1;
	}

	// Calcular el fractal por IFS
	@Override
	public void calcular(){
		
		// Punto inicial
		punto = new Point(0, 0);
		
		// Realizar IFS
		for (int it=0 ; it < 100000; it++) {
			calcularNuevoPunto();
		}

		System.out.println("Pintar");
		frame.actualizarInterfaz();
	}
	
	// Obtener nuevo punto a partir del anterior
	private void calcularNuevoPunto() {
		// Obtener el indice de la transformacion seleccionada y el punto obtenido
		indice = execMontecarlo();
		Afin a = afines.elementAt(indice);
		punto = a.aplicar(punto);
		
		 // Agregar indice a la lista
		indices.add(indice);
		
		// Calcular valor
		if (indices.size() > PROFUNDIDAD){
			int val = (indices.get(indices.size() - PROFUNDIDAD) - 1);
			matrizVal.put(punto, val);
		}
	}

	// Generar imagen a partir de la matriz almacenada
	@Override
	public Image generarImagen(){
		
		int[] imagen = new int[width*height];
		
		Set<Entry<Point, Integer>> values = matrizVal.entrySet();
		for (Entry<Point, Integer> entrada : values){
			int valor = entrada.getValue();
			int f = getYtoF(entrada.getKey().getY());
			int c = getXtoC(entrada.getKey().getX());
			if (c>=0 && f>=0 && c<width && f<height){
				imagen[f * width + c] = paleta.getColor(valor).getRGB();
			}
		}
		
	    Image imagenNueva = frame.createImage(new MemoryImageSource(width, height, imagen, 0, width ));
	    
	    return imagenNueva;
	
	}
	
	public static void setProfundidad(int valor){
		PROFUNDIDAD = valor;
	}
}
