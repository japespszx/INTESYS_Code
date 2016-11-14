package edu.intesys.objects;

import java.util.ArrayList;

/**
 * Created by JohnPaul on 09/28/2016.
 */
public class EightQueensState extends State {
	private ArrayList<Integer> queen;

	public EightQueensState(State parent, State goal, int pos) {
		super(parent, goal);
		queen = new ArrayList<>(((EightQueensState) parent).queen);
		queen.add(pos);
	}

	public EightQueensState(boolean x) {
		//random bool lol, unused. i just need this for non-null goal.
		super(null, null);
		queen = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			queen.add(0);
		}
	}

	public EightQueensState() {
		super(null, new EightQueensState(true));
		queen = new ArrayList<>();
	}

	@Override
	public ArrayList<State> getNextStates() {
		ArrayList<State> nextList = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			nextList.add(new EightQueensState(this, this.getGoal(), i));
		}
		return nextList;
	}

	@Override
	public int scoreSet() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isGoal = true;

		if (this.queen.size() != ((EightQueensState) obj).queen.size()) {
			return false;
		} else {
			if (((EightQueensState) obj).queen.size() != 8) {
				isGoal = false;
			} else {
				for (int i = 0; i < 8; i++) {
					if (((EightQueensState) obj).queen.get(i) != 0) {
						isGoal = false;
						break;
					}
				}
			}
		}

		if (isGoal)
			return true;

		for (int i = 0; i < this.queen.size(); i++) {
			if (this.queen.get(i) != ((EightQueensState) obj).queen.get(i))
				return false;
		}

		return true;
	}

	@Override
	public boolean isValid() {
		if (queen.size() == 1)
			return true;

		int pos = queen.get(queen.size() - 1);
		for (int i = 1; i <= queen.size() - 1; i++) {
			if (pos != queen.get(i - 1) - i && pos != queen.get(i - 1) && pos != queen.get(i - 1) + i) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void print() {
		System.out.println(queen.get(queen.size() - 1));
	}
}
