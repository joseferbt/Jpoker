package juegoPoker;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cartas extends JLabel {
	public final ImageIcon defoutl= new ImageIcon("src/recursos/reverso.png"); 
	private ImageIcon imagen;

	private String valor;
	
	// palo toma valores de S(),C(),H(),D(diamond)
	public Cartas( String valor) {
		
		this.valor = valor;
		this.imagen = new ImageIcon("src/recursos/"+valor+".png");
		this.setIcon(defoutl);
	}
 
	
	public String getValor() {
		return valor;
	}
	public String getPalo() {
		return valor;
	}
	public int getId() {
		return valor.indexOf(0);
	}
	public ImageIcon getImagen() {
		return imagen;
	}
	
	public void setIcono() {
		this.setIcon(imagen);
	}

}


