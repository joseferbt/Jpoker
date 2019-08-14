package juegoPoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

public class ControlPoker extends JPanel {

	private Jugador jugador;
	private Dealer dealer;
	private MazoDePoker mazo;
	private int apuestas, estado, base, jugada,gano;
	private PanelCartas panelCartas;
	private JPanel panel;
	private ArrayList<Cartas> comunitarias;
	private int[] mismoPalo = new int[7];
	private int[] nPalos = new int[4];
	private int[][] posiciones = new int[4][7];
	private int[] hay = new int[2];
	private int[] grupos = new int[14];
	private ArrayList<Jugador> jugadores;
	private Pruebas pruebas = new Pruebas(); // borrar xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	private String texto;

	public ControlPoker() {
		initgui();

		this.setPreferredSize(new Dimension(1200, 670));
		this.setBackground(new Color(50, 50, 50, 0));

	}

	public void initgui() {

		base = 25;
		jugada = 0;
		jugadores = new ArrayList<>();

		comunitarias = new ArrayList<>();

		dealer = new Dealer();
		add(dealer, BorderLayout.NORTH);

		panelCartas = new PanelCartas();

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		panelCartas.add(panel);

		add(panelCartas, BorderLayout.CENTER);

		jugador = new Jugador("jugador1", true, 1000);
		add(jugador, BorderLayout.SOUTH);

		mazo = new MazoDePoker();
		jugadores.add(jugador);
		jugadores.add(dealer);
		reparto();

	}

	public int getEstado() {
		return estado;
	}

	public int getGano() {
		
		return gano;
	}

	public void reparto() {

		/*
		  int contador = 0; mazo.revolver(); for (int i = 0; i < jugadores.size(); i++)
		  { Cartas aux = mazo.getMazo().get(contador); contador++;
		  jugador.repartir(aux); aux = mazo.getMazo().get(contador); contador++;
		  dealer.repartir(aux); } for (int i = 0; i < 5; i++) { Cartas aux =
		  mazo.getMazo().get(contador); contador++; aux.setVisible(false);
		  jugador.getMano().add(aux); dealer.getMano().add(aux); comunitarias.add(aux);
		  panel.add(aux); }
		 
*/
		dealer.setArraycartas(pruebas.prueba01);
		jugador.setArraycartas(pruebas.prueba00);
		comunitarias = pruebas.comunitaria;

		jugador.setArray();
		dealer.setArray();
		estado = 0;
	}

	public void met() {
		updateUI();
		panelCartas.updateUI();

	}

	public Jugador jugador() {
		return jugador;
	}

	public void jugada(int entrada, int aux) {
		switch (entrada) {
		case 0:
			if (base <= jugador.getDinero()) {
				jugador.setApuesta(base);
				setApuestas(base);
				jugador.setEstado(0);
			}
			break;
		case 1:
			jugador.setApuesta(aux + base);
			setApuestas(base + aux);
			if (jugador.getDinero() == 0) {
				jugador.setEstado(4);
			} else {
				jugador.setEstado(0);
			}
		}
	}

	public void etapaJuego() {
		panelCartas.actualizar(apuestas);
		if (jugador.getEstado() == 1 || jugador.getEstado() == 0) {
			if (estado <= 2) {
				jugador.setTurno(true);
			}
			estado++;
			met();
		} else if (jugador.getEstado() == 4) {
			estado = 4;
		}

		switch (estado) {
		case 1:
			for (int i = 0; i < 3; i++) {
				comunitarias.get(i).setIcono();
				comunitarias.get(i).setVisible(true);
			}
			break;
		case 2:
			comunitarias.get(3).setVisible(true);
			comunitarias.get(3).setIcono();
			break;
		case 3:
			for (int i = 0; i < 5; i++) {
				comunitarias.get(i).setIcono();
				comunitarias.get(i).setVisible(true);
			}
			break;
		case 4:
			for (int i = 0; i < 5; i++) {
				comunitarias.get(i).setIcono();
				comunitarias.get(i).setVisible(true);
			}
			dealer.getMano().get(0).setIcono();
			dealer.getMano().get(1).setIcono();
			jugador.getMano().get(0).setIcono();
			jugador.getMano().get(1).setIcono();
			gano();
			break;
		}
	}

	public int getBase() {
		return base;
	}

	public void setApuestas(int apuesta) {
		apuestas += apuesta * 2;
	}

