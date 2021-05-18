package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Quadtree {
	
	int maxQnt = 20;
	Square square;
	ArrayList<Square> squares = new ArrayList<Square>();
	ArrayList<Quadtree> quadtrees = new ArrayList<Quadtree>();
	ArrayList<Particle> actualParticles = new ArrayList<Particle>();
	ArrayList<Particle> allParticles = new ArrayList<Particle>();
	ArrayList<Particle> tempParticles = new ArrayList<Particle>();
	
	boolean isDivided = false;
	
	long startTime;
	long elapsedTime;
	
	public Quadtree(Square square)
	{
		this.square = square;
	}
	
	public void Divide()
	{
		int x = square.x;
		int y = square.y;
		int width = square.width;
		int height = square.height;
		
		squares.add(new Square(x, y, width / 2, height / 2));
		squares.add(new Square(x + width / 2, y, width / 2, height / 2));
		squares.add(new Square(x, y + height / 2, width / 2, height / 2));
		squares.add(new Square(x + width / 2, y + height / 2, width / 2, height / 2));
		
		for(int i = 0; i < squares.size(); i++)
		{
			quadtrees.add(new Quadtree(squares.get(i)));
		}
		
		for(int i = 0; i < quadtrees.size(); i++)
		{
			for(int j = 0; j < allParticles.size(); j++)
			{
				if(quadtrees.get(i).square.ContainsParticle(allParticles.get(j)))
				{
					quadtrees.get(i).square.particles.add(allParticles.get(j));
				}
			}
			quadtrees.get(i).Insert(quadtrees.get(i).square.particles);
		}
		
		isDivided = true;
	}
	
	public void Insert(ArrayList<Particle> particles)
	{
		if(particles.size() <= maxQnt)
		{
			actualParticles = particles;
		}
		else
		{
			if(!isDivided)
			{
				allParticles = particles;
				Divide();
			}
		}
	}
	
	public void Change() 
	{
		squares = new ArrayList<Square>();
		quadtrees = new ArrayList<Quadtree>();
		actualParticles = new ArrayList<Particle>();
		tempParticles = new ArrayList<Particle>();
	}
	
	public void CheckQntInTimer() 
	{
		if(isDivided) 
		{
			for(int i = 0; i < quadtrees.size(); i++)
			{
				quadtrees.get(i).CheckQntInTimer();
			}
			for(int i = 0; i < allParticles.size(); i++)
			{
				if(square.ContainsParticle(allParticles.get(i)))
				{
					tempParticles.add(allParticles.get(i));
				}
			}
			if(tempParticles != actualParticles) 
			{
				Change();
				isDivided = false;
				Insert(allParticles);
			}
		}
		CheckParticlesCollision();
	}
	
	public void Paint(Graphics g)
	{		
		for(int i = 0; i < squares.size(); i++)
		{
			squares.get(i).Paint(g);
		}
		if(isDivided)
		{
			for(int i = 0; i < quadtrees.size(); i++)
			{
				quadtrees.get(i).Paint(g);
			}
		}
	}
	
	public void CheckParticlesCollision() 
	{
		if (actualParticles.size() > 1) 
		{
			for(int i = 0; i < actualParticles.size(); i++)
			{
				Particle particleA = actualParticles.get(i);
				
				for(int j = i + 1; j < actualParticles.size(); j++)
				{
					Particle particleB = actualParticles.get(j);
					if(particleB.x < (particleA.x + particleA.width) && particleA.x < (particleB.x + particleB.width) 
							&& particleB.y < (particleA.y + particleA.height) && particleA.y < (particleB.y + particleB.height))
					{
						particleA.ChangeColor();
						particleB.ChangeColor();
						
						if (particleA.velocityX != particleB.velocityX) 
						{
							particleA.ChangeDirectionX();
							particleB.ChangeDirectionX();
						}
						if (particleA.velocityY != particleB.velocityY) 
						{
							particleA.ChangeDirectionY();
							particleB.ChangeDirectionY();
						}
					}
				}
			}
		}
	}
}
