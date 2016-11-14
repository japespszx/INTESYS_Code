package edu.intesys.objects;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by JohnPaul on 09/25/2016.
 */
public class HanoiTowerState extends State {
	private Stack<Integer> t1 = new Stack<>();
	private Stack<Integer> t2 = new Stack<>();
	private Stack<Integer> t3 = new Stack<>();

	public HanoiTowerState(State parent, Stack<Integer> t1, Stack<Integer> t2, Stack<Integer> t3, Stack<Integer> t4, Stack<Integer> t5, Stack<Integer> t6) {
		super(parent, new HanoiTowerState(t4, t5, t6));
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.setScore();
	}

	public HanoiTowerState(Stack<Integer> t1, Stack<Integer> t2, Stack<Integer> t3, Stack<Integer> t4, Stack<Integer> t5, Stack<Integer> t6) {
		super(null, new HanoiTowerState(t4, t5, t6));
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.setScore();
	}

	public HanoiTowerState(Stack<Integer> t1, Stack<Integer> t2, Stack<Integer> t3) {
		super(null, null);
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}

	@Override
	public ArrayList<State> getNextStates() {
		ArrayList<State> nextList = new ArrayList<>();
		Stack<Integer> temp1 = clone(t1);
		Stack<Integer> temp2 = clone(t2);
		Stack<Integer> temp3 = clone(t3);

		if (!t1.isEmpty()) {
			if (!t2.isEmpty()) {
				if (t1.peek() < t2.peek()) {
					temp2.push(temp1.pop());
					nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
					temp1 = clone(t1);
					temp2 = clone(t2);
					temp3 = clone(t3);
				}
			} else {
				temp2.push(temp1.pop());
				nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
				temp1 = clone(t1);
				temp2 = clone(t2);
				temp3 = clone(t3);
			}
			if (!t3.isEmpty()) {
				if (t1.peek() < t3.peek()) {
					temp3.push(temp1.pop());
					nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
					temp1 = clone(t1);
					temp2 = clone(t2);
					temp3 = clone(t3);
				}
			} else {
				temp3.push(temp1.pop());
				nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
				temp1 = clone(t1);
				temp2 = clone(t2);
				temp3 = clone(t3);
			}
		}

		if (!t2.isEmpty()) {
			if (!t1.isEmpty()) {
				if (t2.peek() < t1.peek()) {
					temp1.push(temp2.pop());
					nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
					temp1 = clone(t1);
					temp2 = clone(t2);
					temp3 = clone(t3);
				}
			} else {
				temp1.push(temp2.pop());
				nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
				temp1 = clone(t1);
				temp2 = clone(t2);
				temp3 = clone(t3);
			}
			if (!t3.isEmpty()) {
				if (t2.peek() < t3.peek()) {
					temp3.push(temp2.pop());
					nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
					temp1 = clone(t1);
					temp2 = clone(t2);
					temp3 = clone(t3);
				}
			} else {
				temp3.push(temp2.pop());
				nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
				temp1 = clone(t1);
				temp2 = clone(t2);
				temp3 = clone(t3);
			}
		}

		if (!t3.isEmpty()) {
			if (!t1.isEmpty()) {
				if (t3.peek() < t1.peek()) {
					temp1.push(temp3.pop());
					nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
					temp1 = clone(t1);
					temp2 = clone(t2);
					temp3 = clone(t3);
				}
			} else {
				temp1.push(temp3.pop());
				nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
				temp1 = clone(t1);
				temp2 = clone(t2);
				temp3 = clone(t3);
			}
			if (!t2.isEmpty()) {
				if (t3.peek() < t2.peek()) {
					temp2.push(temp3.pop());
					nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
					temp1 = clone(t1);
					temp2 = clone(t2);
					temp3 = clone(t3);
				}
			} else {
				temp2.push(temp3.pop());
				nextList.add(new HanoiTowerState(this, temp1, temp2, temp3, ((HanoiTowerState) this.getGoal()).t1, ((HanoiTowerState) this.getGoal()).t2, ((HanoiTowerState) this.getGoal()).t3));
				temp1 = clone(t1);
				temp2 = clone(t2);
				temp3 = clone(t3);
			}
		}

		return nextList;
	}

	@Override
	public int scoreSet() {
		int score = 0;

		score += Math.abs(((HanoiTowerState) this.getGoal()).t1.size() - this.t1.size());
		score += Math.abs(((HanoiTowerState) this.getGoal()).t2.size() - this.t2.size());
		score += Math.abs(((HanoiTowerState) this.getGoal()).t3.size() - this.t3.size());

		return score;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HanoiTowerState) {
			HanoiTowerState hs = (HanoiTowerState) obj;
			if (hs.t1.equals(this.t1) && hs.t2.equals(this.t2) && hs.t3.equals(this.t3))
				return true;
		}

		return false;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void print() {
		StringBuilder sb = new StringBuilder();

		for (int i = t1.size() - 1; i >= 0; i--) {
			sb.append(t1.get(i));
			if (i != 0)
				sb.append(',');
		}
		sb.append(" | ");

		for (int i = t2.size() - 1; i >= 0; i--) {
			sb.append(t2.get(i));
			if (i != 0)
				sb.append(',');
		}
		sb.append(" | ");

		for (int i = t3.size() - 1; i >= 0; i--) {
			sb.append(t3.get(i));
			if (i != 0)
				sb.append(',');
		}

		System.out.println(sb);
	}

	private Stack<Integer> clone(Stack<Integer> t) {
		Stack<Integer> temp = new Stack<>();

		temp.addAll(t);

		return temp;
	}
}
