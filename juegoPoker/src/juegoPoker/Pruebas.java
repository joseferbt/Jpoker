package juegoPoker;

import java.util.ArrayList;

public class Pruebas extends ArrayList{
	public ArrayList<Cartas> prueba00,prueba01,comunitaria;
	
	{
	prueba00 = new ArrayList<>();
	prueba00.add(new Cartas("2P"));
	prueba00.add(new Cartas("4P"));
	prueba00.add(new Cartas("4P"));
	prueba00.add(new Cartas("5P"));
	prueba00.add(new Cartas("5P"));
	prueba00.add(new Cartas("7P"));
	prueba00.add(new Cartas("8P"));
	prueba01 = new ArrayList<>();
	prueba01.add(new Cartas("2P"));
	prueba01.add(new Cartas("2P"));
	prueba01.add(new Cartas("2P"));
	prueba01.add(new Cartas("4P"));
	prueba01.add(new Cartas("5P"));
	prueba01.add(new Cartas("7P"));
	prueba01.add(new Cartas("8P"));
	
	comunitaria = new ArrayList<>();
	comunitaria.add(new Cartas("2P"));
	comunitaria.add(new Cartas("4P"));
	comunitaria.add(new Cartas("5P"));
	comunitaria.add(new Cartas("7P"));
	comunitaria.add(new Cartas("8P"));
	
	}
}
