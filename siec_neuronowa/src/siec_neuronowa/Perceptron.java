package siec_neuronowa;

import java.util.Random;

//przetwarza wyliczon� sum� do postaci zgodnej z formatem wyj�ciowym
//wiele wej�� i jedno wyj�cie + polaryzacja (ang. bias)

public class Perceptron implements IAnswerer {
	
	private static final Random R = new Random();
	private static int cnt = 0;
	private final String name;
	private final double[] weights;
	
	public Perceptron() {
		name = "Perceptron " + cnt++;
		weights = new double[3];
		generateWeigthValues();
	}
	
	public Perceptron(int size) {
		name = "Perceptron " + cnt++;
		weights = new double[size + 1];
		generateWeigthValues();
	}
	
	private void generateWeigthValues() {
		for (int i = 0; i < weights.length; i++) {
			weights[i] = R.nextDouble() * 2 - 1;
		}
	}
	
	/*b��d -> r�nica mi�dzy warto�ci� oczekiwan� a warto�ci� otrzyman�
	  warto�� otrzymana -> wynik oblicze� perceptronu
	  warto�� oczekiwana ->zosta�a wygenerowana do nauczenia perceptronu
	  
	  b��d jest informacj�, w jakim stopniu ka�da z wag powinna by� zmodyfikowana
	  
		nowa warto�� wagi -> suma starej warto�ci oraz pewny przyrost
		przyrost -> iloczyn b��du warto�ci wej�ciowej odpowiadaj�cej danej wadze oraz sta�emu wsp�czynnikowi
		sta�y wsp�czynnik -> sta�a uczenia(okre�la dynamik�, jak mocno ma si� zmienia� warto�� wagi)*/
	
	public void train(double[] x, double expectedAnswer, double learnRate) {
		double adjustedError = (expectedAnswer - answer(x)) *learnRate;
		for (int i = 0; i < weights.length - 1; i++) {
			weights[i] += adjustedError * x[i];
		}
		weights[weights.length - 1] += adjustedError;
	}
	
	@Override
	public double answer(double[] x) {
		double sum = 0;
		for (int i = 0; i < weights.length - 1; i++) {
			sum += x[i] * weights[i];
		}
		sum += weights[weights.length - 1];
	return activate(sum);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	private double activate(double sum) {
		return (sum > 0) ? 1 : -1;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name + ": ");
		for (double d : weights) {
			sb.append(d).append(" ");
		}
		return sb.toString();
	}
}
