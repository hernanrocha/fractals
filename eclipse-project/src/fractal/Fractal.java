package fractal;

public abstract class Fractal {

	protected int[] bufferedImage;
	protected boolean buffered = false;
	
	public int[] getImage(){
		if (!buffered){
			bufferedImage = generateImage();
			buffered = true;
		}
		
		return bufferedImage;
	}
	
	public abstract int[] generateImage();
	
}
