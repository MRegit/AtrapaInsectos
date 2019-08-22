/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atrapandoinsectos.Modelo;



import static atrapandoinsectos.Modelo.Insecto.parar;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author pc
 */
public class Lagartija extends Insecto {
    private Path path;
    public Lagartija(int puntos) {
        super(puntos);
        Image img = new Image(getClass().getResourceAsStream("/Recursos/Imagenes/Lagartija-g.gif"), 175, 175, true, true);
        imagen = new ImageView(img);
         path = generarPath();
        posicion();
       
    }

    @Override
    public void run() {
        Insecto.pt = new PathTransition(Duration.millis(30000), path, super.getObjeto());
        Insecto.pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        Insecto.pt.setCycleCount(Animation.INDEFINITE);
        Insecto.pt.setAutoReverse(true);
        Insecto.pt.play();

    }

    public static void pausar() {
        if (parar == true) {
            Insecto.pt.stop();
        }else{
            Insecto.pt.play();
        }
    }
    public Path generarPath() {
        MoveTo inicio = new MoveTo();
        Path path = new Path();
        path.getElements().add(inicio);
        Random rd = new Random();
        path.setFill(null);
        path.setStroke(Color.GRAY);
        path.setStrokeWidth(2);

            CubicCurveTo c1=new CubicCurveTo(380, 0, 380, 120, 200, 120);
            CubicCurveTo c2=new CubicCurveTo(0, 120, 0, 240, 380, 240);
            CubicCurveTo c3=new CubicCurveTo(480, 240, 480, 360, 200, 360);
            CubicCurveTo c4=new CubicCurveTo(0, 360, 0, 480, 380, 480);
            
            path.getElements().addAll(c1,c2,c3,c4);
       
        return path;
    }

    public PathTransition getPt() {
        return pt;
    }

    public void setPt(PathTransition pt) {
        this.pt = pt;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
    /*
    meto para la posicion inicial de la lagartija
     */
    public void posicion() {
        Random r = new Random();
        int valorDado = r.nextInt(4);
        if (valorDado == 0) {
            imagen.relocate(80, 0);
        }
        if (valorDado == 1) {
            imagen.relocate(950, 0);
        }
        if (valorDado == 2) {
            imagen.relocate(80, 500);
        }
        if (valorDado == 3) {
            imagen.relocate(950, 500);

        }
    }

}
