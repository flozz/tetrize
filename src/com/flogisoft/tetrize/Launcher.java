package com.flogisoft.tetrize;

import java.awt.Insets;
import java.io.IOException;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			private TetrizeWindow window = null;

			public void run() {
				try {
					this.window  = new TetrizeWindow();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
