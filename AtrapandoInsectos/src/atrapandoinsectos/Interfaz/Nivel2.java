/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package atrapandoinsectos.Interfaz;

import atrapandoinsectos.Modelo.Araña;
import atrapandoinsectos.Modelo.Mosca;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class Nivel2 extends Nivel1 {

    
    private ImageView roca1;
    private ImageView roca2;
    private ImageView roca3;
    private ImageView hoja1;
    private ImageView hoja2;
    private ImageView hoja3;
 

    
    public Nivel2(){
        super();
        

    }

    @Override
    public void inicializar() {
        
        super.inicializar();
        roca1 = new ImageView(new Image("/Recursos/Imagenes/Roca.png"));
        roca2 = new ImageView(new Image("/Recursos/Imagenes/Roca.png"));
        roca3 = new ImageView(new Image("/Recursos/Imagenes/Roca.png"));
        roca1.setFitHeight(150);
        roca1.setFitWidth(150);
        roca2.setFitHeight(150);
        roca2.setFitWidth(150);
        roca3.setFitHeight(150);
        roca3.setFitWidth(150);
        roca1.relocate(600, 100);
        roca2.relocate(800, 400);
        roca3.relocate(250, 300);
        hoja1 = new ImageView(new Image("/Recursos/Imagenes/Hoja2.png"));  // aqui el error
        hoja2 = new ImageView(new Image("/Recursos/Imagenes/Hoja2.png"));
        hoja3 = new ImageView(new Image("/Recursos/Imagenes/Hoja2.png"));
        hoja1.relocate(200, 120);
        hoja2.relocate(550, 350);
        hoja3.relocate(870, 200);
        hoja1.setFitHeight(100);
        hoja1.setFitWidth(100);
        hoja2.setFitHeight(100);
        hoja2.setFitWidth(100);
        hoja3.setFitHeight(100);
        hoja3.setFitWidth(100);
        puntosGanar=150;

    }

    @Override
    public void organizar() {

       super.organizar();
       gamePane.getChildren().addAll(roca1, roca2, roca3, hoja1, hoja2, hoja3);
   
       
    }


 
}
