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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


/**
 * Contains all the variables of a game.
 * 
 * @author Fabien LOSION
 */
public class Game {

	final static public int WIDTH = 12; //Cell
	final static public int HEIGHT = 20; //Cell
	final static public int BLOCK_SIZE = 32; //Pixels

	public static List<Block> blocks = new ArrayList<Block>();
	public static Tetromino tetromino = null;

	public Board board;

	/**
	 * The constructor.
	 * 
	 * @param board the Board instance.
	 */
	public Game(Board board) {
		this.board = board;

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new RefreshNUpdate(this), 100, 20);

		//FIXME =================================================================
		for (int i=0 ; i<12 ; i++) {
			Game.blocks.add(new Block(i%7, i, i));
			Game.blocks.get(i).fall(42);
		}
		Game.tetromino = new TetrominoT();
	}

	/**
	 * Apply the unavoidable gravity law on each blocks.
	 */
	public void applyGravity() {
		//Tetromino
		Game.tetromino.applyGravity();
		//Blocks
		for (Block block : Game.blocks)
		{
			block.applyGravity();
		}
	}

	/**
	 * Get the block at the (X, Y) position.
	 * 
	 * @return the instance of the block or null.
	 */
	static public Block getBlock(int x, int y) {
		for (Block block : Game.blocks)
		{
			if (block.getX() == x && block.getY() == y) {
				return block;
			}
		}
		return null;
	}

	/**
	 * Select the next tetromino.
	 */
	static public void nextTetromino() {
		//FIXME
		Game.tetromino = new TetrominoT();
	}
}
