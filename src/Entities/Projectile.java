package Entities;

import java.awt.Graphics;

import Core.Game;

public class Projectile {
	Game game;
	private int x, y, xVel, yVel;
	private int movementSpeed = 3;
	private int ticks = 0;

	public Projectile(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
		if (game.mouseP.x > x) {
			xVel = movementSpeed * 2;
		} else {
			xVel = -movementSpeed * 2;
		}
	}

	public void tick() {
		yVel = -movementSpeed;

		applyMovement();
		applyFriction();
	}

	public void applyMovement() {
		x += xVel;
		y += yVel;
	}

	public void applyFriction() {
		if (ticks % 10 == 0) {
			if (xVel > 0) {
				xVel -= 1;
			} else if (xVel < 0) {
				xVel += 1;
			}
			ticks = 0;
		}

		ticks++;
	}

	public void render(Graphics g) {
		g.drawImage(game.getResMan().getProjectileImage(), x, y, game);
	}

	// Getters and Setters

	public int getxVel() {
		return xVel;
	}

	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	public int getyVel() {
		return yVel;
	}

	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	public int getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}
}