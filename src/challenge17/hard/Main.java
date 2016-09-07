package challenge17.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

	private static int getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.out.println("That is not a Number. Try Again.");
			}
		}
	}

	private static class TicTacToe {
		private Character[] board;
		private Random rdm;
		private boolean smartCPU = false;

		public void play() {
			this.board = new Character[] { ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			this.rdm = new Random();
			int turns = 0;
			boolean playing = true;
			boolean playerTurn = true;

			char mark = 'X';

			draw();
			while (playing) {
				System.out.println("--- TURN " + ++turns + " ---");
				if (playerTurn)
					playerMove();
				else
					cpuMove();

				if (gameWon(mark)) {
					playing = false;
				} else if (isBoardFull()) {
					playing = false;
					mark = ' ';
				} else {

					if (mark == 'X')
						mark = 'O';
					else
						mark = 'X';
					playerTurn = !playerTurn;
				}
				draw();
			}

			System.out.println("--- MATCH END ---");
			draw();
			if (mark == 'X')
				System.out.println("YOU WIN!");
			else if (mark == 'O')
				System.out.println("YOU LOSE!");
			else
				System.out.println("IT'S A TIE!");

		}

		public void toggleSmartCPU() {
			this.smartCPU = !this.smartCPU;
			if (this.smartCPU)
				System.out.println("Smart CPU Movement has been turned ON.");
			else
				System.out.println("Smart CPU Movement has been turned OFF.");
		}

		private void playerMove() {
			int move;
			System.out.println("Your Turn");
			while (true) {
				move = getInput("Enter Your Move:");
				if (isValidMove(move)) {
					this.board[move] = 'X';
					break;
				} else {
					System.out.println("Invalid Move. Try Again.");
				}
			}

		}

		private void cpuMove() {
			int move = -1;
			System.out.println("CPU's Turn");
			if (smartCPU) {
				move = smartMove();
			}
			while (!isValidMove(move))
				move = rdm.nextInt(9) + 1;
			this.board[move] = 'O';
		}

		private int smartMove() {
			if (isValidMove(1) && (board[2] == board[3] || board[4] == board[7] || board[5] == board[9]))
				return 1;
			if (isValidMove(2) && (board[1] == board[3] || board[5] == board[8]))
				return 2;
			if (isValidMove(3) && (board[1] == board[2] || board[6] == board[9] || board[5] == board[7]))
				return 3;
			if (isValidMove(4) && (board[1] == board[7] || board[5] == board[6]))
				return 4;
			if (isValidMove(5)
					&& (board[1] == board[9] || board[2] == board[8] || board[3] == board[7] || board[4] == board[6]))
				return 5;
			if (isValidMove(6) && (board[3] == board[9] || board[4] == board[5]))
				return 6;
			if (isValidMove(7) && (board[1] == board[4] || board[3] == board[5]) || board[8] == board[9])
				return 7;
			if (isValidMove(8) && (board[2] == board[5] || board[7] == board[9]))
				return 8;
			if (isValidMove(9) && (board[1] == board[5] || board[3] == board[6] || board[7] == board[8]))
				return 9;
			return -1;
		}

		private boolean isValidMove(int pos) {
			return pos > 0 && pos < 10 && Character.isDigit(this.board[pos]);
		}

		private boolean isBoardFull() {
			for (Character c : this.board) {
				if (Character.isDigit(c))
					return false;
			}
			return true;
		}

		private boolean gameWon(Character mark) {
			return (board[1] == mark && board[2] == mark && board[3] == mark)
					|| (board[4] == mark && board[5] == mark && board[6] == mark)
					|| (board[7] == mark && board[8] == mark && board[9] == mark)
					|| (board[1] == mark && board[4] == mark && board[7] == mark)
					|| (board[2] == mark && board[5] == mark && board[8] == mark)
					|| (board[3] == mark && board[6] == mark && board[9] == mark)
					|| (board[1] == mark && board[5] == mark && board[9] == mark)
					|| (board[3] == mark && board[5] == mark && board[7] == mark);
		}

		private void draw() {
			System.out.println("     |     |     ");
			System.out.println("  " + board[1] + "  |  " + board[2] + "  |   " + board[3] + "  ");
			System.out.println("     |     |     ");
			System.out.println("-----------------");
			System.out.println("     |     |     ");
			System.out.println("  " + board[4] + "  |  " + board[5] + "  |   " + board[6] + "  ");
			System.out.println("     |     |     ");
			System.out.println("-----------------");
			System.out.println("     |     |     ");
			System.out.println("  " + board[7] + "  |  " + board[8] + "  |   " + board[9] + "  ");
			System.out.println("     |     |     \n\n");
		}

	}

	public static void main(String[] args) {
		TicTacToe tictactoe = new TicTacToe();
		tictactoe.toggleSmartCPU();
		tictactoe.play();
	}

}