package juegoPoker;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.UIManager;



public class PrincipalPoker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		  try { 
			  String className = UIManager.getCrossPlatformLookAndFeelClassName();
			  UIManager.setLookAndFeel(className); 
			  } 
		  catch (Exception e) {}
		  
		  EventQueue.invokeLater(new Runnable() {public void run() { GUIPoker myWindow =
		  new GUIPoker(); }});

	}
}