	public void comprobarJugada(Jugador jugador) {

		jugada = sumaJugada(jugador.getArrayId());
		cuantosPalos(jugador.getArrayPalo());
		// jugadas
		escaleraReal(jugador);
		escaleraColor(jugador, sumaJugada(jugador.getJugada()));
		cuantosGrupos(cualGrupo(jugador), jugador, sumaJugada(jugador.getJugada()));
		System.out.println("suma " + sumaJugada(jugador.getJugada()));

		cartaMayor(jugador, sumaJugada(jugador.getJugada()),0);
		///
		System.out.println(Arrays.toString(jugador.getArrayId()));
		for (int i = 0; i < jugador.getArrayJugadas().size(); i++) {
			System.out.println(jugador.getArrayJugadas().get(i));
		}
		System.out.println(Arrays.toString(jugador.getJugada()));
		System.out.println(Arrays.toString(jugador.getMisId()));
		System.out.println("mismo Palo " + Arrays.toString(mismoPalo));
		nPalos = new int[4];
		mismoPalo = new int[7];
		posiciones = new int[4][7];
		hay = new int[2];
		grupos = new int[14];
		jugada = 0;
	}

	public void iniciarJuego() {
		if (jugador.getDinero() >= base) {

			for (int i = 0; i < jugadores.size(); i++) {

				for (int j = jugadores.get(i).getMano().size() - 1; j >= 0; j--) {

					jugadores.get(i).remover(j);

					if (j < 5 && i == 0) {
						comunitarias.remove(0);
						panel.remove(0);
					}
				}
			}
			// System.out.print("[comunitarias] "+comunitarias.size());
			// System.out.print("[jugador] "+jugadores.get(0).getMano().size());
			// System.out.print("[dealer] "+jugadores.get(1).getMano().size());
			reparto();
			apuestas = 0;
			panelCartas.actualizar(apuestas);
			jugador.turno = true;
			// System.out.println(Arrays.toString(jugador.getJugada()));
			// System.out.println(Arrays.toString(dealer.getJugada()));
			System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]");

		}
	}

	public void jugadaGanadora(Jugador jugador, Jugador dealer) {
		for (int i = 0; i < jugador.getArrayJugadas().size() && i < dealer.getArrayJugadas().size(); i++) {
			if (!jugador.getArrayJugadas().get(i).equals(dealer.getArrayJugadas().get(i))) {
				texto = jugador.getNombre() + " Gano con " + jugador.getArrayJugadas().get(i);
				break;
			} else {
				texto = jugador.getNombre() + " Gano con "+jugador.getArrayJugadas().get(jugador.getArrayJugadas().size()-1);
			}
		}
	}

	public void gano() {

		System.out.println("jugador puntos " + jugador.getPuntos());
		System.out.println("dealer puntos " + dealer.getPuntos());
		for (int i = 0; i < jugadores.size(); i++) {
			comprobarJugada(jugadores.get(i));
		}
		int aux=0;
		int aux1=0;
		for (int j = 0; j < jugador.getJugada().length; j++) {
			if(jugadores.get(0).getJugada()[j]==0&&0==jugadores.get(1).getJugada()[j]) {
			}else if(jugadores.get(0).getJugada()[j]==jugadores.get(1).getJugada()[j]) {
				if(j==2||j>=6) {
					cartaMayor(jugador,0,0);
					cartaMayor(dealer,0,0);
				}
				if(jugador.getJugada()[9] <dealer.getJugada()[9]) {
					jugadaGanadora( dealer, jugador ) ;
					gano=0;
				}else if(jugador.getJugada()[9]>dealer.getJugada()[9]) {
					jugadaGanadora( jugador,  dealer) ;
					gano=1;
					jugador.setDinero(jugador.getDinero()+apuestas);
				}else if(jugador.getJugada()[9]==dealer.getJugada()[9]&&(j==6||j==8||j==9)) {
					cartaMayor(jugador,0,1);
					cartaMayor(dealer,0,1);
					if(jugador.getJugada()[9] <dealer.getJugada()[9]) {
						jugadaGanadora( dealer, jugador ) ;
						gano=0;
						}else if(jugador.getJugada()[9]>dealer.getJugada()[9]) {
							jugadaGanadora( jugador,  dealer) ;
							gano=1;
							jugador.setDinero(jugador.getDinero()+apuestas);
						}else {
							gano=2;
							jugador.setDinero(jugador.getDinero()+(apuestas/2));}
					break;}
				else {
					gano=2;
					jugador.setDinero(jugador.getDinero()+(apuestas/2));}
				System.out.println("[X]");
				break;
			}else if(jugadores.get(0).getJugada()[j]>jugadores.get(1).getJugada()[j]) {
				gano=1;
				jugador.setDinero(jugador.getDinero()+apuestas);
				jugadaGanadora( jugador,  dealer) ;
				System.out.println("[y]");
				break;
			}else {
				gano=0;
				jugadaGanadora( dealer, jugador ) ;
				System.out.println("[z]");
				break;}
			System.out.println("[A]");
		}

		System.out.println(texto);
	}

	public int[] cuantosPalos(String[] array) {

		String[] palos = { "C", "D", "P", "T" };

		for (int i = 0; i < palos.length; i++) {
			int k = 0;
			for (int j = 0; j < array.length; j++) {
				if (palos[i].equals(array[j])) {
					nPalos[i] += 1;
					posiciones[i][k] = j + 1;
					k++;
				}

			}
		}
		for (int i = 0; i < 4; i++) {
			System.out.println(Arrays.toString(posiciones[i]));
		}

		System.out.println("aqui" + Arrays.toString(array));
		System.out.println("aqui" + Arrays.toString(nPalos));
		System.out.println("aqui" + Arrays.toString(cuantosHay(nPalos)));
		System.out.println("_____________________________________________");
		return cuantosHay(nPalos);
	}

	public int[] cuantosHay(int[] cuantosPalos) {

		int aux = cuantosPalos[0];
		for (int i = 0; i < cuantosPalos.length; i++) {
			if (aux < cuantosPalos[i]) {
				hay[0] = cuantosPalos[i];
				hay[1] = i;
				aux = hay[0];
			} else {
				hay[0] = aux;
			}
		}
		return hay;
	}

	public boolean escaleraP(int posicion) {
		int aux = 0;

		for (int i = 0; i < posiciones[posicion].length - 1; i++) {
			if (posiciones[posicion][i] != 0) {
				mismoPalo[i] = jugador.getArrayId()[posiciones[posicion][i] - 1];
				aux++;
			} else if (posiciones[posicion][i] == 0 && aux < 5) {
				return false;
			}
		}
		return true;
	}

	public void escaleraReal(Jugador jugador) {
		int comprobar = 10;
		if (escaleraP(hay[1])) {
			for (int i = 0; i < posiciones[hay[1]].length; i++) {
				if (posiciones[hay[1]][i] != 0) {
					if (jugador.getArrayId()[posiciones[hay[1]][i] - 1] == comprobar) {
						comprobar++;
					}
				}
			}
			if (comprobar == 15) {
				jugador.setPuntos(jugador.getPuntos() + 999999999);
				jugador.getArrayJugadas().add("Escalera Real ");
				jugador.getJugada()[0] = 999999999;
			}
			comprobar = 10;
		}
	}

	public void escaleraColor(Jugador jugador, int puntos) {
		if (puntos == 0) {
			int[] aux = new int[7];
			int x = 0;
			int valores = 0;
			int contador = 0;
			if (escaleraP(hay[1])) {
				for (int i = 0; i < posiciones[hay[1]].length; i++) {
					if (posiciones[hay[1]][i] != 0) {
						aux[i] = jugador.getArrayId()[posiciones[hay[1]][i] - 1];
					}
				}
				Arrays.sort(aux);
				for (int i = 0; i < aux.length; i++) {
					if (aux[i] != 0) {
						int aux1 = x;
						x = aux[i];
						valores += aux[i];
						if (aux1 + 1 == x) {
							contador++;
						}
					}
				}
				if (contador == 5) {
					jugador.setPuntos(jugador.getPuntos() + 100 * valores);
					jugador.getArrayJugadas().add("Escalera Color de " + aux[contador]);
					jugador.getJugada()[1] = aux[contador];
				}
			}
		}
	}

	public int[] cualGrupo(Jugador jugador) {
		for (int i = 0; i < jugador.getArrayId().length; i++) {
			for (int j = 0; j < jugador.getArrayId().length; j++) {
				if (jugador.getArrayId()[i] == jugador.getArrayId()[j]) {
					grupos[jugador.getArrayId()[i] - 1] += 1;
				}
			}
		}
		return grupos;
	}

	public void cuantosGrupos(int[] cualGrupo, Jugador jugador, int j) {
		int[] pares = new int[4];
		int[] trios = new int[3];
		int poker = 0;
		int x = 0;
		int y = 0;
		for (int i = cualGrupo.length - 1; i >= 0; i--) {
			switch (cualGrupo[i]) {
			case 4:
				pares[x] = i + 1;
				x++;
				break;
			case 9:
				trios[y] = i + 1;
				y++;
				break;
			case 16:
				poker = i + 1;
			}
		}
		for (int i = 0; i < pares.length - 1; i++) {
			pares[pares.length - 1] += pares[i];
		}
		for (int i = 0; i < trios.length - 1; i++) {
			trios[trios.length - 1] += trios[i];
		}
		if (poker != 0 && j == 0) {
			jugador.getArrayJugadas().add("Poker de " + poker);
			jugador.setPuntos(jugador.getPuntos() + poker * 80);
			jugador.getJugada()[2] = 8;
			j = sumaJugada(jugador.getJugada());
		}
		if (trios[0] != 0 && pares[0] != 0 && j == 0) { //////////////// full
			jugador.getArrayJugadas().add("Full de " + trios[0] + " y " + pares[0]);
			jugador.setPuntos(jugador.getPuntos() + (trios[2] + pares[3]) * 70);
			jugador.getJugada()[3] = (trios[2] + pares[3]);
		} //
		color(jugador, sumaJugada(jugador.getJugada()));
		escalera(jugador, sumaJugada(jugador.getJugada()));
		j = sumaJugada(jugador.getJugada());
		if (trios[0] != 0 && j == 0) { ///// trio
			for (int i = 0; i < trios.length - 1; i++) {
				if (trios[i] != 0) {
					jugador.getArrayJugadas().add("Trio de " + trios[i]);
				}
			}
			jugador.setPuntos(jugador.getPuntos() + trios[2] * 40);
			jugador.getJugada()[6] = trios[2];
			j = sumaJugada(jugador.getJugada());
		}
		if (pares[0] != 0 && j == 0 && pares[1] != 0) { //// parejas Dobles
			jugador.getArrayJugadas().add("Pardoble de " + (pares[0] + pares[1]));
			jugador.setPuntos(jugador.getPuntos() + pares[3] * 30);
			jugador.getJugada()[7] += pares[3];
			j = sumaJugada(jugador.getJugada());
		}
		// System.out.print("///////as "+pares[0]);
		if (pares[0] != 0 && j == 0) { ///// pareja sola
			jugador.getArrayJugadas().add("Par de " + pares[0]);
			jugador.setPuntos(jugador.getPuntos() + pares[0] * 20);
			jugador.getJugada()[8] += pares[0];
		}

	}

	public void color(Jugador jugador, int puntos) {
		if (puntos == 0) {
			if (escaleraP(hay[1])) {
				Arrays.sort(mismoPalo);
				jugador.setPuntos(jugador.getPuntos() + mismoPalo[6] * 60);
				jugador.getArrayJugadas().add("Color " + mismoPalo[6]);
				jugador.getJugada()[4] = mismoPalo[6];
			}
		}
	}

	public void escalera(Jugador jugador, int puntos) {
		if (puntos == 0) {
			int[] arid = jugador.getArrayId();
			Arrays.sort(arid);
			int contador = 0;
			if (arid[0] == 2 && arid[6] == 14) {
				for (int i = arid.length - 1; i > 0; i--) {
					arid[i] = arid[i - 1];
				}
				arid[0] = 1;
			}
			int aux = arid[0];
			for (int i = 1; i < arid.length; i++) {
				if (aux + 1 == arid[i]) {
					contador++;
					aux++;
				} else if (aux == arid[i]) {
					aux = arid[i];
				} else {
					aux = arid[i];
					contador = 0;
				}
			}
			if (contador >= 4) {
				jugador.setPuntos(jugador.getPuntos() + arid[contador] * 50);
				jugador.getArrayJugadas().add("escalera al " + arid[contador]);
				jugador.getJugada()[5] = arid[contador];
			} else if (jugador.getArrayId()[0] == 1) {
				jugador.getArrayId()[0] = 14;
			}
		}
	}

	public void cartaMayor(Jugador jugador, int jugada,int index) {
		if (jugada == 0) {
			if(index==0) {
				if (jugador.getMisId()[0] < jugador.getMisId()[1]) {
				jugador.setPuntos(jugador.getPuntos() + jugador.getMisId()[1]);
				jugador.getArrayJugadas().add("mayor " + jugador.getMisId()[1]);
				jugador.getJugada()[9] = jugador.getMisId()[1];
			} else {
				jugador.setPuntos(jugador.getPuntos() + jugador.getMisId()[0]);
				jugador.getArrayJugadas().add("mayor " + jugador.getMisId()[0]);
				jugador.getJugada()[9] = jugador.getMisId()[0];
			}}if(index ==1) {
				if (jugador.getMisId()[0] < jugador.getMisId()[1]) {
				jugador.setPuntos(jugador.getPuntos() + jugador.getMisId()[1]);
				jugador.getArrayJugadas().add("mayor " + jugador.getMisId()[0]);
				jugador.getJugada()[9] = jugador.getMisId()[0];
			} else {
				jugador.setPuntos(jugador.getPuntos() + jugador.getMisId()[0]);
				jugador.getArrayJugadas().add("mayor " + jugador.getMisId()[1]);
				jugador.getJugada()[9] = jugador.getMisId()[1];
			}
				
			}
			}
		
	}

	public int sumaJugada(int[] jugada) {

		int aux = 0;
		for (int i = 0; i < jugada.length; i++) {
			aux += jugada[i];
		}
		return aux;
	}

	public String getTexto() {
		return texto;
	}
}