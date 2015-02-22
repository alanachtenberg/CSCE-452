
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//Use PaintPanel content = new PaintPanel();
//then jframewindowname.setContentPane(content);
//jframewindowname.setSize(xres,yres);
//etc. for using this class
public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener {
	 /**
     * Some constants to represent the color selected by the user.
     */
    private final static int BLACK = 0,
                      RED = 1,     
                      GREEN = 2,   
                      BLUE = 3, 
                      ORANGE = 4,   
                      PINK = 5,
                      YELLOW = 6;
    
    private int currentColor = BLACK;  // The currently selected drawing color,
                                       //   coded as one of the above constants.
    private int prevX, prevY;     // The previous location of the brush
    
    private Graphics graphicsForDrawing;  // A graphics context for the panel
    // that is used to draw the user's curve.
    
    /**
     * Constructor for PaintPanel class sets the background color to be white
     */
    PaintPanel() {
       setBackground(Color.WHITE);
       addMouseListener(this);
       addMouseMotionListener(this);
    }
    
    /**
     * Draw the contents of the panel. The drawing
     * is erased whenever this routine is called.
     */
    public void paintComponent(Graphics g) {
       
       super.paintComponent(g);  // Fill with background color (white).
       
       int width = getWidth();    // Width of the panel.
       int height = getHeight();  // Height of the panel.
       
       
       // Mostly drawing the color rectangles and CLEAR "button" here along with some borders
       // around the rectangles.  Spacing between rectangles and future buttons will probably 
       // need to be adjusted here later
       
       int colorSpacing = (height - 56) / 7;
          // Distance between the top of one colored rectangle in the palette
          // and the top of the rectangle below it.  The height of the
          // rectangle will be colorSpacing - 3.  There are 7 colored rectangles,
          // so the available space is divided by 7.  The available space allows
          // for the gray border and the 50-by-50 CLEAR button.
             
       /* Draw a 3-pixel border around the applet in gray.  This has to be
        done by drawing three rectangles of different sizes. */
       
       g.setColor(Color.GRAY);
       g.drawRect(0, 0, width-1, height-1);
       g.drawRect(1, 1, width-3, height-3);
       g.drawRect(2, 2, width-5, height-5);
       
       /* Draw a 56-pixel wide gray rectangle along the right edge of the applet.
        The color palette and Clear button will be drawn on top of this.
        (This covers some of the same area as the border I just drew. */
       
       g.fillRect(width - 56, 0, 56, height);
       
       /* Draw the "Clear button" as a 50-by-50 white rectangle in the lower right
        corner of the applet, allowing for a 3-pixel border. */
       
       g.setColor(Color.WHITE);
       g.fillRect(width-53,  height-53, 50, 50);
       g.setColor(Color.BLACK);
       g.drawRect(width-53, height-53, 49, 49);
       g.drawString("CLEAR", width-48, height-23); 
       
       /* Draw the seven color rectangles. */
       
       g.setColor(Color.BLACK);
       g.fillRect(width-53, 3 + 0*colorSpacing, 50, colorSpacing-3);
       g.setColor(Color.RED);
       g.fillRect(width-53, 3 + 1*colorSpacing, 50, colorSpacing-3);
       g.setColor(Color.GREEN);
       g.fillRect(width-53, 3 + 2*colorSpacing, 50, colorSpacing-3);
       g.setColor(Color.BLUE);
       g.fillRect(width-53, 3 + 3*colorSpacing, 50, colorSpacing-3);
       g.setColor(Color.ORANGE);
       g.fillRect(width-53, 3 + 4*colorSpacing, 50, colorSpacing-3);
       g.setColor(Color.PINK);
       g.fillRect(width-53, 3 + 5*colorSpacing, 50, colorSpacing-3);
       g.setColor(Color.YELLOW);
       g.fillRect(width-53, 3 + 6*colorSpacing, 50, colorSpacing-3);
       
       /* Draw a 2-pixel white border around the color rectangle
        of the current drawing color. */
       
       g.setColor(Color.WHITE);
       g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
       g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
       
    } // end paintComponent()

    /**
     * Change the drawing color after the user has clicked the
     * mouse on the color palette at a point with y-coordinate y.
     */
    private void changeColor(int y) {
       
       int width = getWidth();           // Width of applet.
       int height = getHeight();         // Height of applet.
       
       // Note that if the position of the color rectangles change the equation for
       // colorSpacing will need to change
       int colorSpacing = (height - 56) / 7;  // Space for one color rectangle.
       int newColor = y / colorSpacing;       // Which color number was clicked?
       
       if (newColor < 0 || newColor > 6)      // Make sure the color number is valid.
          return;
       
       /* Remove the hilite from the current color, by drawing over it in gray.
        Then change the current drawing color and draw a hilite around the
        new drawing color.  */
       
       Graphics g = getGraphics();
       g.setColor(Color.GRAY);
       g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
       g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
       currentColor = newColor;
       g.setColor(Color.WHITE);
       g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
       g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
       g.dispose();
       
    } // end changeColor()
    
    
    /**
     * This routine is called in mousePressed when the user selects a different color.
       * It sets up the graphics context, graphicsForDrawing, to be used to draw the user's 
       * sketch in the current color.
     */
    private void setUpDrawingGraphics() {
       graphicsForDrawing = getGraphics();
       switch (currentColor) {
       case BLACK:
          graphicsForDrawing.setColor(Color.BLACK);
          break;
       case RED:
          graphicsForDrawing.setColor(Color.RED);
          break;
       case GREEN:
          graphicsForDrawing.setColor(Color.GREEN);
          break;
       case BLUE:
          graphicsForDrawing.setColor(Color.BLUE);
          break;
       case ORANGE:
          graphicsForDrawing.setColor(Color.ORANGE);
          break;
       case PINK:
          graphicsForDrawing.setColor(Color.PINK);
          break;
       case YELLOW:
          graphicsForDrawing.setColor(Color.YELLOW);
          break;
       }
    } // end setUpDrawingGraphics()
    
	//@Override not an override, is an interface
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		// Unnecessary but required by MouseListener and MouseMotionListener interface
		// Can leave empty unless needed
	}

	//Needed for picking colors
	//@Override same as before
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();   // x-coordinate where the user clicked.
        int y = e.getY();   // y-coordinate where the user clicked.
        
        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.
        
        if (x > width - 53) {
            // User clicked to the right of the drawing area.
            // This click is either on the clear button or
            // on the color palette.
         if (y > height - 53)
            repaint();       //  Clicked on "CLEAR button".
         else
            changeColor(y);  // Clicked on the color palette.
      }
	}

	
	
	//@Override same as before
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
        graphicsForDrawing.dispose();
        graphicsForDrawing = null;
	}
	
	
	
	//@Override same as before
	public void mouseMoved(MouseEvent e) {
		// Unnecessary but required by MouseListener and MouseMotionListener interface
		// Can leave empty unless needed
	}

	//@Override same as before
	public void mouseClicked(MouseEvent e) {
		// Unnecessary but required by MouseListener and MouseMotionListener interface
		// Can leave empty unless needed
	}

	//@Override same as before
	public void mouseEntered(MouseEvent e) {
		// Unnecessary but required by MouseListener and MouseMotionListener interface
		// Can leave empty unless needed
	}

	//@Override same as before
	public void mouseExited(MouseEvent e) {
		// Unnecessary but required by MouseListener and MouseMotionListener interface
		// Can leave empty unless needed
	}
}