import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;


public class Spaceship implements ApplicationConstants{
	


	
	final float MAX_SPEED = .8f;
	final float LASER_SPEED = 4.0f;
	private float sX,sY, sAngle, sScale;
	float sVx = 0.1f, sVy = -0.1f, sVa = 0.5f;
	
	

	
	static PApplet theApp;
	public Spaceship(float x, float y, float angle, float scale){
		sX = x;
		sY = y;
		sAngle = angle;
		sScale = scale;
	}
	
	public void draw() {
		drawSpaceship();
	}

	void drawSpaceship() {

		theApp.pushMatrix();
		theApp.translate(sX, sY);
		
		theApp.rotate(sAngle);
		theApp.scale(sScale);

		// I draw the "bounding circle" of the spaceship: make sure that the stroke always looks about 
		// 2 pixels thick, regardless of the scale applied
		theApp.strokeWeight(2/sScale);
		theApp.noFill();
		theApp.stroke(255, 0, 0);
		theApp.ellipse(0, 0, SHIP_LENGTH, SHIP_LENGTH);
		

		

		theApp.noStroke();
		// oops, my drawing origin was not at the center of the spaceship.  I have to add a small offset
		theApp.translate(0, -45);

		// draw left and right engines
		drawEngine(-60, 80);
		drawEngine(40, 80);

		// draw the hull
		theApp.fill(HULL.getRed(), HULL.getGreen(), HULL.getBlue());
		theApp.beginShape();
		theApp.vertex(0, -80);
		theApp.vertex(-100, 120);
		theApp.vertex(0, 80);
		theApp.vertex(100, 120);
		theApp.endShape(theApp.CLOSE);

		// draw the cockpit
		theApp.fill(COCKPIT.getRed(), COCKPIT.getGreen(), COCKPIT.getBlue());
		theApp.arc(0, 0, 40, 60, theApp.PI, 2*theApp.PI);
		

		theApp.popMatrix();
	}

	void drawEngine(float ulX, float ulY) {
		theApp.noStroke();
		theApp.fill(ENGINE.getRed(), ENGINE.getGreen(), ENGINE.getBlue());
		theApp.pushMatrix();
		theApp.translate(ulX, ulY);
		theApp.rect(0, 0, 20, 40);
		theApp.beginShape();
		theApp.vertex(5, 39);
		theApp.vertex(0, 60);
		theApp.vertex(20, 60);
		theApp.vertex(15, 39);
		theApp.endShape(theApp.CLOSE);
		theApp.popMatrix();
	}


	void moveSpaceship() {
		sX += sVx;
		sY += sVy;
		//sAngle += sVa;
		
		if(sX <=0){
			sX = theApp.width - 1;
		}
		
		if(sX >= theApp.width){
			sX = 0;
		}
		
		if(sY <=0){
			sY = theApp.height - 1;
		}
		
		if(sY >= theApp.height){
			sY = 0;
		}
	}

	public static void setApp(PApplet app) {
		theApp = app;
		
	}

	public void decAngle() {
		sAngle -= sVa;
		
	}

	public void incAngle() {
		sAngle += sVa;
		
	}

	public void accelerate() {
		sVx += 0.1f*theApp.sin(sAngle);
		sVy -= 0.1f*theApp.cos(sAngle);
		
		if(sVx >= MAX_SPEED){
			sVx = MAX_SPEED;
		}
		if(sVy <= -MAX_SPEED){
			sVy = -MAX_SPEED;
		}
		System.out.println(sVx + " ||" + sVy);
		
	}

	public float getAngle() {
		
		return sAngle;
	}

	public float getY() {
		
		return sY;
	}

	public float getX() {
		
		return sX;
	}
	

	public Laser createLaser() {
		return new Laser(	(float) (sX + SHIP_LENGTH/5*Math.sin(sAngle)),
							(float) (sY - SHIP_LENGTH/5*Math.cos(sAngle)),
							(float) (sVx + LASER_SPEED*Math.sin(sAngle)),
							(float) (sVy - LASER_SPEED*Math.cos(sAngle)));
	}
	
	



}
