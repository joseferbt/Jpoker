package juegoPoker;

import java.util.ArrayList;

public class Pruebas extends ArrayList{
	public ArrayList<Cartas> prueba00,prueba01,comunitaria;
	
	{
		comunitaria = new ArrayList<>();
		comunitaria.add(new Cartas("9T"));
		comunitaria.add(new Cartas("9D"));
		comunitaria.add(new Cartas("5C"));
		comunitaria.add(new Cartas("4T"));
		comunitaria.add(new Cartas("7p"));
		prueba00 = new ArrayList<>();
		prueba01 = new ArrayList<>();
		
		prueba01.add(new Cartas("2P"));
		prueba01.add(new Cartas("8C"));

		prueba00.add(new Cartas("3D"));
		prueba00.add(new Cartas("8P"));
		
		for(int i=0;i<5;i++) {
			prueba00.add(comunitaria.get(i));
			prueba01.add(comunitaria.get(i));
		}
	

	}
}
