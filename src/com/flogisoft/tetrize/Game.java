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
		Game.blocks.add(new Block(Block.BLUE, 0, 0));
		Game.blocks.get(0).fall(10);
		Game.blocks.add(new Block(Block.GREEN, 1, 0));
		Game.blocks.get(1).fall(15);
		Game.blocks.add(new Block(Block.RED, 2, 0));
		Game.blocks.get(2).fall(5);
	}

	/**
	 * Apply the unavoidable gravity law on each blocks.
	 */
	public void applyGravity() {
		for (Block block : Game.blocks)
		{
			block.applyGravity();
		}
	}

}
