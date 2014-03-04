package coloreo;
import java.awt.Color;


public class ColoreoModulo extends Coloreo {
	
	private int[] colores = new int[]{
			Color.RED.getRGB(),
			Color.GREEN.getRGB(),
			Color.BLUE.getRGB(),
			Color.BLACK.getRGB()
	};
	
	private int cantColores = colores.length;

	@Override
	public int colorear(int valor) {
		
		return colores[valor%cantColores];
	}

}
