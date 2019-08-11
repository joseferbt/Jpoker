package juegoPoker;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ControlPoker extends JPanel {

	private Jugador jugador;
	private Dealer dealer;
	private MazoDePoker mazo;
	private int jugadores,apuestas,estado,analizarJ,analizarP;
	private PanelCartas panelCartas;
	private JPanel panel;
	private Escucha escucha;
	private ArrayList<Cartas> comunitarias;
	private ArrayList<Cartas> jugadaJugador;
	private ArrayList<Cartas> jugadaPc;


	public ControlPoker() {
		initgui();

		this.addMouseListener(escucha);

		jugadaJugador = new ArrayList();
		jugadaPc = new ArrayList();

		this.setPreferredSize(new Dimension(1200,670));
		this.setBackground(new Color(50,50,50,0));
		llenarComparacionJ();
		llenarComparacionP();
		pareja(jugadaJugador);
}

	public void initgui() {
			this.setLayout(new BorderLayout());
			jugadores=2;

			escucha = new Escucha();

			comunitarias = new ArrayList<>();

			dealer = new Dealer();
			add(dealer,BorderLayout.NORTH);
			
			panelCartas= new PanelCartas();
			
			panel = new JPanel();
			panel.setBackground(new Color(0,0,0,0));
			panelCartas.add(panel);
			
			add(panelCartas,BorderLayout.CENTER); 
			
			jugador = new Jugador("jugador1",true);
			add(jugador,BorderLayout.SOUTH);
			
			mazo = new MazoDePoker();
			
			reparto();

}


	public void llenarComparacionJ(){
		
			
			this.jugadaJugador.add(comunitarias.get(0));
			this.jugadaJugador.add(comunitarias.get(1));
			this.jugadaJugador.add(comunitarias.get(2));
			this.jugadaJugador.add(comunitarias.get(3));
			this.jugadaJugador.add(comunitarias.get(4));
			this.jugadaJugador.add(jugador.getMano().get(0));
			this.jugadaJugador.add(jugador.getMano().get(1));
			
			
		}



	public void llenarComparacionP(){
	
		this.jugadaPc.add(comunitarias.get(0));
		this.jugadaPc.add(comunitarias.get(1));
		this.jugadaPc.add(comunitarias.get(2));
		this.jugadaPc.add(comunitarias.get(3));
		this.jugadaPc.add(comunitarias.get(4));
		this.jugadaPc.add(dealer.getMano().get(0));
		this.jugadaPc.add(dealer.getMano().get(1));
 }

	public void pareja(ArrayList<Cartas> array){
		for(int i = 0; i<(array.size()); i++){
			for(int j = 0; j<array.size();j++){
				if(array.get(i).getId()  == array.get(j).getId()){
					analizarP = 2;

	}
			}
		}
		System.out.println(analizarP);

	}
/*
	public void triple(ArrayList<Cartas> array) {
		for(int i = 0; i<(array.size()); i++){
			for(int j = 0; j<array.size();j++){
				for(int k = 0; k<array.size();k++) {
					if(array.get(i) == array.get(j) == array.get(k)) {
						
					}
				}

	}
		}
	}
*/	
	public void reparto() {
		for(int i=0;i<jugadores;i++) {
			Cartas aux = mazo.getMazo().get(0);
			jugador.repartir(aux);
			mazo.getMazo().remove(0);
			aux = mazo.getMazo().get(0);
			dealer.repartir(aux);
			mazo.getMazo().remove(0);
	}

		for(int i=0;i<5;i++) {
			Cartas aux = mazo.getMazo().get(0);
			comunitarias.add(aux);
			panel.add(aux);
			mazo.getMazo().remove(aux);
		}
		
		estado=0;

}

	public void met() {
		updateUI();
		panelCartas.updateUI();
	}

	public void jugada() {

	}

		private class Escucha extends MouseAdapter {
			@Override 
		public  void mouseEntered(MouseEvent evento) {
				int aux=apuestas;
				apuestas=jugador.apostado;

				if (aux!=apuestas&& jugador.getEstado()!=2) {
					panelCartas.actualizar(2*apuestas);
					estado++;

					met();
					jugador.turno();
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
		}	
	}

