package paleta;

import java.awt.Color;
import java.io.File;

public class PaletaDefault extends Paleta {
	
	public PaletaDefault(){
		super(true);
		
		addColor(new Color(0, 0, 66), 0);
		addColor(new Color(0, 0, 130), 49);
		addColor(new Color(0, 0, 195), 96);
		addColor(new Color(0, 0, 255), 131);
		addColor(new Color(0, 66, 255), 168);
		addColor(new Color(0, 130, 255), 215);
		addColor(new Color(0, 195, 255), 300);
		addColor(new Color(0, 255, 255), 350);
		addColor(new Color(0, 255, 195), 400);
		addColor(new Color(0, 255, 130), 415);
		addColor(new Color(0, 255, 66), 428);
		addColor(new Color(0, 255, 0), 441);
		addColor(new Color(66, 255, 0), 455);
		addColor(new Color(130, 255, 0), 510);
		addColor(new Color(195, 255, 0), 534);
		addColor(new Color(255, 255, 0), 581);
		addColor(new Color(255, 195, 0), 608);
		addColor(new Color(255, 130, 0), 677);
		addColor(new Color(255, 66, 0), 695);
		addColor(new Color(255, 0, 0), 950);
	}

	@Override
	public void reset() {}

	@Override
	public void save(File f) {}

}
