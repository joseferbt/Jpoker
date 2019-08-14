package juegoPoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Jugador extends JPanel {

	private JPanel panelCartas, panelBotones;
	private int dinero, apuesta, x, y, puntos, alto, estado;
	public boolean turno;
	protected String texto, nombre;
	private int[] arrayId,jugada,misId;
	private String[] arrayPalo, arrayValor;
	private ArrayList<Cartas> mano;
	private ArrayList<String> jugadas;

	public Jugador(String nombre, boolean turno, int dinero) {
		misId=new int[2];
		arrayId = new int[7];
		arrayPalo = new String[7];
		arrayValor = new String[7];
		jugadas = new ArrayList<>();
		jugada=new int[10];
		this.setDinero(dinero);
		apuesta = 0;// borrar
		this.turno = turno;
		this.nombre = nombre;
		texto = nombre + " Dinero : ";

		x = 200;
		y = 10;
		alto = 22;

		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		setPreferredSize(new Dimension(400, 150));

		mano = new ArrayList<Cartas>();

		panelCartas = new JPanel();
		panelCartas.setBackground(new Color(0, 0, 0, 0));
		panelCartas.setPreferredSize(new Dimension(180, 140));
		panelCartas.setBounds(200, 0, 180, 140);
		// panelCartas.setLayout(null);
		add(panelCartas);
	}

	public Jugador() {
		misId=new int[2];
		jugada=new int[10];
		arrayId = new int[7];
		arrayPalo = new String[7];
		arrayValor = new String[7];
		jugadas = new ArrayList<>();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 150));
		mano = new ArrayList<Cartas>();
		panelCartas = new JPanel();
		panelCartas.setBackground(new Color(0, 0, 0, 0));
		panelCartas.setPreferredSize(new Dimension(180, 140));
		add(panelCartas, BorderLayout.EAST);
	}

	public void imprimir() {
		repaint();
	}

	public void repartir(Cartas carta) {
		this.mano.add(carta);
		carta.setIcon(carta.defoutl);
		carta.setBounds(0, y, 85, 130);
		panelCartas.add(carta);
		panelCartas.updateUI();
		x += 90;
		

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		if (getDinero() > 0) {
			g.drawString(texto + Integer.toString(getDinero()), 10, 20);
		} else {
			g.drawString(texto, 10, 20);
		}
	}

	public int getEstado() {
		return estado;
	}

	public int getDinero() {
		return dinero;
	}

	public ArrayList<Cartas> getMano() {
		return mano;
	}

	public void setApuesta(int valor) {
		setDinero(getDinero() - valor);
		repaint();
		turno=false;
	}
	
	public void remover(int index) {
		mano.remove(0);
		
		if(index==0) {
			panelCartas.remove(0);
			panelCartas.remove(0);
			x = 200;
			y = 10;
			this.updateUI();
			panelCartas.updateUI();
			for(int i =jugadas.size()-1;i>=0;i--)
			jugadas.remove(i);
			}
		setPuntos(0);
		jugada = new int[10];

	
	}

	public void setTurno(boolean valor) {
		turno = valor;
	}

	public void setArray() {
		for (int i = 0; i < mano.size(); i++) {
			arrayValor[i] = mano.get(i).getValor();
		}
		misId[0]= mano.get(0).getId();
		misId[1]=mano.get(1).getId();
		Arrays.sort(arrayValor);
		for (int i = 0; i < 7; i++) {
			if (arrayValor[i].length() > 2) {
				arrayId[i] = Integer.valueOf(arrayValor[i].substring(0, 2));
				arrayPalo[i] = arrayValor[i].substring(2);
			} else {
				arrayId[i] = Integer.valueOf(arrayValor[i].substring(0, 1));
				arrayPalo[i] = arrayValor[i].substring(1);
			}
		}
	}
	

	public int[] getArrayId() {
		return arrayId;
	}

	public String[] getArrayPalo() {
		return arrayPalo;
	}

	public String[] getArrayValor() {
		return arrayValor;
	}
	public int[] getMisId() {
		return misId;
	}

	public ArrayList<String> getArrayJugadas() {
		return jugadas;
	}

	public void setArraycartas(ArrayList<Cartas> cartas) {
		mano = cartas;
	}
	public String getNombre() {
		return nombre;
	}
	public int[] getJugada() {
		return jugada;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/**
	 * @param dinero the dinero to set
	 */
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}
}
