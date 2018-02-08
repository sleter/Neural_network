import java.util.Random;

public class Creature implements Comparable<Creature>{
	
	private static final Random R = new Random();
	private final String genes;
	private static String TARGET = "Never gonna give you up";
	private static double mutationRate = 0.02;
	private double fitness;
	private boolean hasNotLivedYet = true;
	
	public Creature() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < TARGET.length(); i++) {
			sb.append(generateRandomGene());
		}
		genes = sb.toString();
	}
	
	public Creature(Creature parent1, Creature parent2) {
		int pivot = R.nextInt(parent1.genes.length() + 1);
		genes = reproduceFromTwoParents(parent1, parent2, pivot);
	}
	
	private String reproduceFromTwoParents(Creature parent1,
			Creature parent2, int pivot) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < parent1.genes.length(); i++) {
			if (i < pivot) {
				sb.append(parent1.genes.charAt(i));
			} else {
				sb.append(parent2.genes.charAt(i));
			}
		}
		if (R.nextDouble() < mutationRate) {
			sb.setCharAt(R.nextInt(parent1.genes.length()), generateRandomGene());
		}
		return sb.toString();
	}
	
	public static void setTARGET(String TARGET) {
		Creature.TARGET = TARGET;
	}
	public static void setMutationRate(double mutRate) {
		mutRate = (mutRate < 0.0) ? 0.0 : ((mutRate > 1.0) ? 1.0 : mutRate);
		Creature.mutationRate = mutRate;
	}
	
	public void live() {
		fitness = 0.0;
		for (int i = 0; i < genes.length(); i++) {
			if (genes.charAt(i) == TARGET.charAt(i)) {
				fitness += 1.0;
			}
		}
		fitness /= genes.length();
		hasNotLivedYet = false;
	}
	
	public double getFitness() {
		if (hasNotLivedYet) {
			live();
		}
		return fitness;
	}
		
	private char generateRandomGene() {
		int min = 32;
		int max = 127;
		char c = (char) (R.nextInt(max - min) + min);
		return c;
	}
		
	@Override
	public String toString() {
		if (hasNotLivedYet) {
			live();
		}
		return genes + " - fit: " + fitness;
	}
		
	@Override
	public int compareTo(Creature o) {
		return -Double.compare(fitness, o.fitness);
	}
}
