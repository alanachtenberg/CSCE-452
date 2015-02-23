import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

//Use PaintPanel content = new PaintPanel();
//then jframewindowname.setContentPane(content);
//jframewindowname.setSize(xres,yres);
//etc. for using this class
public class PaintPanel extends JPanel {
	 
	JLabel rgbValue = new JLabel(""); // for displaying the rgb value the sliders make
	JSlider sliderR, sliderG, sliderB;
	DrawingCanvas canvas = new DrawingCanvas(); //canvas is for showing the sample color
	
	public PaintPanel() {
	    sliderR = getSlider(0, 255, 0, 50, 5); //Making red slider
	    sliderG = getSlider(0, 255, 0, 50, 5); //Making green slider
	    sliderB = getSlider(0, 255, 0, 50, 5); //Making blue slider
	 
	    JPanel panel = new JPanel(); // Panel for the sliders and labels and such
	    panel.setLayout(new GridLayout(6, 2, 15, 0));
	    panel.add(new JLabel("R-G-B Sliders (0 - 255)"));
	    panel.add(sliderR);
	    panel.add(sliderG);
	    panel.add(sliderB);
	    
	    panel.add(new JLabel("RGB Value: ", JLabel.LEFT));
	    
	    rgbValue.setBackground(Color.white); //for showing the rgb value
	    rgbValue.setForeground(Color.black); 
	    rgbValue.setOpaque(true);
	    panel.add(rgbValue);

	    add(panel, BorderLayout.SOUTH);
	    add(canvas);
	    
	    //Add in clear button here later?
	    
	    //Create another panel for the robot arm to paint on here
	    
	}
	
	// Creates a small canvas that shows the RGB color made from the sliders
	class DrawingCanvas extends Canvas {
	    Color color;
	    int redValue, greenValue, blueValue;

	    public DrawingCanvas() {
	      setSize(75, 75);
	      color = new Color(0, 0, 0);
	      setBackgroundColor();
	    }

	    public void setBackgroundColor() {
	      color = new Color(redValue, greenValue, blueValue); 
	      setBackground(color);
	    }
	  }
	
	
	//function for making sliders easier
	public JSlider getSlider(int min, int max, int init, int mjrTkSp, int mnrTkSp) {
	    JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
	    slider.setPaintTicks(true);
	    slider.setMajorTickSpacing(mjrTkSp);
	    slider.setMinorTickSpacing(mnrTkSp);
	    slider.setPaintLabels(true);
	    slider.addChangeListener(new SliderListener());
	    return slider;
	  }
	
	//Listens for changes on the sliders and repaints the canvas
	class SliderListener implements ChangeListener {
	    public void stateChanged(ChangeEvent e) {
	      JSlider slider = (JSlider) e.getSource();
	      if (slider == sliderR) {
	        canvas.redValue = slider.getValue();
	        displayRGBColor();
	      } else if (slider == sliderG) {
	        canvas.greenValue = slider.getValue();
	        displayRGBColor();
	      } else if (slider == sliderB) {
	        canvas.blueValue = slider.getValue();
	        displayRGBColor();
	      }
	      canvas.repaint();
	    }
	      
	    // Displays the RGB color
	      public void displayRGBColor() {
	          canvas.setBackgroundColor();
	          rgbValue.setText(Integer.toString(canvas.color.getRGB() & 0xffffff, 16));
	        }

    

    //private int prevX, prevY;     // The previous location of the brush
    
    //private Graphics graphicsForDrawing;  // A graphics context for the panel
    // that is used to draw the user's curve.
    
    /**
     * Constructor for PaintPanel class sets the background color to be white
     */
    /*PaintPanel() {
       setBackground(Color.WHITE);

    }*/

}
}