import java.util.*;

//klasa implementuj¹ca populacjê osobników
public class Population {
	
	private static final Random R = new Random();
	private ArrayList<Creature> population = new ArrayList<>();
	private int counter = 0;
	
	public Population(int size) {
		for (int i = 0; i < size; i++) {
			population.add(new Creature());
		}
	}
	
	public void live() {
		for (Creature c : population) {
			c.live();
		}
		population.sort(null);
		counter++;
	}
	
	//--------------------------------------------------------------
	public void reproduce() {
		ArrayList<Integer> mateNumbers = new ArrayList<>();
		for (int i = 0; i < population.size() / 2; i++) {
			int n = (int) (population.get(i).getFitness() * 100);
			for (int j = 0; j < n; j++) {
				mateNumbers.add(i);
			}
		}
		ArrayList<Creature> newPopulation = new ArrayList<>();
		for (int i = 0; i < population.size(); i++) {
			int a = mateNumbers.get(R.nextInt(mateNumbers.size()));
			int b = mateNumbers.get(R.nextInt(mateNumbers.size()));
			newPopulation.add(new Creature(population.get(a),
			population.get(b)));
		}
		population = newPopulation;
	}
	//--------------------------------------------------------------
	
	public double getPercentFitted() {
		double cnt = 0;
		for (Creature c : population) {
			if (c.getFitness() > 0.999) {
				cnt += 1.0;
			}
		}
		return cnt / population.size();
	}
	
	@Override
	public String toString() {
		int size = (population.size() > 5) ? 5 : population.size();
		StringBuilder sb = new StringBuilder();
		sb.append("=======================================\n");
		sb.append("Generation: ").append(counter).append("\n");
		for (int i = 0; i < size; i++) {
			sb.append(population.get(i)).append("\n");
		}
		return sb.toString();
	}
}
