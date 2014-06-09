import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Frame extends JFrame{
	Panel p;
	public Frame()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		this.setSize(xSize,ySize);
		this.setVisible(true);
		this.setTitle("Space Sim");
		p = new Panel(this);
		this.add(p);
		//this.pack();
		System.out.println(p.getWidth() + " " + "" + p.getHeight());
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("Key has been pressed");
				if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
				{
					if(p.planetRun){
						p.planetRun = false;
					}
					else{
						p.planetRun = true;
					}
				}
				
				if(arg0.getKeyCode() == KeyEvent.VK_F1){
					BufferedImage b = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_RGB);
					p.paintComponent(b.getGraphics());
					try {
						//ImageIO.write(b,"png", new File("/screenshots/" + 100*Math.random()));
						new File("/screenshots").mkdirs();
						File folder = new File("/screenshots");
						File [] listOfFiles = folder.listFiles();
//						if(listOfFiles == null)
//							ImageIO.write(b,"png", new File("1.png"));
//						else{
							int num = 0; 
							for(File f : listOfFiles)
							{	
								String s = f.getName();
								if(s.contains(".")){
									s = s.substring(0,s.indexOf("."));
									try{
										int temp = Integer.parseInt(s);
											
										if(temp > num)
											num = temp;
									}
									catch(Exception e)
									{
										System.err.println("Error in Parsing files while screenshot");
									}
								}	
								
							}
							if(new File("/screenshots/" + ++num + ".png").mkdirs())
								ImageIO.write(b,"png", new File(++num + ".png"));
							
					} catch (IOException e) {
						// TODO Auto-generated catch block
						new File("/screenshots").mkdirs();
						try {
							ImageIO.write(b,"png", new File("/screenshots/1.png"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
					
				}
				
				//single star and  planet system
				if(arg0.getKeyCode() == KeyEvent.VK_1)
				{
					Planet.allPlanets = new <Planet> ArrayList();
					Planet pl = new Planet(p.getWidth()/2, p.getHeight()/2,0,0,5E+17, Color.BLACK);
					Planet gl = new Planet(p.getWidth()/4, p.getHeight()/4,150,-150,1500,Color.BLUE);
					p.screenx = 0;
					p.screeny = 0;
				}
				
				if(arg0.getKeyCode() == KeyEvent.VK_2){
					Planet.allPlanets = new <Planet> ArrayList();
					Planet pl = new Planet(p.getWidth()/2, p.getHeight()/2,0,0,5E+17, Color.BLACK);
					Planet gl = new Planet(p.getWidth()/4, p.getHeight()/4,150,-150,1500,Color.BLUE);
					Planet gg = new Planet(3*(p.getWidth()/4),3*(p.getHeight()/4),-150,150,1500, Color.RED);
					p.screenx = 0;
					p.screeny = 0;
					
				}
				
				//Planet orbiting a Binary star system 
				if(arg0.getKeyCode() == KeyEvent.VK_9)
				{
					Planet.allPlanets = new <Planet> ArrayList();
					Planet pl = new Planet(300,300,300,300,5E+17,Color.RED);
					Planet g = new Planet(400,200,-300,-300,5E+17,Color.BLUE);
					Planet fl = new Planet(350,300, 50, -300, 1500, Color.GREEN);
					Planet gg = new Planet(3*(p.getWidth()/4),3*(p.getHeight()/4),-150,150,1500, Color.MAGENTA);
					p.screenx = 0;
					p.screeny = 0;
				}
				
				//binary star system
				if(arg0.getKeyCode() == KeyEvent.VK_0){
					Planet.allPlanets = new <Planet> ArrayList();
					Planet pl = new Planet(300,300,300,300,5E+17,Color.RED);
					Planet g = new Planet(400,200,-300,-300,5E+17,Color.BLUE);
					p.screenx = 0;
					p.screeny = 0;
				}
				
				//returning screen to orginal position
				if(arg0.getKeyCode() == KeyEvent.VK_CONTROL){
					p.screenx = 0;
					p.screeny = 0;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.validate();
	}
	
	public static void main( String [] args)
	{
		//Planet myPlanet = new Planet(0,0);
		//myPlanet.mass = 1;
//		Planet otherPlanet = new Planet(30, 40);
		//System.out.println(myPlanet.G);
//		otherPlanet.mass = 500;
		//System.err.println("angle " + myPlanet.angleTo(otherPlanet));
		//System.err.println("Force x: " + myPlanet.getForceX() + " Force Y: " + myPlanet.getForceY());
		Frame mine = new Frame();
		mine.p.repaint();
		//while( 4 == 4){
			//System.err.println("Force x: " + otherPlanet.getForceX() + " Force Y: " + otherPlanet.getForceY());
		//}
		
		
	}
}
