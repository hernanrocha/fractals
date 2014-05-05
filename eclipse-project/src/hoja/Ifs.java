package hoja;

import java.awt.*;
import java.applet.*;
import java.awt.image.*;
import java.awt.event.*;
import java.lang.Math;

import javax.swing.JApplet;

public class Ifs extends JApplet implements ActionListener    {
	public Ifs() {
	}
	final int black = 0xFF000000;

	// ancho,y alto del panel de dibujo size es width*height
	int width=1000, height=700, size=width*height;

	// pixels contiene la imagen
	int pixels[];
	//  productor de imágenes que usará pixels como imagen
	MemoryImageSource source;
	// imagen que utilizará source como fuente
	Image image;

	Point punto;

	// transformaciones
	Afines arce,árbol,árbol2,sierpinski;

	// componentes del applet
	Button btnDibujar;
	Button btnLimpiar;
	Choice choice;  
	Checkbox casilla;

	/**
	 * Punto de entrada al applet. 
	 * Se encarga de:
	 *   - Dar a la ventana el estilo 
	 *   - Crear los componentes (botones, etc.) 
	 */
	public void init()  {

		initForm();
		
		Afines.cargarAfines();

		reset();   
	}

	private void cargaTransform() {

		
//		helecho = new Afines();
//		helecho.agregarAfin(new Afin(0	,0		,0		,0.20	,0 		,10)	,	0.01);
//		helecho.agregarAfin(new Afin(0.85	,0.04	,-0.04	,0.85	,0		,100)	,0.85);
//		helecho.agregarAfin(new Afin(0.20	,-0.26	,0.23	,0.22	,0		,100)	,0.07);
//		helecho.agregarAfin(new Afin(-0.15,0.30	,0.26	,0.24	,0		,28)	,0.07);

		arce = new Afines();
		arce.agregarAfin(new Afin(0.14	,0.1	,0		,0.51	,-0.08 	,-1.31)	,0.10);
		arce.agregarAfin(new Afin(0.43	,0.52	,-0.45	,0.50 	,1.49	,-0.75)	,0.35);
		arce.agregarAfin(new Afin(0.45	,-0.49	,0.47	,0.47	,-1.62	,-0.74)	,0.35);
		arce.agregarAfin(new Afin(0.49	,0		,0.0	,0.51	,0.02	,1.62)	,0.20);

		árbol = new Afines();
		árbol.agregarAfin(new Afin(0		,0		,0		,0.50	,0 	,0)		,0.05);
		árbol.agregarAfin(new Afin(0.42	,-0.42	,0.42	,0.42	,0	,0.2)	,0.4);
		árbol.agregarAfin(new Afin(0.42	,0.42	,-0.42	,0.42	,0	,0.2)	,0.4);
		árbol.agregarAfin(new Afin(0.10	,0		,0		,0.10	,0	,0.2)	,0.15);

		árbol2 = new Afines();
		árbol2.agregarAfin(new Afin(0.195	,-0.488	,0.344	,0.443	,0.4431 	,0.2452)	,0.2);
		árbol2.agregarAfin(new Afin(0.462	, 0.414	,-0.252 ,0.361	,0.2511 	,0.5692)	,0.2);
		árbol2.agregarAfin(new Afin(-0.058,-0.070	,0.453	,-0.111 ,0.5976 	,0.0969)	,0.2);
		árbol2.agregarAfin(new Afin(-0.035, 0.070	,-0.469 ,-0.022 ,0.4431 	,0.5069)	,0.2);
		árbol2.agregarAfin(new Afin(-0.637, 0.0  	,0.0  	,0.501	,0.8562 	,0.2513)	,0.2);

		sierpinski = new Afines();
		sierpinski.agregarAfin(new Afin(0.50	,0	,0	,0.50	,150 	,10),0.33);
		sierpinski.agregarAfin(new Afin(0.50	,0  ,0	,0.50	,1	,300)	,0.33);
		sierpinski.agregarAfin(new Afin(0.50	,0	,0	,0.50	,-150,10)	,0.34);

	}

