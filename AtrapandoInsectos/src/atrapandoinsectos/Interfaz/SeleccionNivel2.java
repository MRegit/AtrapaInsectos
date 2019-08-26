/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import static atrapandoinsectos.Interfaz.PantallaMenu.jugador;
import atrapandoinsectos.Modelo.Jugador;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author israe
 */
public class SeleccionNivel2 {

    private VBox root;
    private Label mensaje;
    private Button btaceptar;
    private Button btcancelar;
    private ComboBox nMoscas;
    private ComboBox nHormigas;
    private HBox cajabotones;
    private GridPane cajaSeleccion;
    private Stage stage;

    //private Thread thrTiempo;
    private Stage stageJuego;

    public SeleccionNivel2(Stage stage1) {
        //this.thrTiempo = thrTiempo;
        this.stageJuego = stage1;
        Inicializar();
        Organizar();
        nuevoStage();
        btaceptar.setOnAction((ActionEvent e) -> {
            stageJuego.close();
            Nivel2 nivel2 = new Nivel2((Integer)nHormigas.getValue(),(Integer)nMoscas.getValue());
            this.stage.close();

        });
        btcancelar.setOnAction((ActionEvent e) -> {
//            this.stage.close();
//            this.thrTiempo.resume();
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
        cajaSeleccion = new GridPane(); 
        mensaje = new Label("Selecione el numero de Hormigas y Moscas a Capturar...");
        mensaje.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: WHITE;");
        btaceptar = new Button("Aceptar");
        btcancelar = new Button("Salir");
        btaceptar.setStyle("-fx-text-fill: WHITE; -fx-background-color: GREEN;");
        btcancelar.setStyle("-fx-text-fill: WHITE; -fx-background-color: ORANGE;");
        cajabotones = new HBox();
        root.setStyle("-fx-background-color: BLUE");
        ObservableList<Integer> hormigas =  FXCollections.observableArrayList(3,4,5,6,7,8);
        ObservableList<Integer> moscas =  FXCollections.observableArrayList(3,4,5,6,7,8);
        nHormigas = new ComboBox(hormigas);
        nMoscas = new ComboBox(moscas);
        nHormigas.getSelectionModel().select(2);
        nMoscas.getSelectionModel().select(2);

    }

    /*
     metodo ue organiza toda la scena
     */
    public void Organizar() {
        Label horm = new Label("Hormigas:");
        horm.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: WHITE;");
        horm.setPadding(new Insets(10,25,10,5));
        Label mosc = new Label("Moscas:");
        mosc.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: WHITE;");
        mosc.setPadding(new Insets(10,25,10,5));
        cajaSeleccion.add(horm, 0, 0);
        cajaSeleccion.add(nHormigas,0,1);
        cajaSeleccion.add(mosc,1,0);
        cajaSeleccion.add(nMoscas, 1, 1);
        cajaSeleccion.setPadding(new Insets(10,15,10,15));
        cajaSeleccion.setAlignment(Pos.CENTER);
        cajabotones.getChildren().addAll(btaceptar, btcancelar);
        cajabotones.setSpacing(20);
        cajabotones.setAlignment(Pos.CENTER);
        root.getChildren().addAll(mensaje,cajaSeleccion, cajabotones);
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
        stage.setTitle("Seleccione Nuemero de Hormigas y Moscas");
        stage.setResizable(false);  //ventana con relleno no deseado
        stage.show();

    }

    public int getnMoscas() {
        return nMoscas.getSelectionModel().getSelectedIndex();
    }

    public int getnHormigas() {
        return nHormigas.getSelectionModel().getSelectedIndex();
    }
    
    

}
