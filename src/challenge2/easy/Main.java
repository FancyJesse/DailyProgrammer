package challenge2.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static Double getInput(String id) {
		String input;
		while (true) {
			try {
				System.out.print("Enter Value for " + id + "> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				input = br.readLine();
				if (input.isEmpty())
					return null;
				else
					return Double.parseDouble(input);
			} catch (IOException | NumberFormatException e) {
				System.out.println("Invalid Input. Try Again.");
			}
		}
	}

	private static void operations(Double A, Double F, Double M) {

		if (A == null) {
			System.out.println("Performing: A = F/M");
			A = F / M;
			System.out.println(A + " = " + F + "/" + M);
		} else if (F == null) {
			System.out.println("Performing: F = M*A");
			F = M * A;
			System.out.println(F + " = " + M + "*" + A);
		} else if (M == null) {
			System.out.println("Performing: M = F/A");
			M = F / A;
			System.out.println(M + " = " + F + "/" + A);
		} else {
			System.out.println("Verifying: F = M*A");
			if (F == M * A) {
				System.out.println(F + " = " + M + "*" + A);
				System.out.println("Equation is Valid.");
			} else {
				System.out.println(F + " != " + M + "*" + A);
				System.out.println("Equation is Invalid.");
			}
		}

	}

	public static void main(String[] args) {
		Double A, F, M;

		A = getInput("A");
		F = getInput("F");
		M = getInput("M");

		int count = 0;
		if (A == null)
			count++;
		if (F == null)
			count++;
		if (M == null)
			count++;

		if (count > 1)
			System.out.println("Not Enough Information To Perform Calculation.");
		else
			operations(A, F, M);

	}

}
