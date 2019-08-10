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
	private int jugadores, apuestas, estado, base,analizarP;
	private PanelCartas panelCartas;
	private JPanel panel;
	private ArrayList<Cartas> jugadaJ;
	private ArrayList<Cartas> jugadaPc;
	private ArrayList<Cartas> comunitarias;
	private Cartas cartaComprobacion;
	private int[] arrayComprobacion,arrayDoble;
	public ControlPoker() {
		initgui();

		this.setPreferredSize(new Dimension(1200, 670));
		this.setBackground(new Color(50, 50, 50, 90));
		arrayComprobacion = new int[7];
		llenarComparacionJ();
		llenarComparacionP();
		escaleraJugador();
		pokerJugador();
		dobleParejaJugador();
	}
	
	public void llenarComparacionJ(){
			jugadaJ = new ArrayList<Cartas>();
		this.jugadaJ.add(jugador.getMano().get(0));
		this.jugadaJ.add(jugador.getMano().get(1));
		this.jugadaJ.add(comunitarias.get(0));
		this.jugadaJ.add(comunitarias.get(1));
		this.jugadaJ.add(comunitarias.get(2));
		this.jugadaJ.add(comunitarias.get(3));
		this.jugadaJ.add(comunitarias.get(4));

	}

	public void llenarComparacionP(){
			jugadaPc = new ArrayList<Cartas>();
		this.jugadaPc.add(dealer.getMano().get(0));
		this.jugadaPc.add(dealer.getMano().get(1));
		this.jugadaPc.add(comunitarias.get(0));
		this.jugadaPc.add(comunitarias.get(1));
		this.jugadaPc.add(comunitarias.get(2));
		this.jugadaPc.add(comunitarias.get(3));
		this.jugadaPc.add(comunitarias.get(4));

}

	public void parejaJugador(){

		if(jugador.getMano().get(0).getId() == jugador.getMano().get(1).getId()) {
			cartaComprobacion = new Cartas(jugador.getMano().get(0).getId(),jugador.getMano().get(0).getPalo());
			jugadaJ.remove(0);
			jugadaJ.remove(1);
			analizarP = 2;
			
		}
		else{
			analizarP = 1;
		}
	}
	
	public void trioJugador() {
		parejaJugador();			
		if(analizarP == 2) {
		for(int i = 0;i<jugadaJ.size();i++){
				if (cartaComprobacion.getId() == jugadaJ.get(i).getId()) {
					analizarP = 3;
					jugadaJ.remove(i);
				}
				else {
					System.out.println("Hay pareja pero no hay trio");
					analizarP = 345;
				}
		}
		}
				if(analizarP == 1) {
					System.out.println("No hay pareja por ende no hay trio");
					analizarP = 100;
				}
			
		System.out.println(analizarP);

		}
	

	public void pokerJugador() {
		trioJugador();
		if (analizarP == 3) {
		for (int i = 0; i < jugadaJ.size(); i++) {
			
				if (cartaComprobacion.getId() == jugadaJ.get(i).getId()) {
					analizarP = 8;
					jugadaJ.remove(i);
				}
			}
		System.out.println(analizarP);
		}
			if(analizarP == 100) {
				System.out.println("No hay pareja, ni trio, no hay poker");
				System.out.println(analizarP);

			}
			
			if(analizarP == 345) {
				System.out.println("Hay pareja pero no hay trio");
				System.out.println(analizarP);
			}
	}
		
	

	public void escaleraJugador(){
		int aux;
		int aux2;
		int  ejemplo = -234;
		parejaJugador();
		 if(analizarP == 1 ){
			 //llenar el arreglo de los numeros de las cartas
			 for(int ik = 0; ik<(jugadaJ.size());ik++) {
				 this.arrayComprobacion[ik] = jugadaJ.get(ik).getId();
			 }
				 
				 //Acomodar arreglo
			 for(int i = 0; i<(jugadaJ.size())-1; i++) {
				 for(int j = 0;j<(jugadaJ.size())-1;j++) {
					 if(arrayComprobacion[j] > arrayComprobacion[j+1]) {
						 aux = arrayComprobacion[j];
						 arrayComprobacion[j] = arrayComprobacion[j+1];
						 arrayComprobacion[j+1] = aux;
					 }
				 }
		
			 }
			 //Mirar si estan en escalera
			 for(int i = 0; i<5;i++) {
				 aux2 = arrayComprobacion[i];
				 if(aux2+1 == arrayComprobacion[i+1]) {
					 analizarP = 4;
				 }
				 else if(aux2 == arrayComprobacion[i+1]) {
					 ejemplo = 40;
				 }
				 else {
					 ejemplo = 99;
				 }
				 
			 }
			 
			 for(int i = 0; i<arrayComprobacion.length;i++)
			 System.out.println(arrayComprobacion[i]);
			 System.out.println(ejemplo);
		}
		 if(analizarP == 2) {
			 System.out.println("No hay escalera por que hay pareja");
		 }
		 
	}

	public void dobleParejaJugador(){
		parejaJugador();
		
		int auxDoble;
		if(analizarP == 2) {
		
			for(int j = 0; j<jugadaJ.size(); j++) {
				for(int k = 0; k<jugadaJ.size();k++) {
					if(jugadaJ.get(j).getId() == jugadaJ.get(k).getId()) {
						analizarP = 5;
						System.out.println("Hay doble pareja");
					}
					else {
						analizarP = -5;
					}
				}
			}
			System.out.println(analizarP);
		}
		if(analizarP == 1) {
			System.out.println("No hay doble pareja por que no hay ni una pareja");
		}
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