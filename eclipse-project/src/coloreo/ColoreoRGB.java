package coloreo;

import java.awt.Color;

import conjunto.Conjunto;

public class ColoreoRGB extends Coloreo {

	int base = 10;
	
	@Override
	public int colorear(int valor) {
		
		if (valor == Conjunto.MAX_ITERACIONES){
			return Color.BLACK.getRGB();
		}
		
		int red = base + valor * 10;
		if(red > 255){
			red = 255;
		}
		
		valor -= 25;
		if(valor < 0){
			valor = 0;
		}
		
		int green = base + valor * 10;
		if(green > 255){
			green = 255;
		}
		
		valor -= 25;
		if(valor < 0){
			valor = 0;
		}
		
		int blue = base + valor * 10;
		if(blue > 255){
			blue = 255;
		}
		
		valor -= 25;
		if(valor > 0){
//			return colorear(valor);
			return Color.WHITE.getRGB();
		}
		
		return (new Color(red, green, blue)).getRGB();
	}

}
