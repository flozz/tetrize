package com.flogisoft.tetrize;

import java.awt.Insets;
import java.io.IOException;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			private TetrizeWindow window = null;

			public void run() {
				//TetrizeWindow window;
				try {
					this.window  = new TetrizeWindow();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.window.setVisible(true);
				//Window's size
				Insets insets = this.window.getInsets();
				this.window.setSize(
						532 + insets.left + insets.right, //Width
						640 + insets.top + insets.bottom  //Height
						);
			}
		});
	}
}
