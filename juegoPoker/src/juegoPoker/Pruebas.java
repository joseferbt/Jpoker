package juegoPoker;

import java.util.ArrayList;

public class Pruebas extends ArrayList{
	public ArrayList<Cartas> prueba00,prueba01,comunitaria;
	
	{
		comunitaria = new ArrayList<>();
		comunitaria.add(new Cartas("10T"));
		comunitaria.add(new Cartas("14T"));
		comunitaria.add(new Cartas("11T"));
		comunitaria.add(new Cartas("13T"));
		comunitaria.add(new Cartas("7C"));
		prueba00 = new ArrayList<>();
		prueba01 = new ArrayList<>();
		
		prueba01.add(new Cartas("13D"));
		prueba01.add(new Cartas("8C"));

		prueba00.add(new Cartas("5C"));
		prueba00.add(new Cartas("12P"));
		
		for(int i=0;i<5;i++) {
			prueba00.add(comunitaria.get(i));
			prueba01.add(comunitaria.get(i));
		}
	

	}
}
