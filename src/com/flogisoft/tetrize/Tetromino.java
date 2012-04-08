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
	protected int posY = 4; //FIXME
	protected int offset = 0;

	/**
	 * Rotate the tetromino.
	 */
	public void rotate() {
		this.rotation = (this.rotation + 1) % 4;
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
}