////////////////////////////////////////////////////////////////////////////
//                                                                        //
// Tetrize - An implementation of the famous Tetris game, in Java.        //
//                                                                        //
// Copyright (C) 2012  Fabien LOISON <flo at flogisoft dot com>           //
//                                                                        //
// This program is free software: you can redistribute it and/or modify   //
// it under the terms of the GNU General Public License as published by   //
// the Free Software Foundation, either version 3 of the License, or      //
// (at your option) any later version.                                    //
//                                                                        //
// This program is distributed in the hope that it will be useful,        //
// but WITHOUT ANY WARRANTY; without even the implied warranty of         //
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          //
// GNU General Public License for more details.                           //
//                                                                        //
// You should have received a copy of the GNU General Public License      //
// along with this program.  If not, see <http://www.gnu.org/licenses/>.  //
//                                                                        //
////////////////////////////////////////////////////////////////////////////


package com.flogisoft.tetrize;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * The drawable surface.
 * 
 * @author Fabien LOISON
 */
public class Board extends JPanel implements ActionListener {

	public static Image RES_BACKGROUND = null;
	public static Image RES_BLOCKS = null;
	public static Image RES_DIGITS = null; //15x19 px
	public static Image RES_GAMEOVER = null;
	public static Image RES_PAUSE = null;

	/**
	 * The constructor.
	 */
	public Board() {
		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
	}

	/**
	 * Loads the images resources from files.
	 */
	public void loadResources() {
		try {
			Board.RES_BACKGROUND = ImageIO.read(getClass().getResourceAsStream(
					"/com/flogisoft/tetrize/res/background.png"));
			Board.RES_BLOCKS = ImageIO.read(getClass().getResourceAsStream(
					"/com/flogisoft/tetrize/res/tileset.png"));
			Board.RES_DIGITS = ImageIO.read(getClass().getResourceAsStream(
					"/com/flogisoft/tetrize/res/digits.png"));
			Board.RES_GAMEOVER = ImageIO.read(getClass().getResourceAsStream(
					"/com/flogisoft/tetrize/res/gameover.png"));
			Board.RES_PAUSE = ImageIO.read(getClass().getResourceAsStream(
					"/com/flogisoft/tetrize/res/pause.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Refresh the JPanel.
	 * 
	 * @param g the Graphics instance.
	 */
	public void paintComponent(Graphics g) {
		//Draw the background
		g.drawImage(Board.RES_BACKGROUND, 0, 0, this);

		if (!Game.paused) {
			//Draw the blocks
			for (Block block : Game.blocks)
			{
				block.draw(g, this);
			}
			//Draw the tetromino
			if (Game.tetromino != null) {
				Game.tetromino.draw(g, this);
			}
			if (Game.nextTetromino != null) {
				Game.nextTetromino.drawAsNext(g, this);
			}
		}
		//Pause
		if (Game.paused) {
			g.drawImage(Board.RES_PAUSE, 0, 0, this);
		}
		//Game over
		if (Game.gameover) {
			g.drawImage(Board.RES_GAMEOVER, 0, 0, this);
		}
		//Speed
		this.draw_number(Game.speed, 544, 210, g);
		//Score
		this.draw_number(Game.score, 544, 279, g);
		//
		g.dispose();
	}

	/**
	 * Draw a number on the board.
	 * 
	 * @param numb the number to draw.
	 * @param x the X position (in pixel, right).
	 * @param y the y position (in pixel, top).
	 * @param g the Graphics instance.
	 */
	public void draw_number(int numb, int x, int y, Graphics g) {
		int offset = 15;
		do {
			g.drawImage(
				Board.RES_DIGITS,      //Source image
				x-offset,              //From x pos (dest)
				y,                     //From y pos (dest)
				x-offset+15,           //To x pos (dest)
				y+19,                  //To y pos (dest)
				15 * (numb % 10),      //From x pos (src)
				0,                     //From y pos(src)
				15 * (numb % 10) + 15, //To x pos (src)
				19,                    //To y pos (src)
				this);
			numb /= 10;
			offset += 15;
		} while (numb > 0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Nothing to do
	}

	/**
	 * Handle the keyboard events.
	 */
	private class TAdapter extends KeyAdapter {

		/**
		 * Handle the KeyPress event.
		 */
		public void keyPressed(KeyEvent ev) {
			//Get the key code
			int key = ev.getKeyCode();

			//Game is over ?
			if (Game.gameover) {
				if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
					Game.newGame();
				}
				return;
			}

			//Toggle pause
			if (key == KeyEvent.VK_ESCAPE) {
				Game.paused = ! Game.paused;
			}

			//Stop here if the game is paused
			if (Game.paused) {
				return;
			}

			//Move the tetromino :D
			if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
				Game.tetromino.rotate();
			}

			if (key == KeyEvent.VK_LEFT) {
				Game.tetromino.moveLeft();
			}

			if (key == KeyEvent.VK_RIGHT) {
				Game.tetromino.moveRight();
			}

			if (key == KeyEvent.VK_DOWN) {
				Game.tetromino.moveDown();
			}

		}
	}
}
