package juegoPoker;

import java.util.ArrayList;

public class Pruebas extends ArrayList{
	public ArrayList<Cartas> prueba00,prueba01,comunitaria;
	
	{
		comunitaria = new ArrayList<>();
		comunitaria.add(new Cartas("6D"));
		comunitaria.add(new Cartas("10D"));
		comunitaria.add(new Cartas("6T"));
		comunitaria.add(new Cartas("13P"));
		comunitaria.add(new Cartas("12D"));
		prueba00 = new ArrayList<>();
		prueba01 = new ArrayList<>();
		
		prueba01.add(new Cartas("11C"));
		prueba01.add(new Cartas("2D"));

		prueba00.add(new Cartas("10P"));
		prueba00.add(new Cartas("6C"));
		
		for(int i=0;i<5;i++) {
			prueba00.add(comunitaria.get(i));
			prueba01.add(comunitaria.get(i));
		}
	
	System.out.print(prueba00.size()+"  " +prueba01.size());
	
	}
}
