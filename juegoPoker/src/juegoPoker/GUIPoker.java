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
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GUIPoker extends JFrame {
	private ControlPoker controlPoker;
	private JButton verCartas, ir, pasar, subir, retiro;
	private SpinnerNumberModel modelo;
	private Escucha escucha;
	private JSpinner cuadro;
	private JPanel panelBotones;
	private int alto;

	public GUIPoker() {
		initGui();

		// configuracion Default Windows
		 setSize(1200,700);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initGui() {
		alto=22;
		escucha = new Escucha();
		
		panelBotones=new JPanel();
		panelBotones.setLayout(null);
		panelBotones.setBackground(new Color(0,0,0,0));
		panelBotones.setPreferredSize(new Dimension(200,140));
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

		modelo = new SpinnerNumberModel(controlPoker.getBase(), controlPoker.getBase(), controlPoker.jugador().getDinero(), controlPoker.getBase());

		cuadro = new JSpinner(modelo);
		cuadro.setBounds(80, 104, 50, alto);
		// cuadro.setVisible(false);
		panelBotones.add(cuadro);

		pasar = new JButton(new ImageIcon("src/recursos/pasar.jpg"));
		pasar.setBorderPainted(false);
		pasar.addActionListener(escucha);
		pasar.setBounds(75, 35, 30, 10);
		// pasar.setVisible(false);
		panelBotones.add(pasar);

		retiro = new JButton("R");
		retiro.addActionListener(escucha);
		retiro.setBounds(75, 50, 20, 20);
		panelBotones.add(retiro);

		//panelBotones.setBounds(0, 0, 200, 150);
		controlPoker.jugador().add(panelBotones);
	}

	private class Escucha extends MouseAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		/*	if (juega) {

				if (e.getSource() == ir) {
					if (controlPoker.getBase() <= dinero) {
						dinero -= controlPoker.getBase();
						apostado += controlPoker.getBase();
						repaint();
						estado = 0;
					}
				}
				if (e.getSource() == subir) {
					dinero -= Integer.valueOf(cuadro.getModel().getValue().toString()) + controlPoker.getBase();
					apostado += controlPoker.getBase() + Integer.valueOf(cuadro.getModel().getValue().toString());
					estado = 0;
					repaint();
				}
				if (e.getSource() == pasar) {
					estado = 1;
				}
				if (e.getSource() == retiro) {
					estado = 2;
				}
				turno = false;
			}*/
		}

		@Override
		public void mouseEntered(MouseEvent evento) {
			/*
			mano.get(0).setIcono();
			mano.get(1).setIcono();
		}

		public void mouseExited(MouseEvent evento) {
			mano.get(0).setIcon(mano.get(0).defoutl);
			mano.get(1).setIcon(mano.get(0).defoutl);
		}
*/
	}
	}
}
