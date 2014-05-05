package hoja;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

/** Use MemoryImageSource to create an animated image. **/
public class AnimationApplet extends JApplet
                       implements Runnable
{
  // Will use thread reference as a flag
  Thread fThread;
  //msecs of sleep time
  int fDeltaT = 10;
  // Need a reference to the panel on which animation appears
  DrawingPanel fDrawingPanel;

  /** Create a simple interface to draw the animation.**/
  public void init () {
    Container content_pane = getContentPane ();

    // Create an instance of DrawingPanel
    fDrawingPanel = new DrawingPanel ();

    // Add the DrawingPanel to the contentPane.
    content_pane.add (fDrawingPanel);
  } // init

  /** Start the thread that controls the animation.**/
  public void start () {
    // Tell the panel to create the image source.
    fDrawingPanel.init ();

    // If the thread reference not null then a
    // thread is already running. Otherwise, create
    // a thread and start it.
    if (fThread == null) {
        fThread = new Thread (this);
        fThread.start ();
    }
  } // start

  /** Stop thread by setting flag. **/
  public void stop () {
     fThread = null;
  }

  public void run () {

    // Loop through sleep periods until flag set to null
    while (fThread != null) {
      try  {
        Thread.sleep (fDeltaT);
      } catch  (InterruptedException e) {}
      // Send request for create new image
      fDrawingPanel.newFrame ();
    }
  } // run

} //class AnimationApplet

/** Display the animation on this panel. **/
class DrawingPanel extends JPanel
{
  Image fImage;
  int fWidth, fHeight;
  int [] fPixels;

  MemoryImageSource fSource;
  int fFrame=0;

  /**
    * Do the image building in this method rather
    * than in a constructor so that it will know
    * the size of the panel after it has been added
    * to the applet.
   **/
  void init () {
    fWidth = getSize ().width;
    fHeight = getSize ().height;
    // Create an array to hold the color of each pixel
    fPixels = new int [fWidth * fHeight];
    // The MemoryImageSource creates an image from the array.
    fSource = new MemoryImageSource (fWidth, fHeight,
                                    fPixels, 0, fWidth);

    // Set the flag for animations.
    fSource.setAnimated (true);
    fImage = createImage (fSource);
  } // init

  /** Modify the pixel color parameters for each frame. **/
  void newFrame () {
	  int index = 0;
	  // Create a mask from the current frame number
	  byte mask =  (byte) (fFrame & 0xff);

	  for (int y = 0; y < fHeight; y++) {
		  for  (int x = 0; x < fWidth; x++) {
			  // Modify the color components each frame.
			  int alpha = 255;
			  int red =  (y * 255) / (fWidth - 1);
			  red = red & mask;

			  int green =  (x * 255) / (fHeight - 1);
			  green = green & mask;

			  int blue = 255 -  (255 * ( x - fWidth))/fWidth;
			  blue = blue & mask;

			  fPixels[index++] =  (255 << alpha) | (red << 16) | (green << 8) | blue;

		  }
	  }
	  fFrame++;
	  // Push out the new data for a new frame.
	  fSource.newPixels (0, 0, fWidth, fHeight);
  } // newFrame
	  

  /** Draw the image on the panel. **/
  public void paintComponent (Graphics g) {
    g.drawImage ( fImage, 0, 0, this );
  }

}// class DrawingPanel