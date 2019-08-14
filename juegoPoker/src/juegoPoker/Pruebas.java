package juegoPoker;

import java.util.ArrayList;

public class Pruebas extends ArrayList{
	public ArrayList<Cartas> prueba00,prueba01,comunitaria;
	
	{
		comunitaria = new ArrayList<>();
		comunitaria.add(new Cartas("10T"));
		comunitaria.add(new Cartas("9D"));
		comunitaria.add(new Cartas("11P"));
		comunitaria.add(new Cartas("4C"));
		comunitaria.add(new Cartas("8P"));
		prueba00 = new ArrayList<>();
		prueba01 = new ArrayList<>();
		
		prueba01.add(new Cartas("14D"));
		prueba01.add(new Cartas("14T"));

		prueba00.add(new Cartas("7D"));
		prueba00.add(new Cartas("3D"));
		
		for(int i=0;i<5;i++) {
			prueba00.add(comunitaria.get(i));
			prueba01.add(comunitaria.get(i));
		}
	

	}
}
