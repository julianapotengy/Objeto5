package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Main extends JFrame implements ActionListener {

	private MyCanvas canvas = new MyCanvas();
	private int canvasSize = 700;
	private int canvasSizeX = canvasSize - 24;
	private int canvasSizeY = canvasSize - 44;
	
	private int maxParticles = 1000;
	private ArrayList<Particle> allParticles = new ArrayList<Particle>(maxParticles);
	private int particleSize = 2;
	
	Random generator = new Random();
	
	Timer timer = new Timer(100, this);
	
	Square initialSquare = new Square(0, 0, canvasSizeX, canvasSizeY);
	Quadtree initialQuadtree = new Quadtree(initialSquare);
	
	boolean quadtree = true;
	
	long startTime;
	long elapsedTime;
	
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	public Main() {
		
		for(int i = 0; i < maxParticles; i++)
		{
			int x = generator.nextInt(canvasSizeX - particleSize);
			int y = generator.nextInt(canvasSizeY - particleSize);
			
			allParticles.add(new Particle(i, x, y, particleSize, particleSize, canvasSizeX, canvasSizeY));
		}
		
		setLayout (new BorderLayout ());
		setSize(canvasSize, canvasSize);
		setTitle("Álan, Fernanda e Juliana");
		add("Center", canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		timer.start();
		
		initialQuadtree.Insert(allParticles);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < maxParticles; i++)
		{
			allParticles.get(i).Move();
			if(allParticles.get(i).collided) 
			{
				allParticles.get(i).timerChangeColor++;
			}
			if(allParticles.get(i).timerChangeColor >= 4) 
			{
				allParticles.get(i).timerChangeColor = 0;
				allParticles.get(i).collided = false;
			}
		}
		
		if(quadtree) 
		{
			initialQuadtree.CheckQntInTimer();
		}
		else 
		{
			CheckParticlesCollision();
		}

		canvas.repaint();
	}
	
	private void CheckParticlesCollision()
	{
		//long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < maxParticles; i++)
		{
			Particle particleA = allParticles.get(i);
			
			for(int j = i + 1; j < maxParticles; j++)
			{
				Particle particleB = allParticles.get(j);
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
		//long elapsedTime = System.currentTimeMillis() - startTime;
		//System.out.println(elapsedTime);
	}
	
	private class MyCanvas extends Canvas {
		
		@Override
		public void paint(Graphics g)
		{
			if (startTime != 0) 
			{
				elapsedTime = System.currentTimeMillis() - startTime;
				System.out.println(elapsedTime);
			}
			
			startTime = System.currentTimeMillis();
			
			for(int i = 0; i < maxParticles; i++)
			{
				allParticles.get(i).Paint(g);
			}
			if (quadtree) 
			{
				initialQuadtree.Paint(g);
			}
		}
	}
}
