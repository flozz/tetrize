package com.flogisoft.tetrize;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TetrizePanel extends JPanel {

	private int BLOCK_SIZE = 32;

	private Image resBackground = null;
	private Image resTileset = null;

	public TetrizePanel() throws IOException {
		this.resBackground = ImageIO.read(getClass().getResourceAsStream(
				"/com/flogisoft/tetrize/res/background.png"));
		this.resTileset = ImageIO.read(getClass().getResourceAsStream(
				"/com/flogisoft/tetrize/res/tileset.png"));
	}

	public void paintComponent(Graphics g) {
        g.drawImage(this.resBackground, 0, 0, this);
        this.drawBlock(g, 0, 1, 1);
        this.drawBlock(g, 2, 0, 2);
        this.drawBlock(g, 4, 11, 19);
	}

	private void drawBlock(Graphics g, int blockID, int cellX, int cellY) {
		g.drawImage(
				this.resTileset, //Image
				cellX * this.BLOCK_SIZE, //From x pos (dest)
				cellY * this.BLOCK_SIZE, //From y pos (dest)
				cellX * this.BLOCK_SIZE + this.BLOCK_SIZE, //To x pos (dest)
				cellY * this.BLOCK_SIZE + this.BLOCK_SIZE, //To y pos (dest)
				blockID * this.BLOCK_SIZE, //From x pos (src)
				0, //From y pos(src)
				blockID * this.BLOCK_SIZE + this.BLOCK_SIZE, //To x pos (src)
				this.BLOCK_SIZE, //To y pos (src)
				this);
	}

}