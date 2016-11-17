package edu.intesys.objects;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by JohnPaul on 10/24/2016.
 */
public class TicTacToeState extends MinimaxState {
	char[][] grid = new char[3][3];

	public TicTacToeState(State parent, char[][] grid, int maxDepth) {
		super(parent, null, maxDepth);

		this.grid = grid;
		this.scoreSet();
	}

	public TicTacToeState(String[] grid, int maxDepth) {
		super(null, null, maxDepth);

		for (int i = 0; i < grid.length; i++) {
			this.grid[i] = grid[i].toCharArray();
		}

		this.scoreSet();
	}

	public TicTacToeState(String[] grid) {
		super(null, null, -1);

		for (int i = 0; i < grid.length; i++) {
			this.grid[i] = grid[i].toCharArray();
		}

		this.scoreSet();
	}

	@Override
	public ArrayList<State> getNextStates() {
		ArrayList<State> nextStates = new ArrayList<>();
		char[][] newGrid = new char[3][];

		char ch = this.isMinNode() ? 'x' : 'o';

		for (int i = 0; i < grid.length; i++) {
			newGrid[i] = Arrays.copyOf(grid[i], grid[i].length);
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (newGrid[i][j] == '-') {
					newGrid[i][j] = ch;

					nextStates.add(new TicTacToeState(this, newGrid, this.getMaxDepth()));

					for (int k = 0; k < grid.length; k++) {
						newGrid[k] = Arrays.copyOf(grid[k], grid[k].length);
					}
				}
			}
		}

		return nextStates;
	}

	@Override
	public boolean equals(Object obj) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] != ((TicTacToeState) obj).grid[i][j])
					return false;
			}
		}

		return true;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void print() {
		for (char[] c : grid) {
			for (char c1 : c) {
				System.out.print(c);
			}
			System.out.println();
		}

		System.out.println();
	}

	@Override
	public void computeScore() {
		//TODO computations

		if (this.isMinNode()) {
			for (int i = 0; i < 3; i++) {
				if (grid[i][0] == 'x' && grid[i][1] == 'x' && grid[i][2] == 'x'
						|| grid[0][i] == 'x' && grid[1][i] == 'x' && grid[2][i] == 'x') {
					this.setScoreMinimax(-100);
					break;
				}

				if (grid[i][0] == 'o' && grid[i][1] == 'o' && grid[i][2] == 'o'
						|| grid[0][i] == 'o' && grid[1][i] == 'o' && grid[2][i] == 'o') {
					this.setScoreMinimax(100);
					break;
				}
			}


			if (grid[0][0] == 'x' && grid[1][1] == 'x' && grid[2][2] == 'x') {
				this.setScoreMinimax(-100);
			} else if (grid[0][0] == 'o' && grid[1][1] == 'o' && grid[2][2] == 'o') {
				this.setScoreMinimax(100);
			}


		} else {

		}
		this.propagateScore();
	}

	@Override
	public boolean isMinNode() {
		int xCount = 0;
		int oCount = 0;

		for (char[] chars : this.grid) {
			for (char aChar : chars) {
				if (aChar == 'x')
					xCount++;
				else if (aChar == 'o')
					oCount++;
			}
		}

		if (xCount == oCount)
			return false;
		else
			return true;
	}

	@Override
	public boolean isLeaf() { //kung panalo ka na or puno na yung board
		for (int i = 0; i < 3; i++) {
			if (grid[i][0] == 'x' && grid[i][1] == 'x' && grid[i][2] == 'x'
					|| grid[i][0] == 'o' && grid[i][1] == 'o' && grid[i][2] == 'o'
					|| grid[0][i] == 'x' && grid[1][i] == 'x' && grid[2][i] == 'x'
					|| grid[0][i] == 'o' && grid[1][i] == 'o' && grid[2][i] == 'o') {
				return true;
			}
		}

		if (grid[0][0] == 'x' && grid[1][1] == 'x' && grid[2][2] == 'x'
				|| grid[0][0] == 'o' && grid[1][1] == 'o' && grid[2][2] == 'o'
				|| grid[0][2] == 'x' && grid[1][1] == 'x' && grid[2][0] == 'x'
				|| grid[0][2] == 'o' && grid[1][1] == 'o' && grid[2][0] == 'o')
			return true;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '-')
					return false;
			}
		}

		return true;
	}
}
