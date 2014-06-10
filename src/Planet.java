import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;


public class Planet {
	double x,y;
	Panel p;
	final double G = 6.67*Math.pow(10,-11);
	boolean clicked; 
	double forceX;
	double forceY;
	double vx;
	double vy;
	double ax;
	double ay;
	double mass;
	Color color;
	double ticInit; //= 0;
	double ticNow; //= 0; 
	double scale = 1496000;
	double timerTime = 2;
	ArrayList<Point> myPoints;
	
	static ArrayList<Planet> allPlanets = new ArrayList<Planet>();
	boolean isVisible;
	public Planet(int x, int y)
	{
		clicked = false; 
		this.x = x;
		this.y = y;
		ticInit = System.currentTimeMillis();
		ticNow = 0;
		//this.p = p;
		ax = 0;
		ay = 0;
		forceX = 0;
		forceY = 0;
		this.color = Color.black;
		allPlanets.add(this);
		isVisible = true;
		myPoints = new ArrayList<Point>();
	}
	
	public Planet(int x, int y, double vx, double vy, double mass, Color c)
	{
		this.x = x;
		this.y = y;
		this.p = p;
		this.vx = vx;
		this.vy = vy;
		ax = 0;
		ay = 0;
		forceX = 0;
		forceY = 0;
		this.mass = mass;
		color = c; 
		allPlanets.add(this);
		isVisible = true;
		myPoints = new ArrayList<Point>();
	}
	
	public double angleTo(Planet p)
	{
		double dx = p.x - x;
		double dy = p.y - y;
		//System.out.println(dx + ", " + dy);
		double angle = 0;
		if(dy < 0)
		{
			//System.out.println("yaaaay");
			if(dx < 0)
			{
				angle = Math.PI - Math.atan(Math.abs(dy/dx));
				angle = Math.toDegrees(angle);
			}
			else if(dx > 0){
				angle = Math.atan(Math.abs(dy/dx));
				angle = Math.toDegrees(angle);
			}
			else if(dx == 0){
				angle = 90;
			}
		}
		else if(dy > 0){
			if(dx < 0){
				angle = Math.PI + Math.atan(Math.abs(dy/dx));
				angle = Math.toDegrees(angle);
			}
			else if(dx > 0){
				//System.out.println("Actually yay");
				//System.out.println(angle);
				angle = (2*Math.PI) - Math.atan(Math.abs(dy/dx));
				angle = Math.toDegrees(angle);
				
			}
			else if( dx == 0)
			{
				angle = 270;
			}
		}
		else if( dy == 0){
			if(dx < 0){
				angle = 180;
			}
			if(dx > 0){
				angle = 0;
			}
		}
		return angle;
	}
	
	public double euclidDistance(Planet P)
	{
		double dx = P.x-this.x;
		double dy = P.y-this.y;
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy,2));
	}
	public double getForceX()
	{
		forceX = 0;
		for(Planet p : allPlanets)
		{
			if(p.equals(this)){
				//System.out.println("I am this");
				continue;
			}	
			double force = G * (double)mass * ((double)p.mass)/Math.pow(euclidDistance(p),2);
			//System.out.println(force);
			forceX += force*Math.cos(Math.toRadians(angleTo(p)));
		}	
		
		return forceX;
	}
	
	public double getForceY()
	{
		forceY = 0;
		for(Planet p : allPlanets)
		{
			if(p.equals(this)){
				//System.out.println("I am this");
				continue;
			}	
			double force = -G * (double)mass * ((double)p.mass)/Math.pow(euclidDistance(p),2);
		//	System.out.println("Other planet mass " + (double)p.mass);
		//	System.out.println(force);
			forceY += force*Math.sin(Math.toRadians(angleTo(p)));
		}	
		
		return forceY;
	}
	
	public void tic()
	{
		//Effects of collision (add momentum and masses together)
//		for(int i = 0; i < allPlanets.size(); i++){
//			if(allPlanets.get(i) != this ){
//				if(Math.round(x - allPlanets.get(i).x) <=10 && Math.round(y-allPlanets.get(i).y) <= 10){
//					int newx = (int) x;
//					int newy = (int) y;
//					// (m1v1 + m2v2)/(m1+m2) = v3
//					double newvx = (mass*vx + allPlanets.get(i).mass*allPlanets.get(i).vx)/(mass + allPlanets.get(i).mass);
//					double newvy = (mass*vy + allPlanets.get(i).mass*allPlanets.get(i).vy)/(mass + allPlanets.get(i).mass);
//					Color toBe;
//					if(mass > allPlanets.get(i).mass)
//						toBe = color;
//					else
//						toBe = allPlanets.get(i).color;
//					Planet newP = new Planet(newx, newy, newvx, newvy, mass+allPlanets.get(i).mass, toBe);
//					allPlanets.remove(this);
//					allPlanets.remove(allPlanets.get(i));
//					// 	allPlanets.add(newP);
//				}
//			}	
//		}
		//x = xi + vt + .5at^2
		ticInit = ticNow;
		ticNow = System.currentTimeMillis();
		//if(/*ticNow - ticInit*/ 0 == 0 ){
			myPoints.add(new Point((int) Math.round(x), (int) Math.round(y)));
			ax = getForceX()/mass;
			ay = getForceY()/mass;
			x = (x + vx*(timerTime/1000) + .5*ax*Math.pow((timerTime/1000), 2));
			y = (y + vy*(timerTime/1000) + .05*ay*Math.pow(timerTime/1000,2));
			vx = vx + ax*(timerTime/1000);
			vy = vy + ay*(timerTime/1000);	
//		}
//		else
//		{
//			
//			double timeDiff = ticNow - ticInit; 
//			System.out.println("Time Diff is: " + timeDiff/1000);
//			myPoints.add(new Point((int) Math.round(x), (int) Math.round(y)));
//			ax = getForceX()/mass;
//			ay = getForceY()/mass;
//			x = (x + vx*(timeDiff/1000) + .5*ax*Math.pow((timeDiff/1000), 2));
//			y = (y + vy*(timeDiff/1000) + .05*ay*Math.pow(timeDiff/1000,2));
//			vx = vx + ax*(timeDiff/1000);
//			vy = vy + ay*(timeDiff/1000);	
//		}
	}
	
}
