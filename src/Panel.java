import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Panel extends JPanel implements ChangeListener{
	Timer tim;
	ArrayList<Planet> planets = new ArrayList<Planet>();
	int screenx;
	int screeny;
	Point mousePos;
	boolean run;
	JColorChooser tcc;
	Color toMake;
	Panel me;
	Frame myFrame;
	double timerTime = 2;
	boolean planetRun;
	public Panel(Frame myFrame)
	{
		this.myFrame = myFrame; 
		planetRun = true;
		this.setSize(myFrame.getWidth(), myFrame.getHeight());
		timerTime = 2;
		run = true;
		me = this;
		toMake = Color.black;
		this.setVisible(true);
		tim = new Timer((int)timerTime, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				tic();
			}
		});
		//mousePos = new Point(MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y); 
		screenx = 0;
		screeny = 0; 
		this.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("MOuse PRessed");
				if(arg0.getButton() == MouseEvent.BUTTON1)
				{
					Point mousePoint = arg0.getPoint();
					for(Planet pl : Planet.allPlanets)
					{
						if(Math.abs((Math.round(pl.x)-screenx) - mousePoint.x) < 10 && Math.abs((Math.round(pl.y)-screeny)-mousePoint.y) < 10)
						{
							if(pl.clicked)
								pl.clicked = false;
							else
								pl.clicked = true;
						}
					}
					//g.drawOval((int) Math.round(p.x)-screenx - 10 , (int) Math.round(p.y)-screeny - 10, 20, 20);
					//g.fillOval((int) Math.round(p.x)-screenx - 10, (int) Math.round(p.y)-screeny - 10,20,20);
					
				}
				if(arg0.getButton() == MouseEvent.BUTTON3)
				{
					run = false;
					JTextField vxField = new JTextField(5);
					JTextField vyField = new JTextField(5);
					JTextField mField = new JTextField(5);
					
					JPanel inputPanel = new JPanel();
					inputPanel.add(new JLabel("Velocity X (m/s): "));
					inputPanel.add(vxField);
					inputPanel.add(Box.createHorizontalStrut(15));
					inputPanel.add(new JLabel("Velocity Y (m/s): "));
					inputPanel.add(vyField);
					inputPanel.add(Box.createHorizontalStrut(15));
					inputPanel.add(new JLabel("Mass (kg): "));
					inputPanel.add(mField);
					tcc = new JColorChooser(inputPanel.getForeground());
					tcc.getSelectionModel().addChangeListener(me);
					tcc.setBorder(BorderFactory.createTitledBorder(
                            "Choose Object Color"));
					inputPanel.add(tcc,BorderLayout.PAGE_END); 
					
					int result = JOptionPane.showConfirmDialog(null, inputPanel, "Enter the velocity and mass of your object.", 
							JOptionPane.OK_CANCEL_OPTION);
					if(result == JOptionPane.OK_OPTION){
						//new Planet(screenx + mousePos.x, screeny + mousePos.y);
						System.out.println("VX: " + vxField.getText());
						System.out.println("VY: " + vyField.getText());
						new Planet(screenx + mousePos.x, screeny + mousePos.y, Double.parseDouble(vxField.getText()), Double.parseDouble(vyField.getText()), Double.parseDouble(mField.getText()), toMake);
						run = true;
					}	
					if( result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION)
					{
						run = true;
					}
				}
				toMake = new Color(51,51,51);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});

		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("key pressed");
				if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
				{
					System.out.println("Space Pressed");
					if(run){
						run = false; 
					}
					else{
						run = true;
					}
				}
				
			}
 
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Key released");
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Key Typed");
				
			}
			
		});
		//this.validate();
		repaint();
		tim.start();
	}
	
	public void tic()
	{
		//System.out.println("X  " + (MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x) + " y: " + (MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y)  );
		if(run == true){
			mousePos = new Point(MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y);
			if(mousePos.x <= 3)
			{
				screenx-=5;
			}
			else if(mousePos.x >= this.getWidth()-3){
				screenx+=5;
			}
			
			if(mousePos.y <= 3){
				screeny-=5;
			}
			if(mousePos.y >= this.getHeight()-3){
				screeny+=5;
			}
			repaint();
			if(planetRun){
				for(int i = 0; i < Planet.allPlanets.size(); i++)
				{
					Planet.allPlanets.get(i).tic();
				}
			}
		}	
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		//g.dispose();
		g.clearRect(0,0,this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawString("Directions", 10, 25);
		g.drawString("-", 74, 25);
		g.drawLine(75, 28, 75, 63);
		g.drawString("+", 72, 75);
		g.drawString("-", 48, 49);
		g.drawLine(55,45,95,45);
		g.drawString("+", 100, 50);
//		g.drawRect(10,10,10,10);
//		g.fillRect(10, 10, 10, 10);
		g.drawString(screenx + ", " + screeny, 4 , 10);
		for(Planet p : Planet.allPlanets)
		{
			g.setColor(p.color);
			if(p.x > screenx && p.y > screeny){
				g.drawOval((int) Math.round(p.x)-screenx - 10 , (int) Math.round(p.y)-screeny - 10, 20, 20);
				g.fillOval((int) Math.round(p.x)-screenx - 10, (int) Math.round(p.y)-screeny - 10,20,20);
			}
			for( Point po : p.myPoints){
				g.drawOval(po.x - screenx, po.y - screeny, 1, 1);
			}
			if(p.clicked){
				g.drawString("VX: " + p.vx,(int) Math.round(p.x)-screenx ,(int) Math.round(p.y)-screeny+20);
				g.drawString("VY: " + p.vy,(int) Math.round(p.x)-screenx ,(int) Math.round(p.y)-screeny+30);
				g.drawString("Mass: " + p.mass, (int) Math.round(p.x)-screenx ,(int) Math.round(p.y)-screeny+40);
			}
			g.setColor(Color.black);
			
		}
		// Drawing minimap
		int scalemini = 100; // 1000 pixels in real = 1 pixel mini
		int xmini = (int) (((double) this.getWidth())*.80);
		int ymini = (int) (((double) this.getHeight())*.80);    
		int widthmini = (int) (((double) this.getWidth())*.2);
		int heightmini = (int) (((double) this.getHeight())*.2);
		g.clearRect(xmini, ymini, widthmini,heightmini);
		g.drawRect(xmini, ymini, widthmini,heightmini);
		Point topLeft =  new Point((int) Math.round((0-(double)widthmini/2)*scalemini), (int) Math.round((0- (double) heightmini/2)*scalemini));
//		int screenxminipos = (int) Math.round(this.getWidth() - (double)widthmini/2 - this.getWidth()/scalemini);
//		int screenyminipos = (int) Math.round(this.getHeight() - (double)heightmini/2 - this.getHeight()/scalemini);
//		int screenminiwidth = (int) Math.round((double)this.getWidth()/scalemini);
//		int screenminiheight = (int)Math.round((double)this.getHeight()/scalemini);
//		g.drawRect(screenxminipos, screenyminipos, screenminiwidth, screenminiheight);
		for (Planet p : Planet.allPlanets)
		{
			g.setColor(p.color);
			if(p.x > topLeft.x && p.y> topLeft.y && p.x < topLeft.x+(widthmini * scalemini) && p.y < topLeft.y +(widthmini*scalemini) ){
				g.setColor(p.color);
				int px = (int) Math.round(getWidth() - ((double)widthmini)/2 + ((double)p.x)/scalemini);
				int py = (int) Math.round(getHeight() - ((double)heightmini)/2 + ((double)p.y)/scalemini);
				g.drawOval(px,py,3,3);
				g.fillOval(px, py, 3, 3);
				
				for( Point po : p.myPoints){
					int pox = (int) Math.round(getWidth() - ((double)widthmini)/2 + ((double)po.x)/scalemini);
					int poy = (int) Math.round(getHeight() - ((double)heightmini)/2 + ((double)p.y)/scalemini);
					//g.drawOval(pox, poy, 1, 1);
				}
			}
			g.setColor(Color.black);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		toMake = tcc.getColor();
		
	}
}
