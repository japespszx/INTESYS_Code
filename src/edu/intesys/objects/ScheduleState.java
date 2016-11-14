package edu.intesys.objects;

import java.util.ArrayList;

/**
 * Created by JohnPaul on 10/12/2016.
 */
public class ScheduleState extends State {

	//TODO this thing men
	public ScheduleState(State parent, State goal) {
		super(parent, goal);
	}

	@Override
	public ArrayList<State> getNextStates() {
		return null;
	}

	@Override
	public int scoreSet() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	public void print() {

	}
}
