package juegoPoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlAux extends JPanel {
	private JPanel panelFinal;
	private Jugador jugador;
	private Dealer dealer;
	private MazoDePoker mazo;
	private int apuestas, estado, base,jugada;
	private PanelCartas panelCartas;
	private JPanel panel;
	private ArrayList<Cartas> comunitarias;
	private int[] nPalos = new int[4];
	private int[][] posiciones = new int[4][7];
	private int[] hay = new int[2];
	private int[] grupos = new int[14];
	private int[] numeroJugadas; 
	private ArrayList<Jugador> jugadores;

	public ControlAux() {
		initgui();

		this.setPreferredSize(new Dimension(1200, 670));
		this.setBackground(new Color(50, 50, 50, 0));
	}

	public void initgui() {

		base = 25;
		jugada=0;
		jugadores = new ArrayList<>();

		comunitarias = new ArrayList<>();

		dealer = new Dealer();
		add(dealer, BorderLayout.NORTH);

		panelCartas = new PanelCartas();

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		panelCartas.add(panel);

		add(panelCartas, BorderLayout.CENTER);

		jugador = new Jugador("jugador1", true,1000);
		add(jugador, BorderLayout.SOUTH);

		mazo = new MazoDePoker();
		jugadores.add(jugador);
		jugadores.add(dealer);
		
	
		reparto();

	}

	public void reparto() {
		for (int i = 0; i < jugadores.size(); i++) {
			Cartas aux = mazo.getMazo().get(0);
			jugador.repartir(aux);
			mazo.getMazo().remove(0);
			aux = mazo.getMazo().get(0);
			dealer.repartir(aux);
			mazo.getMazo().remove(0);
		}
		for (int i = 0; i < 5; i++) {
			Cartas aux = mazo.getMazo().get(0);
			aux.setVisible(false);
			jugador.getMano().add(aux);
			dealer.getMano().add(aux);
			comunitarias.add(aux);
			panel.add(aux);
			mazo.getMazo().remove(aux);
		}
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
				jugador.estado = 0;
			}
			break;
		case 1:
			jugador.setApuesta(aux + base);
			setApuestas(base + aux);
			if (jugador.getDinero() == 0) {
				jugador.estado = 4;
			} else {
				jugador.estado = 0;
			}
		}
	}

	public void etapaJuego() {

		if (jugador.getEstado() == 1 || jugador.getEstado() == 0) {
			panelCartas.actualizar(apuestas);
			estado++;
			met();
		} else if (jugador.estado == 4) {
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

		case 4:
		case 3:
			for (int i = 0; i < 5; i++) {
				comunitarias.get(i).setIcono();
				comunitarias.get(i).setVisible(true);
			}
			dealer.mano.get(0).setIcono();
			dealer.mano.get(1).setIcono();
			jugador.mano.get(0).setIcono();
			jugador.mano.get(1).setIcono();
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
		jugada =sumaJugada(jugador.getArrayId());
		cuantosPalos(jugador.getArrayPalo());
		escaleraReal(jugador);
		escaleraColor(jugador);
		cuantosGrupos(cualGrupo(jugador),jugador);
		color(jugador);
		escalera(jugador);
		cartaMayor(jugador);
		 numeroJugadas = new int[9];
	int aux = numeroJugadas[0];


			 /*{analizarP,analizarT,analizarPok,analizarE,analizarEc,analizarFu,
					analizarRoy,analizarCrMay,analizaresc};*/
		System.out.println(Arrays.toString(jugador.getArrayId()));
		for (int i = 0; i < jugador.getArrayJugadas().size();i++) {
			System.out.println(jugador.getArrayJugadas().get(i));
		}
		for(int i = 0; i<numeroJugadas.length;i++) {
			if(numeroJugadas[i]>aux) {
				aux = numeroJugadas[i];
			}
		}
		System.out.println("La jugada mayor es "+aux);
		 nPalos =new int[4];
		 posiciones = new int[4][7];
		 hay = new int[2];
		 grupos = new int[14];
		 jugada=0;
	}
	
	public void gano() {
		comprobarJugada(jugador);
		comprobarJugada(dealer);
		System.out.println("jugador puntos "+jugador.puntos);
		System.out.println("dealer puntos "+dealer.puntos);
		if(jugador.puntos>dealer.puntos ) {
			System.out.println("Gana jugador");
			
		}
		 if(jugador.puntos<dealer.puntos) {
			System.out.println("Gana dealer");
		}
		
	
	System.out.println(jugador.dinero);
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
		//	System.out.println(Arrays.toString(posiciones[i]));
		}

		
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
		for (int i = 0; i < posiciones[posicion].length; i++) {
			if (posiciones[posicion][i] != 0) {
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
				jugador.puntos += 1000000000;
				jugador.getArrayJugadas().add("Escalera Real");
				numeroJugadas[0]= 10;
			}
			comprobar = 10;
		}
	}

	public void escaleraColor(Jugador jugador) {

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
				jugador.puntos += 100 * valores;
				jugador.getArrayJugadas().add("Escalera Color");
				numeroJugadas[1] = 9;
			}
		}
	}

	public int[] cualGrupo(Jugador jugador) {

		for (int i = 0; i < jugador.getArrayId().length; i++) {
			for (int j = 0; j < jugador.getArrayId().length; j++) {
				if (jugador.getArrayId()[i] == jugador.getArrayId()[j]) {
					grupos[jugador.getArrayId()[i]-1] += 1;
				}
			}
		}
		return grupos;
	}
