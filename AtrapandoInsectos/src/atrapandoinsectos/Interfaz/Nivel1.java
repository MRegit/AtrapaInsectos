/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import atrapandoinsectos.AtrapandoInsectos;
import static atrapandoinsectos.Interfaz.PantallaMenu.jugador;
import atrapandoinsectos.Modelo.Hormiga;
import atrapandoinsectos.Modelo.Jugador;
import atrapandoinsectos.Modelo.Lagartija;
import atrapandoinsectos.Modelo.Mosca;
import atrapandoinsectos.Modelo.Telarana;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class Nivel1 {

    protected VBox root3;
    protected HBox panelsuper;
    protected HBox c;
    protected ImageView corazon;
    protected ImageView corazon2;
    protected ImageView corazon3;
    protected Lagartija lagartija;
    protected Button btsalir;
    protected Label lblTiempo;
    protected Label lblpuntos;
    protected Pane gamePane = new Pane();
    ;
    protected int puntosGanar;
    protected MediaPlayer mediaPlayer;
    protected Thread thrTiempo;

    protected final Thread thrpuntos;
    protected Thread thrtelarana;

    protected List<Hormiga> hormigas = new ArrayList<>(); //lista de hormigas
    protected List<Mosca> moscas = new ArrayList<>(); // list de moscas
    protected List<Thread> thrMove = new ArrayList<>();
    protected Telarana telarana = new Telarana();

    protected Stage stage;

    protected Thread Mlagartija;

    public Nivel1(int nHormigas, int nMoscas) {
        Mosca.vaciarPath();
        Mosca.generarPaths();
        crearHormigas(nHormigas);
        crearMoscas(nMoscas);
        inicializar();
        organizar();
        nuevoStage();

        thrTiempo = new Thread(new Tiempo());
        thrTiempo.setDaemon(true);
        thrTiempo.start();

        thrpuntos = new Thread(new Puntos(puntosGanar));
        thrpuntos.setDaemon(true);
        thrpuntos.start();

        //hilo qie quila la imagen por el gif 
        thrtelarana = new Thread(new Aparece());
        thrtelarana.setDaemon(true);
        thrtelarana.start();

        //inidio del hilo para el movimiento de la lagartija
        Mlagartija = new Thread(lagartija);
        Mlagartija.setDaemon(true);
        Mlagartija.start();

        //buscar nueva version de suspend
        btsalir.setOnAction((ActionEvent e) -> {
            thrTiempo.suspend(); // al dar click en el boton salir se detinee el tiempo
            Mlagartija.suspend();
            e.consume();
            for (Thread hilos : thrMove) {
                hilos.suspend();
            }
            lagartija.getPt().pause();
            for (Mosca m : moscas) {
                m.getPt().pause();
            }
            Salir salir = new Salir(this.thrTiempo, this.thrMove, moscas, lagartija);
            e.consume();
        });

    }

    public void inicializar() {
        panelsuper = new HBox();
        panelsuper.setId("panelsuper");
        jugador.setNivelAlcanzado(1);
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
        btsalir.setId("botonSalir");
        lblTiempo = new Label("Tiempo: ");
        lblpuntos = new Label("Puntos: ");
        puntosGanar = PantallaMenu.jugador.getPuntos();
        for (Hormiga h : hormigas) {
            puntosGanar += h.getPuntos();
        }
        for (Mosca m : moscas) {
            puntosGanar += m.getPuntos();
        }

        PantallaMenu.jugador.getObjeto().relocate(570, 300); //posicion de la arana central
        PantallaMenu.jugador.getObjeto().setFocusTraversable(true);
        PantallaMenu.jugador.getObjeto().setOnKeyPressed(e -> mover(e));

        root3 = new VBox();
        //

    }

    public void organizar() {

        c.getChildren().add(corazon);

        c.getChildren().add(corazon2);
        c.getChildren().add(corazon3);
        panelsuper.getChildren().add(seccionMusica());
        panelsuper.getChildren().add(lblTiempo);
        panelsuper.getChildren().add(c);
        panelsuper.getChildren().add(lblpuntos);
        panelsuper.getChildren().add(btsalir);
        panelsuper.setAlignment(Pos.CENTER);
        panelsuper.setPadding(new Insets(20, 5, 20, 5));
        panelsuper.setSpacing(150);
        panelsuper.setPrefSize(1200, 100);
        gamePane.getChildren().add(telarana.getObjeto());
        telarana.getObjeto().setLayoutX(2000);
        telarana.getObjeto().setLayoutY(2000);
        gamePane.setId("gamePane");
        //gamePane.getChildren().add(new ImageView(new Image("/Recursos/Imagenes/cesped1.png")));
        gamePane.setPrefSize(1200, 600);
        gamePane.getChildren().add(PantallaMenu.jugador.getObjeto());
        gamePane.getChildren().add(lagartija.getObjeto());

        root3.getChildren().addAll(panelsuper, gamePane);
    }

    public void nuevoStage() {
        stage = new Stage();
        Scene scene = new Scene(root3, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/Recursos/Estilos/estilos2.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Nivel 1");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

    }
    /**
     * Se crea la sección de la música
     * VBox  
     */
    
    public VBox seccionMusica() {
        VBox paneles = new VBox();
        Path path = Paths.get("src/Recursos/Musica/musica.mp3");
        Media media = new Media(path.toFile().toURI().toString());
        //Se crea un nuevo objeto de tipo MediaPlayer
        mediaPlayer = new MediaPlayer(media);
        //se indica que se inicie automaticamente cuando se cree la clase PantallaJuego
        mediaPlayer.setAutoPlay(true);
        //La musica se repetirá una y otra vez
        mediaPlayer.setCycleCount(20);
        //Se crean los botones de play y pausa para la música
        Image img = new Image(getClass().getResourceAsStream("/Recursos/Imagenes/play.png"), 30, 30, true, true);
        ImageView play = new ImageView(img);
        ((Node) play).setId("labelPause");
        Image img2 = new Image(getClass().getResourceAsStream("/Recursos/Imagenes/pause.png"), 30, 30, true, true);
        ImageView pau = new ImageView(img2);
        ((Node) pau).setId("labelPause");
        Bloom bloom = new Bloom();
        bloom.setThreshold(10);
        paneles.setEffect(bloom);
        //se crean los eventos de cada boton para que el usuario pueda interactuar con ellos.
        ((Node) play).setOnMouseClicked(e -> {

            mediaPlayer.play();
        });
        ((Node) pau).setOnMouseClicked(e -> {

            mediaPlayer.pause();
        });
        MediaView mediaView = new MediaView(mediaPlayer);
        paneles.getChildren().addAll(((Node) play), ((Node) pau));
        paneles.setSpacing(10);
        paneles.setAlignment(Pos.CENTER);
        return paneles;
    }

    /*
    metodo para mover la arana y sus colisiones
     */
    public void mover(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                e.consume();
                PantallaMenu.jugador.Arriba();
                if (PantallaMenu.jugador.getObjeto().getLayoutY() > 0) {

                    PantallaMenu.jugador.getObjeto().setLayoutY(PantallaMenu.jugador.getObjeto().getLayoutY() - 5);
                }
                ColisionHormiga();
                ColisionMosca();
                ColisionLagartija();
                ColisionTelaarana();
                if (Nivel2.ColisionRocas()) {
                    PantallaMenu.jugador.getObjeto().setLayoutY(PantallaMenu.jugador.getObjeto().getLayoutY() + 5);
                }
                if (Nivel2.ColisionHojas()) {
                    PantallaMenu.jugador.getObjeto().setLayoutY(PantallaMenu.jugador.getObjeto().getLayoutY() + 5);
                }

                break;
            case DOWN:

                PantallaMenu.jugador.Abajo();
                if (PantallaMenu.jugador.getObjeto().getLayoutY() < 525) {
                    PantallaMenu.jugador.getObjeto().setLayoutY(PantallaMenu.jugador.getObjeto().getLayoutY() + 5);
                }
                ColisionHormiga();
                ColisionMosca();
                ColisionLagartija();
                ColisionTelaarana();
                if (Nivel2.ColisionRocas()) {
                    PantallaMenu.jugador.getObjeto().setLayoutY(PantallaMenu.jugador.getObjeto().getLayoutY() - 5);
                }
                if (Nivel2.ColisionHojas()) {
                    PantallaMenu.jugador.getObjeto().setLayoutY(PantallaMenu.jugador.getObjeto().getLayoutY() - 5);
                }
                break;
            case RIGHT:
                e.consume();
                PantallaMenu.jugador.Derecha();
                if (PantallaMenu.jugador.getObjeto().getLayoutX() < 1025) {
                    PantallaMenu.jugador.getObjeto().setLayoutX(PantallaMenu.jugador.getObjeto().getLayoutX() + 5);
                }
                ColisionHormiga();
                ColisionMosca();
                ColisionLagartija();
                ColisionTelaarana();
                if (Nivel2.ColisionRocas()) {
                    PantallaMenu.jugador.getObjeto().setLayoutX(PantallaMenu.jugador.getObjeto().getLayoutX() - 5);
                }
                if (Nivel2.ColisionHojas()) {
                    PantallaMenu.jugador.getObjeto().setLayoutX(PantallaMenu.jugador.getObjeto().getLayoutX() - 5);
                }
                break;
            case LEFT:
                e.consume();
                PantallaMenu.jugador.Izquierda();
                if (PantallaMenu.jugador.getObjeto().getLayoutX() > 100) {
                    PantallaMenu.jugador.getObjeto().setLayoutX(PantallaMenu.jugador.getObjeto().getLayoutX() - 5);

                }
                ColisionHormiga();
                ColisionMosca();
                ColisionLagartija();
                ColisionTelaarana();
                if (Nivel2.ColisionRocas()) {
                    PantallaMenu.jugador.getObjeto().setLayoutX(PantallaMenu.jugador.getObjeto().getLayoutX() + 5);
                }
                if (Nivel2.ColisionHojas()) {
                    PantallaMenu.jugador.getObjeto().setLayoutX(PantallaMenu.jugador.getObjeto().getLayoutX() + 5);
                }
                break;
        }
    }

    /*
    creacion de la 5 hormigas 
     */
    public void crearHormigas(int numeroHormigas) {
        for (int i = 0; i < numeroHormigas; i++) {
            Hormiga hormiga = new Hormiga(10);
            Random rd = new Random();
            int pX = 100 + rd.nextInt(1000);
            int pY = 10 + rd.nextInt(515);
            hormiga.fijarPosicionObjeto(pX, pY);
            hormigas.add(hormiga);
            gamePane.getChildren().add(hormiga.getObjeto());
            Thread h = new Thread(hormiga);
            h.setDaemon(true);
            thrMove.add(h);
            h.start();

        }
    }

    /*
    creacion de las 5 moscas
     */
    public void crearMoscas(int numeroMoscas) {
        for (int i = 0; i < numeroMoscas; i++) {
            Mosca mosca = new Mosca(15);
            moscas.add(mosca);
            gamePane.getChildren().add(mosca.getObjeto());
            Thread m = new Thread(mosca);
            m.setDaemon(true);
            thrMove.add(m);
            m.start();

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
            if (isCollision(h.getObjeto(), PantallaMenu.jugador.getObjeto())) {    // consdicion si la imagen colisiona con la arana
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
            if (isCollision(m.getObjeto(), PantallaMenu.jugador.getObjeto())) {    // consdicion si la imagen colisiona con la arana
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
        if (isCollision(lagartija.getObjeto(), PantallaMenu.jugador.getObjeto())) {

            gamePane.getChildren().remove(PantallaMenu.jugador.getObjeto());//
            Node j = AparecerArana(PantallaMenu.jugador.getVidas());

            if (!gamePane.getChildren().add(j)) {
                gamePane.getChildren().add(j);
            }
        }
    }

    /*
    colision entre la arana y la telarana, falta aumentar que la lagartija quede atrapada por 5 segundos
     */
    public void ColisionTelaarana() {
        if (isCollision(PantallaMenu.jugador.getObjeto(), telarana.getObjeto())) {

            gamePane.getChildren().remove(telarana.getObjeto());
            telarana.setImagen();
            Thread thrQuitarLagartija = new Thread(new QuitarLagartijaAtrapada());
            thrQuitarLagartija.start();

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

        private int puntos;

        public Puntos(int puntos) {
            this.puntos = puntos;
        }

        @Override
        public void run() {
            do {
                try {
                    Platform.runLater(() -> ganar(puntos));
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (PantallaMenu.jugador.getPuntos() <= puntos);

        }
    }

    /*
    hilo para la telarana, aparece durante 5 segundos y demora en aparecer unos 30 segundos
     */
    class Aparece implements Runnable {

        private boolean acabar = false;

        @Override
        public void run() {
            while (acabar == false) {
                try {
                    //agrega la telarana en una posicion aleatoria por 5 segundos
                    Random rd = new Random();
                    int milisegundos = 5000 + rd.nextInt(20000);
                    Thread.sleep(milisegundos);
                    Platform.runLater(() -> meterTelaArana());
                    Thread.sleep(10000);
                    acabar = true;
                    Platform.runLater(() -> {
                        telarana.getObjeto().setLayoutX(2000);
                        telarana.getObjeto().setLayoutY(2000);
                        gamePane.getChildren().remove(telarana.getObjeto());

                    });

                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    /**
     * Hilo para setear la lagartija cuando se atrapa la telaraña
     */
    class QuitarLagartijaAtrapada implements Runnable {

        private boolean acabar = false;

        @Override
        public void run() {
            while (acabar == false) {
                lagartija.getPt().pause();
                Platform.runLater(() -> {
                    lagartija.setImagen("Lagartija-telerana.png");
                    lagartija.getObjeto().setDisable(true);
                });

                try {
                    Thread.sleep(5000);
                    acabar = true;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }
                lagartija.getPt().play();
                Platform.runLater(() -> {
                    lagartija.setImagen("Lagartija-g.gif");
                    lagartija.getObjeto().setDisable(false);
                });
            }
        }
    }

    /*
    metodo donde añade la telarana y da una posicion aleatoria 
     */
    private void meterTelaArana() {
        telarana.posicion();
    }

    public void ganar(int puntos) {
        lblpuntos.setText("Puntos: " + PantallaMenu.jugador.getPuntos());
        if (PantallaMenu.jugador.getPuntos() == puntos) {
            this.thrpuntos.suspend();
            mediaPlayer.stop();
            NivelSuperado NS = new NivelSuperado(this.stage);

        }
    }

    /*
    metodo que me retorna a la arana al colisionar con la lagartija
    y me resta un corazon
     */
    public Node AparecerArana(int vida) {
        Node imagenjugador = null;
        if (vida == 3) {
            PantallaMenu.jugador.setVidas(vida - 1); //elimina una vida
            c.getChildren().remove(corazon);//elimina un corazon
            imagenjugador = PantallaMenu.jugador.getObjeto();
            imagenjugador.relocate(570, 300);
        }
        if (vida == 2) {
            PantallaMenu.jugador.setVidas(vida - 1);

            c.getChildren().remove(corazon2);
            imagenjugador = PantallaMenu.jugador.getObjeto();
            imagenjugador.relocate(570, 300);
        }
        if (vida == 1) {
            c.getChildren().remove(corazon3);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Vidas terminadas");
            alert.setHeaderText("Click en el boton Aceptar para guardar partida");
            alert.setContentText("Al presionar cancel (fin del juego)");
            Jugador jg = new Jugador(jugador.getNombre(), LocalDate.now(), jugador.getPuntos(), jugador.getNivelAlcanzado());
            jg.Escritura();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                mediaPlayer.stop();

                this.stage.close();
                imagenjugador = PantallaMenu.jugador.getObjeto();
                imagenjugador.relocate(570, 300);

            } else {
                // ... user chose CANCEL or closed the dialog
                Platform.exit();
                
            }

        }
        //condicio para el alert 
        return imagenjugador;
    }

}
