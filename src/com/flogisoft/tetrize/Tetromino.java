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
import java.util.Random;


/**
 * The base class for tetrominoes.
 * 
 * @author Fabien LOISON
 */
public class Tetromino {

	//Tetromino's color
	protected int color = Block.PURPLE;

	//Tetromino and rotation
	protected int tetromino[][] = {
		{0, 0, 0, 0,
		 0, 0, 0, 0,
		 0, 0, 0, 0,
		 0, 0, 0, 0},

		{0, 0, 0, 0,
		 0, 0, 0, 0,
		 0, 0, 0, 0,
		 0, 0, 0, 0},

		{0, 0, 0, 0,
		 0, 0, 0, 0,
		 0, 0, 0, 0,
		 0, 0, 0, 0},

		{0, 0, 0, 0,
		 0, 0, 0, 0,
		 0, 0, 0, 0,
		 0, 0, 0, 0}};
	protected int rotation = 0;

	//Tetromino's position
	protected int posX = (Game.WIDTH - 4) / 2;
	protected int posY = -4;
	protected int offset = 0;

	/**
	 * The constructor.
	 */
	public Tetromino() {
		Random randomGenerator = new Random();
		this.rotation = randomGenerator.nextInt(4);
	}

	/**
	 * Rotate the tetromino.
	 */
	public void rotate() {
		this.rotation = (this.rotation + 1) % 4;
		if (this.checkBorderLeftCollide()) {
			this.posX += 1;
			return;
		}
		if (this.checkBorderRightCollide()) {
			this.posX -= 1;
		}
		if (this.checkCollide(0, 0, 0)) {
			this.rotation = (this.rotation - 1) % 4;
		}
	}

	/**
	 * Draws the tetromino's block on the Board.
	 * 
	 * @param g the Graphics instance.
	 * @param board the board
	 */
	public void draw(Graphics g, Board board) {
		for (int y=0 ; y<4 ; y++) {
			for (int x=0 ; x<4 ; x++) {
				if (this.tetromino[this.rotation][y*4+x] == 1) {
					g.drawImage(
						Board.RES_BLOCKS,                                     //Source image
						(this.posX + x) * Game.BLOCK_SIZE,                    //From x pos (dest)
						(this.posY + y) * Game.BLOCK_SIZE + this.offset,      //From y pos (dest)
						(this.posX + x) * Game.BLOCK_SIZE + Game.BLOCK_SIZE,  //To x pos (dest)
						(this.posY + y) * Game.BLOCK_SIZE + Game.BLOCK_SIZE + this.offset,  //To y pos (dest)
						this.color * Game.BLOCK_SIZE,                         //From x pos (src)
						0,                                                    //From y pos(src)
						this.color * Game.BLOCK_SIZE + Game.BLOCK_SIZE,       //To x pos (src)
						Game.BLOCK_SIZE,                                      //To y pos (src)
						board);
				}
			}
		}
	}

	/**
	 * Move the tetromino to the left.
	 */
	public void moveLeft() {
		this.posX -= 1;
		if (this.checkBorderLeftCollide() || this.checkCollide(0, 1, 0)) {
			this.posX += 1;
		}
	}

	/**
	 * Move the tetromino to the right.
	 */
	public void moveRight() {
		this.posX += 1;
		if (this.checkBorderRightCollide() || this.checkCollide(0, 1, 0)) {
			this.posX -= 1;
		}
	}

	/**
	 * Move the tetromino to the bottom.
	 */
	public void moveDown() {
		this.posY += 1;
		this.offset = 0;
		if (this.checkCollide(0, 1, 0)) {
			this.copyBlocks();
			Game.checkLines();
			Game.checkGameOver();
			Game.nextTetromino();
		}
	}

	/**
	 * Apply the gravity law.
	 */
	public void applyGravity() {
		//Falling...
		this.offset += Game.speed;
		if (this.offset >= Game.BLOCK_SIZE) {
			this.offset = 0;
			this.posY += 1;
			if (this.checkCollide(0, 1, 0)) {
				this.copyBlocks();
				Game.checkLines();
				Game.checkGameOver();
				Game.nextTetromino();
			}
		}
		//TODO Check collide
	}

	/**
	 * Check if there is a collision between the tetromino and the left border.
	 * 
	 * @return true if there is a collide.
	 */
	private boolean checkBorderLeftCollide() {
		if (this.posX >= 0) {
			return false;
		}

		for (int y=0 ; y<4 ; y++) {
			for (int x=0 ; x<0-this.posX ; x++) {
				if (this.tetromino[this.rotation][y*4+x] == 1) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Check if there is a collision between the tetromino and the right border.
	 * 
	 * @return true if there is a collide.
	 */
	private boolean checkBorderRightCollide() {
		if (this.posX <= Game.WIDTH-4) {
			return false;
		}

		for (int y=0 ; y<4 ; y++) {
			for (int x=4+(Game.WIDTH-this.posX-4) ; x<4 ; x++) {
				if (this.tetromino[this.rotation][y*4+x] == 1) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Check for collision.
	 * 
	 * @param incX X increment
	 * @param incY Y increment
	 * @param incRot rotation increment
	 * 
	 * @return true if there is a collide.
	 */
	private boolean checkCollide(int incX, int incY, int incRot) {
		for (int y=0 ; y<4 ; y++) {
			for (int x=0 ; x<4 ; x++) {
				if (this.tetromino[(this.rotation+incRot)%4][y*4+x] == 1) {
					if (Game.getBlock(this.posX+x+incX, this.posY+y+incY) instanceof Block) {
						return true;
					}
					if (this.posY+y+incY >= Game.HEIGHT) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Copy the blocks of the tetromino as stand alone blocks in the main list.
	 */
	private boolean copyBlocks() {
		for (int y=0 ; y<4 ; y++) {
			for (int x=0 ; x<4 ; x++) {
				if (this.tetromino[this.rotation][y*4+x] == 1) {
					Game.blocks.add(new Block(this.color, this.posX+x, this.posY+y));
				}
			}
		}
		return false;
	}
}