package paleta;
import java.awt.Color;

import conjunto.Conjunto;


public class ColoreoMonocromatico extends Paleta {

	@Override
	public int getColor(int valor) {
		if (valor == Conjunto.MAX_ITERACIONES){
			return Color.BLACK.getRGB();
		}
		
		return Color.WHITE.getRGB();
	}

}
