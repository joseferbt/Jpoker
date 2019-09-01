package juegoPoker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientePoker extends JFrame implements Runnable{
	private Jugador jugador1,jugador2,jugador3,jugador4;
	private PanelCartas comunitario;
	private JButton ver,retirar,pasar,ir;
	private ServerSocket server;
	private Socket sc;
	private JOptionPane optionPane;
	private String host;
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
		puerto = PUERTO;
	}
	
		public void initGUI() {
			
		}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			sc = new Socket(host,puerto);
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
