package edu.intesys.main;

import edu.intesys.objects.MinimaxState;
import edu.intesys.objects.State;
import edu.intesys.objects.TicTacToeState;

import java.util.ArrayList;

/**
 * Created by JohnPaul on 09/20/2016.
 */
public class Main {
	public static void main(String[] args) {
		Main m = new Main();
//		m.test();
//		m.Search("BFS", new WSState(new WSState('R', 0, 0), 'L', 3, 3));
//		m.Search("DFS", new WSState(new WSState('R', 0, 0), 'L', 3, 3));
//		m.Search("BFS", new SlidingPuzzleState("123456780", "413206758")); //15 moves away
//		m.Search("DFS", new SlidingPuzzleState("123456780", "413206758"));
//		m.Search("Heuristic", new SlidingPuzzleState("123456780", "704132856")); //some random pattern i found on the internets
//		m.Search("A*", new SlidingPuzzleState("123456780", "704132856"));

//		Stack<Integer> t1 = new Stack<>();
//		Stack<Integer> t2 = new Stack<>();
//		Stack<Integer> t3 = new Stack<>();
//
//		t1.push(3);
//		t1.push(2);
//		t1.push(1);
//
//		m.Search("BFS", new HanoiTowerState(t1, t2, t3, t3, t2, t1));
//		m.Search("DFS", new HanoiTowerState(t1, t2, t3, t3, t2, t1));
//		m.Search("Heuristic", new HanoiTowerState(t1, t2, t3, t3, t2, t1));

//		String[] row = new String[6];
//		row[0] = "S-W----O";
//		row[1] = "--W--W--";
//		row[2] = "--W-GW--";
//		row[3] = "--WWWW--";
//		row[4] = "--------";
//		row[5] = "O-------";
//
//		m.Search("BFS", new MazeState(row));
//		m.Search("DFS", new MazeState(row));
//		m.Search("Heuristic", new MazeState(row));
//		m.Search("A*", new MazeState(row));

//		m.Search("BFS", new EightQueensState());
//		m.Search("Heuristic", new MastermindState("0941"));
//		m.Search("A*", new MastermindState("0941"));

		m.test();
	}

	void Search(String searchType, State init) {
		ArrayList<State> visitedList = new ArrayList<>();
		ArrayList<State> exploreList = new ArrayList<>();
		ArrayList<State> finalPath = new ArrayList<>();
		exploreList.add(init);

		while (!exploreList.isEmpty()) {
			State currState = null;
			switch (searchType) {
				case "BFS":
				case "Beam":
				case "HillClimb":
					currState = exploreList.get(0);
					visitedList.add(currState);
					exploreList.remove(0);
					break;
				case "DFS":
					currState = exploreList.get(exploreList.size() - 1);
					visitedList.add(currState);
					exploreList.remove(exploreList.size() - 1);
					break;
				case "Heuristic":
					currState = State.getHeuristicState(exploreList);
					visitedList.add(currState);
					exploreList.remove(currState);
//					System.out.println(currState.getScore());
					break;
				case "A*":
					currState = State.getAStarState(exploreList);
					visitedList.add(currState);
					exploreList.remove(currState);
//					System.out.println(currState.getScore());
					break;
			}

			if (currState.equals(currState.getGoal())) {
				while (currState != null) {
					finalPath.add(currState);
					currState = currState.getParent();
				}

				for (int i = finalPath.size() - 1; i >= 0; i--) {
					finalPath.get(i).print();
				}

				break;
			} else {
				ArrayList<State> nextStates;

				switch (searchType) {
					case "Beam":
						nextStates = currState.getBeamStates(3);
						break;
					case "HillClimb":
						nextStates = currState.getHillClimbState();
						if (nextStates.get(0).getScore() > currState.getScore())
							nextStates.remove(0);
						break;
					default:
						nextStates = currState.getNextStates();
				}

				for (State s : nextStates) {
					if (!visitedList.contains(s) && !exploreList.contains(s) && s.isValid())
						exploreList.add(s);
				}
			}
		}

//		visitedList.forEach(State::print);
		System.out.println("Nodes visited: " + visitedList.size());
		System.out.println("Moves to goal: " + finalPath.size());
	}

	void Minimax(String searchType, MinimaxState init) {
		ArrayList<State> visitedList = new ArrayList<>();
		ArrayList<State> exploreList = new ArrayList<>();
		ArrayList<State> finalPath = new ArrayList<>();
		exploreList.add(init);

		while (!exploreList.isEmpty()) {
			State currState = null;

			//DFS
			currState = exploreList.get(exploreList.size() - 1);
			visitedList.add(currState);
			exploreList.remove(exploreList.size() - 1);

			if (((MinimaxState)currState).isLeaf()) {
				((MinimaxState)currState).computeScore();
			} else {
				ArrayList<State> nextStates;

				switch (searchType) {
					default: nextStates = currState.getNextStates();
				}

				for (State s : nextStates) {
					if (!visitedList.contains(s) && !exploreList.contains(s) && s.isValid())
						exploreList.add(s);
				}
			}
		}

//		visitedList.forEach(State::print);
		System.out.println("Nodes visited: " + visitedList.size());
		System.out.println("Moves to goal: " + finalPath.size());
	}

	void test(){
		String a = "OOO";
		String b = "X-X";
		String c = "-X-";
		String[] grid = new String[3];
		grid[0] = a;
		grid[1] = b;
		grid[2] = c;

		TicTacToeState ttt = new TicTacToeState(grid);

		System.out.println(ttt.isLeaf());
	}
}
