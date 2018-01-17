package siec_neuronowa;

import java.util.ArrayList;
import java.util.Random;

//mo�e rejestrowa� bariery, kt�re okre�laj� 
//obszary warto�ci dodatnich oraz ujemnych na p�aszczy�nie X-Y

public class ReferenceFrame implements IAnswerer { //klasa p�aszczyzny wzorcowej
	
	private static final Random R = new Random();
	private final ArrayList<IAnswerer> answerers = new
	ArrayList<>();
	private String name;
	
	//pozwala na dodanie obiektu implementuj�cego znajomy nam interfejs i 
	//jednocze�nie barier� odpowiedzi dodatnich i ujemnych
	public void addBarrier(IAnswerer ans) {
		answerers.add(ans);
	}
	
	public ReferenceFrame(String name) {
		this.name = name;
	}
	
	//pozwala wygenerowa� losow� warto�� z zakresu [-1, 1]
	public double genCoord() {
		return R.nextDouble() * 2 - 1;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public double answer(double[] x) {
		boolean result = false;
		//przegl�da wszystkie zarejestrowane bariery w poszukiwaniu dodatniej odpowiedzi
		for (IAnswerer ans : answerers) {
			if (ans.answer(x) > 0) {
				return 1;
			}
		}
		return -1;
	}
}
