/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import atrapandoinsectos.Modelo.Araña;
import atrapandoinsectos.Modelo.Hormiga;
import atrapandoinsectos.Modelo.Insecto;
import atrapandoinsectos.Modelo.Mosca;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
    private Pane gamePane;

    private Thread thrTiempo;
    private Thread thrpuntos;
    //private Hormiga hormiga;
    private List<Hormiga> hormigas = new ArrayList<>(); //lista de hormigas
    private List<Mosca> moscas = new ArrayList<>(); // list de moscas
    private List<Thread> thrMove = new ArrayList<>();
    

      private static int puntaje = 0;
    
    
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
            for (Thread hilos : thrMove) {
                hilos.suspend();
            }
            Salir salir = new Salir(this.thrTiempo, this.thrMove);

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
        lagartija = new ImageView(new Image("/Recursos/Imagenes/Lagartija-g.gif"));
        lagartija.setFitWidth(150);
        lagartija.setFitHeight(150);
        lagartija.relocate(50, 0);
        btsalir = new Button("Salir");
        btsalir.setPrefSize(100, 75);
        gamePane = new Pane();
        lblTiempo = new Label("Tiempo: ");
        lblpuntos = new Label("Puntos: ");
        
        
       

        PantallaMenu.jugador.getObjeto().relocate(570, 300); //posicion de la arana central
        PantallaMenu.jugador.getObjeto().setFocusTraversable(true);
        PantallaMenu.jugador.getObjeto().setOnKeyPressed(e -> mover(e));
        
        
        
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
        panelsuper.setPrefSize(1200,100);
        gamePane.setId("gamePane");
        gamePane.setPrefSize(1200,600);
        gamePane.getChildren().add(PantallaMenu.jugador.getObjeto());
        gamePane.getChildren().add(lagartija);
      
        root3.getChildren().addAll(panelsuper, gamePane);
    }

    public void nuevoStage() {
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
                PantallaMenu.jugador.Arriba();
                if (PantallaMenu.jugador.getObjeto().getLayoutY() > 0) {
        
                PantallaMenu.jugador.getImagen().setLayoutY(PantallaMenu.jugador.getImagen().getLayoutY() - 5);
                }
                ColisionHormiga();
                ColisionMosca();
                
                break;
            case DOWN:
                PantallaMenu.jugador.Abajo();
                if (PantallaMenu.jugador.getObjeto().getLayoutY() < 525) {
                    PantallaMenu.jugador.getImagen().setLayoutY(PantallaMenu.jugador.getImagen().getLayoutY() + 5);
                }
                ColisionHormiga();
                ColisionMosca();
                break;
            case RIGHT:
                e.consume();
                PantallaMenu.jugador.Derecha();
                if (PantallaMenu.jugador.getObjeto().getLayoutX() < 1025) {
                    PantallaMenu.jugador.getImagen().setLayoutX(PantallaMenu.jugador.getImagen().getLayoutX() + 5);
                }
                ColisionHormiga();
                ColisionMosca();
                break;
            case LEFT:
                PantallaMenu.jugador.Izquierda();
                if (PantallaMenu.jugador.getObjeto().getLayoutX() > 100) {
                    PantallaMenu.jugador.getImagen().setLayoutX(PantallaMenu.jugador.getImagen().getLayoutX() - 5);
                
                }       
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
            Random rd = new Random();
            int pX = 100 + rd.nextInt(1000);
            int pY = 10 + rd.nextInt(515);
            hormiga.fijarPosicionObjeto(pX, pY);
            hormigas.add(hormiga);
            gamePane.getChildren().add(hormiga.getObjeto());

            Thread h = new Thread(hormiga);
            thrMove.add(h);
            h.start();

        }
    }

    /*
    creacion de las 5 moscas
     */
    public void crearMoscas() {
        for (int i = 0; i < 4; i++) {
            Mosca mosca = new Mosca(15);
            Random rd = new Random();
            int pX = 100 + rd.nextInt(1000);
            int pY = 10 + rd.nextInt(515);
            mosca.fijarPosicionObjeto(pX, pY);
            moscas.add(mosca);
            gamePane.getChildren().add(mosca.getObjeto());

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
            if (isCollision(h.getImagen(), PantallaMenu.jugador.getImagen())) {    // consdicion si la imagen colisiona con la arana
                gamePane.getChildren().remove(h.getObjeto());
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
            if (isCollision(m.getImagen(), PantallaMenu.jugador.getImagen())) {    // consdicion si la imagen colisiona con la arana
                gamePane.getChildren().remove(m.getObjeto());
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
            for (segundos = 0; segundos <= 1000; segundos++) {
                try {
                    Platform.runLater(() -> lblTiempo.setText("Tiempo: " + segundos));
                    Thread.sleep(1000);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }
/*
    hilo para que me ensena el incremento de puntos
    */
     class Puntos implements Runnable {

        @Override
        public void run() {
            for (int t = 0; t <= 1000; t++) {
                try {
                    Platform.runLater(() -> lblpuntos.setText("Puntos: " + Nivel1.puntaje));
                    Thread.sleep(50);
                    System.out.println("El puntajes es: " + Nivel1.puntaje);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public VBox getRoot3() {
        return root3;
    }

    public void setRoot3(VBox root3) {
        this.root3 = root3;
    }
    
}