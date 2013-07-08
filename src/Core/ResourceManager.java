package Core;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ResourceManager {

	private BufferedImage platformImage, playerImageLeft, playerImageRight, backgroundImage;

	public ResourceManager(Game game) {
		loadImages();
	}

	public void loadImages() {
		try {
			setPlatformImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Images/Platform 400x150.png")));
			setPlayerImageLeft(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Images/head.png")));
			setPlayerImageRight(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Images/headflip.png")));
			setBackgroundImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Images/Background 800x600.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters and Setters

	public BufferedImage getPlatformImage() {
		return platformImage;
	}

	public void setPlatformImage(BufferedImage platform) {
		this.platformImage = platform;
	}

	public BufferedImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(BufferedImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public BufferedImage getPlayerImageLeft() {
		return playerImageLeft;
	}

	public void setPlayerImageLeft(BufferedImage playerImageLeft) {
		this.playerImageLeft = playerImageLeft;
	}

	public BufferedImage getPlayerImageRight() {
		return playerImageRight;
	}

	public void setPlayerImageRight(BufferedImage playerImageRight) {
		this.playerImageRight = playerImageRight;
	}
}