/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

/**
 *
 * @author pc
 */

import atrapandoinsectos.Modelo.Araña;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
public class Nivel2 {
    private VBox root;
    private HBox panelsuper;
    private HBox c;
    private ImageView corazon;
    private ImageView corazon2;
    private ImageView corazon3;
    private ImageView roca1;
    private ImageView roca2;
    private ImageView hoja1;
    private ImageView hoja2;
    private ImageView hoja3;
    private Button btsalir;
    private Label lblTiempo;
    private Label puntos;

    private Araña jugador;
    private Pane gamePane;
    
    private Thread thrTiempo;
    
    public Nivel2() {
        inicializar();
        organizar();
        thrTiempo = new Thread(new Nivel2.Tiempo());
        thrTiempo.start();
        nuevoStage();
        
        btsalir.setOnAction((ActionEvent e) -> {
            thrTiempo.stop(); // al dar click en el boton salir se detinee el tiempo
            Salir salir = new Salir(this.thrTiempo);
        });
    }
    
    
    
    public void inicializar() {

        panelsuper = new HBox();
        c = new HBox();
        corazon = new ImageView(new Image("/Recursos/Imagenes/corazon.png"));
        corazon2 = new ImageView(new Image("/Recursos/Imagenes/corazon.png"));
        corazon3 = new ImageView(new Image("/Recursos/Imagenes/corazon.png"));
        corazon.setFitHeight(50);
        corazon.setFitWidth(50);
        corazon2.setFitHeight(50);
        corazon2.setFitWidth(50);
        corazon3.setFitHeight(50);
        corazon3.setFitWidth(50);
        roca1 = new ImageView(new Image("/Recursos/Imagenes/Roca.png"));
        roca2 = new ImageView(new Image("/Recursos/Imagenes/Roca.png"));
        roca1.setFitHeight(50);
        roca1.setFitWidth(50);
        roca2.setFitHeight(50);
        roca2.setFitWidth(50);
        roca1.relocate(400, 400);
        roca2.relocate(800, 500);
        hoja1 =  new ImageView(new Image("/Recursos/Imagenes/hoja.png"));
        hoja2 =  new ImageView(new Image("/Recursos/Imagenes/hoja.png"));
        hoja3 =  new ImageView(new Image("/Recursos/Imagenes/hoja.png"));
        hoja1.relocate(50, 500);
        hoja2.relocate(1000, 300);
        hoja3.relocate(600, 600);
        hoja1.setFitHeight(50);
        hoja1.setFitWidth(50);
        hoja2.setFitHeight(50);
        hoja2.setFitWidth(50);
        hoja3.setFitHeight(50);
        hoja3.setFitWidth(50);
        btsalir = new Button("Salir");
        btsalir.setPrefSize(125, 85);
        gamePane = new Pane();
        lblTiempo = new Label("Tiempo: ");
        puntos = new Label("Puntos: ");

        //jugador = new Araña(); //inicializar la arana pasar los paametros 
        //jugador.getImagen().setFitHeight(75);
        //jugador.getImagen().setFitWidth(75);
        //jugador.getImagen().relocate(600, 350); //posicion de la arana central
        root = new VBox();
        
        
    }
    
    public void organizar() {

        c.getChildren().add(corazon);

        c.getChildren().add(corazon2);
        c.getChildren().add(corazon3);
        panelsuper.getChildren().add(lblTiempo);
        panelsuper.getChildren().add(c);
        panelsuper.getChildren().add(puntos);
        panelsuper.getChildren().add(btsalir);
        panelsuper.setSpacing(180);
   

        gamePane.getChildren().add(new ImageView(new Image("/Recursos/Imagenes/cesped1.png")));
        gamePane.getChildren().addAll(roca1,roca2,hoja1,hoja2,hoja3);
        //gamePane.getChildren().add(jugador.getImagen());

        root.getChildren().addAll(panelsuper, gamePane);
    }

    private void nuevoStage() {
        Stage stage = new Stage();
        Scene scene = new Scene(root, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos2.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Nivel 2");
        stage.setResizable(false);  //ventana con relleno no deseado
        stage.show();
     
    }
    
    
    class Tiempo implements Runnable {

        int segundos;

        @Override
        public void run() {
            for (segundos = 100; segundos >= 0; segundos--) {
                try {
                    Platform.runLater(() -> lblTiempo.setText("Tiempo Restante " + segundos));
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel2.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }
}
