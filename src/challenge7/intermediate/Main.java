package challenge7.intermediate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static final int SIZE = 512;

	private static int getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("That Is Not A Number. Try Again.");
			}
		}
	}

	private static void drawFigure(int level, Graphics g, Point p1, Point p2, Point p3) {
		if (level == 1) {
			Polygon p = new Polygon();
			p.addPoint(p1.x, p1.y);
			p.addPoint(p2.x, p2.y);
			p.addPoint(p3.x, p3.y);
			g.fillPolygon(p);
		} else {
			Point p4 = midpoint(p1, p2);
			Point p5 = midpoint(p2, p3);
			Point p6 = midpoint(p1, p3);

			drawFigure(level - 1, g, p1, p4, p6);
			drawFigure(level - 1, g, p4, p2, p5);
			drawFigure(level - 1, g, p6, p5, p3);
		}
	}

	private static Point midpoint(Point p1, Point p2) {
		return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}

	public static void main(String[] args) {
		int levels;
		levels = getInput("How Many Levels To Draw?");

		DrawingPanel drawingPanel = new DrawingPanel(SIZE, SIZE);
		drawingPanel.setBackground(Color.white);
		Graphics graphics = drawingPanel.getGraphics();

		int height = (int) Math.round(SIZE * Math.sqrt(3.0) / 2.0);
		Point p1 = new Point(0, height);
		Point p2 = new Point(SIZE / 2, 0);
		Point p3 = new Point(SIZE, height);
		drawFigure(levels, graphics, p1, p2, p3);
	}

}
