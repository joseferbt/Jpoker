package juegoPoker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Dealer extends Jugador  {

	public Dealer() {
		setBackground(new Color(0,0,0,0));
		texto = "Dealer";
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.black);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		g.drawString(texto, 10, 20);		
	}
	
}
