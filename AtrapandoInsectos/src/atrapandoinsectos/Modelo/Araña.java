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
    private ImageView jugador;
    public Araña(){
        
    }
    public Araña(String nombre,String tipo, int puntos) {
        super(puntos);
        this.nombre = nombre;
        this.tipo=tipo;
        Image img=new Image(getClass().getResourceAsStream("/Recursos/Imagenes/"+tipo+".png"),75,75,true,true);
        this.jugador = new ImageView(img);
    }
    public void fijarPosicionObjeto(double x, double y){
        //fija la poscion de x con respecto a X y Y usando 
        jugador.setLayoutX(x);
        jugador.setLayoutY(y);
        
    }
    public void girar(){
        jugador.setRotate(90);
        
    }
     public double getPosicionX(){
        return jugador.getLayoutX();
    }
    
    public double getPosicionY(){
        return jugador.getLayoutY();
    }
   //Metodo para obtener la imagen
    public Node getObjeto(){
        return jugador;
    }
    public void Arriba(){
        jugador.setRotate(360);
    }
    public void Derecha(){
        jugador.setRotate(90);
    }
    public void Abajo(){
        jugador.setRotate(180);
    }
    public void Izquierda(){
        jugador.setRotate(270);
    }
}
