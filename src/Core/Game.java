package Core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Entities.Player;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	// Other Classes
	private ResourceManager resMan;
	private Player player;
	private InputHandler inputHandler;

	Thread GameThread;

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	public Point mouseP = new Point(-1, -1);

	public static boolean running = false;
	public static final String TITLE = "Game: The Game -InDev-";
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static final Dimension gameDim = new Dimension(WIDTH, HEIGHT);
	JFrame frame;

	public int xOffset = 0;
	public int yOffset = 0;

	// Variables for the FPS and UPS counter
	private int ticks = 0;
	private int frames = 0;
	private int FPS = 0;
	private int UPS = 0;
	public double delta;

	// Used in the "run" method to limit the frame rate to the UPS
	boolean limitFrameRate = false;
	boolean shouldRender;

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		long lastTimer = System.currentTimeMillis();
		delta = 0D;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			// If you want to limit frame rate, shouldRender = false
			shouldRender = false;

			// If the time between ticks = 1, then various things (shouldRender = true, keeps FPS locked at UPS)
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			if (!limitFrameRate && ticks > 0)
				shouldRender = true;

			// If you should render, render!
			if (shouldRender) {
				frames++;
				render();
			}

			// Reset stuff every second for the new "FPS" and "UPS"
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				FPS = frames;
				UPS = ticks;
				frames = 0;
				ticks = 0;
				frame.setTitle(TITLE + " FPS: " + FPS + " UPS: " + UPS);
			}
		}
	}

	public synchronized void start() {
		running = true;
		GameThread = new Thread(this);
		GameThread.start();
	}

	public static synchronized void stop() {
		running = false;
		System.exit(0);
	}

	public Game() { // Typical stuff
		setMinimumSize(gameDim);
		setMaximumSize(gameDim);
		setPreferredSize(gameDim);
		frame = new JFrame(TITLE + " FPS: " + FPS + " UPS: " + UPS);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		requestFocus();

		init();
	}

	private void init() {
		setResMan(new ResourceManager(this));
		setInputHandler(new InputHandler(this));
		player = new Player(this);
	}

	public void tick() {
		player.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(getResMan().getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);

		player.render(g);

		g.dispose();
		bs.show();
	}

	// Getters and Setters

	public ResourceManager getResMan() {
		return resMan;
	}

	public void setResMan(ResourceManager resMan) {
		this.resMan = resMan;
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}
}