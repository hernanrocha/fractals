package paleta;

import java.awt.Color;
import java.util.Vector;

public class ColoreoFuego extends Paleta {
	
	int[] limites = new int[]{
			15, 20, 25,
			30, 35, 40, 45, 50,
			55, 60, 65, 70};
	
//	int[] limites = new int[]{
//			10, 20, 30, 40, 50,
//			60, 70, 80, 90, 100};
	
	Vector<Color> colores = new Vector<Color>();
	
	public ColoreoFuego(){
		colores.add(new Color(5, 5, 5));
		colores.add(new Color(86, 54, 51));
		colores.add(new Color(103, 52, 50));
		colores.add(new Color(116, 37, 36));
		colores.add(new Color(148, 36, 36));
		colores.add(new Color(205, 21, 18));
		colores.add(new Color(239, 4, 6));
		colores.add(new Color(241, 72, 39));
		colores.add(new Color(247, 88, 6));
		colores.add(new Color(250, 151, 40));
		colores.add(new Color(251, 168, 6));
		colores.add(new Color(247, 200, 88));
		colores.add(new Color(240, 216, 168));
		colores.add(new Color(250, 250, 200));
	}

	@Override
	public int getColor(int valor) {
		for (int i = limites.length - 2; i >= 0; i--){
			if (valor > limites[i]){
				return transformar(colores.get(i), colores.get(i + 1), limites[i], limites[i+1], valor).getRGB();
			}
		}
		
		// Intervalo 0 - limites[0] (15)
		
		return new Color(5,5,5).getRGB();
		
	}
	
	public Color transformar(Color c1, Color c2, int min, int max, int v){
		int dif = max - min;
		int difRed = c2.getRed() - c1.getRed(), difGreen = c2.getGreen() - c1.getGreen(), difBlue = c2.getBlue() - c1.getBlue();
		
		int delta = v - min;
		
//		cDif --> dif
//		cV   --> v
		
		System.out.println(min + " - " + v + " - " + max);
		
		System.out.println(difRed);
		
		double rojo = c1.getRed() + difRed * delta / dif;
		double verde = c1.getGreen() + difGreen * delta / dif;
		double azul = c1.getBlue() + difBlue * delta / dif;
		
		if (rojo>255 || verde>255 || azul>255){
			System.out.println("Error");
		}
		
		if (rojo<0|| verde<0 || azul<0){
			System.out.println("Error");
		}
		
		System.out.println("rojo: " + rojo);
		System.out.println("verde: " + verde);
		System.out.println("azul: " + azul);
		
		return new Color ((int) rojo,(int) verde,(int) azul);
	}

}
