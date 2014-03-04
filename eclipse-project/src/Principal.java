//import java.awt.Image;
//import java.awt.MediaTracker;
//import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.awt.image.ImageObserver;
//import java.awt.image.MemoryImageSource;
//import java.awt.image.PixelGrabber;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.imageio.ImageIO;
//
//
//
public class Principal {
//
//	private static int iniAncho;
//	private static int iniAlto;
//	private static Object imagenNueva;
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
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
//	    try {
//	    	PixelGrabber pgObj = new PixelGrabber( imagenFuente, 0,0,iniAncho,iniAlto,pix,0,iniAncho );
//	    	if( pgObj.grabPixels() && ( (pgObj.getStatus() & ImageObserver.ALLBITS ) != 0 ) ) {
//	    		for( int i=0; i < (iniAncho*iniAlto); i++ ) {
//	    			if(verPixel(pix[i]))
//	    				pix[i] = 0xFFFFFFFF;
//	    		}
//	    	}
//	    	else {
//	    		System.out.println( "Problemas al descomponer la imagen" );
//	    	}
//	    } catch( InterruptedException e ) {
//	    	System.out.println( e );
//	    }
//
//	    imagenNueva = this.createImage( new MemoryImageSource(iniAncho,iniAlto,pix,0,iniAncho ) );
//	}
//
}
