/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Modelo;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class Hormiga extends Insecto{
    public Hormiga(int puntos) {
        super(puntos);
        Image img=new Image(getClass().getResourceAsStream("/Recursos/Imagenes/hormiga.png"),75,75,true,true);
        imagen= new ImageView(img);
    }

    @Override
    public void run(){
        while (parar == false) {

                //Los condicionales sirven para que el personaje no pase los bordes
                String[] eventos = {"UP", "DOWN", "RIGHT", "LEFT"};
                Random indice = new Random();
                int indice2 = indice.nextInt(4);
                System.err.println(indice2);
                String evento = eventos[indice2];
                switch (evento) {
                    case "UP":
                        super.getObjeto().setRotate(360);

                        for (int i = 0; i < 40; i++) {
                            if (super.getObjeto().getLayoutY() > 90) {
                                //mover arriba
                                //nuevay = posiciony - delta;

                                double y1 = super.getObjeto().getLayoutY() - 5;
                                System.out.println(y1);
                                Platform.runLater(() -> {
                                    super.getObjeto().setLayoutY(y1);

                                });
                                try {
                                    sleep(50);
                                } catch (InterruptedException ex) {
                                    
                                }
                            }
                            //chequearColisiones();
                        }

                        break;
                    case "DOWN":
                        super.getObjeto().setRotate(180);
                        for (int i = 0; i < 40; i++) {

                            if (super.getObjeto().getLayoutY() < 550) {

                                double y2 = super.getObjeto().getLayoutY() + 5;
                                System.out.println(y2);
                                Platform.runLater(() -> {
                                    super.getObjeto().setLayoutY(y2);
                                });
                                try {
                                    sleep(50);
                                } catch (InterruptedException ex) {
                                    
                                }

                            }
                            //chequearColisiones();
                        }

                        break;
                    case "LEFT":
                        super.getObjeto().setRotate(270);
                        for (int i = 0; i < 40; i++) {

                            if (super.getObjeto().getLayoutX() > 10) {

                                double x1 = super.getObjeto().getLayoutX() - 5;
                                System.out.println(x1);

                                Platform.runLater(() -> {
                                    super.getObjeto().setLayoutX(x1);
                                });
                                try {
                                    sleep(50);
                                } catch (InterruptedException ex) {
                                    
                                }
                            }
                            //chequearColisiones();
                        }

                        break;
                    case "RIGHT":
                        super.getObjeto().setRotate(90);
                        for (int i = 0; i < 20; i++) {
                            if (super.getObjeto().getLayoutX() < 950) {

                                double x2 = super.getObjeto().getLayoutX() + 5;
                                System.out.println(x2);
                                Platform.runLater(() -> {
                                    super.getObjeto().setLayoutX(x2);
                                });
                                try {
                                    sleep(50);
                                } catch (InterruptedException ex) {
                                    
                                }
                            }
                            //chequearColisiones();
                        }

                        break;
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                   
                    System.out.println("Error");
                }

            }

        }

    
}
