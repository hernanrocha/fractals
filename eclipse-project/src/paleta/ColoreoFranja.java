package paleta;

import java.awt.Color;

public class ColoreoFranja extends Paleta {

	private int[] colores = new int[]{
			Color.RED.getRGB(),
			Color.GREEN.getRGB(),
			Color.BLUE.getRGB(),
			Color.BLACK.getRGB()
	};
	
	private int cantColores = colores.length;

	@Override
	public int getColor(int valor) {
		
		return colores[(valor/3) % cantColores];
	}

}
