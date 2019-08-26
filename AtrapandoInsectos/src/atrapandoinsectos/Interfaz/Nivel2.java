/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import static atrapandoinsectos.Interfaz.PantallaMenu.jugador;
import atrapandoinsectos.Modelo.Araña;
import atrapandoinsectos.Modelo.Hormiga;
import atrapandoinsectos.Modelo.Jugador;
import atrapandoinsectos.Modelo.Mosca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private static List<ImageView> rocas = new ArrayList<>(); //lista de las rocas
    private static List<ImageView> hojas = new ArrayList<>(); //lista de las hojas

    public Nivel2(int nHormigas,int nMoscas) {
        super(nHormigas,nMoscas);

    }

    @Override
    public void inicializar() {

        roca1 = new ImageView(new Image("/Recursos/Imagenes/Roca.png"));
        roca2 = new ImageView(new Image("/Recursos/Imagenes/Roca.png"));
        roca3 = new ImageView(new Image("/Recursos/Imagenes/Roca.png"));
        roca1.setFitHeight(100);
        roca1.setFitWidth(100);
        roca2.setFitHeight(100);
        roca2.setFitWidth(100);
        roca3.setFitHeight(100);
        roca3.setFitWidth(100);
        roca1.relocate(600, 100);
        roca2.relocate(800, 400);
        roca3.relocate(250, 300);
        hoja1 = new ImageView(new Image("/Recursos/Imagenes/Hoja2.png"));
        hoja2 = new ImageView(new Image("/Recursos/Imagenes/Hoja2.png"));
        hoja3 = new ImageView(new Image("/Recursos/Imagenes/Hoja2.png"));
        hoja1.relocate(200, 120);
        hoja2.relocate(550, 450);
        hoja3.relocate(870, 200);
        hoja1.setFitHeight(100);
        hoja1.setFitWidth(100);
        hoja2.setFitHeight(100);
        hoja2.setFitWidth(100);
        hoja3.setFitHeight(100);
        hoja3.setFitWidth(100);
        rocas.add(roca1);
        rocas.add(roca2);
        rocas.add(roca3);
        hojas.add(hoja1);
        hojas.add(hoja2);
        hojas.add(hoja3);
        super.inicializar();
        jugador.setNivelAlcanzado(2);
    }

    @Override
    public void organizar() {

        super.organizar();
        gamePane.getChildren().addAll(roca1, roca2, roca3, hoja1, hoja2, hoja3);
        removeVidas();

    }

    public static boolean ColisionRocas() {

        for (ImageView r : rocas) {
            if (Nivel1.isCollision(r, PantallaMenu.jugador.getObjeto())) {
                return true;
            }
        }
        return false;
    }

    public static boolean ColisionHojas() {
        for (ImageView h : hojas) {
            if (Nivel1.isCollision(h, PantallaMenu.jugador.getObjeto())) {
                return true;
            }
        }
        return false;
    }
    
    public void removeVidas() {
        int vida = jugador.getVidas();
        if (vida == 2) {
            c.getChildren().remove(corazon);

        }

        if (vida == 1) {
            c.getChildren().remove(corazon2);
            c.getChildren().remove(corazon);

        }
    }
    @Override
    public Node AparecerArana(int vida) {
        Node imagenjugador = null;
        if (vida == 3) {
            PantallaMenu.jugador.setVidas(vida - 1); //elimina una vida
            c.getChildren().remove(corazon);//elimina un corazon
            imagenjugador = PantallaMenu.jugador.getObjeto();
            imagenjugador.relocate(570, 300);
        }
        if (vida == 2) {
            PantallaMenu.jugador.setVidas(vida - 1);

            c.getChildren().remove(corazon2);
            imagenjugador = PantallaMenu.jugador.getObjeto();
            imagenjugador.relocate(570, 300);
        }
        if (vida == 1) {
            c.getChildren().remove(corazon3);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vidas terminadas");
            alert.setHeaderText("Click en el boton Aceptar para guardar partida");
            alert.setContentText("Al presionar cancel (fin del juego)");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                mediaPlayer.stop();
                Jugador jg = new Jugador(jugador.getNombre(), LocalDate.now(), jugador.getPuntos(), jugador.getNivelAlcanzado());
                jg.Escritura();
                gamePane.getChildren().removeAll(roca1, roca2, roca3, hoja1, hoja2, hoja3);
                for(ImageView r:rocas){
                    r.relocate(2000, 2000);
                }
                for(ImageView h:hojas){
                    h.relocate(2000, 2000);
                }
                this.stage.close();
                imagenjugador = PantallaMenu.jugador.getObjeto();
                imagenjugador.relocate(570, 300);

            } else {
                // ... user chose CANCEL or closed the dialog
                Platform.exit();
                imagenjugador = PantallaMenu.jugador.getObjeto();
                imagenjugador.relocate(570, 300);
            }

        }
        //condicio para el alert 
        return imagenjugador;
    }
    
    @Override
       public void ganar(int puntos) {
        lblpuntos.setText("Puntos: " + PantallaMenu.jugador.getPuntos());
        if (PantallaMenu.jugador.getPuntos() == puntos) {
            this.thrpuntos.suspend();
            mediaPlayer.stop();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("FELICIDADES Juego Finalizado");
            alert.setHeaderText("Click en el boton Aceptar para guardar partida y Regresar al menú.");
            alert.setContentText("Click en el boton cancelar para Salir.");
            Jugador jg = new Jugador(jugador.getNombre(), LocalDate.now(), jugador.getPuntos(), jugador.getNivelAlcanzado());
            jg.Escritura();
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
               
                mediaPlayer.stop();
                puntos = 0;
                this.stage.close();

            } else {
                // ... user chose CANCEL or closed the dialog
                Platform.exit();
            }
        }
    }

}
