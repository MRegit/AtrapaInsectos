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
    private int vidas=3;
    private String nombre;
    private String tipo;
    public Araña(){
        
    }
    public Araña(String nombre,String tipo, int puntos) {
        super(puntos);
        this.nombre = nombre;
        this.tipo=tipo;
        Image img=new Image(getClass().getResourceAsStream("/Recursos/Imagenes/"+tipo+".png"),75,75,true,true);
        imagen= new ImageView(img);
    }
 
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
