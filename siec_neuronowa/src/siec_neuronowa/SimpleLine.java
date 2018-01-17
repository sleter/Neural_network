package siec_neuronowa;

//klasa implementuj¹ca barierê w postaci lini prostej

public class SimpleLine implements IAnswerer {
	
	private double a, b;
	private String name;
	
	public SimpleLine(double a, double b, String name) {
		this.a = a;
		this.b = b;
		this.name = name;
	}
	
	@Override
	public double answer(double[] x) {
		return computeLine(a, x[0], b, x[1]);
	}
	
	@Override
		public String getName() {
	return name;
	}
	
	private double computeLine(double a, double x, double b, double y) {
		double result = a * x + b;
		return (y > result) ? 1 : -1;
	}
}
