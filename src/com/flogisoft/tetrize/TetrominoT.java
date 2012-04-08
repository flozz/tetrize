package com.flogisoft.tetrize;

public class TetrominoT extends Tetromino {

	public TetrominoT() {
		super();
		this.color = Block.GREEN;
		this.tetromino = new int[][] {
				{1, 1, 1, 0,
				 0, 1, 0, 0,
				 0, 0, 0, 0,
				 0, 0, 0, 0},

				{0, 0, 1, 0,
				 0, 1, 1, 0,
				 0, 0, 1, 0,
				 0, 0, 0, 0},

				{0, 1, 0, 0,
				 1, 1, 1, 0,
				 0, 0, 0, 0,
				 0, 0, 0, 0},

				{0, 1, 0, 0,
				 0, 1, 1, 0,
				 0, 1, 0, 0,
				 0, 0, 0, 0}};
	}
}
