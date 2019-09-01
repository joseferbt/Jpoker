package juegoPoker;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClientePoker extends JFrame implements Runnable{
	private ArrayList<Jugador> jugadores;
	private PanelCartas comunitario;
	private JButton ver,retirar,pasar,ir;
	private ServerSocket server;
	private Socket sc;
	private JOptionPane optionPane;
	private String host;
	private JPanel panelBotones;
	private int puerto;
	private DataOutputStream salida;
	private DataInputStream entrada;
	
	public ClientePoker(String host,int PUERTO) {
		host = host;
		optionPane = new JOptionPane();
		setSize(1200,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Texas Hold´em");
		jugadores = new ArrayList<Jugador>();
		puerto = PUERTO;
	}
	
		public void initGUI() {
			panelBotones = new JPanel();
			add(jugadores.get(0),BorderLayout.NORTH);
			add(jugadores.get(1),BorderLayout.NORTH);
			comunitario = new PanelCartas();
			add(jugadores.get(2),BorderLayout.SOUTH);
			add(jugadores.get(3),BorderLayout.SOUTH);
			add(comunitario,BorderLayout.CENTER);
			
		}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			sc = server.accept();
			optionPane.showMessageDialog(optionPane, "Has iniciado el juego");
			salida = new DataOutputStream(sc.getOutputStream());
			entrada = new DataInputStream(sc.getInputStream());
			iniciarWhile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void iniciarWhile() {
		while(true) {
			initGUI();
		}
	}
	

}
