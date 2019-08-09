/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Toshiba
 */
public class Salir {

    private VBox root;
    private Label mensaje;
    private Button btaceptar;
    private Button btcancelar;
    private HBox cajabotones;
    private Stage stage;
    
    private Thread thrTiempo;

    public Salir(Thread thrTiempo) {
        this.thrTiempo = thrTiempo;
        Inicializar();
        Organizar();
        nuevoStage();
        btaceptar.setOnAction((ActionEvent e) -> {
            Platform.exit();

        });
        btcancelar.setOnAction((ActionEvent e) -> {
            //volver al nivel 1 o al nivel 2
            
//   try {
//                thrTiempo.join(30000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
//            }
            this.stage.close();
            this.thrTiempo.resume();
            //this.thrTiempo.start();
        });
    }

    public void Inicializar() {
        root = new VBox();
        mensaje = new Label("¿Esta seguro que quiere salir del juego?");
        mensaje.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: WHITE;");
        btaceptar = new Button("Aceptar");
        btcancelar = new Button("Cancelar");
        btaceptar.setStyle("-fx-text-fill: WHITE; -fx-background-color: GREEN;");
        btcancelar.setStyle("-fx-text-fill: WHITE; -fx-background-color: ORANGE;");
        cajabotones = new HBox();
        root.setStyle("-fx-background-color: BLUE");
        

    }

    public void Organizar() {
        cajabotones.getChildren().addAll(btaceptar, btcancelar);
        cajabotones.setSpacing(20);
        cajabotones.setAlignment(Pos.CENTER);
        root.getChildren().addAll(mensaje, cajabotones);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

    }

    private void nuevoStage() {
        stage = new Stage();
        Scene scene = new Scene(root, 200, 150);
        scene.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos2.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Salir");
        stage.setResizable(false);  //ventana con relleno no deseado
        stage.show();

    }
}
