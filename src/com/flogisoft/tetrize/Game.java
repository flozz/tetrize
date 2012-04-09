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
import java.util.Random;
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

	public static int speed = 1;
	private static int nSpeed = 10;

	public static boolean gameover = false;

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

		Game.nextTetromino();
	}

	/**
	 * Apply the unavoidable gravity law on each blocks.
	 */
	public void applyGravity() {
		if (Game.gameover) {
			return;
		}
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
		for (Block block : Game.blocks) {
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
		Random randomGenerator = new Random();
		int nextPiece = randomGenerator.nextInt(7);

		switch (nextPiece) {
		case 0:
			Game.tetromino = new TetrominoBar();
			break;
		case 1:
			Game.tetromino = new TetrominoT();
			break;
		case 2:
			Game.tetromino = new TetrominoSquared();
			break;
		case 3:
			Game.tetromino = new TetrominoL();
			break;
		case 4:
			Game.tetromino = new TetrominoMirroredL();
			break;
		case 5:
			Game.tetromino = new TetrominoS();
			break;
		case 6:
			Game.tetromino = new TetrominoZ();
			break;
		}
	}

	/**
	 * Check if there is full lines on the board.
	 */
	static public void checkLines() {
		int countBlocks[] = new int[Game.HEIGHT];
		for (Block block : Game.blocks) {
			if (block.getY() < 0) {
				continue;
			}
			countBlocks[block.getY()] += 1;
		}

		for (int i=0 ; i<Game.HEIGHT ; i++) {
			if (countBlocks[i] == Game.WIDTH) {
				removeLine(i);
				if (Game.speed < Game.BLOCK_SIZE-1) {
					Game.nSpeed -= 1;
					if (Game.nSpeed <= 0) {
						Game.nSpeed = 10;
						Game.speed += 1;
					}
				}
			}
		}
	}

	/**
	 * Check for a Game Over.
	 */
	static public void checkGameOver() {
		for (Block block : Game.blocks) {
			if (block.getY() <= 0) {
				Game.gameover = true;
			}
		}
	}

	/**
	 * Removes a line and makes falling the blocks that have to fall :).
	 * 
	 * @param line the line number.
	 */
	static private void removeLine(int line) {
		List<Block> newBlocks = new ArrayList<Block>();
		for (Block block : Game.blocks) {
			if (block.getY() < line) {
				block.fall(1);
			}
			if (block.getY() != line) {
				newBlocks.add(block);
			}
		}
		Game.blocks = newBlocks;
	}
}
