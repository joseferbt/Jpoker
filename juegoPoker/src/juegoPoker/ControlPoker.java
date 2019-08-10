package juegoPoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.util.ArrayList;

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
		this.setBackground(new Color(50, 50, 50, 90));
	}

	public void initgui() {

		base = 10;

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
			comunitarias.add(aux);
			panel.add(aux);
			mazo.getMazo().remove(aux);
		}
		estado = 0;

	}

	public void met() {
		updateUI();
		panelCartas.updateUI();

	}

	public Jugador jugador() {
		return jugador;
	}

	public void etapaJuego() {
		int aux = apuestas;
		apuestas = jugador.apostado;

		if (aux != apuestas && jugador.getEstado() != 2) {
			panelCartas.actualizar(2 * apuestas);
			estado++;

			met();
		}
		switch (estado) {
		case 1:
			comunitarias.get(0).setIcono();
			comunitarias.get(1).setIcono();
			comunitarias.get(2).setIcono();
			break;
		case 2:
			comunitarias.get(3).setIcono();
			break;
		case 3:
			comunitarias.get(4).setIcono();
			break;
		}
	}

	public int getBase() {
		return base;
	}

	public void setApuestas(int apuesta) {
		apuestas += apuesta * 2;
	}
}
