/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package atrapandoinsectos.Modelo;

import atrapandoinsectos.Interfaz.Nivel1;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Toshiba
 */
public class Telarana extends Thread {

    private ImageView imagen;

    public Telarana(ImageView img) {
        this.imagen = img;
        imagen.setPickOnBounds(false);

    }

    /*
    meto para la posicioon de la telarana
     */
    public void posicion() {
        Random r = new Random();
        int valorDadox = 100+r.nextInt(850);
        int valorDadoy = r.nextInt(80);
        imagen.relocate(valorDadox, valorDadoy);

    }

    public Node getObjeto() {
        return imagen;
    }
}
