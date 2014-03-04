package coloreo;
import java.awt.Color;

import conjunto.Conjunto;


public class ColoreoMonocromatico extends Coloreo {

	@Override
	public int colorear(int valor) {
		if (valor == Conjunto.MAX_ITERACIONES){
			return Color.BLACK.getRGB();
		}
		
		return Color.WHITE.getRGB();
	}

}