//modificar
	public void cuantosGrupos(int[] cualGrupo,Jugador jugador) {
		int[] pares = new int[4];
		int[] trios = new int[3];
		int poker = 0;
		int x = 0;
		int y = 0;
		for (int i = 0; i < cualGrupo.length; i++) {

			switch (cualGrupo[i]) {
			case 4:
				pares[x] = i;
				x++;
				break;
			case 9:
				trios[y] = i;
				y++;
				break;
			case 16:
				poker = i;
			}
		}
		for (int i = 0; i < pares.length - 1; i++) {
			pares[pares.length - 1] += pares[i];
		}
		for (int i = 0; i < trios.length - 1; i++) {
			trios[trios.length - 1] += trios[i];
		}
		if (poker != 0) {
			jugador.getArrayJugadas().add("Poker");
			jugador.puntos += poker * 11*jugada;
			numeroJugadas[2]= 8;
		}
		if (trios[0] != 0 && pares[0] != 0) {
			jugador.getArrayJugadas().add("Full");
			jugador.puntos += (trios[2] + pares[3]) * 8*jugada;
			numeroJugadas[3]= 7;
		}
		if (trios[0] != 0) {
			for (int i = 0; i < trios.length - 1; i++) {
				if(trios[i]!=0) {
					jugador.getArrayJugadas().add("trio");
					numeroJugadas[6] = 5;
					}
				}
			jugador.puntos += trios[2] *jugada* 4;
		}
		if (pares[0] != 0) {
			for (int i = 0; i < pares.length - 1; i++) {
				if(pares[i]!=0) {
					jugador.getArrayJugadas().add("par");
					numeroJugadas[7] = 4;
					}
			}
			jugador.puntos += pares[3]*jugada * 2;
			
		}
	}

	public void color(Jugador jugador) {
		if(escaleraP(hay[1])) {
			jugador.puntos+=jugada*5;
			numeroJugadas[4] = 8;
		}
	}

	public void escalera(Jugador jugador ) {
		Arrays.sort(jugador.getArrayId());
		int contador=0;
		int aux=jugador.getArrayId()[0];
		for(int i=1;i<jugador.getArrayId().length;i++) {
			if(aux+1==jugador.getArrayId()[i]){
				contador++;
				aux++;
			}
		}
		if(contador>=4) {
			jugador.puntos+=jugada*5;
			numeroJugadas[5] = 7;
		}
	}

	public void cartaMayor(Jugador jugador) {
		jugador.puntos+=sumaJugada(jugador.getArrayId());
		numeroJugadas[0]= 1;
	}
	
	public int sumaJugada(int[] jugada) {
		int aux=0;
		for(int i =0;i<jugada.length;i++) {
			aux+=jugada[i];
		}
		return aux;
	}
	
}