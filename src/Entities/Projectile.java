package Entities;

import java.awt.Graphics;

import Core.Game;

public class Projectile {
	Game game;
	int x, y, destX, destY;
	int movementSpeed = 1;

	public Projectile(Game game, int x, int y, int destinationX, int destinationY) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.destX = destinationX - (61 / 2);
		this.destY = destinationY - (60 / 2);
	}

	public void tick() {
		if (destX > x && destY < y) {
			x += movementSpeed;
			y -= movementSpeed;
		} else if (destX > x && destY > y) {
			x += movementSpeed;
			y += movementSpeed;
		} else if (destX < x && destY > y) {
			x -= movementSpeed;
			y += movementSpeed;
		} else if (destX < x && destY < y) {
			x -= movementSpeed;
			y -= movementSpeed;
		} else if (destX < x)
			x -= movementSpeed;
		else if (destX > x)
			x += movementSpeed;
		else if (destY < y)
			y -= movementSpeed;
		else if (destY > y)
			y += movementSpeed;
	}

	public void render(Graphics g) {
		g.drawImage(game.getResMan().getProjectileImage(), x, y, game);
	}
}