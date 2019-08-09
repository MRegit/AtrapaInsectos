/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import atrapandoinsectos.Modelo.Araña;
import atrapandoinsectos.Modelo.Hormiga;
import atrapandoinsectos.Modelo.Mosca;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class Nivel1 {

    private VBox root3;
    private HBox panelsuper;
    private HBox c;
    private ImageView corazon;
    private ImageView corazon2;
    private ImageView corazon3;
    private ImageView lagartija;
    private Button btsalir;
    private Label lblTiempo;
    private Label lblpuntos;

    private Araña jugador;
    private Pane gamePane;

    private Thread thrTiempo;
    private Thread thrpuntos;
    //private Hormiga hormiga;
    private List<Hormiga> hormigas = new ArrayList<>(); //lista de hormigas
    private List<Mosca> moscas = new ArrayList<>(); // list de moscas
    

    private int puntaje;
    

    public Nivel1() {

        inicializar();
        organizar();
        nuevoStage();

        thrTiempo = new Thread(new Tiempo());
        thrTiempo.start();
        thrpuntos = new Thread(new Puntos());
        thrpuntos.start();

        crearHormigas();
        crearMoscas();

        btsalir.setOnAction((ActionEvent e) -> {
            thrTiempo.suspend(); // al dar click en el boton salir se detinee el tiempo
            Salir salir = new Salir(this.thrTiempo);

        });
        System.out.println(this.puntaje);

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
        lagartija = new ImageView(new Image("/Recursos/Imagenes/Lagartija-g.gif"));
        lagartija.setFitWidth(150);
        lagartija.setFitHeight(150);
        lagartija.relocate(50, 0);
        btsalir = new Button("Salir");
        btsalir.setPrefSize(125, 85);
        gamePane = new Pane();
        lblTiempo = new Label("Tiempo: ");
        lblpuntos = new Label("Puntos: ");
        
        
       

        jugador = new Araña("user", "spider1", 0); //inicializar la arana pasar los paametros 

        jugador.getImagen().relocate(570, 300); //posicion de la arana central
        jugador.getImagen().setFocusTraversable(true);
        jugador.getImagen().setOnKeyPressed(e -> mover(e));
        
        
        
        root3 = new VBox();

    }

    public void organizar() {

        c.getChildren().add(corazon);

        c.getChildren().add(corazon2);
        c.getChildren().add(corazon3);
        panelsuper.getChildren().add(lblTiempo);
        panelsuper.getChildren().add(c);
        panelsuper.getChildren().add(lblpuntos);
        panelsuper.getChildren().add(btsalir);
        panelsuper.setSpacing(180);

        gamePane.getChildren().add(new ImageView(new Image("/Recursos/Imagenes/cesped1.png")));
        gamePane.getChildren().add(jugador.getImagen());
        gamePane.getChildren().add(lagartija);
      
        root3.getChildren().addAll(panelsuper, gamePane);
    }

    private void nuevoStage() {
        Stage stage = new Stage();
        Scene scene = new Scene(root3, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos2.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Nivel 1");
        stage.setResizable(false);  //ventana con relleno no deseado
        stage.show();

    }

    public void mover(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                e.consume();
                jugador.getImagen().setLayoutY(jugador.getImagen().getLayoutY() - 5);
                ColisionHormiga();
                ColisionMosca();
                break;
            case DOWN:
                jugador.getImagen().setLayoutY(jugador.getImagen().getLayoutY() + 5);
                jugador.getImagen().setRotate(180);
                ColisionHormiga();
                ColisionMosca();
                break;
            case RIGHT:
                e.consume();
                jugador.getImagen().setLayoutX(jugador.getImagen().getLayoutX() + 5);
                jugador.getImagen().setRotate(90);
                ColisionHormiga();
                ColisionMosca();
                break;
            case LEFT:
                jugador.getImagen().setLayoutX(jugador.getImagen().getLayoutX() - 5);
                jugador.getImagen().setRotate(-90);
                ColisionHormiga();
                ColisionMosca();
                break;
        }
    }

    /*
    creacion de la 5 hormigas
     */
    public void crearHormigas() {
        for (int i = 0; i < 5; i++) {
            Hormiga hormiga = new Hormiga(10);
            hormigas.add(hormiga);
            gamePane.getChildren().add(hormiga.getImagen());
            Thread move =  new Thread(hormiga);
            move.start();

        }
    }

    /*
    creacion de las 5 moscas
     */
    public void crearMoscas() {
        for (int i = 0; i < 4; i++) {
            Mosca mosca = new Mosca(15);
            moscas.add(mosca);
            gamePane.getChildren().add(mosca.getImagen());

        }
    }

    /*
    metodo que confirmara si existe una collison entre 2 objetos
     */
    public static boolean isCollision(Node n1, Node n2) {
        Bounds b1 = n1.getBoundsInParent();
        Bounds b2 = n2.getBoundsInParent();
        if (b1.intersects(b2)) { // dos nodos se intersecta
            return true; // retorna verdadero    siempre que se mueve revisar si colisiona con la lista de monedas se puede vr eso si esta colisionando borrar la moneda
        } else {
            return false;
        }
    }

    /*
     metodo que revisa las colisiones de las hormigas
     */
    public void ColisionHormiga() {
        Iterator<Hormiga> itHormiga = hormigas.iterator(); //ir uno a uno en una coleccion, puedo eliminar sin afectar su indice
        
        while (itHormiga.hasNext()) {
            Hormiga h = itHormiga.next();
            if (isCollision(h.getImagen(), jugador.getImagen())) {    // consdicion si la imagen colisiona con la arana
                gamePane.getChildren().remove(h.getImagen());
                itHormiga.remove();
                this.puntaje += 10; //puntaje por cada colision
            }

        }
    }

    /*
    metodo que revisa las colisiones de las moscas
     */
    public void ColisionMosca() {
        Iterator<Mosca> itMosca = moscas.iterator(); //ir uno a uno en una coleccion, puedo eliminar sin afectar su indice
        while (itMosca.hasNext()) {
            Mosca m = itMosca.next();
            if (isCollision(m.getImagen(), jugador.getImagen())) {    // consdicion si la imagen colisiona con la arana
                gamePane.getChildren().remove(m.getImagen());
                itMosca.remove();
                this.puntaje += 15; //puntaje por cada colision
            }
        }
    }

    /*
    timepo en que se demora el nivel 1
     */
    class Tiempo implements Runnable {

        int segundos;

        @Override
        public void run() {
            for (segundos = 0; segundos <= 100; segundos++) {
                try {
                    Platform.runLater(() -> lblTiempo.setText("Tiempo: " + segundos));
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    class Puntos implements Runnable {

        @Override
        public void run() {

            try {
                Platform.runLater(() -> lblpuntos.setText("Puntos: " + puntaje));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}