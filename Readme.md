Readme for Space Gravity Simulator

This is a simple program that simulates gravity in space written in Java. The equation used to calculate gravity is Gmm/r^2 with G being the universal gravitation constant, m is the mass of one object in kg, and other m is the mass of the other object in kg, and r is the distance between the two objects. The program calculates the total gravity on a single object by calculating the force of gravity due to every other object in the x direction and the force of gravity due to every other object in  the y direction. All the values and calculations in the x and y directions are done separately. The program adds all these forces to calculate the acceleration and velocity
in each direction. 

Starting the Simulator
	To start the simulator run the GravitySimulator.jar file.


Doing Stuff in the Simulator
	Adding objects:
		1. Right click on the location you want to add the object
		2. In the pop up fill in the initial x velocity, initial y velocity, mass, and
                    choose a color.
		3. Press OK to create the object. 
		4. Press Cancel to cancel the creation process
Moving the Screen:
		- You can change the location of the screen by moving the mouse to any of the edges.
		- On the top left corner there are coordiante which indicate the the coordiante of the screen.
		- To return to the origin (where the screen is when the program is started) press the ctrl key.
		- On the top left there is also a compass that shows the sign of each direction:
			- Up is negative y
			- Down is positive y
			- Left is negative x
			- Right is positive x
	Interacting with Objects:
		- Left click on an object to receive velocity information and mass
	Pausing the Simulator:
		- Press space to pause the simulator
	Starting Presets:
		Presets are premade simulations with objects already placed in. 
		Start a preset by pressing either the 1,2,9, or 0 key.
			1. A one planet and one star system with a single planet (blue) orbiting a star (black)
			2. A two planets and one star system with two planets (red and blue) orbiting a star (black)
			9. A binary star sytem with two planets and two stars. (Stars: red and blue, Planets: Green and Magenta)
			0. A binary star system with two stars (red and blue).
	Taking a Screenshot:
		- Take a screenshot by pressing the f1 key.
		- Screenshots will be stored as <insertsomenumber>.png in the folder where the program is being run from. 
		** Screenshot functionality is only garunteed in windows. Screenshots in other operating systems (i.e. Linux) has caused some errors.
		   Errors aren't fatal so program will still run despite them, but screenshot might not work. 

Hotkeys:
	1. One star, one planet preset
	2. One star, two planets preset
	9. Two stars, two planets preset
	0. Two stars preset
	Ctrl. Return screen to starting position
	Space. Pause the game
	F1. Take a screenshot

	
