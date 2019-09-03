package juegoPoker;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServidorPoker extends JFrame {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private int localHost;
    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition otroJugadorConeectado,turnoOtroJugador;
    private int jugadorActual;
    private ArrayList<JugadorS> jugadores;
    private ServerSocket servidor;
    private int puerto;
    public ServidorPoker(){
        super("Servidor Texas Hold´ém");
        try {
            servidor = new ServerSocket(puerto);
            ejecutarJuego = Executors.newCachedThreadPool();
            bloqueoJuego = new ReentrantLock();
            otroJugadorConeectado = bloqueoJuego.newCondition();
            turnoOtroJugador = bloqueoJuego.newCondition();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jugadores = new ArrayList<JugadorS>(4);
    }

    public void execute(){
    for(int i = 0;i<4;i++){
        try{
            jugadores.add(new JugadorS(servidor.accept(),i));
            ejecutarJuego.execute(jugadores.get(i));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    bloqueoJuego.lock();
    jugadores.get(0).setTurno(true);


    }


//Clase privada del jugador, maneja todos los metodos como subprocesos

    private class JugadorS implements Runnable{
        private Socket conexion;
        private ObjectInputStream entrada;
        private ObjectOutputStream salida;
        private boolean dealer;
        private boolean turno;
        private int turnoJ;



        public JugadorS(Socket socket, int numero){
            conexion = socket;
            turnoJ = numero;
            try {
                salida =new ObjectOutputStream(conexion.getOutputStream());
                entrada = new ObjectInputStream(conexion.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        public int getIdN(){
            return turnoJ;
        }
        public void setTurno(boolean turno1){
            turno1 = turno;
        }
        @Override

        public void run() {

        }
    }
}
