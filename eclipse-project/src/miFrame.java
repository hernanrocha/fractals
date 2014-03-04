import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import basic.Punto;
import coloreo.Coloreo;
import coloreo.ColoreoFuego;
import coloreo.ColoreoRGB;
import conjunto.Conjunto;
import conjunto.ConjuntoMandelbrot;

/**
 * 
 */

/**
 * @author hernan
 *
 */
public class miFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblImagen;
	private Grafico g;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					miFrame frame = new miFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public miFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lblImagen = new JLabel("");
		contentPane.add(lblImagen, BorderLayout.CENTER);
		lblImagen.addMouseListener( new ManejadorClicsRaton(this) );
		
		JButton btnAumentar = new JButton("Aumentar");
		btnAumentar.setEnabled(false);
		btnAumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Fractal: Alfombra de Sierpinski
//				f = new FractalCompuesto(f);
//				generarImagen();
				
				// Conjunto de Mandelbrot
//				ConjuntoMandelbrot con = new ConjuntoMandelbrot();
//				System.out.println("Res " + con.iterar(new Complejo()));
				
				// Grafico de Mandelbrot
				g.zoomIn(new Punto(1, 1));
				g.calcular();
				g.graficar(lblImagen);
				
//				Fraccion f1 = new Fraccion(1, 1);
//				Fraccion f2 = new Fraccion(4, 5);
//				ComplejoFraccion c1 = new ComplejoFraccion(f1, f2);
//				
//				Fraccion f3 = new Fraccion(6, 6);
//				Fraccion f4 = new Fraccion(8, 10);
//				ComplejoFraccion c2 = new ComplejoFraccion(f3, f4);
//				
//				if(c1.equals(c2)){
//					System.out.println("Son iguales");
//				}
//				
//				MatrizHash matriz = new MatrizHash();
//				
//				matriz.put(c1, 5);
//				System.out.println(matriz.get(c2));
								
			}
		});
		contentPane.add(btnAumentar, BorderLayout.SOUTH);
		
//		Complejo c = new Complejo(0.3, 0.5);
		
		// Personalizacion
//		Funcion f = new FuncionPolinomica(2, new Complejo(0.279, 0));
//		Funcion f = new FuncionPolinomica(3, new Complejo(0.4, 0));
//		Funcion f = new FuncionPolinomica(3, new Complejo(-1, 0));
//		Funcion f = new FuncionPolinomica(3, new Complejo(0.3, 0.6));
		
//		Fraccion c1 = new Fraccion(3, 3);
//		Fraccion c2 = new Fraccion(6, 6);
//		
//		if(c1.equals(c2)){
//			System.out.println("Son iguales");
//		}
		
//		Coloreo coloreo = new ColoreoMonocromatico();
//		Coloreo coloreo = new ColoreoModulo();
//		Coloreo coloreo = new ColoreoFranja();
//		Coloreo coloreo = new ColoreoRGB();
//		Coloreo coloreo = new ColoreoLinea(45, 5);
		Coloreo coloreo = new ColoreoFuego();
		
		Conjunto conjunto = new ConjuntoMandelbrot();
//		Conjunto conjunto = new ConjuntoJulia(f);
		g = new Grafico(this, conjunto, coloreo);
		
		// Grafico de Julia (primero)
		g.calcular();
		g.graficar(lblImagen);

	}
	
	public void zoomIn(Punto p){
		g.zoomIn(p);
		g.calcular();
		g.graficar(lblImagen);
	}

	public void zoomOut(Punto p) {
		g.zoomOut(p);
		g.calcular();
		g.graficar(lblImagen);		
	}
		
//		generarImagen();
		
//		Image imagenFuente = Toolkit.getDefaultToolkit().getImage("darksouls.jpg");
//	    MediaTracker tracker = new MediaTracker(this);
//	    tracker.addImage( imagenFuente,1 );    
//
//	    try {
//	    	if( !tracker.waitForID( 1,10000 ) ) {
//	    		System.out.println( "Error en la carga de la imagen" );
//	    		System.exit( 1 );        
//	    	}
//	    } catch( InterruptedException e ) {
//	    	System.out.println( e );
//	    }  
//
//	    iniAncho = imagenFuente.getWidth( this );
//	    iniAlto = imagenFuente.getHeight( this );
//
//	    int[] pix = new int[iniAncho * iniAlto];
//	    System.out.println(iniAncho);
//	    System.out.println(iniAlto);
//	    try {
//		    PixelGrabber pgObj = new PixelGrabber(imagenFuente, 0, 0, iniAncho, iniAlto, pix, 0, iniAncho);
//	    	if( pgObj.grabPixels() && ( (pgObj.getStatus() & ImageObserver.ALLBITS ) != 0 ) ) {
//	    		for( int i=0; i < (iniAncho*iniAlto); i++ ) {
//	    			//if(verPixel(pix[i]))
//	    				//pix[i] = 0xFFFFFFFF;
//	    			System.out.println(pix[i]);
//	    		}
//	    	}
//	    	else {
//	    		System.out.println( "Problemas al descomponer la imagen" );
//	    	}
//	    } catch( InterruptedException e ) {
//	    	System.out.println( e );
//	    }
		


	
//	public void mostrarImagen(ImageIcon icon){
//
//	}
	
//	public void generarImagen(int[] pix, int pixelsX, int pixelsY){
////	    int[] pix = f.getImage();
//	    int tam = (int) Math.sqrt(pix.length);
//	    
//	    System.out.println(tam);
//	    
//	    imagenNueva = this.createImage(new MemoryImageSource(tam, tam, pix, 0, tam ));
//	    
//        //Escalar Imagen
//	    ImageIcon icon = new ImageIcon(imagenNueva);
//        ImageIcon tmpIcon = new ImageIcon(icon.getImage().getScaledInstance(pixelsX, pixelsY, Image.SCALE_DEFAULT));
//	    lblImagen.setIcon(tmpIcon);
//	}
	


}