/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import atrapandoinsectos.Modelo.Araña;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class Nivel1 {
    private VBox root3;
   private Araña jugador;
   private Pane gamePane;

    public Nivel1() {
        root3 =new VBox();
    }
    public VBox seccionSuperior(){
        VBox rootSuperior=new VBox();
        return rootSuperior;
    }
    public VBox seccionInferior(){
        VBox rootInferior=new VBox();
        gamePane=new Pane();
        gamePane.setId("gamePane");
        return rootInferior;
    }

    public VBox getRoot3() {
        return root3;
    }

    public void setRoot3(VBox root3) {
        this.root3 = root3;
    }

    public Araña getJugador() {
        return jugador;
    }

    public void setJugador(Araña jugador) {
        this.jugador = jugador;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }
    
}
