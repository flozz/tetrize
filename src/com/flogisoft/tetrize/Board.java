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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * The drawable surface.
 * 
 * @author Fabien LOISON
 */
public class Board extends JPanel implements ActionListener {

	public static Image RES_BACKGROUND = null;
	public static Image RES_BLOCKS = null;

	/**
	 * The constructor.
	 */
	public Board() {
		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
	}

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
		//Draw the background
		g.drawImage(Board.RES_BACKGROUND, 0, 0, this);
		//Draw the blocks
        for (Block block : Game.blocks)
		{
			block.draw(g, this);
		}
        //Draw the tetromino
        if (Game.tetromino != null) {
        	Game.tetromino.draw(g, this);
        }
        g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Nothing to do
	}

	/**
	 * Handle the keyboard events.
	 */
	private class TAdapter extends KeyAdapter {

		/**
		 * Handle the KeyPress event.
		 */
		public void keyPressed(KeyEvent ev) {
			int key = ev.getKeyCode();

			if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
	            Game.tetromino.rotate();
	        }

			if (key == KeyEvent.VK_LEFT) {
	            Game.tetromino.moveLeft();
	        }

			if (key == KeyEvent.VK_RIGHT) {
	            Game.tetromino.moveRight();
	        }

			if (key == KeyEvent.VK_DOWN) {
	            Game.tetromino.moveDown();
	        }
		}
    }
}