/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos;

import atrapandoinsectos.Interfaz.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Toshiba
 */
public class AtrapandoInsectos extends Application {

    private static Scene scene;
    private static Scene scene2;
    private static Scene scene3;
    public static Nivel1 nivel;
    public static Stage pr;

    @Override
    public void start(Stage primaryStage) {
        pr = primaryStage;
        PantallaMenu pMenu = new PantallaMenu();     //pantalla donde se elige a la arana
        PantallaInicio paI = new PantallaInicio(); 
        MostrarPuntajes puntajes = new MostrarPuntajes();//panta donde se elige play salir o historial
        scene = new Scene(paI.getRoot(), 1200, 700);
        
        scene.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos.css").toExternalForm());
        VBox root2 = pMenu.getRoot2();
        scene2 = new Scene(root2, 1200, 700);  //escena para elegri al arana
        scene2.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos2.css").toExternalForm());
        scene3 = new Scene (puntajes.getRoot(),1200,700);
        //click en play que se dirige a la scena para elegir la arana
        paI.getbPlay().setOnAction((ActionEvent e) -> {   //dar accion al boton 
            pr.setScene(scene2);
            

        });

        // boton para acceder a la venta del historial del jugador
        paI.getbScore().setOnAction((ActionEvent e) -> {
               pr.setScene(scene3);
        });
        
        // Boton en Mostrar resultados para regresar al menu
        puntajes.getBt().setOnAction((ActionEvent e)->{
               pr.setScene(scene);
        });
        
        pMenu.getbRegresar().setOnAction((ActionEvent e)->{
               pr.setScene(scene);
        });
        
        //boton para salir del juego
        paI.getbExit().setOnAction((ActionEvent e) -> {
            Platform.exit();

        });
        //boton para empezar el juego
        pMenu.getbEmpezar().setOnAction((ActionEvent e) -> {
            pMenu.getbEmpezar().setDisable(true);
            pMenu.getGuardarNombre().setDisable(false);
            nivel = new Nivel1(5,4);                 //activa el timepo cuando presiona el boton play

        });
        pr.setTitle("CATCH INSECTS!!");
        pr.setScene(scene);
        pr.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}