package fractal;
import java.awt.Color;


public class FractalCompuesto extends Fractal {

	private Fractal fractalInterno;
	
	public FractalCompuesto(Fractal f){
		fractalInterno = f;
	}
	
	@Override
	public int[] generateImage() {
		System.out.println("Generando imagen...");
		
		int[] interno = fractalInterno.getImage();
		
		int tamInterno = interno.length;
		int tamExterno = tamInterno * 9;
		
		int ladoInterno = (int) Math.sqrt(tamInterno);
		int ladoExterno = (int) Math.sqrt(tamExterno);
				
		int[] externo = new int[tamExterno];
		int[] negro = new int[tamInterno];
		
		// Copiar primer arreglo
		for (int fila = 0; fila < 3; fila++){
			for (int columna = 0; columna < 3; columna++){
				for(int i = 0; i < ladoInterno; i++){
					System.arraycopy(interno, ladoInterno * i, externo, (ladoInterno * ladoExterno * fila) + ladoExterno * i + ladoInterno * columna, ladoInterno);
				}
			}
		}
		
		// Generar arreglo negro
		int color = (int) (Math.random() * 4);
		int colorInt = 0;
		switch (color) {
		case 0:		
			colorInt = Color.MAGENTA.getRGB();
			break;
		case 1:
			colorInt = Color.DARK_GRAY.getRGB();
			break;
		case 2:
			colorInt = Color.BLUE.getRGB();
			break;
		case 3:
			colorInt = Color.GREEN.getRGB();
			break;
		default:
			break;
		}
		
		for(int i = 0; i < negro.length; i++){			
			negro[i] = colorInt;
		}
		
		// Copiar arreglo negro
		for(int i = 0; i < ladoInterno; i++){
//			System.arraycopy(negro, 0, externo, (ladoInterno * ladoExterno * 0) + ladoExterno * i + ladoInterno * 2, ladoInterno);
//			System.arraycopy(negro, 0, externo, (ladoInterno * ladoExterno * 2) + ladoExterno * i + ladoInterno * 0, ladoInterno);
//			System.arraycopy(negro, 0, externo, (ladoInterno * ladoExterno * 1) + ladoExterno * i + ladoInterno * 0, ladoInterno);
//			System.arraycopy(negro, 0, externo, (ladoInterno * ladoExterno * 1) + ladoExterno * i + ladoInterno * 2, ladoInterno);
			System.arraycopy(negro, 0, externo, (ladoInterno * ladoExterno * 1) + ladoExterno * i + ladoInterno * 1, ladoInterno);
		}
		
		return externo;
	}

}
