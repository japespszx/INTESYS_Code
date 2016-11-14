package edu.intesys.objects;

import java.util.ArrayList;

/**
 * Created by John Paul San Pedro on 10/3/2016.
 */
public class MazeState extends State {
	private char[][] map;
	private Coordinates charCoords;
	private ArrayList<Coordinates> gems = new ArrayList<>();

	public MazeState(State parent, State goal, char[][] map, int charx, int chary, ArrayList<Coordinates> gems) {
		super(parent, goal);

		this.map = map;
		this.gems = new ArrayList<>(gems);
		this.charCoords = new Coordinates(charx, chary);
		if (this.gems.contains(charCoords)) {
			this.gems.remove(charCoords);
			if (charx == 1 && chary == 5) {
				System.out.println();
			}
			System.out.println(charx + " " + chary);
		}
		this.setScore();
	}

	public MazeState(String[] rows) {
		super(null, new MazeState(MazeState.constGoal(rows)));

		this.map = new char[rows.length][rows[0].length()];

		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < rows[0].length(); j++) {
				map[i][j] = rows[i].charAt(j);
			}
		}

		int[] ch = MazeState.constChar(rows);

		this.charCoords = new Coordinates(ch[0], ch[1]);

		map[charCoords.getY()][charCoords.getX()] = '-';

		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < rows[0].length(); j++) {
				if (map[i][j] == 'O') {
					gems.add(new Coordinates(j, i));
				}
			}
		}

		for (Coordinates c :
				gems) {
			map[c.getY()][c.getX()] = '-';
		}

		this.setScore();
	}

	public MazeState(int[] goal) {
		super(null, null);

		this.charCoords = new Coordinates(goal[0], goal[1]);
	}

	private static int[] constGoal(String[] row) {
		int[] goal = new int[2];
		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < row[0].length(); j++) {
				if (row[i].charAt(j) == 'G') {
					goal[0] = j;
					goal[1] = i;
				}
			}
		}

		return goal;
	}

	private static int[] constChar(String[] row) {
		int[] ch = new int[2];
		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < row[0].length(); j++) {
				if (row[i].charAt(j) == 'S') {
					ch[0] = j;
					ch[1] = i;
				}
			}
		}

		return ch;
	}

	@Override
	public ArrayList<State> getNextStates() {
		ArrayList<State> nextList = new ArrayList<>();

		if (this.charCoords.getX() + 1 <= map[0].length - 1 && map[this.charCoords.getY()][this.charCoords.getX() + 1] != 'W')
			nextList.add(new MazeState(this, this.getGoal(), this.map, this.charCoords.getX() + 1, this.charCoords.getY(), gems));

		if (this.charCoords.getX() - 1 >= 0 && map[this.charCoords.getY()][this.charCoords.getX() - 1] != 'W')
			nextList.add(new MazeState(this, this.getGoal(), this.map, this.charCoords.getX() - 1, this.charCoords.getY(), gems));

		if (this.charCoords.getY() + 1 <= map.length - 1 && map[this.charCoords.getY() + 1][this.charCoords.getX()] != 'W')
			nextList.add(new MazeState(this, this.getGoal(), this.map, this.charCoords.getX(), this.charCoords.getY() + 1, gems));

		if (this.charCoords.getY() - 1 >= 0 && map[this.charCoords.getY() - 1][this.charCoords.getX()] != 'W')
			nextList.add(new MazeState(this, this.getGoal(), this.map, this.charCoords.getX(), this.charCoords.getY() - 1, gems));

		return nextList;
	}

	@Override
	public int scoreSet() {
		if (gems.size() > 0) {
			ArrayList<Integer> scores = new ArrayList<>();

			for (int i = 0; i < gems.size(); i++) {
				scores.add(Math.abs(this.charCoords.getX() - gems.get(i).getX() + this.charCoords.getY() - gems.get(i).getY()));
			}

			int max = scores.get(0);

			if (scores.size() > 1) {
				for (int i = 1; i < scores.size(); i++) {
					if (scores.get(i) > max)
						max = scores.get(i);
				}
			}

			return max;
		} else {
			return Math.abs(this.charCoords.getX() - ((MazeState) this.getGoal()).charCoords.getX() + this.charCoords.getY() - ((MazeState) this.getGoal()).charCoords.getY());

		}

	}

	@Override
	public boolean equals(Object obj) {
		if (((MazeState) obj).charCoords.getX() == this.charCoords.getX() && ((MazeState) obj).charCoords.getY() == this.charCoords.getY() && ((MazeState) obj).gems.size() == this.gems.size()) {
			for (int i = 0; i < this.gems.size(); i++) {
				if (((MazeState) obj).gems.get(i) != this.gems.get(i)) {
					return false;
				}
			}
		} else {
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
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (j == this.charCoords.getX() && i == this.charCoords.getY()) {
					System.out.print('S');
				} else if (gems.contains(new Coordinates(j, i))) {
					System.out.print('O');
				} else {
					System.out.print(map[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println(charCoords.getX() + " " + charCoords.getY());
		System.out.println();
	}
}
