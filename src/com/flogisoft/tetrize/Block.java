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
 * A Tetromino base block.
 * 
 * @author Fabien LOISON
 */
public class Block {

	//Some const
	final public static int PURPLE = 0;
	final public static int PINK = 1;
	final public static int RED = 2;
	final public static int NAVY = 3;
	final public static int BLUE = 4;
	final public static int GREEN = 5;
	final public static int ORANGE = 6;

	//Color of the block
	private int color;

	//Position of the block
	private int posX; //Cell
	private int posY; //Cell

	/**
	 * The constructor of the Block class.
	 * 
	 * @param color the block's color (Block.PURPLE, Block.PINK,...)
	 * @param posX the X position of the block (in cell)
	 * @param posY the Y position of the block (in cell)
	 */
	public Block(int color, int posX, int posY) {
		this.color = color;
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * Draws the block on the Board.
	 * 
	 * @param g the Graphics instance.
	 * @param board the board
	 */
	public void draw(Graphics g, Board board) {
		g.drawImage(
			Board.RES_BLOCKS,                               //Source image
			this.posX * Game.BLOCK_SIZE,                    //From x pos (dest)
			this.posY * Game.BLOCK_SIZE,                    //From y pos (dest)
			this.posX * Game.BLOCK_SIZE + Game.BLOCK_SIZE,  //To x pos (dest)
			this.posY * Game.BLOCK_SIZE + Game.BLOCK_SIZE,  //To y pos (dest)
			this.color * Game.BLOCK_SIZE,                   //From x pos (src)
			0,                                              //From y pos(src)
			this.color * Game.BLOCK_SIZE + Game.BLOCK_SIZE, //To x pos (src)
			Game.BLOCK_SIZE,                                //To y pos (src)
			board);
	}
}
