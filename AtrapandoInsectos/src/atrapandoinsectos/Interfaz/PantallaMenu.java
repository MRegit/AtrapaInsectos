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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class PantallaMenu {

    private String nombre;
    private String tipo;
    static  Araña jugador;
    private Button bEmpezar=new Button("PLAY");
    private Button bRegresar = new Button("Regresar");
    private Button guardarNombre=new Button("Guardar");;
    private VBox root2;
    private ImageView personaje;
    /**
     * Constrcutor de la clase
     */
    public PantallaMenu() {
        root2 = new VBox();
        root2.getChildren().addAll(seccionSuperior(), seccionCentro(), seccionInferior());
    }
    /**
     * Crea la seccion superior, y agrega el logo del juego
     * StackPane
     */
    public StackPane seccionSuperior(){
        StackPane panelLogo=new StackPane();
        panelLogo.setPrefSize(1200,150);
        Image img=new Image(getClass().getResourceAsStream("/Recursos/Imagenes/LOGO.png"),200,200,true,true);
        ImageView logo=new ImageView(img);
        panelLogo.getChildren().add(logo);
        panelLogo.setId("panelLogo");
        return panelLogo;
    }
    /**
     * Seccion del centro donde se creala araña y se muestra las intrucciones
     * HBox
     */
    public HBox seccionCentro(){
        HBox rootCentro=new HBox();
        VBox izquierda=new VBox();
        rootCentro.setId("rootCentro");
        izquierda.setId("izquierda");
        izquierda.setPadding(new Insets(30));
        izquierda.setSpacing(20);
        StackPane derecha=new StackPane();
        ImageView instrucciones=new ImageView(new Image("/Recursos/Imagenes/instrucciones.gif",350,400,true,true));
        derecha.getChildren().add(instrucciones);
        GridPane informacion=new GridPane();
        informacion.setVgap(20);
        informacion.setHgap(10);
        informacion.setId("informacion");
        StackPane  mostrador=new StackPane();
        Label lNombre=new Label("NOMBRE:");
        TextField nombre=new TextField("user 1");
        Label lSkin=new Label("SKIN:");
        guardarNombre=new Button("Guardar");
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
                bEmpezar.setFont(new Font(20));
                
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
    /**
     * Método que crea y añade los botonde de play y regresar
     * StackPane
     */
    public StackPane seccionInferior(){
        StackPane panelInferior=new StackPane();
        panelInferior.setId("inferior");
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(bEmpezar,bRegresar);
        hb.setSpacing(30);
        panelInferior.getChildren().add(hb);
        bRegresar.setPrefSize(170, 85);
        bEmpezar.setPrefSize(170, 85);
        bEmpezar.setId("001");
        bRegresar.setId("002");
        bEmpezar.setFont(new Font(20));
        bRegresar.setFont(new Font(20));
        panelInferior.setPadding(new Insets(40));
        return panelInferior;
    }
    /**
     * 
     * @return 
     */
    public Araña getJugador() {
        return jugador;
    }
    /**
     * 
     * @param jugador 
     */
    public void setJugador(Araña jugador) {
        this.jugador = jugador;
    }
    /**
     * 
     * @return 
     */
    public Button getbEmpezar() {
        return bEmpezar;
    }
    /**
     * 
     * @param bEmpezar 
     */
    public void setbEmpezar(Button bEmpezar) {
        this.bEmpezar = bEmpezar;
    }
    /***
     * 
     * @return 
     */
    public VBox getRoot2() {
        return root2;
    }
    /**
     * 
     * @param root2 
     */
    public void setRoot2(VBox root2) {
        this.root2 = root2;
    }
    /**
     * 
     * @return 
     */
    public Button getGuardarNombre() {
        return guardarNombre;
    }
    /**
     * 
     * @param guardarNombre 
     */
    public void setGuardarNombre(Button guardarNombre) {
        this.guardarNombre = guardarNombre;
    }
    /**
     * 
     * @return 
     */
    public Button getbRegresar() {
        return bRegresar;
    }

}
