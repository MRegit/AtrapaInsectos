/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author israe
 */
public class Jugador implements Comparable<Jugador>{
    private String nombre;
    private LocalDate fecha;
    private int puntos;
    private int nivelAlcanzado;
    private int vidas = 3; 

    public Jugador(String nombre, LocalDate fecha, int puntos, int nivelAlcanzado) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.puntos = puntos;
        this.nivelAlcanzado = nivelAlcanzado;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getNivelAlcanzado() {
        return nivelAlcanzado;
    }

    public int getVidas() {
        return vidas;
    }

    public void setNombe(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setNivelAlcanzado(int nivelAlcanzado) {
        this.nivelAlcanzado = nivelAlcanzado;
    }

    public void perdioVida() {
        this.vidas = vidas -1;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", fecha=" + fecha + ", puntos=" + puntos + ", nivelAlcanzado=" + nivelAlcanzado + '}';
    }
    
    public void Escritura() {
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter("src/recursos/datos/jugadores.txt", true))) {
            
            escribir.write("\n"+nombre+ "," + fecha + "," + puntos + "," + nivelAlcanzado);
//            escribir.newLine();
            escribir.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    /**
     * Ordena los jugadores por puntos
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Jugador o) {
        
        if(this.puntos>o.puntos) {
            return -1;}
        if(this.puntos==o.puntos) {
            return 0; }
        if(this.puntos<o.puntos) {
            return 1;}
        return 0;
    }
    
    
    
}
