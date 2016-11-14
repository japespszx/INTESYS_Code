package edu.intesys.objects;

import java.util.ArrayList;

/**
 * Created by JohnPaul on 09/25/2016.
 */
public class SlidingPuzzleState extends State {
	private String puzzle;

	public SlidingPuzzleState(State parent, String puzzle, String goal) {
		super(parent, new SlidingPuzzleState(goal));
		this.puzzle = puzzle;
		this.setScore();
	}

	public SlidingPuzzleState(String puzzle, String goal) {
		super(null, new SlidingPuzzleState(goal));
		this.puzzle = puzzle;
		this.setScore();
	}

	public SlidingPuzzleState(String puzzle) {
		super(null, null);
		this.puzzle = puzzle;
	}

	public String getPuzzle() {
		return puzzle;
	}

	@Override
	public ArrayList<State> getNextStates() {
		ArrayList<State> nextList = new ArrayList<>();
		int num = 0;

		for (int i = 0; i < puzzle.length(); i++) {
			if (puzzle.charAt(i) == '0') {
				num = i;
				break;
			}
		}

		//123.740.865
		//012
		//345
		//678
		switch (num) {
			case 0:
				nextList.add(new SlidingPuzzleState(this, swap(0, 1), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(0, 4), ((SlidingPuzzleState) this.getGoal()).puzzle));
				break;
			case 1:
				nextList.add(new SlidingPuzzleState(this, swap(1, 0), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(1, 2), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(1, 4), ((SlidingPuzzleState) this.getGoal()).puzzle));
				break;
			case 2:
				nextList.add(new SlidingPuzzleState(this, swap(2, 1), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(2, 5), ((SlidingPuzzleState) this.getGoal()).puzzle));
				break;
			case 3:
				nextList.add(new SlidingPuzzleState(this, swap(3, 0), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(3, 4), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(3, 6), ((SlidingPuzzleState) this.getGoal()).puzzle));
				break;
			case 4:
				nextList.add(new SlidingPuzzleState(this, swap(4, 1), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(4, 3), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(4, 5), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(4, 7), ((SlidingPuzzleState) this.getGoal()).puzzle));
				break;
			case 5:
				nextList.add(new SlidingPuzzleState(this, swap(5, 2), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(5, 4), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(5, 8), ((SlidingPuzzleState) this.getGoal()).puzzle));
				break;
			case 6:
				nextList.add(new SlidingPuzzleState(this, swap(6, 3), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(6, 7), ((SlidingPuzzleState) this.getGoal()).puzzle));
				break;
			case 7:
				nextList.add(new SlidingPuzzleState(this, swap(7, 4), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(7, 6), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(7, 8), ((SlidingPuzzleState) this.getGoal()).puzzle));
				break;
			case 8:
				nextList.add(new SlidingPuzzleState(this, swap(8, 5), ((SlidingPuzzleState) this.getGoal()).puzzle));
				nextList.add(new SlidingPuzzleState(this, swap(8, 7), ((SlidingPuzzleState) this.getGoal()).puzzle));
				break;
		}

		return nextList;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SlidingPuzzleState) {
			if (((SlidingPuzzleState) obj).puzzle.equals(this.puzzle))
				return true;
		}
		return false;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public int scoreSet() {
		int score = 0;
		for (int i = 0; i < puzzle.length(); i++) {
			if (puzzle.charAt(i) != ((SlidingPuzzleState) this.getGoal()).puzzle.charAt(i)) {
				score++;
			}
		}
		return score;
	}

	@Override
	public void print() {
		System.out.println(puzzle.substring(0, 3));
		System.out.println(puzzle.substring(3, 6));
		System.out.println(puzzle.substring(6, 9));
		System.out.println();
	}

	private String swap(int a, int b) {
		char[] carr = puzzle.toCharArray();
		char tmp = carr[a];
		carr[a] = carr[b];
		carr[b] = tmp;
		return new String(carr);
	}
}
