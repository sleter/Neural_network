package siec_neuronowa;

import java.util.ArrayList;

//jeúli odpowiedü jest nieujemna, drukowana jest zielona jedynka, w przeciwnym wypadku czerwone zero
//standard terminala VT100

public class ConsolePrinter {
	
	private final ArrayList<IAnswerer> answerers = new
	ArrayList<>();
	
	//rejestruje obiekt, ktÛry implementuje interfejs IAnswer
	public void addAnswerer(IAnswerer ans) {
		answerers.add(ans);
	}
	
	//drukuje wyniki w konsoli programu
	public void printResults() {
		printDivisionLine();
		printPaddedNames();
		printAnswerFields();
	}
	
	private void printDivisionLine() {
		for (IAnswerer a : answerers) {
		System.out.print(String.format("%-46s", "=").replace(' ', '='));}
		System.out.println("");
	}
	
	private void printAnswerFields() {
		for (double y = 1.0; y > -1.1; y -= 0.1) {
			for (IAnswerer ans : answerers) {
				for (double x = -1.0; x < 1.0; x += 0.1) {
					printSingleAnswer(ans, x, y);
				}
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
	
	private void printSingleAnswer(IAnswerer ans, double x, double y) {
		double[] d = new double[2];
		d[0] = x;
		d[1] = y;
		if (ans.answer(d) > 0) {
			System.out.print("\033[31m1\033[39m ");
		} else {
			System.out.print("\033[32m0\033[39m ");
		}
	}
	
	private void printPaddedNames() {
		for (IAnswerer ans : answerers) {
			System.out.print(String.format("%-46s", ans.getName()));
		}
		System.out.println("");
	}
}
