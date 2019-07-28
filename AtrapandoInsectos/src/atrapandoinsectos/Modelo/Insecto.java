/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Modelo;

import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class Insecto {
    private ImageView imagen;
    private int puntos;

    public Insecto() {
    }

    public Insecto(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
}
