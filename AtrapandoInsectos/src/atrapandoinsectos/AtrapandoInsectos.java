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
    public static Stage pr;
    @Override
    public void start(Stage primaryStage) {
        pr = primaryStage;
        PantallaMenu pMenu=new PantallaMenu();
        PantallaInicio paI = new PantallaInicio();
        scene = new Scene(paI.getRoot(), 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos.css").toExternalForm());
        VBox root2=pMenu.getRoot2();
        scene2=new Scene(root2,1200,700);
        scene2.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos2.css").toExternalForm());
        paI.getbPlay().setOnAction((ActionEvent e) -> {
            pr.setScene(scene2);
        });
        paI.getbScore().setOnAction((ActionEvent e) -> {

        });
        paI.getbExit().setOnAction((ActionEvent e) -> {
            Platform.exit();
        });
        pMenu.getbEmpezar().setOnAction((ActionEvent e) -> {
            pr.setScene(new Scene(pMenu.getRoot2()));
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
