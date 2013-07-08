package Entities;

import java.awt.Graphics;

import Core.Game;

public class Player {

	// Class references
	Game game;

	// Size and Location
	private int x = 200, y = 250, width, height;

	// Movement
	private int movementSpeed = 10;
	private int gravity = 1;
	private int jumpHeight = -15;
	private int xVel, yVel;
	private boolean canJump = false;
	private boolean goLeft = false;

	public Player(Game game) {
		this.game = game;

		width = game.getResMan().getPlayerImageLeft().getWidth();
		height = game.getResMan().getPlayerImageLeft().getHeight();
	}

	public void tick() {
		scanMovementInput();
		applyMovement();
		applyGravity();
		applyFriction();
		checkBoundaries();
	}

	public void scanMovementInput() {
		if (game.getInputHandler().left.down) {
			xVel = -movementSpeed;
			goLeft = true;
		}
		if (game.getInputHandler().right.down) {
			xVel = movementSpeed;
			goLeft = false;
		}
		if (game.getInputHandler().up.down && canJump) {
			yVel = jumpHeight;
			canJump = false;
		}
	}

	public void applyMovement() {
		x += xVel;
		y += yVel;
	}

	public void checkBoundaries() {
		if (x < 0) {
			x = 0;
		}
		if (x > game.getWidth() - width) {
			x = game.getWidth() - width;
		}
	}

	public void applyFriction() {
		if (canJump) {
			if (xVel > 0) {
				xVel--;
			} else if (xVel < 0) {
				xVel++;
			}
		}
	}

	public void applyGravity() {
		if (y < game.getHeight() - height) {
			yVel += gravity;
		} else if (y >= game.getHeight() - height) {
			yVel = 0;
			canJump = true;
		}
	}

	public void render(Graphics g) {
		if (goLeft)
			g.drawImage(game.getResMan().getPlayerImageLeft(), x, y, game);
		if (!goLeft)
			g.drawImage(game.getResMan().getPlayerImageRight(), x, y, game);
	}

	// Getters and Setters

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

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

	public int getJumpHeight() {
		return jumpHeight;
	}

	public void setJumpHeight(int jumpHeight) {
		this.jumpHeight = jumpHeight;
	}
}