package juegoPoker;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServidorPoker extends JFrame {
    private ObjectInputStream in;
    private ControlPoker control;
    private int localHost;
    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition otroJugadorConeectado,turnoOtroJugador;
    private int jugadorActual;
    private ArrayList<Jugar> jugadores;

    public ServidorPoker(){
        ejecutarJuego = Executors.newFixedThreadPool(4);
        bloqueoJuego = new ReentrantLock();
        otroJugadorConeectado = bloqueoJuego.newCondition();
        turnoOtroJugador = bloqueoJuego.newCondition();

        jugadores = new ArrayList<Jugar>(4);
    }

    private class Jugar extends Jugador implements Runnable{

    }
}