	private void reset() {
		// Valores iniciales de la imagen
		pixels = new int[size];
		initialValues(pixels,width,height);
		
		source = new MemoryImageSource(width, height, pixels, 0, width);  	  
		image = createImage(source);                   
		
		// Punto inicial
		punto = new Point();
//		punto.x = 1 + (int) (Math.random() * width - 2);
//		punto.y = 1 + (int) (Math.random() * height - 2);
		punto.x = 0;
		punto.y = 0;
	}

	private void initialValues(int m[], int width, int height) {
		for (int fila =0; fila<height; fila++){
			for (int columna = 0; columna < width; columna++ ){
				if (fila == 0 || fila == height-1 || columna == 0 || columna == width-1){
					putPixel(m, columna, fila, Color.BLUE.getRGB());
				} else {
					putPixel(m, columna, fila, Color.DARK_GRAY.getRGB());
				}
			}
		}
	}


	public void paint(Graphics g)
	{                
		// Dibujar la imagen
		source = new MemoryImageSource(width, height, pixels, 0, width);	  	  
		image = createImage(source);          
		image.flush();
		g.drawImage(image, 0, 0, null);

	}

	// redefinimos update para que no borre la pantalla y no de la sensación de 
	// parpadeo
	public void update(Graphics g) 
	{
		paint(g);
	}



	private int getPixel(int m[], int x, int y) {
		int valor =0;
		if (x>=0 && y>=0 && x<width && y<height)
			valor  = m[(height-(y+1))*width+x];
		return valor;   
	}

	private void putPixel(int m[], int x, int y, int c) {
		if (x>=0 && y>=0 && x<width && y<height)
			m[(height-(y+1))*width+x] = c ;
	}



	/**
	 * Crea el estilo de la ventana, así como sus componentes
	 */
	void initForm()
	{
		// Fuente 
		Font fuente = new Font("Arial", Font.PLAIN, 20);
		setFont(fuente);
		
		// Colores de fondo y de las letras
		this.setBackground(Color.BLACK);
		this.setForeground(Color.BLACK);
		
		// Estilo de la ventana
		getContentPane().setLayout(new BorderLayout());

		// componentes

		btnDibujar = new Button ("Dibujar");
		btnDibujar.addActionListener(this);

		btnLimpiar = new Button ("Limpiar");
		btnLimpiar.addActionListener(this);

		// lista de imágenes
		choice = new Choice();
		
		// Add all the operation names from the Hashtable.
		choice.add("helecho");
		choice.add("arce");
		choice.add("árbol 1");
		choice.add("árbol 2");
		choice.add("sierpinski");

		// casilla para marcar
		casilla = new Checkbox();
		casilla.setState(false);
		Panel p = new Panel();
		p.setLayout (new FlowLayout());
		p.add (choice);
		p.add (btnDibujar);
		p.add (btnLimpiar);
		p.add(new Label("    "));
		Label l = new Label("Colores ");
		l.setForeground(Color.blue);
		p.add(l);
		p.add(casilla);

		getContentPane().add(BorderLayout.SOUTH,p);
		repaint();
	}




	////////////////////////////////////////////////////
	// Zoom and restart button handlers
	public void actionPerformed(ActionEvent evt) {
		Object object1 = evt.getSource();

		if (object1.equals(btnLimpiar)) {
//			reset();  
//			repaint();
			
		}


		if (object1.equals(btnDibujar)) {
			reset();
			
			String key = choice.getSelectedItem(); // key = helecho
			showStatus("Calculando...");
			
			for (int it=0 ; it < 100000; it++) {    	// i = Iteracion
				nuevoPunto(key,it);
			}
			
			showStatus("");
			repaint();
		}

	}

