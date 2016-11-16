package edu.intesys.objects;

import java.util.ArrayList;

/**
 * Created by John Paul San Pedro on 06/11/2016.
 */
public abstract class MinimaxState extends State {
	private int numChildren = 0;
	private int maxDepth;
	private boolean isPruned;

	public MinimaxState(State parent, State goal, int maxDepth) {
		super(parent, goal);
		this.maxDepth = maxDepth;
		this.isPruned = false;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public boolean isPruned() {
		return isPruned;
	}

	public void prune() {
		isPruned = true;
	}

	@Override

	public ArrayList<State> getNextStates() {
		return null;
	}

	public abstract void computeScore();

	@Override
	public int scoreSet() {
		if (this.isMinNode())
			return Integer.MAX_VALUE; //+infinity
		else { // if max node
			return Integer.MIN_VALUE; //-infinity
		}
	}

	public void propagateScore() {
		if (numChildren == 0 && this.getParent() != null) {
			((MinimaxState) this.getParent()).submitScore(this);
		}
	}

	public void submitScore(State child) {
		if (this.isMinNode()) {
			this.setScoreMinimax(Math.min(this.getScore(), child.getScore()));
		} else {
			this.setScoreMinimax(Math.max(this.getScore(), child.getScore()));
		}

		numChildren--;
		propagateScore();
	}

	public abstract boolean isMinNode();

	public abstract boolean isLeaf();
}
