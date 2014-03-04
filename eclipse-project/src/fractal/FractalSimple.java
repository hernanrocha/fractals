package fractal;
import java.awt.Color;

/**
 * 
 */

/**
 * @author hernan
 *
 */
public class FractalSimple extends Fractal {

	@Override
	public int[] generateImage() {
		System.out.println("Generando simple");
		int[] pix = new int[]{Color.WHITE.getRGB()};
		
		return pix;
	}

}
