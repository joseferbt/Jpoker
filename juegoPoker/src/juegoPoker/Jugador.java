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

public class Jugador extends JPanel  implements Runnable{
	
	protected JPanel panelCartas,panelBotones;
	public int apostado;
	protected int dinero, apuesta,x,y,prueba,alto,estado;
	public boolean turno;
	protected String texto,nombre;
	private JButton verCartas,ir,pasar,subir,retiro;
	private SpinnerNumberModel modelo;
	private JSpinner cuadro;
	private Escucha escucha;
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
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setPreferredSize(new Dimension(400,150));
		
		mano = new ArrayList<Cartas>();
		escucha = new Escucha();
		
		panelCartas = new JPanel();
		panelCartas.setBackground(new Color(0,0,0,0));
		panelCartas.setPreferredSize(new Dimension(250,140));
		panelCartas.setLayout(null);
		
		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(0,0,0,0));
		panelBotones.setPreferredSize(new Dimension(250,140));
		panelBotones.setLayout(null);
		
		verCartas=new JButton("ver");
		verCartas.addMouseListener(escucha);
		verCartas.setBounds(10,35,52,alto);
		//verCartas.setVisible(false);
		panelBotones.add(verCartas);
		
		ir = new JButton("ir");
		ir.setBounds(10, 70, 42,alto);
		ir.addActionListener(escucha);
		//ir.setVisible(false);
		panelBotones.add(ir);
		
		subir = new JButton("subir");
		subir.addActionListener(escucha);
		subir.setBounds(10,105,65,alto);
		//subir.setVisible(false);
		panelBotones.add(subir);
		
		modelo = new SpinnerNumberModel(apuesta,apuesta,dinero,apuesta);
	
		cuadro = new JSpinner(modelo);
		cuadro.setBounds(80,104,50,alto);
		//cuadro.setVisible(false);
		panelBotones.add(cuadro);
		
		pasar = new JButton(new ImageIcon("src/recursos/pasar.jpg"));
		pasar.setBorderPainted(false);
		pasar.addActionListener(escucha);
		pasar.setBounds(75, 35,30, 10);
		//pasar.setVisible(false);
		panelBotones.add(pasar);
		
		retiro = new JButton("R");
		retiro.addActionListener(escucha);
		retiro.setBounds(75, 50, 20, 20);
		panelBotones.add(retiro);
		
		panelBotones.setBounds(0, 0, x, 150);
		panelCartas.add(panelBotones);
		
		panelCartas.setBounds(10, 0, 400, 150);
		add(panelCartas,BorderLayout.EAST);
		if(!turno) {
			this.verCartas.setVisible(false);
			ir.setVisible(false);
			subir.setVisible(false);
			retiro.setVisible(false);
			pasar.setVisible(false);
			cuadro.setVisible(false);
		}
	}
	public Jugador() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400,150));
		mano = new ArrayList<Cartas>();
		panelCartas = new JPanel();
		panelCartas.setBackground(new Color(50,0,0,0));
		panelCartas.setPreferredSize(new Dimension(180,140));
		add(panelCartas,BorderLayout.EAST);
	}
	
	public void apuesta(int apostar) {
		apuesta=apostar;
	}
	
	
	public void asd() {
		repaint();
	}
	
	public void repartir(Cartas carta) {
		this.mano.add(carta);
		carta.setBounds(x, y, 85, 130);
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
	
	public void anadir(int i) {
		add(mano.get(i));
	}
	
	public void turno() {
		turno=true;
		if(!turno) {
			this.verCartas.setVisible(true);
			ir.setVisible(true);
			subir.setVisible(true);
			retiro.setVisible(true);
			pasar.setVisible(true);
			cuadro.setVisible(true);
			}
		}
	public int getEstado() {
		return estado;
	}
	
	private class Escucha extends MouseAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(turno) {
				
				if(e.getSource()== ir) {
					prueba=1;
					if(apuesta<= dinero) {
						dinero -= apuesta;
						apostado+=apuesta;
						repaint();
						estado = 0;
						}
					}
				if (e.getSource()== subir) {
					dinero -= Integer.valueOf(cuadro.getModel().getValue().toString())+apuesta;
					apostado+=apuesta+Integer.valueOf(cuadro.getModel().getValue().toString());
					estado = 0;
					repaint();
				}
				if(e.getSource() == pasar) {
					estado = 1;
				}
				if(e.getSource()==retiro) {
					estado = 2;
				}
					turno=false;
			}
		}
		@Override 
		public  void mouseEntered(MouseEvent evento) {
				mano.get(0).setIcono();
				mano.get(1).setIcono();
		}
		public  void mouseExited(MouseEvent evento) {
			mano.get(0).setIcon(mano.get(0).defoutl);
			mano.get(1).setIcon(mano.get(0).defoutl);
		}
		
		}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
