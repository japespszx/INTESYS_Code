package edu.intesys.objects;

import java.util.ArrayList;

/**
 * Created by JohnPaul on 09/21/2016.
 */
public class WSState extends State {
	private char boatPos;
	private int sL;
	private int wL;

	public WSState(State parent, State goal, char boatPos, int sL, int wL) {
		super(parent, goal);
		this.boatPos = boatPos;
		this.sL = sL;
		this.wL = wL;
	}

	public WSState(State goal, char boatPos, int sL, int wL) {
		super(null, goal);
		this.boatPos = boatPos;
		this.sL = sL;
		this.wL = wL;
	}

	public WSState(char boatPos, int sL, int wL) {
		super(null, null);
		this.boatPos = boatPos;
		this.sL = sL;
		this.wL = wL;
	}

	@Override
	public int scoreSet() {
		if (this.getGoal() == null)
			return 0;
		else
			return 0; //dont return 0 here next time lol
	}

	public ArrayList<State> getNextStates() {
		ArrayList<State> nextList = new ArrayList<>();
		char newBoatPos;

		switch (boatPos) {
			case 'L':
				newBoatPos = 'R';

				// move two wolves to the right
				if (wL > 1) {
					nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL, wL - 2));
				}

				//move one wolf to the right
				if (wL >= 1) {
					nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL, wL - 1));
				}

				// move two sheep to the right
				if (sL > 1) {
					nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL - 2, wL));
				}

				//move one sheep to the right
				if (sL >= 1) {
					nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL - 1, wL));
				}

				//move one sheep and one wolf to the right
				if (sL >= 1 && wL >= 1) {
					nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL - 1, wL - 1));
				}

				break;
			case 'R':
				newBoatPos = 'L';

				if (wL < 3) {
					//move one wolf to the left
					nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL, wL + 1));

					//move two wolves to the left
					if (wL < 2) {
						nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL, wL + 2));
					}
				}

				if (sL < 3) {
					//move one sheep to the left
					nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL + 1, wL));

					//move two sheep to the left
					if (sL < 2) {
						nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL + 2, wL));
					}
				}

				//move one wolf and one sheep to the left
				if (wL < 3 && sL < 3) {
					nextList.add(new WSState(this, this.getGoal(), newBoatPos, sL + 1, wL + 1));
				}

				break;
		}

		return nextList;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WSState) {
			WSState s = (WSState) obj;
			if (s.boatPos == this.boatPos && s.sL == this.sL && s.wL == this.wL)
				return true;
		}

		return false;
	}

	public boolean isValid() {
		if (this.boatPos == 'L') {
			if (this.wL < this.sL && this.sL != 3)
				return false;
		} else {
			if (this.wL > this.sL && this.sL != 0)
				return false;
		}

		return true;
	}

	public void print() {
		System.out.println("Boat:        " + boatPos);
		System.out.println("Wolves on L: " + wL + "\tWolves on R: " + (3 - wL));
		System.out.println("Sheep on L:  " + sL + "\tSheep on R:  " + (3 - sL));
		System.out.println();
	}
}
