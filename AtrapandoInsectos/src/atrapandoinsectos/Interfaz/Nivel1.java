/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import atrapandoinsectos.Modelo.Hormiga;
import atrapandoinsectos.Modelo.Lagartija;
import atrapandoinsectos.Modelo.Mosca;
import atrapandoinsectos.Modelo.Telarana;
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
    private Lagartija lagartija;
    private Button btsalir;
    private Label lblTiempo;
    private Label lblpuntos;
    private Pane gamePane;

    private Thread thrTiempo;

    private Thread thrQuitarLagartija;

    private final Thread thrpuntos;
    private Thread thrtelarana;

    private List<Hormiga> hormigas = new ArrayList<>(); //lista de hormigas
    private List<Mosca> moscas = new ArrayList<>(); // list de moscas
    private List<Thread> thrMove = new ArrayList<>();
    private Telarana telarana;
    //
    Image img = new Image(getClass().getResourceAsStream("/Recursos/Imagenes/Telarana1.png"), 120, 120, true, true);
    ImageView imgv = new ImageView(img);  //imagen de la telarana para hacer colision

    ImageView imgTelaLagartija = new ImageView(new Image("/Recursos/Imagenes/Lagartija-telerana.png"));

    private Stage stage;

    private Thread Mlagartija;

    public Nivel1() {

        inicializar();
        organizar();
        nuevoStage();

        thrTiempo = new Thread(new Tiempo());
        thrTiempo.start();

        thrpuntos = new Thread(new Puntos());
        thrpuntos.start();
        //hilo qie quila la imagen por el gif 
        this.thrQuitarLagartija = new Thread(new QuitarLagartijaAtrapada());
        this.thrQuitarLagartija.start();

        thrtelarana = new Thread(new Aparece());
        thrtelarana.start();
        //inidio del hilo para el movimiento de la lagartija
        Mlagartija = new Thread(lagartija);
        Mlagartija.start();

        crearHormigas();
        crearMoscas();
        //buscar nueva version de suspend
        btsalir.setOnAction((ActionEvent e) -> {
            thrTiempo.suspend(); // al dar click en el boton salir se detinee el tiempo
            Mlagartija.suspend();
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
        lagartija = new Lagartija(0);
        btsalir = new Button("Salir");
        btsalir.setPrefSize(100, 75);
        gamePane = new Pane();
        lblTiempo = new Label("Tiempo: ");
        lblpuntos = new Label("Puntos: ");
        //

        telarana = new Telarana(imgv);

        PantallaMenu.jugador.getImagen().relocate(570, 300); //posicion de la arana central
        PantallaMenu.jugador.getImagen().setFocusTraversable(true);
        PantallaMenu.jugador.getImagen().setOnKeyPressed(e -> mover(e));

        root3 = new VBox();
        //

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
        panelsuper.setPrefSize(1200, 100);
        //gamePane.setId("gamePane");
        gamePane.getChildren().add(new ImageView(new Image("/Recursos/Imagenes/cesped1.png")));
        gamePane.setPrefSize(1200, 600);
        gamePane.getChildren().add(PantallaMenu.jugador.getImagen());
        gamePane.getChildren().add(lagartija.getImagen());

        root3.getChildren().addAll(panelsuper, gamePane);
    }

    public void nuevoStage() {
        stage = new Stage();
        Scene scene = new Scene(root3, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos2.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Nivel 1");
        stage.setResizable(false);  //ventana con relleno no deseado
        stage.show();

    }

    /*
    metodo para mover la arana y sus colisiones
     */
    public void mover(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                e.consume();
                PantallaMenu.jugador.Arriba();
                if (PantallaMenu.jugador.getImagen().getLayoutY() > 0) {

                    PantallaMenu.jugador.getImagen().setLayoutY(PantallaMenu.jugador.getImagen().getLayoutY() - 5);
                }
                ColisionHormiga();
                ColisionMosca();
                ColisionLagartija();
                ColisionTelaarana();

                break;
            case DOWN:
                PantallaMenu.jugador.Abajo();
                if (PantallaMenu.jugador.getImagen().getLayoutY() < 525) {
                    PantallaMenu.jugador.getImagen().setLayoutY(PantallaMenu.jugador.getImagen().getLayoutY() + 5);
                }
                ColisionHormiga();
                ColisionMosca();
                ColisionLagartija();
                ColisionTelaarana();
                break;
            case RIGHT:
                e.consume();
                PantallaMenu.jugador.Derecha();
                if (PantallaMenu.jugador.getImagen().getLayoutX() < 1025) {
                    PantallaMenu.jugador.getImagen().setLayoutX(PantallaMenu.jugador.getImagen().getLayoutX() + 5);
                }
                ColisionHormiga();
                ColisionMosca();
                ColisionLagartija();
                ColisionTelaarana();
                break;
            case LEFT:
                PantallaMenu.jugador.Izquierda();
                if (PantallaMenu.jugador.getImagen().getLayoutX() > 100) {
                    PantallaMenu.jugador.getImagen().setLayoutX(PantallaMenu.jugador.getImagen().getLayoutX() - 5);

                }
                ColisionHormiga();
                ColisionMosca();
                ColisionLagartija();
                ColisionTelaarana();
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
//            Thread m = new Thread(mosca);
//            m.start();

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
     metodo que revisa las colisiones de las hormigas y lanza el puntaje de 10
     */
    public void ColisionHormiga() {
        Iterator<Hormiga> itHormiga = hormigas.iterator(); //ir uno a uno en una coleccion, puedo eliminar sin afectar su indice

        while (itHormiga.hasNext()) {
            Hormiga h = itHormiga.next();
            if (isCollision(h.getImagen(), PantallaMenu.jugador.getImagen())) {    // consdicion si la imagen colisiona con la arana
                gamePane.getChildren().remove(h.getObjeto());
                itHormiga.remove();
                PantallaMenu.jugador.setPuntos(10 + PantallaMenu.jugador.getPuntos());
                //this.puntaje += 10; //puntaje por;  cada colision

            }

        }

    }

    /*
    metodo que revisa las colisiones de las moscas y lanza puntaje de 15
     */
    public void ColisionMosca() {
        Iterator<Mosca> itMosca = moscas.iterator(); //ir uno a uno en una coleccion, puedo eliminar sin afectar su indice
        while (itMosca.hasNext()) {
            Mosca m = itMosca.next();
            if (isCollision(m.getImagen(), PantallaMenu.jugador.getImagen())) {    // consdicion si la imagen colisiona con la arana
                gamePane.getChildren().remove(m.getObjeto());
                itMosca.remove();
                PantallaMenu.jugador.setPuntos(15 + PantallaMenu.jugador.getPuntos());
                //this.puntaje += 15; //puntaje por cada 
            }
        }
    }

    /*
    colision entre lagartija y arana,donde se elimina un corazon y vuelve aparecer falta mejorar
     */
    public void ColisionLagartija() {
        if (isCollision(lagartija.getImagen(), PantallaMenu.jugador.getImagen())) {

            gamePane.getChildren().remove(PantallaMenu.jugador.getImagen());//
            ImageView j = AparecerArana(PantallaMenu.jugador.getVidas());

            gamePane.getChildren().add(j);
//            
        }
    }

    /*
    colision entre la arana y la telarana, falta aumentar que la lagartija quede atrapada por 5 segundos
     */
    public void ColisionTelaarana() {
        if (isCollision(telarana.getImagen(), PantallaMenu.jugador.getImagen())) {

            if (!gamePane.getChildren().contains(this.imgTelaLagartija)) {
                //detener el hilo de la lagartija
                gamePane.getChildren().remove(telarana.getImagen());
                this.imgTelaLagartija.relocate(lagartija.getImagen().getLayoutX(), lagartija.getImagen().getLayoutY());
                gamePane.getChildren().remove(lagartija.getImagen());
                gamePane.getChildren().add(imgTelaLagartija);

            }

//            System.out.println("despues de img telaLagartija");
        }
    }

    /*
    timepo en que se demora el nivel 1
     */
    class Tiempo implements Runnable {

        int segundos;

        @Override
        public void run() {
            do {
                try {

                    Platform.runLater(() -> lblTiempo.setText("Tiempo: " + segundos));
                    Thread.sleep(1000);
                    segundos++;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }

            } while (true);

        }
    }

    /*
    hilo para los puntos acumlados
     */
    class Puntos implements Runnable {

        @Override
        public void run() {
            do {
                try {
                    Platform.runLater(() -> ganar());
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (PantallaMenu.jugador.getPuntos() <= 110);

        }
    }

    /*
    hilo para la telarana, aparece durante 5 segundos y demora en aparecer unos 30 segundos
     */
    class Aparece implements Runnable {

        @Override
        public void run() {

            do {
                try {
                    //agrega la telarana en una posicion aleatoria por 5 segundos
                    Platform.runLater(() -> meterTelaArana());
                    Thread.sleep(10000);
                    //remueve la telarana por 30 segundos
                    Platform.runLater(() -> gamePane.getChildren().remove(imgv));
                    Thread.sleep(5000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (true);
        }
    }

    /*
    
     */
 /*
    hilo para la telarana, aparece durante 5 segundos y demora en aparecer unos 30 segundos
     */
    class QuitarLagartijaAtrapada implements Runnable {

        @Override
        public void run() {

            do {

                try {
                    Platform.runLater(() -> ColisionTelaarana());
                    Thread.sleep(5000);
                    Platform.runLater(() -> devueltalagartija());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (true);
        }
    }

    /*
    metodo que ace iniciar el hilo d ela lagartija y la hace aparecer
     */
    private void devueltalagartija() {
        if (gamePane.getChildren().contains(imgTelaLagartija)) {
            gamePane.getChildren().remove(imgTelaLagartija);
            gamePane.getChildren().add(lagartija.getImagen());
            //activar el hilo de la telarana
        }
    }

    /*
    metod que maneja si la lagartija se choco con la telarana
     */
//    private void lagartijaTela() {
//        if (this.lagartija.getImagen().isDisable() == true && !this.imgTelaLagartija.isDisable()) {
//            gamePane.getChildren().remove(this.lagartija.getImagen());
//
//        }
//    }

    /*
    metodo donde anada la telarana y da una posicion aleatoria 
     */
    private void meterTelaArana() {
        gamePane.getChildren().add(telarana.getImagen());
        telarana.posicion();
    }

    /*
    
     */
 /*
    metodo para ir al otro nivel
    
     */
    public void ganar() {
        lblpuntos.setText("Puntos: " + PantallaMenu.jugador.getPuntos());
        if (PantallaMenu.jugador.getPuntos() == 110) {
            this.thrpuntos.suspend();
            NivelSuperado NS = new NivelSuperado(this.stage);

        }
    }

    /*
    metodo que me retorna a la arana al colisionar con la lagartija
    y me resta un corazon
     */
    public ImageView AparecerArana(int vida) {
        ImageView imagenjugador = null;
        if (vida == 3) {
            PantallaMenu.jugador.setVidas(vida - 1); //elimina una vida
            c.getChildren().remove(corazon);//elimina un corazon
            imagenjugador = PantallaMenu.jugador.getImagen();
            imagenjugador.relocate(570, 300);
        }
        if (vida == 2) {
            PantallaMenu.jugador.setVidas(vida - 1);

            c.getChildren().remove(corazon2);
            imagenjugador = PantallaMenu.jugador.getImagen();
            imagenjugador.relocate(570, 300);
        }
        if (vida == 1) {
            PantallaMenu.jugador.setVidas(vida - 1);

            c.getChildren().remove(corazon3);
            imagenjugador = PantallaMenu.jugador.getImagen();
            imagenjugador.relocate(570, 300);
        }
        //condicio para el alert 
        return imagenjugador;
    }

}
