package main;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Particle extends Canvas {
	
	int id, x, y, width, height, canvasSizeX, canvasSizeY;
	float velocityX, velocityY;
	boolean collided = false;
	int timerChangeColor = 0;
	
	public Particle(int id, int x, int y, int width, int height, int canvasSizeX, int canvasSizeY)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.canvasSizeX = canvasSizeX;
		this.canvasSizeY = canvasSizeY;
		
		Random rand = new Random();
		while (velocityX == 0 && velocityY == 0)
		{
			velocityX = rand.nextInt(3) - 1;
			velocityY = rand.nextInt(3) - 1;
		}
	}
	
	public void Paint(Graphics g)
	{
		Graphics g2D = (Graphics2D) g;
		
		g2D.setColor(GetColor());
		g2D.fillOval(x, y, width, height);
	}
	
	public void Move() 
	{
		x += velocityX;
		y += velocityY;
		CheckCollisionWalls();
	}
	
	public Color GetColor()
	{
		if(!collided)
		{
			return Color.black;
		}
		else return Color.blue;
	}
	
	public void ChangeColor()
	{
		if(!collided) 
		{
			collided = true;
		}else 
		{
			if (timerChangeColor > 0) 
			{
				timerChangeColor = 0;
				collided = false;
			}
		}
	}
	
	public void CheckCollisionWalls()
	{
		if(x >= canvasSizeX || x <= 0)
		{
			ChangeDirectionX();
		}
		if(y <= 0 || y >= canvasSizeY)
		{
			ChangeDirectionY();
		}
	}
	
	public void ChangeDirectionX()
	{
		velocityX *= -1;
	}
	public void ChangeDirectionY()
	{
		velocityY *= -1;
	}
}
