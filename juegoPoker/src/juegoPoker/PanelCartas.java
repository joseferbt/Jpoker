package juegoPoker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelCartas extends JPanel {
	private int apuesta;
	private String texto;
	
	public PanelCartas() {

		setBackground(new Color(0,0,0,0));
		setPreferredSize(new Dimension(700,350));
		texto="Apuestas : "; 
	}
	
	public void actualizar(int apuesta) {
		this.apuesta=apuesta;
		repaint();
		updateUI();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		g.drawString(texto+apuesta, 5, 15);
	}
}
