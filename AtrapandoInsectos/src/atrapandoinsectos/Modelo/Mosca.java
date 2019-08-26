/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Modelo;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author pc
 */
public class Mosca extends Insecto {

    private Path path;
    static  ArrayList<Path> UbicacionesPaths = new ArrayList<Path>();

    public Mosca(int puntos) {
        super(puntos);
        Image img = new Image(getClass().getResourceAsStream("/Recursos/Imagenes/Mosca.png"), 70, 70, true, true);
        imagen = new ImageView(img);
        path = escogerPath();
    }

    @Override
    public void run() {

        pt = new PathTransition(Duration.millis(50000), path, super.getObjeto());
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.setAutoReverse(false);
        pt.play();
    }

    public ImageView getImagen() {
        return imagen;
    }

    public static void generarPaths() {
        Integer[] rotaciones = {0, 90, 180, 270};
        Integer[] rotaciones2 = {45, 135, 225, 315};
        //diseño 1 de path
        for (Integer i : rotaciones) {
            MoveTo inicio = new MoveTo(200, 90);
            Path path = new Path();
            path.getElements().addAll(inicio, new CubicCurveTo(800, 0, 800, 125, 500, 100), new CubicCurveTo(100, 100, 100, 225, 500, 150), new CubicCurveTo(800, 125, 800, 250, 500, 200), new CubicCurveTo(100, 200, 100, 300, 500, 250), new CubicCurveTo(800, 250, 800, 375, 500, 300), new CubicCurveTo(100, 275, 100, 375, 500, 350), new CubicCurveTo(800, 375, 800, 500, 500, 400), new CubicCurveTo(100, 350, 100, 450, 500, 450), new CubicCurveTo(800, 475, 800, 600, 500, 500), new CubicCurveTo(100, 425, 100, 525, 700, 550), new LineTo(200, 90));
            path.setRotate(i);
            UbicacionesPaths.add(path);

        }
        //diseño 2 de path
        for (Integer i : rotaciones2) {
            Path path2 = new Path();
            MoveTo moveTo = new MoveTo(200, 200);
            path2.getElements().add(moveTo);
            path2.getElements().addAll(new CubicCurveTo(370, 150, 65 + 170, 300, 700, 200), new LineTo(700, 200), new LineTo(200, 500), new LineTo(450, 50), new LineTo(200, 200));
            path2.setRotate(i);
            UbicacionesPaths.add(path2);

        }

    }

    public Path escogerPath() {
        Random rd = new Random();
        int indice = rd.nextInt(UbicacionesPaths.size());
        System.out.println(indice);
        Path p = UbicacionesPaths.get(indice);
        UbicacionesPaths.remove(p);
        return p;
    }
    public static void vaciarPath() {
        UbicacionesPaths=new ArrayList<Path>();
    }
    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

}
