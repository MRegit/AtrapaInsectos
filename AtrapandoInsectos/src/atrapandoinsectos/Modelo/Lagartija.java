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
public class Lagartija extends Insecto{

    public Lagartija(int puntos) {
        super(puntos);
        Image img=new Image(getClass().getResourceAsStream("/Recursos/Imagenes/lagartija.png"),75,75,true,true);
        imagen = new ImageView(img);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
