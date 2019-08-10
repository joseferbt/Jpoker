package juegoPoker;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cartas extends JLabel {
	public final ImageIcon defoutl= new ImageIcon("src/recursos/reverso.png"); 
	private ImageIcon imagen;
	private int idCarta;
	private String palo;
	
	// palo toma valores de S(),C(),H(),D(diamond)
	public Cartas(int idCarta, String palo) {
		this.idCarta = idCarta;
		this.palo = palo;
		this.imagen = new ImageIcon("src/recursos/"+idCarta+palo+".png");
		this.setIcon(defoutl);
	}
 
	
	public String getPalo() {
		return palo;
	}
	
	public int getId() {
		return idCarta;
	}
	public ImageIcon getImagen() {
		return imagen;
	}
	
	public void setIcono() {
		this.setIcon(imagen);
	}

}


