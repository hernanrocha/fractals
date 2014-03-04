/**
 * 
 */
package coloreo;

import java.awt.Color;

/**
 * @author hernan
 *
 */
public class ColoreoLinea extends Coloreo {

	private int valor;
	private int margen;

	public ColoreoLinea(int valor, int margen){
		this.valor = valor;
		this.margen = margen;
	}
	
	@Override
	public int colorear(int valor) {
		int dif = this.valor - valor;
		if(dif < 0){
			dif = -dif;
		}
		
		if (dif <= margen){
			return Color.WHITE.getRGB();
		}
		
		return Color.BLACK.getRGB();
	}

}
