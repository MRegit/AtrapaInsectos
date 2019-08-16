/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atrapandoinsectos.Modelo;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class Araña extends Insecto {

    private int vidas = 3;
    private String nombre;
    private String tipo;

    public Araña() {

    }

    public Araña(String nombre, String tipo, int puntos) {
        super(puntos);
        this.nombre = nombre;
        this.tipo = tipo;
        Image img = new Image(getClass().getResourceAsStream("/Recursos/Imagenes/" + tipo + ".png"), 90, 90, true, true);
        imagen = new ImageView(img);
        imagen.setPickOnBounds(false);
        
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getTipo() {
        return tipo;
    }

    public int getVidas() {
        return vidas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }
    

}
