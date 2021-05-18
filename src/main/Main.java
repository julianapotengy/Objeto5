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
	private int canvasSizeX = canvasSize - 25;
	private int canvasSizeY = canvasSize - 45;
	
	private int maxParticles = 50;
	private ArrayList<Particle> allParticles = new ArrayList<Particle>(maxParticles);
	private int particleSize = 10;
	
	Random generator = new Random();
	
	Timer timer = new Timer(100, this);
	
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	public Main() {
		
		for(int i = 0; i < maxParticles; i++)
		{
			int x = generator.nextInt(canvasSizeX);
			int y = generator.nextInt(canvasSizeY);
			
			allParticles.add(new Particle(i, x, y, particleSize, particleSize, canvasSizeX, canvasSizeY));
		}
		
		setLayout (new BorderLayout ());
		setSize(canvasSize, canvasSize);
		setTitle("Canvas demo");
		add("Center", canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < maxParticles; i++)
		{
			allParticles.get(i).Move();
		}
		CheckParticlesCollision();
		canvas.repaint();
	}
	
	private void CheckParticlesCollision()
	{
		
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
	}
	
	private class MyCanvas extends Canvas {
		
		@Override
		public void paint(Graphics g)
		{
			for(int i = 0; i < maxParticles; i++)
			{
				allParticles.get(i).Paint(g);
			}
		}
	}
}
