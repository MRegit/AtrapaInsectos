/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class PantallaInicio {

    private Pane root;
    private Button bPlay;
    private Button bScore;
    private Button bExit;

    public PantallaInicio() {
        root = new Pane();
        root.setId("root");
        VBox root2 = new VBox();
        Image img = new Image(getClass().getResourceAsStream("/Recursos/Imagenes/LOGO.png"), 450, 350, true, true);
        ImageView logo = new ImageView(img);
        logo.setLayoutX(650);
        logo.setLayoutY(100);

        bPlay = new Button("PLAY");
        bPlay.setPrefSize(170, 85);
        bPlay.setId("bPlay");

        //bPlay.setAlignment(Pos.CENTER);
        bScore = new Button("SCORE");
        bScore.setId("bScore");    //identificaciond el nodo
        bScore.setPrefSize(170, 85);
        bExit = new Button("EXIT");
        bExit.setId("bExit");
        bExit.setPrefSize(170, 85);
        root2.getChildren().addAll(bPlay, bScore, bExit);
        root2.setAlignment(Pos.CENTER);
        root2.setSpacing(20);
        root2.setLayoutX(790);
        root2.setLayoutY(340);
        root.getChildren().addAll(logo, root2);

    }
    /**
     * 
     * @return 
     */
    public Pane getRoot() {
        return root;
    }
    /**
     * 
     * @param root 
     */
    public void setRoot(Pane root) {
        this.root = root;
    }
    /**
     * 
     * @return 
     */
    public Button getbPlay() {
        return bPlay;
    }
    /**
     * 
     * @param bPlay 
     */
    public void setbPlay(Button bPlay) {
        this.bPlay = bPlay;
    }
    /**
     * 
     * @return 
     */
    public Button getbScore() {
        return bScore;
    }
    /**
     * 
     * @param bScore 
     */
    public void setbScore(Button bScore) {
        this.bScore = bScore;
    }
    /**
     * 
     * @return 
     */
    public Button getbExit() {
        return bExit;
    }
    /**
     * 
     * @param bExit 
     */
    public void setbExit(Button bExit) {
        this.bExit = bExit;
    }

}
