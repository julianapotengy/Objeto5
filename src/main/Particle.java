package main;

import java.awt.*;
import javax.swing.*;

public class Particle extends Canvas {
	
	int id, x, y, width, height;
	
	public Particle(int id, int x, int y, int width, int height)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void Paint(Graphics g)
	{
		g.fillOval(x, y, width, height);
	}
}
