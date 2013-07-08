package Level;

import java.awt.Graphics;
import java.awt.Rectangle;

import Core.Game;

public class Platform {

	// Class references
	Game game;

	// Size and location
	private int x, y, width, height;

	// Rectangle for collision
	private Rectangle collRect;

	public Platform(Game game) {
		this.game = game;

		width = 400;
		height = 150;
		x = 0;
		y = game.getHeight() - this.height;

		setCollRect(new Rectangle(x, y, width, height));
	}

	public void render(Graphics g) {
		g.drawImage(game.getResMan().getPlatformImage(), x, y, game);
	}

	// Getters and Setters

	public Rectangle getCollRect() {
		return collRect;
	}

	public void setCollRect(Rectangle collRect) {
		this.collRect = collRect;
	}
}