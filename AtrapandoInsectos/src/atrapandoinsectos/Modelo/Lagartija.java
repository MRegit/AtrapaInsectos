/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */


import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class Lagartija extends Insecto {

    public Lagartija(int puntos) {
        super(puntos);
        Image img = new Image(getClass().getResourceAsStream("/Recursos/Imagenes/Lagartija-g.gif"), 175, 175, true, true);
        imagen = new ImageView(img);
        imagen.relocate(100, 0);
        posicion();
        //lagartija.relocate(50, 0);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    meto para la posicion inicial de la lagartija
     */
    public void posicion() {
        Random r = new Random();
        int valorDado = r.nextInt(4);
        if (valorDado == 0) {
            imagen.relocate(80, 0);
        }
        if (valorDado == 1) {
            imagen.relocate(950, 0);
        }
        if (valorDado == 2) {
            imagen.relocate(80, 500);
        }
        if (valorDado == 3) {
            imagen.relocate(950, 500);

        }
    }

}