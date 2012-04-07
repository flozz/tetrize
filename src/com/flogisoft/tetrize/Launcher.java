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
import javax.swing.SwingUtilities;


/**
 * Home sweet home...
 * 
 * @author Fabien LOISON
 */
public class Launcher {

	/**
	 * The entry point of the program.
	 * 
	 * @param args no comment...
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			private TetrizeWindow window = null;

			public void run() {
				this.window  = new TetrizeWindow();
				this.window.setVisible(true);
				//Window's size
				Insets insets = this.window.getInsets();
				this.window.setSize(
						Game.WIDTH * Game.BLOCK_SIZE + insets.left + insets.right, //Width
						Game.HEIGHT * Game.BLOCK_SIZE + insets.top + insets.bottom //Height
						);
			}
		});
	}
}
