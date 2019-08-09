/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Modelo;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class Mosca extends Insecto {

    public Mosca(int puntos) {
        super(puntos);
        Image img = new Image(getClass().getResourceAsStream("/Recursos/Imagenes/Mosca.png"), 70, 70, true, true);
        imagen = new ImageView(img);
        
        Random r = new Random();
        imagen.setLayoutX(r.nextInt(500));
        imagen.setLayoutY(r.nextInt(500));
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ImageView getImagen() {
        return imagen;
    }

    
    
    
}