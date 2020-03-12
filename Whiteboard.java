import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Whiteboard extends JFrame implements ActionListener{
	
	//Panel holding the buttons
	private JPanel tools;
	
	//Shapes that can be drawn
	private JButton line;
	private JButton rectangle;
	private JButton circle;
	private JButton filledRectangle;
	private JButton filledCircle;
	
	//Options
	private JButton color;
	private JButton clear;
	
	//Point used to determine position of mouse
	private Point mouse;
	
	//Color used to draw
	private Color sampleColor;
	
	//Various booleans corresponding to the button pressed
	private boolean lineOn;
	private boolean rectangleOn;
	private boolean circleOn;
	private boolean filledRectangleOn;
	private boolean filledCircleOn;
	private boolean clearOn;
	
	//Constructor
	public Whiteboard() {
		//Setting up the GUI
		super("Whiteboard");
		
		this.setLayout(new BorderLayout());
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tools = new JPanel(new GridLayout(2,5));
		
		lineOn = false;
		rectangleOn = false;
		circleOn = false;
		filledRectangleOn = false;
		filledCircleOn = false;
		clearOn = false;
		
		line = new JButton("Line");
		line.setEnabled(true);
		line.addActionListener(this);
		line.setActionCommand("Line");
		line.setVisible(true);
		tools.add(line);
		
		rectangle = new JButton("Rectangle");
		rectangle.setEnabled(true);
		rectangle.addActionListener(this);
		rectangle.setActionCommand("Rectangle");
		rectangle.setVisible(true);
		tools.add(rectangle);
		
		circle = new JButton("Circle");
		circle.setEnabled(true);
		circle.addActionListener(this);
		circle.setActionCommand("Circle");
		circle.setVisible(true);
		tools.add(circle);
		
		filledRectangle = new JButton("Filled Rectangle");
		filledRectangle.setEnabled(true);
		filledRectangle.addActionListener(this);
		filledRectangle.setActionCommand("Filled Rectangle");
		filledRectangle.setVisible(true);
		tools.add(filledRectangle);
		
		filledCircle = new JButton("Filled Circle");
		filledCircle.setEnabled(true);
		filledCircle.addActionListener(this);
		filledCircle.setActionCommand("Filled Circle");
		filledCircle.setVisible(true);
		tools.add(filledCircle);
		
		color = new JButton("Color");
		color.setEnabled(true);
		color.addActionListener(this);
		color.setActionCommand("Color");
		color.setVisible(true);
		tools.add(color);
		
		clear = new JButton("Clear");
		clear.setEnabled(true);
		clear.addActionListener(this);
		clear.setActionCommand("Clear");
		clear.setVisible(true);
		tools.add(clear);
		
		this.add(tools, BorderLayout.NORTH);
		

		getContentPane().setBackground(Color.WHITE);

	}

	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand(); 
		
		if(s.equals("Clear")) {
			clearOn = true;
			line.setEnabled(true);
			rectangle.setEnabled(true);
			filledRectangle.setEnabled(true);
			circle.setEnabled(true);
			filledCircle.setEnabled(true);
			clear.setEnabled(false);
			
			repaint(); //Repaint does nothing when clearOn is true
		} else if(s.equals("Color")) {
			sampleColor = JColorChooser.showDialog(this,"JColorChooser", sampleColor); //Prompts user to choose color
		} else {
			if(s.equals("Line")) { //For Line
				lineOn = true;
				line.setEnabled(false);
				rectangle.setEnabled(false);
				circle.setEnabled(false);
				filledRectangle.setEnabled(false);
				filledCircle.setEnabled(false);
				clear.setEnabled(true);
			} else if(s.equals("Rectangle")) { //For Rectangle
				rectangleOn = true;
				line.setEnabled(false);
				rectangle.setEnabled(false);
				circle.setEnabled(false);
				filledRectangle.setEnabled(false);
				filledCircle.setEnabled(false);
				clear.setEnabled(true);
			} else if(s.equals("Circle")) { //For Circle
				circleOn = true;
				line.setEnabled(false);
				rectangle.setEnabled(false);
				circle.setEnabled(false);
				filledRectangle.setEnabled(false);
				filledCircle.setEnabled(false);
				clear.setEnabled(true);
			} else if(s.equals("Filled Rectangle")) { //For Filled Rectangle
				filledRectangleOn = true;
				line.setEnabled(false);
				rectangle.setEnabled(false);
				circle.setEnabled(false);
				filledRectangle.setEnabled(false);
				filledCircle.setEnabled(false);
				clear.setEnabled(true);
			} else if(s.equals("Filled Circle")) { //For Filled Circle
				filledCircleOn = true;
				line.setEnabled(false);
				rectangle.setEnabled(false);
				circle.setEnabled(false);
				filledRectangle.setEnabled(false);
				filledCircle.setEnabled(false);
				clear.setEnabled(true);
			}
			this.addMouseListener(new MouseListener() { //Calls paint when mouse is clicked (Left-click only)
		        public void mousePressed(MouseEvent me) { }
		        public void mouseReleased(MouseEvent me) { }
		        public void mouseEntered(MouseEvent me) { }
		        public void mouseExited(MouseEvent me) { }
		        public void mouseClicked(MouseEvent me) { 
		        	repaint();
		        	//System.out.println("Clicked");
		        }
			});
		}
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		mouse = MouseInfo.getPointerInfo().getLocation();
		g.setColor(sampleColor);
		
		//Checks which button was clicked, draws corresponding shape
		if(lineOn) {
			g.drawLine((int) mouse.getX(), (int) mouse.getY(), (int) mouse.getX() + 200, (int) mouse.getY());
		} else if(rectangleOn) {
			g.drawRect((int) mouse.getX(), (int) mouse.getY(), 200, 300);
		} else if(filledRectangleOn) {
			g.drawRect((int) mouse.getX(), (int) mouse.getY(), 200, 300);
			g.fillRect((int) mouse.getX(), (int) mouse.getY(), 200, 300);
		}else if(circleOn) {
			g.drawOval((int) mouse.getX(), (int) mouse.getY(), 100, 100);
		} else if(filledCircleOn) {
			g.drawOval((int) mouse.getX(), (int) mouse.getY(), 100, 100);
			g.fillOval((int) mouse.getX(), (int) mouse.getY(), 100, 100);
		} else if(clearOn) {
			
		}
		
		//Sets all variables to false
		lineOn = false;
		rectangleOn = false;
		filledRectangleOn = false;
		filledCircleOn = false;
		circleOn = false;
	}

	//Main method
	public static void main(String[] args) {
		Whiteboard win = new Whiteboard();
		win.setVisible(true);
	}
}
