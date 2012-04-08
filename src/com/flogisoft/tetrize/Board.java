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
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * The drawable surface.
 * 
 * @author Fabien LOISON
 */
public class Board extends JPanel {

	public static Image RES_BACKGROUND = null;
	public static Image RES_BLOCKS = null;

	/**
	 * Loads the images resources from files.
	 */
	public void loadResources() {
		try {
			Board.RES_BACKGROUND = ImageIO.read(getClass().getResourceAsStream(
					"/com/flogisoft/tetrize/res/background.png"));
			Board.RES_BLOCKS = ImageIO.read(getClass().getResourceAsStream(
					"/com/flogisoft/tetrize/res/tileset.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Refresh the JPanel.
	 * 
	 * @param g the Graphics instance.
	 */
	public void paintComponent(Graphics g) {
        g.drawImage(this.RES_BACKGROUND, 0, 0, this);

        for (Block block : Game.blocks)
		{
			block.draw(g, this);
		}
	}
}