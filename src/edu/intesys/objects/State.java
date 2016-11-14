package edu.intesys.objects;

import java.util.ArrayList;

/**
 * Created by JohnPaul on 09/20/2016.
 */
public abstract class State {

	private State parent;
	private State goal;
	private int depth;
	private int score;

	public State(State parent, State goal) {
		this.parent = parent;
		this.goal = goal;
		if (this.parent == null)
			this.depth = 0;
		else
			this.depth = this.parent.depth + 1;
	}

	public static State getHeuristicState(ArrayList<State> exploreList) {
		State pick = exploreList.get(0);

		for (State s :
				exploreList) {
			if (s.score < pick.score)
				pick = s;
		}

		return pick;
	}

	public static State getAStarState(ArrayList<State> exploreList) {
		State pick = exploreList.get(0);

		for (State s :
				exploreList) {
			if (s.score + s.depth < pick.score + pick.depth)
				pick = s;
		}

		return pick;
	}

	public ArrayList<State> getBeamStates(int count) {
		ArrayList<State> nextList = this.getNextStates();
		ArrayList<State> beamedList = new ArrayList<>();

		for (int i = 0; i < count && !nextList.isEmpty(); i++) {
			State beam = nextList.get(0);

			for (int j = 1; j < nextList.size(); j++) {
				if (nextList.get(j).score < beam.score)
					beam = nextList.get(j);
			}

			beamedList.add(beam);
			nextList.remove(beam);
		}

		return beamedList;
	}

	public ArrayList<State> getHillClimbState() {
		ArrayList<State> nextList = getNextStates();
		ArrayList<State> maximaList = new ArrayList<>();

		State maximaState = nextList.get(0);

		for (int j = 1; j < nextList.size(); j++) {
			if (nextList.get(j).score < maximaState.score)
				maximaState = nextList.get(j);
		}

		maximaList.add(maximaState);

		return maximaList;
	}

	public int getDepth() {
		return depth;
	}

	public void setScore() {
		if (goal != null)
			this.score = scoreSet();
		else
			this.score = 0;
	}

	//lol rip design patterns
	public void setScoreMinimax(int score) {
		this.score = score;
	}

	public State getGoal() {
		return goal;
	}

	public State getParent() {
		return parent;
	}

	public abstract ArrayList<State> getNextStates();

	public abstract int scoreSet();

	public int getScore() {
		return score;
	}

	@Override
	public abstract boolean equals(Object obj);

	public abstract boolean isValid();

	public abstract void print();
}
