/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import static atrapandoinsectos.Interfaz.PantallaMenu.jugador;
import atrapandoinsectos.Modelo.Jugador;
import java.time.LocalDate;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Toshiba
 */
public class NivelSuperado {
    private VBox root;
    private Label lmensaje;
    private String mensaje;
    private Button btaceptar;
    private Button btcancelar;
    private HBox cajabotones;
    private Stage stage;

    //private Thread thrTiempo;
    private Stage stageJuego;
    
    public NivelSuperado(Stage stage1,String mensaje){
        //this.thrTiempo = thrTiempo;
        this.stageJuego = stage1;
        this.mensaje=mensaje;
        Inicializar();
        Organizar();
        nuevoStage();
        btaceptar.setOnAction((ActionEvent e) -> {
            SeleccionNivel2 select = new SeleccionNivel2(stageJuego);
            this.stage.close();
            

        });
        btcancelar.setOnAction((ActionEvent e) -> {
            Jugador jg= new Jugador(jugador.getNombre(),LocalDate.now(),jugador.getPuntos(),jugador.getNivelAlcanzado());
            jg.Escritura();

            Platform.exit();
        });
    }
    
    /*
    metodo para inicializar las variables de esta scena
    */
     public void Inicializar() {
        root = new VBox();
        lmensaje = new Label(mensaje);
        lmensaje.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: WHITE;");
        btaceptar = new Button("Aceptar");
        btcancelar = new Button("Salir");
        btaceptar.setStyle("-fx-text-fill: WHITE; -fx-background-color: GREEN;");
        btcancelar.setStyle("-fx-text-fill: WHITE; -fx-background-color: ORANGE;");
        cajabotones = new HBox();
        root.setStyle("-fx-background-color: BLUE");
        
    }
      /*
     metodo ue organiza toda la scena
     */

    public void Organizar() {
        cajabotones.getChildren().addAll(btaceptar, btcancelar);
        cajabotones.setSpacing(20);
        cajabotones.setAlignment(Pos.CENTER);
        root.getChildren().addAll(lmensaje, cajabotones);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

    }
    /*
    meotdo que crea un nuevo stage
    */
    public void nuevoStage() {
        stage = new Stage();
        Scene scene = new Scene(root, 400, 200);
        scene.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos2.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Felicidades");
        stage.setResizable(false);  //ventana con relleno no deseado
        stage.show();

    }

    private Object LocalDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
