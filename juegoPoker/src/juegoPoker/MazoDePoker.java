package juegoPoker;

import java.util.ArrayList;
import java.util.Random;



public class MazoDePoker {
	private ArrayList<Cartas> baraja;
	private Random aleatorio;
	public MazoDePoker() {
		//Añadir todas las cartas de corazones
		baraja= new ArrayList<Cartas>();
		aleatorio = new Random();
		cartasBaraja();
	}
	
	public void cartasBaraja() {
		String[] palos = {"C","P","D","T"};
		for(int i=2;i<=14;i++) {
			for(int j=0;j<4;j++) {
				
				baraja.add(new Cartas(i+palos[j]));
			}
		} 
		revolver();
	}
	
	public void revolver() {
		for(int i=0;i<42;i++) {
			int valor =aleatorio.nextInt(baraja.size());
			Cartas aux = baraja.get(valor);
			baraja.remove(valor);
			int valor1 = aleatorio.nextInt(baraja.size());
			Cartas aux1 =  baraja.get(valor1);
			baraja.remove(valor1);
			baraja.add(valor1, aux);
			baraja.add(valor, aux1);
		}
	}
	public ArrayList<Cartas> getMazo(){
		return baraja;
	}
}
