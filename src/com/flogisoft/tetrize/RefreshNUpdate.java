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

import java.util.TimerTask;


/**
 * Update the blocks and refresh the screen.
 * 
 * @author Fabien LOISON
 */
public class RefreshNUpdate extends TimerTask {

	private Game game;

	/**
	 * The constructor.
	 * 
	 * @param game the Game instance.
	 */
	public RefreshNUpdate(Game game) {
		super();
		this.game = game;
	}

	/**
	 * All the actions to do.
	 */
	public void run() {
		this.game.applyGravity();
		this.game.board.repaint();
	}

}
