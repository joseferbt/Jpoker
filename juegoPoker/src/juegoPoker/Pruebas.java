package juegoPoker;

import java.util.ArrayList;

public class Pruebas extends ArrayList{
	public ArrayList<Cartas> prueba00,prueba01,comunitaria;
	
	{
		comunitaria = new ArrayList<>();
		comunitaria.add(new Cartas("9T"));
		comunitaria.add(new Cartas("9D"));
		comunitaria.add(new Cartas("6C"));
		comunitaria.add(new Cartas("4T"));
		comunitaria.add(new Cartas("5P"));
		prueba00 = new ArrayList<>();
		prueba01 = new ArrayList<>();
		
		prueba00.add(new Cartas("5P"));
		prueba00.add(new Cartas("9C"));

		prueba01.add(new Cartas("9D"));
		prueba01.add(new Cartas("4P"));
		
		for(int i=0;i<5;i++) {
			prueba00.add(comunitaria.get(i));
			prueba01.add(comunitaria.get(i));
		}
	

	}
}
