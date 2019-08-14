package juegoPoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GUIPoker extends JFrame {
	
	private ControlPoker controlPoker;
	private JOptionPane pane;
	private JButton verCartas, ir, pasar, subir, retiro;
	private SpinnerNumberModel modelo;
	private Escucha escucha;
	private JSpinner cuadro;
	private JPanel panelBotones;
	private int alto,gano;

	public GUIPoker() {
		initGui();

		// configuracion Default Windows
		setSize(700, 700);
		this.setLocation(1400, 0);
		setVisible(true);
		setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initGui() {
		alto = 22;
		escucha = new Escucha();

		panelBotones = new JPanel();
		panelBotones.setLayout(null);
		panelBotones.setBackground(new Color(0, 0, 0, 0));
		panelBotones.setPreferredSize(new Dimension(200, 140));
		controlPoker = new ControlPoker();
		add(controlPoker);

		verCartas = new JButton("ver");
		verCartas.addMouseListener(escucha);
		verCartas.setBounds(10, 35, 52, alto);
		panelBotones.add(verCartas);

		ir = new JButton("ir");
		ir.setBounds(10, 70, 42, alto);
		ir.addActionListener(escucha);
		// ir.setVisible(false);
		panelBotones.add(ir);

		subir = new JButton("subir");
		subir.addActionListener(escucha);
		subir.setBounds(10, 105, 65, alto);
		// subir.setVisible(false);
		panelBotones.add(subir);

		modelo = new SpinnerNumberModel(controlPoker.getBase(), controlPoker.getBase(),
				controlPoker.jugador().getDinero() - controlPoker.getBase(), controlPoker.getBase());
		cuadro = new JSpinner(modelo);
		cuadro.setBounds(80, 104, 50, alto);
		// cuadro.setVisible(false);
		panelBotones.add(cuadro);

		pasar = new JButton("Pasar");
		pasar.setBorderPainted(false);
		pasar.addActionListener(escucha);
		pasar.setBounds(75, 35, 70, alto);
		// pasar.setVisible(false);
		panelBotones.add(pasar);

		retiro = new JButton("Retiro");
		retiro.addActionListener(escucha);
		retiro.setBounds(75, 70, 70, 20);
		panelBotones.add(retiro);
		panelBotones.setBounds(0, 0, 200, 150);
		controlPoker.jugador().add(panelBotones);
		
		
		pane = new JOptionPane();
	}

	private class Escucha extends MouseAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (controlPoker.jugador().turno) {

				if (e.getSource() == ir) {
					controlPoker.jugada(0, 0);
					modelo.setMaximum(controlPoker.jugador().getDinero() - controlPoker.getBase());
					repaint();
				}

				if (e.getSource() == subir) {
					controlPoker.jugada(1, Integer.valueOf(cuadro.getModel().getValue().toString()));
					modelo.setMaximum(controlPoker.jugador().getDinero() - controlPoker.getBase());
					repaint();
				}
				if (e.getSource() == pasar) {
					controlPoker.jugador().estado = 1;
					repaint();
				}
				if (e.getSource() == retiro) {
					controlPoker.jugador().estado = 2;
				}
				// controlPoker.jugador().turno = false;
				controlPoker.etapaJuego();
				if(controlPoker.getEstado() == 4){
			
				if(controlPoker.getGano() ){
					int gano = pane.showConfirmDialog(null,controlPoker.getTexto()+"Ganaste!!!!! ¿Quieres seguir jugando?","Termino la partida", pane.YES_NO_OPTION);
					if(gano == pane.YES_OPTION){
						controlPoker.iniciarJuego();
					}
					if(gano == pane.NO_OPTION){
						System.exit(0);
					}
				}
				
				if(!controlPoker.getGano()){
					int gano = pane.showConfirmDialog(null,controlPoker.getTexto()+"Perdiste!! ¿Quieres seguir jugando?","Termino la partida", pane.YES_NO_OPTION);
					if(gano == pane.YES_OPTION){
						controlPoker.iniciarJuego();
					}
					if(gano == pane.NO_OPTION){
						System.exit(0);
					}
				}
				repaint();
			}
			}
		}

		@Override
		public void mouseEntered(MouseEvent evento) {
			
			controlPoker.jugador().getMano().get(0).setIcono();
			controlPoker.jugador().getMano().get(1).setIcono();

		}

		public void mouseExited(MouseEvent evento) {
			if (controlPoker.getEstado()<4) {
				controlPoker.jugador().getMano().get(0).setIcon(controlPoker.jugador().getMano().get(1).defoutl);
				controlPoker.jugador().getMano().get(1).setIcon(controlPoker.jugador().getMano().get(1).defoutl);
			}
		}

	}
}
