package juegoPoker;

import java.awt.Color;

import javax.swing.JFrame;

public class GUIPoker extends JFrame {
	private ControlPoker controlPoker;
	
	public GUIPoker() {
   	 initGui();
	  
   	 //configuracion Default Windows
   	 //setSize(1200,700);
   	 pack();
   	 setLocationRelativeTo(null);
   	 setVisible(true);
   	 setResizable(true);
   	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	 
    }
	
	public void initGui() {
		controlPoker = new ControlPoker();
		add(controlPoker);
	
	}
}
