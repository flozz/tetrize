package com.flogisoft.tetrize;

import java.io.IOException;

import javax.swing.JFrame;

public class TetrizeWindow extends JFrame {

	private TetrizePanel tpanel = null;

	public TetrizeWindow() throws IOException {
		super();
		build();
	}

	private void build() throws IOException {
		this.setTitle("Tetrize");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.tpanel = new TetrizePanel();
		this.setContentPane(this.tpanel);

	}
}