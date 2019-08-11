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
	private int jugadores, apuestas, estado, base;
	private PanelCartas panelCartas;
	private JPanel panel;
	private ArrayList<Cartas> comunitarias;
	


	public ControlPoker() {
		initgui();

		this.setPreferredSize(new Dimension(1200, 670));
		this.setBackground(new Color(50, 50, 50, 0));
	
	}
	
	public void initgui() {

		base = 25;

		jugadores = 2;

		comunitarias = new ArrayList<>();

		dealer = new Dealer();
		add(dealer, BorderLayout.NORTH);

		panelCartas = new PanelCartas();

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		panelCartas.add(panel);

		add(panelCartas, BorderLayout.CENTER);

		jugador = new Jugador("jugador1", true);
		add(jugador, BorderLayout.SOUTH);

		mazo = new MazoDePoker();

		reparto();

	}

	public void reparto() {
		for (int i = 0; i < jugadores; i++) {
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
		System.out.println(Arrays.toString(jugador.getArrayValor()));
		System.out.println(Arrays.toString(jugador.getArrayId()));
		System.out.println(Arrays.toString(jugador.getArrayPalo()));
		System.out.println(Arrays.toString(dealer.getArrayValor()));
		System.out.println(Arrays.toString(jugador.getArrayId()));
		System.out.println(Arrays.toString(jugador.getArrayPalo()));
		//System.out.println(Arrays.toString(jugador.getArrayPalo()));
	}

	public void met() {
		updateUI();
		panelCartas.updateUI();

	}

	public Jugador jugador() {
		return jugador;
	}
	
	public void jugada(int entrada,int aux) {
		switch(entrada) {
			case 0:	
				if(base<=jugador.getDinero()) {
					jugador.setApuesta(base);
					setApuestas(base);
					jugador.estado = 0;
					}break;
			case 1:
					jugador.setApuesta(aux + base);
					setApuestas(base + aux);
					if(jugador.getDinero()==0) {
						jugador.estado = 4;
					}else {jugador.estado =0;}
					}
		}

	public void etapaJuego() {

		if ( jugador.getEstado() == 1||jugador.getEstado() == 0) {
			panelCartas.actualizar( apuestas);
			estado++;
			met();
		}else if(jugador.estado==4) {
			estado=4;
		}
		
		
		switch (estado) {
		case 1:
			for(int i = 0; i<3;i++ ) {
				comunitarias.get(i).setIcono();
				comunitarias.get(i).setVisible(true);
			}
			break;
		case 2:
			comunitarias.get(3).setVisible(true);
			comunitarias.get(3).setIcono();
			break;
		
		case 4 : case 3:
			for(int i = 0; i<5;i++ ) {
				comunitarias.get(i).setIcono();
				comunitarias.get(i).setVisible(true);
			}
			dealer.mano.get(0).setIcono();
			dealer.mano.get(1).setIcono();
			break;
		}
	}

	public int getBase() {
		return base;
	}

	public void setApuestas(int apuesta) {
		apuestas += apuesta * 2;
	}
	
	public int cuantosPalos(String[] array) {
		return 0;
	}
	
	public void EscaleraReal(Jugador jugador) {
		for(int i =0;i<jugador.mano.size();i++) {
		if(true) {
			
		}
		}
	}
	
}