package siec_neuronowa;

import java.util.Random;

//przetwarza wyliczon¹ sumê do postaci zgodnej z formatem wyjœciowym
//wiele wejœæ i jedno wyjœcie + polaryzacja (ang. bias)

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
	
	/*b³¹d -> ró¿nica miêdzy wartoœci¹ oczekiwan¹ a wartoœci¹ otrzyman¹
	  wartoœæ otrzymana -> wynik obliczeñ perceptronu
	  wartoœæ oczekiwana ->zosta³a wygenerowana do nauczenia perceptronu
	  
	  b³¹d jest informacj¹, w jakim stopniu ka¿da z wag powinna byæ zmodyfikowana
	  
		nowa wartoœæ wagi -> suma starej wartoœci oraz pewny przyrost
		przyrost -> iloczyn b³êdu wartoœci wejœciowej odpowiadaj¹cej danej wadze oraz sta³emu wspó³czynnikowi
		sta³y wspó³czynnik -> sta³a uczenia(okreœla dynamikê, jak mocno ma siê zmieniaæ wartoœæ wagi)*/
	
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
