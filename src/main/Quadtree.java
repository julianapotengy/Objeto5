package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Quadtree {
	
	int maxQnt = 4;
	Square square;
	ArrayList<Square> squares = new ArrayList<Square>();
	ArrayList<Quadtree> quadtrees = new ArrayList<Quadtree>();
	ArrayList<Particle> actualParticles = new ArrayList<Particle>();
	
	boolean isDivided = false;
	
	public Quadtree(Square square)
	{
		this.square = square;
	}
	
	public void Divide(ArrayList<Particle> particles)
	{
		int x = square.x;
		int y = square.y;
		int width = square.width;
		int height = square.height;
		
		squares.add(new Square(Math.round(x), Math.round(y), Math.round(width / 2), Math.round(height / 2)));
		squares.add(new Square(Math.round(x + width / 2), Math.round(y), Math.round(width / 2), Math.round(height / 2)));
		squares.add(new Square(Math.round(x), Math.round(y + height / 2), Math.round(width / 2), Math.round(height / 2)));
		squares.add(new Square(Math.round(x + width / 2), Math.round(y + height / 2), Math.round(width / 2), Math.round(height / 2)));
		
		for(int i = 0; i < squares.size(); i++)
		{
			quadtrees.add(new Quadtree(squares.get(i)));
		}
		
		for(int i = 0; i < quadtrees.size(); i++)
		{
			for(int j = 0; j < particles.size(); j++)
			{
				if(quadtrees.get(i).square.ContainsParticle(particles.get(j)))
				{
					squares.get(i).particles.add(particles.get(j));
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
				Divide(particles);
			}
		}
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
}
