package paleta;

import java.awt.Color;
import java.io.File;

public class PaletaAfin extends Paleta {

	public PaletaAfin(){
		addColor(new Color(255, 10, 10), 0);
		addColor(new Color(10, 255, 10), 1);
		addColor(new Color(10, 10, 255), 2);
	}
	
	@Override
	public void reset() {
	}

	@Override
	public void save(File f) {
	}

}
