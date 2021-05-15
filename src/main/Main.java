package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Main extends JFrame {

	private MyCanvas canvas = new MyCanvas();
	private int canvasSize = 700;
	
	private int maxParticles = 10;
	private ArrayList<Particle> allParticles = new ArrayList<Particle>(maxParticles);
	private int particleSize = 15;
	
	Random generator = new Random();
	
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	public Main() {
		setLayout (new BorderLayout ());
		setSize(canvasSize, canvasSize);
		setTitle("Canvas demo");
		add("Center", canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	private class MyCanvas extends Canvas {
		@Override
		public void paint(Graphics g) {
			for(int i = 0; i < maxParticles; i++)
			{
				int x = generator.nextInt(canvasSize - (particleSize * 2));
				int y = generator.nextInt(canvasSize - (particleSize * 2));
				
				allParticles.add(new Particle(i, x, y, particleSize, particleSize));
				allParticles.get(i).Paint(g);
			}
		}
	}
}
