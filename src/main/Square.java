package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Square {
	
	int x, y, width, height;
	ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public Square(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void Paint(Graphics g)
	{
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}
	
	public boolean ContainsParticle(Particle particle)
	{
		boolean contains = (particle.x + particle.width > x && particle.x < x + width && particle.y + particle.height > y && particle.y < y + height);
		return contains;
	}
}
