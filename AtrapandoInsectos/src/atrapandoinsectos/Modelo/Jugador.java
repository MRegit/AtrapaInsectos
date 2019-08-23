/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Modelo;

import java.time.LocalDate;

/**
 *
 * @author israe
 */
public class Jugador {
    private String nombe;
    private LocalDate fecha;
    private int puntos;
    private int nivelAlcanzado;
    private int vidas = 3; 

    public Jugador(String nombe, LocalDate fecha, int puntos, int nivelAlcanzado) {
        this.nombe = nombe;
        this.fecha = fecha;
        this.puntos = puntos;
        this.nivelAlcanzado = nivelAlcanzado;
    }

    public String getNombe() {
        return nombe;
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

    public void setNombe(String nombe) {
        this.nombe = nombe;
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
        return "Jugador{" + "nombe=" + nombe + ", fecha=" + fecha + ", puntos=" + puntos + ", nivelAlcanzado=" + nivelAlcanzado + '}';
    }
    
    
    
    
    
}