	// En cada iteracion, obtener un nuevo punto
	private void nuevoPunto(String key,int it) {
		double x,y;

		Point nuevoPunto = new Point(0,0);
		boolean color = casilla.getState();

		if (key.equals("helecho")) {
			nuevoPunto = Afines.HELECHO.aplicar(punto); // Obtener nuevo punto a partir del anterior
			int c = Color.YELLOW.getRGB(); // Color
			int index = Afines.HELECHO.getIndice();

			// Color a pintar
			if (color) c = index==0 ? 0xff00ff00 : (index == 1 ? 0xff00ff88 : (index == 2)  ? 0xffaaff00 : 0xffaaffff );


			if (it>20) putPixel(pixels,nuevoPunto.x+width/2,nuevoPunto.y,c);

		}
//		if (key.equals("arce")) {
//			arce.itera();
//			int c = 0xff00ffaa;
//			x = 100.0*arce.getX();
//			y = 100.0*arce.getY();
//			int index = arce.getIndice();
//			if (color) c = index==0 ? 0xff00ff00 : (index == 1 ? 0xff00ff88 : (index == 2)  ? 0xffaaff00 : 0xffaaffff );
//			if (it>20) putPixel(pixels,(int)(x+width/2),(int)(y+height/2),c);
//
//		}
//
//		if (key.equals("árbol 1")) {
//			árbol.itera();
//			int c = 0xff00ffaa;
//			int index = árbol.getIndice();
//			if (color) c = index==0 ? 0xff00ff00 : (index == 1 ? 0xff00ff88 : (index == 2)  ? 0xffaaff00 : 0xffaaffff );
//			x = 1200.0*árbol.getX();
//			y = 1200.0*árbol.getY();
//			if (it>20) putPixel(pixels,(int)x+width/2,(int)y,c);
//
//		}
//
//		if (key.equals("árbol 2")) {
//			árbol2.itera();
//			int c = 0xffaaaa00;
//			int index = árbol2.getIndice();
//			if (color) c = index==0 ? 0xffaaff00 : (index == 1 ? 0xffaaff88 : (index == 2)  ? 
//					0xffaa55aa : (index == 3) ? 0xffaaffff : 0xffaaaaff );
//			x = 750.0*árbol2.getX();
//			y = 750.0*árbol2.getY();
//			if (it>20) putPixel(pixels,(int)x-30,(int)y,c);
//
//		}
//
//		if (key.equals("sierpinski")) {
//			nuevoPunto = sierpinski.aplicar(punto);
//			int c = 0xff00ffaa;
//			int index =sierpinski.getIndice();
//			if (color) c = index==0 ? 0xff00ff00 : (index == 1 ? 0xff0000ff : 0xffff0000 );
//			if (it>20) putPixel(pixels,nuevoPunto.x+width/2,nuevoPunto.y,c);
//
//		}


		punto =nuevoPunto;

		/*   
	   try {
	   	Thread.sleep(500);
	   } catch(Exception e) {

	   }*/
	}  


	///////////////////////////////////////////////////
//	private	final String labelParam = "label";
//	private	final String backgroundParam = "background";
//	private	final String foregroundParam = "foreground";
//
//	private	final String widthParam  = "imageWidth";
//	private	final String heightParam = "imageHeight";

	/**
	 * Reads parameters from the applet's HTML host and sets applet
	 * properties.
	 */
//	private void usePageParams()
//	{
//		final String defaultLabel = "Default label";
//		final String defaultBackground = "C0C0C0";
//		final String defaultForeground = "000000";    
//		final String defaultWidth  = "256";    
//		final String defaultHeight = "256";
//
//		String labelValue;
//		String backgroundValue;
//		String foregroundValue;    
//		String szWidth;
//		String szHeight;
//
//		// get parameters from the HTML page
//		labelValue = getParameter(labelParam);
//		backgroundValue = getParameter(backgroundParam);
//		foregroundValue = getParameter(foregroundParam);
//		szWidth = getParameter(widthParam);
//		szHeight = getParameter(heightParam);
//
//		// si el ancho y e alto no son parámetros
//		if ((szHeight == null) || (szWidth == null))
//		{
//			szWidth  = defaultWidth;
//			szHeight = defaultHeight;
//		}
//
//		if ((labelValue == null) || (backgroundValue == null) ||
//				(foregroundValue == null))
//		{
//			// la pág. no tiene valores para los parámetros; tomar los valores por defecto
//			labelValue = defaultLabel;
//			backgroundValue = defaultBackground;
//			foregroundValue = defaultForeground;
//		}
//
//		// convertir ancho y alto en enteros
//		width = Integer.valueOf(szWidth).intValue();
//		height = Integer.valueOf(szHeight).intValue();
//
//
//	}

}
