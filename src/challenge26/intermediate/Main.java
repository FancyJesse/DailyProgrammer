package challenge26.intermediate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {

	private static class Employee {
		private String name;
		private int age;
		private double wage;

		public void setName(String name) {
			this.name = name;
		}

		public void setWage(double wage) {
			this.wage = wage;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public void dump() {
			System.out.println(name + ", " + age + ", " + new DecimalFormat("$#.##").format(wage));
		}
	}

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine();
			} catch (Exception e) {
				System.out.println("Something Went Wrong. Try Again.");
			}
		}
	}

	private static Employee getNewEmployee() {
		Employee employee = new Employee();

		employee.setName(getInput("Employee Name:"));

		while (true)
			try {
				employee.setAge(Integer.parseInt(getInput("Employee Age:")));
				break;
			} catch (Exception e) {
				System.out.println("Not a Number. Try Again.");
			}

		while (true)
			try {
				employee.setWage(Double.parseDouble(getInput("Employee Wage:")));
				break;
			} catch (Exception e) {
				System.out.println("Not a Number. Try Again.");
			}

		return employee;
	}

	private static void dumpEmployeeList(ArrayList<Employee> employeeList) {
		for (int i = 0; i < employeeList.size(); i++) {
			System.out.println("Employee " + (i + 1));
			employeeList.get(i).dump();
			if (i + 1 < employeeList.size())
				System.out.println();
		}
	}

	public static void main(String[] args) {
		String input;

		ArrayList<Employee> employeeList = new ArrayList<>();
		boolean running = true;
		while (running) {
			input = getInput("1. Add Employee\n2. Edit Employee\n3. View Employees\n4. Quit");
			System.out.println();
			switch (input) {
			case "1":
				Employee employee = getNewEmployee();
				employeeList.add(employee);
				break;
			case "2":
				try {
					dumpEmployeeList(employeeList);
					int index = Integer.parseInt(getInput("\nEnter Employee Number to Edit")) - 1;

					employeeList.get(index);
					System.out.println("\nSet New Employee Information");
					employeeList.set(index, getNewEmployee());
				} catch (Exception e) {
					System.out.println("Invalid Employee Number.");
				}
				break;
			case "3":
				dumpEmployeeList(employeeList);
				break;
			case "4":
				running = false;
				System.out.println("Exiting. Good Bye.");
				break;
			default:
				System.out.println("Invalid Option. Try Again.");
			}
			System.out.println();
		}

	}

}
