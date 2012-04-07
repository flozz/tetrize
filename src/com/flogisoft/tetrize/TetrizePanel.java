package com.flogisoft.tetrize;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TetrizePanel extends JPanel {

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
        this.drawBlock(g, Block.BLUE, 1, 1);
        this.drawBlock(g, Block.GREEN, 0, 2);
        this.drawBlock(g, Block.RED, 11, 19);
	}

	private void drawBlock(Graphics g, int blockColor, int cellX, int cellY) {
		g.drawImage(
				this.resTileset,                             //Source image
				cellX * Game.BLOCK_SIZE,                     //From x pos (dest)
				cellY * Game.BLOCK_SIZE,                     //From y pos (dest)
				cellX * Game.BLOCK_SIZE + Game.BLOCK_SIZE,   //To x pos (dest)
				cellY * Game.BLOCK_SIZE + Game.BLOCK_SIZE,   //To y pos (dest)
				blockColor * Game.BLOCK_SIZE,                   //From x pos (src)
				0,                                           //From y pos(src)
				blockColor * Game.BLOCK_SIZE + Game.BLOCK_SIZE, //To x pos (src)
				Game.BLOCK_SIZE,                             //To y pos (src)
				this);
	}

}