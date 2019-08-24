/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atrapandoinsectos.Interfaz;

import atrapandoinsectos.Modelo.Araña;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class PantallaMenu {

    private String nombre;
    private String tipo;
    static  Araña jugador;
    private Button bEmpezar=new Button("PLAY");;
    private VBox root2;
    private ImageView personaje;

    public PantallaMenu() {
        root2 = new VBox();
        root2.getChildren().addAll(seccionSuperior(), seccionCentro(), seccionInferior());
    }

    public StackPane seccionSuperior(){
        StackPane panelLogo=new StackPane();
        panelLogo.setPrefSize(1200,150);
        Image img=new Image(getClass().getResourceAsStream("/Recursos/Imagenes/LOGO.png"),200,200,true,true);
        ImageView logo=new ImageView(img);
        panelLogo.getChildren().add(logo);
        panelLogo.setId("panelLogo");
        return panelLogo;
    }
    public HBox seccionCentro(){
        HBox rootCentro=new HBox();
        VBox izquierda=new VBox();
        izquierda.setPadding(new Insets(30));
        izquierda.setSpacing(20);
        StackPane derecha=new StackPane();
        Rectangle instrucciones=new Rectangle(300,300);
        derecha.getChildren().add(instrucciones);
        GridPane informacion=new GridPane();
        informacion.setVgap(20);
        informacion.setHgap(10);
        StackPane  mostrador=new StackPane();
        Label lNombre=new Label("NOMBRE:");
        TextField nombre=new TextField("user 1");
        Label lSkin=new Label("SKIN:");
        Button guardarNombre=new Button("Guardar");
        ComboBox comboSkin=new ComboBox<>();
        comboSkin.setDisable(true);
        bEmpezar.setDisable(true);
        String[] skins={"spider1","Arana","araña1","araña4"};
        comboSkin.setItems(FXCollections.observableArrayList(skins));
        comboSkin.setValue(skins[0]);
        guardarNombre.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                comboSkin.setDisable(false);
                tipo=(String)comboSkin.getValue();
                jugador=new Araña(nombre.getText(),tipo,0);
                guardarNombre.setDisable(true);
                bEmpezar.setDisable(false);
            }
            
        });
        informacion.add(lNombre, 0, 0);
        informacion.add(nombre, 1, 0);
        informacion.add(guardarNombre, 2, 0);
        informacion.add(lSkin, 0, 1);
        informacion.add(comboSkin, 1, 1);
        comboSkin.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                tipo=(String)comboSkin.getValue();
                jugador=new Araña(nombre.getText(),tipo,0);
                
                Image img= new Image(getClass().getResourceAsStream("/Recursos/Imagenes/"+tipo+".png"),200,200,true,true);
                personaje.setImage(img);
            }
        });
        Image img=new Image(getClass().getResourceAsStream("/Recursos/Imagenes/spider1.png"),200,200,true,true);
        personaje=new ImageView(img);
        
        mostrador.getChildren().add(personaje);
        izquierda.getChildren().addAll(informacion,mostrador);
        rootCentro.getChildren().addAll(izquierda,derecha);
        rootCentro.setAlignment(Pos.CENTER);
        rootCentro.setSpacing(200);
        rootCentro.setPadding(new Insets(20));
        return rootCentro;
    }
    public StackPane seccionInferior(){
        StackPane panelInferior=new StackPane();
        
        panelInferior.getChildren().add(bEmpezar);
        bEmpezar.setPrefSize(170, 85);
        panelInferior.setPadding(new Insets(40));
        return panelInferior;
    }

    public Araña getJugador() {
        return jugador;
    }

    public void setJugador(Araña jugador) {
        this.jugador = jugador;
    }

    public Button getbEmpezar() {
        return bEmpezar;
    }

    public void setbEmpezar(Button bEmpezar) {
        this.bEmpezar = bEmpezar;
    }

    public VBox getRoot2() {
        return root2;
    }

    public void setRoot2(VBox root2) {
        this.root2 = root2;
    }

}
