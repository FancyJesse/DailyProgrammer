package challenge6.easy;

import java.math.BigDecimal;
import java.math.MathContext;

public class Main {

	public static void main(String[] args) {
		MathContext mathContext = new MathContext(30);
		BigDecimal pi, a, b, buffer;

		boolean endLoop = false;
		pi = new BigDecimal(0.0, mathContext);
		buffer = pi;
		for (int i = 1;; i += 2) {
			a = new BigDecimal(1.0 / (2.0 * i - 1), mathContext);
			b = new BigDecimal(1.0 / (2.0 * i + 1), mathContext);
			pi = pi.add((a.subtract(b)));

			switch (buffer.compareTo(pi)) {
			case -1:
				buffer = pi;
				break;
			case 0:
				endLoop = true;
				System.out.println("Pi: " + pi.multiply(new BigDecimal(4), mathContext));
				break;
			case 1:
			default:
				endLoop = true;
				System.out.println("Something went wrong.");
				break;
			}

			if (endLoop)
				break;
		}
	}

}
