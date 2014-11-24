


import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;


public class Week11Part2 extends PApplet implements ApplicationConstants {

	final Color DEEP_SPACE = new Color(5, 15, 20);

	float sX = 200, sY=400, sAngle = 0;
	float sVx = 0.1f, sVy = -0.2f, sVa = 0.1f;
	float sScale;
	int lastTime;

	Spaceship ship;
	ArrayList<Laser> bullets = new ArrayList<Laser>();
	public void setup() {
		size(800, 600);
		Spaceship.setApp(this);
		Laser.setApp(this);
		// scale down so that length is 10% of width 10% == 0.1
		sScale = 0.1f * width / Spaceship.SHIP_LENGTH;
		
		ship = new Spaceship(400,300,0,sScale);
		lastTime = millis();
	}

	public void draw() {
		background(DEEP_SPACE.getRed(), DEEP_SPACE.getGreen(), DEEP_SPACE.getBlue());
		
		int currentTime = millis();
		int dt = currentTime - lastTime;
		for(int k=bullets.size()-1; k>=0; k--){
			if (!bullets.get(k).update(dt)){
				bullets.remove(bullets.get(k));
			}
		}
		lastTime = currentTime;
		
		ship.moveSpaceship();

		
		drawScene();
		
		
		translate(-width,0);
		drawScene();
		
		translate(2*width,0);
		drawScene();
		
		translate(-width,0); // back to origin
		
		translate(0,-height);
		drawScene();
		
		translate(0,2*height);
		drawScene();

		// + corners
	}


	private void drawScene() {
		
		for(Laser beam: bullets){
			beam.draw();
		}
		
		ship.drawSpaceship();
	}


	public void mouseClicked() {
	}


	public void keyPressed() {
		// case of "special" keys
		if (key == CODED) {
			switch(keyCode) {
			case UP:
				ship.accelerate();
				break;

			case DOWN:
				fire();
				break;

			case LEFT:
				ship.decAngle();
				break;

			case RIGHT:
				ship.incAngle();
				break;

			case ALT:
				break;

			case CONTROL:
				break;

			case SHIFT:
				break;

			default:
				//do something (or ignore)
				break;
			}
		}

		switch(key) {
		case ' ':
			break;

		default:
			//do something (or ignore)
			break;
		}
	}

	public void fire(){
		bullets.add(ship.createLaser());
	}
	
}
