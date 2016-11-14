package edu.intesys.objects;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by JohnPaul on 10/12/2016.
 */
public class MastermindState extends State {
	private ArrayList<Character> num;

	public MastermindState(String goalNum) {
		// lambda expr. copy pasted from http://stackoverflow.com/questions/15331637/convert-string-to-arraylist-character-in-java
		super(null, new MastermindState(null, null, new ArrayList<Character>(goalNum.chars().mapToObj(e -> (char) e).collect(Collectors.toList()))));
		num = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			this.num.add('x');
		}
	}

	public MastermindState(State parent, State goal, ArrayList<Character> num) {
		super(parent, goal);
		this.num = num;
		this.setScore();
	}

	public ArrayList<Character> getNum() {
		return num;
	}

	@Override
	public ArrayList<State> getNextStates() {
		ArrayList<State> nextList = new ArrayList<>();
		for (int i = 0; i < num.size(); i++) {
			if (num.get(i) == 'x') {
				for (int j = 0; j < 10; j++) {
					ArrayList<Character> newNum = ((ArrayList<Character>) num.clone());
					newNum.remove(i);
					newNum.add(i, Integer.toString(j).charAt(0));
					nextList.add(new MastermindState(this, this.getGoal(), newNum));
				}
				break;
			}
		}
		return nextList;
	}

	@Override
	public int scoreSet() {
		int score = 0;
		for (int i = 0; i < num.size(); i++) {
			if (num.get(i) == 'x')
				score += 10;
			else
				score += Math.abs(Integer.parseInt(((MastermindState) this.getGoal()).getNum().get(i).toString()) - Integer.parseInt(num.get(i).toString()));
		}

		return score;
	}

	@Override
	public boolean equals(Object obj) {
		for (int i = 0; i < num.size(); i++) {
			if (num.get(i) != ((MastermindState) obj).num.get(i))
				return false;
		}
		return true;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void print() {
		num.forEach(System.out::print);
		System.out.println();
	}
}
