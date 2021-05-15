package main;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Particle extends Canvas {
	
	int id, x, y, width, height, canvasSize, direction;
	float velocity = 1;
	
	public Particle(int id, int x, int y, int width, int height, int canvasSize)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.canvasSize = canvasSize;
		
		ChangeDirection();
	}
	
	public void Paint(Graphics g)
	{
		g.fillOval(x, y, width, height);
	}
	
	public void Move() 
	{
		CheckDirection();
		CheckCollisionWalls();
	}
	
	public void CheckCollisionWalls()
	{
		if(x >= (canvasSize - (width * 2))) // direita
		{
			while(direction == 0 || direction == 2 || direction == 3 || direction == 4  || direction == 5)
			{
				ChangeDirection();
			}
		}
		else if(x <= 0) // esquerda
		{
			while(direction == 1 || direction == 2 || direction == 3 || direction >= 6)
			{
				ChangeDirection();
			}
		}
		else if(y <= 0) // cima
		{
			while(direction <= 2 || direction == 4 || direction == 6)
			{
				ChangeDirection();
			}
		}
		else if(y >= (canvasSize - (height * 3.5f))) // baixo
		{
			while(direction <= 1 || direction == 3 || direction == 5 || direction == 7)
			{
				ChangeDirection();
			}
		}
	}
	
	public void ChangeDirection()
	{
		Random rand = new Random();
		direction = rand.nextInt(8);
	}
	
	public void CheckDirection()
	{
		if(direction == 0) // direita
		{
			x += velocity;
		}
		else if(direction == 1) // esquerda
		{
			x -= velocity;
		}
		else if(direction == 2) // cima
		{
			y -= velocity;
		}
		else if(direction == 3) // baixo
		{
			y += velocity;
		}
		else if(direction == 4) // diagonal direita cima
		{
			x += velocity;
			y -= velocity;
		}
		else if(direction == 5) // diagonal direita baixo
		{
			x += velocity;
			y += velocity;
		}
		else if(direction == 6) // diagonal esquerda cima
		{
			x -= velocity;
			y -= velocity;
		}
		else if(direction == 7) // diagonal esquerda baixo
		{
			x -= velocity;
			y += velocity;
		}
	}
}
