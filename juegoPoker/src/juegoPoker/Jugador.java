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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Jugador extends JPanel {
	
	protected JPanel panelCartas,panelBotones;
	public int apostado;
	protected int dinero, apuesta,x,y,prueba,alto,estado;
	public boolean turno;
	protected String texto,nombre;

	protected ArrayList<Cartas> mano;
	
	public Jugador(String nombre,boolean turno) {
		dinero=1000;
		apuesta=10;// borrar
		this.turno= turno;
		this.nombre = nombre;
		texto= nombre+" Dinero : ";
		apostado=0;
		x=200;y=10;
		alto=22;
		
		setBackground(new Color(0,0,0,50));
		setLayout(null);
		setPreferredSize(new Dimension(400,150));
		
		mano = new ArrayList<Cartas>();

		panelCartas = new JPanel();
		panelCartas.setBackground(new Color(0,0,0,0));
		panelCartas.setPreferredSize(new Dimension(180,140));
		panelCartas.setBounds(200, 0, 180, 140);
		//panelCartas.setLayout(null);
		add(panelCartas);
		}

	public Jugador() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400,150));
		mano = new ArrayList<Cartas>();
		panelCartas = new JPanel();
		panelCartas.setBackground(new Color(0,0,0,0));
		panelCartas.setPreferredSize(new Dimension(180,140));
		add(panelCartas,BorderLayout.EAST);
	}
	
	public void apuesta(int apostar) {
		apuesta=apostar;
	}
	
	
	public void imprimir() {
		repaint();
	}
	
	public void repartir(Cartas carta) {
		this.mano.add(carta);
		carta.setBounds(0, y, 85, 130);
		panelCartas.add(carta);
		panelCartas.updateUI();
		x+=90;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		if(dinero>0) {
			g.drawString(texto+Integer.toString(dinero), 10, 20);		
		}else {
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
	public void setApuesta(int valor ) {
		 dinero -= valor;
		 repaint();
	}
	public void setTurno(boolean valor) {
		turno=valor;
	}
		
}
