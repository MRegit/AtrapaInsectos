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
import javafx.scene.shape.LineTo;
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
        imagen.setPickOnBounds(true);
         path = generarPath();
        
       
    }

    @Override
    public void run() {
        pt = new PathTransition(Duration.millis(50000), path, super.getObjeto());
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.setAutoReverse(false);
        pt.play();

    }

    public Path generarPath() {
        Integer[] rotaciones={25,-205,205,-25};
        MoveTo inicio = new MoveTo(70, 250);
        Path path = new Path();
        path.getElements().add(inicio);
        Random rd = new Random();
        path.setFill(null);
        CubicCurveTo c1 = new CubicCurveTo(250, 200, 115, 350, 350, 250);
        CubicCurveTo c2 = new CubicCurveTo(550, 200, 465, 350, 650, 250);
        CubicCurveTo c3 = new CubicCurveTo(950, 200, 815, 350, 950, 250);
        LineTo line = new LineTo(550, 50);
        LineTo line2 = new LineTo(120, 250);
        LineTo line3 = new LineTo(550, 500);
        LineTo line4 = new LineTo(950, 250);
        LineTo line5 = new LineTo(120, 250);
        path.getElements().addAll(c1, c2, c3, line, line2, line3, line4, line5);
        int rotacion=rotaciones[rd.nextInt(4)];
        path.setRotate(rotacion);
        return path;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void setImagen(String ruta) {
        Image imag=new Image(getClass().getResourceAsStream("/Recursos/Imagenes/"+ruta), 175, 175, true, true);
        this.imagen.setImage(imag);
        
    }


    

}