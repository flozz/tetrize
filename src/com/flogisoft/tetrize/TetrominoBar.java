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


/**
 * Bar Tetromino.
 * 
 * @author Fabien LOISON
 */
public class TetrominoBar extends Tetromino {

	/**
	 * The constructor.
	 */
	public TetrominoBar() {
		super();
		this.color = Block.PURPLE;
		this.tetromino = new int[][] {
				{0, 0, 0, 0,
				 0, 0, 0, 0,
				 1, 1, 1, 1,
				 0, 0, 0, 0},

				{0, 1, 0, 0,
				 0, 1, 0, 0,
				 0, 1, 0, 0,
				 0, 1, 0, 0},

				{0, 0, 0, 0,
				 0, 0, 0, 0,
				 1, 1, 1, 1,
				 0, 0, 0, 0},

				{0, 1, 0, 0,
				 0, 1, 0, 0,
				 0, 1, 0, 0,
				 0, 1, 0, 0}};
	}
}
