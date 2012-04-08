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

import java.awt.Insets;
import javax.swing.JFrame;


/**
 * The Tetrize main window.
 * 
 * @author Fabien LOSION
 */
public class TetrizeWindow extends JFrame {

	private Board board = null;

	/**
	 * The constructior of the TetrizeWindow class.
	 */
	public TetrizeWindow() {
		super();
		this.setTitle("Tetrize");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.board = new Board();
		this.board.loadResources();
		this.setContentPane(this.board);
		//Display the window
		this.setVisible(true);
		//Window's size
		Insets insets = this.getInsets();
		this.setSize(
			Game.WIDTH * Game.BLOCK_SIZE + insets.left + insets.right, //Width
			Game.HEIGHT * Game.BLOCK_SIZE + insets.top + insets.bottom //Height
			);
	}

	/**
	 * Get the Board.
	 * 
	 * @return the board instance.
	 */
	public Board getBoard() {
		return this.board;
	}
}