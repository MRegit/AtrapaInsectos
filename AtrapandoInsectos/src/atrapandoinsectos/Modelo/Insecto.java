/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Modelo;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public abstract class Insecto implements Runnable{
    protected ImageView imagen;
    private int puntos;
    protected boolean parar;

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

    public Node getObjeto() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }
    public void fijarPosicionObjeto(double x, double y){
        //fija la poscion de x con respecto a X y Y usando 
        imagen.setLayoutX(x);
        imagen.setLayoutY(y);
        
    }

     public double getPosicionX(){
        return imagen.getLayoutX();
    }
    
    public double getPosicionY(){
        return imagen.getLayoutY();
    }
    public void Derecha(){
        imagen.setRotate(90);
    }
    public void Abajo(){
        imagen.setRotate(180);
    }
    public void Izquierda(){
        imagen.setRotate(270);
    }
        public void Arriba(){
        imagen.setRotate(360);
    }

    public boolean isParar() {
        return parar;
    }

    public void setParar(boolean parar) {
        this.parar = parar;
    }

    public ImageView getImagen() {
        return imagen;
    }

}
