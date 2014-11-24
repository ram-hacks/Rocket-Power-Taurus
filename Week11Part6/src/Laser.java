import java.awt.Color;

import processing.core.PApplet;

public class Laser{

	static final int LIFE_SPAN = 2000;
	
	Color color = Color.magenta;
	float lAngle;
	float lX = 0, lY = 0;
	float lVx = 1f, lVy = 1f;
	int lifeTime = 0;

	static PApplet theApp;
	public Laser(){}

	public Laser(float x, float y,float vx, float vy){
		lX = x;
		lY = y;
		lVx = vx;
		lVy = vy;
		lAngle = (float) Math.atan2(lVy,  lVx);
	}

	public void draw(){
		theApp.pushMatrix();
		theApp.translate(lX, lY);
		theApp.rotate(lAngle);
		theApp.fill(color.getRGB());
		theApp.scale(0.3f);
		theApp.rect(-10,-5, 20, 10);
		theApp.popMatrix();
	}

	public boolean update(int dt){
		lX += lVx;
		lY += lVy;
		lifeTime += dt;
		return (lifeTime < LIFE_SPAN);
	}

	public static void setApp(PApplet app){
		theApp = app;
	}
}