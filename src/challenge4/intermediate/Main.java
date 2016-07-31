package challenge4.intermediate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				return br.readLine();
			} catch (IOException e) {
			}
		}
	}

	private static Object calculateExpression(String expression) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			return engine.eval(expression);
		} catch (ScriptException e) {
			return "Invalid Expression";
		}
	}

	public static void main(String[] args) {
		String expression;
		Object solution;

		expression = getInput("Input Your Expression To Calculate: ");
		solution = calculateExpression(expression);
		System.out.println(expression + " = " + solution);
	}

}