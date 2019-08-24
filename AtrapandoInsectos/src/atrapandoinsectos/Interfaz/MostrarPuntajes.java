/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atrapandoinsectos.Interfaz;

import atrapandoinsectos.Modelo.Jugador;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author israe
 */
public class MostrarPuntajes {
    private List<Jugador> jugadores ;
    private StackPane root ;
    private Button bt;
    
    public MostrarPuntajes(){
        root = new StackPane();
        root.getChildren().add(new ImageView(new Image("/Recursos/Imagenes/cesped2.jpg")));
        
        VBox vb = new VBox();
        
        Label lb = new Label("Historial de Jugadores");
        lb.setTextFill(Color.WHITE);
        lb.setPadding(new Insets(10,10,10,10));
        lb.setFont(new Font(50));
        lb.setBackground(new Background(new BackgroundFill(Color.color(0.5, 0.5, 0.5, 0.7),new CornerRadii(9),new Insets(15,10,15,10))));
        lb.setOpacity(50);
        TableView tb = new TableView();
        tb.setMaxSize(1150,600);
        tb.setMinSize(250,500);
          
        bt = new Button("<-- Regresar");
        bt.setFont(new Font(25));
        bt.setTextFill(Color.CRIMSON);
        
        jugadores = cargarDatos();
        Collections.sort(jugadores);
        
        TableColumn<String, Jugador> clnombre = new TableColumn<>("Nombre");
        TableColumn<Jugador, LocalDate> clfecha = new TableColumn<>("Fecha");
        TableColumn<Jugador, Integer> clpuntos = new TableColumn<>("Puntos");
        TableColumn<Jugador, Integer> clnivel = new TableColumn<>("Nivel");
        
        ObservableList<Jugador> onl =  FXCollections.observableArrayList(jugadores);
        tb.setItems(onl);
        
        clnombre.setCellValueFactory(new PropertyValueFactory<String, Jugador>("nombre"));
        clnombre.setMinWidth(tb.getMaxWidth()/4);
        clfecha.setCellValueFactory(new PropertyValueFactory<Jugador, LocalDate>("fecha"));
        clfecha.setMinWidth(tb.getMaxWidth()/4);
        clpuntos.setCellValueFactory(new PropertyValueFactory<Jugador, Integer>("puntos"));
        clpuntos.setMinWidth(tb.getMaxWidth()/4);
        clnivel.setCellValueFactory(new PropertyValueFactory<Jugador, Integer>("nivelAlcanzado"));
        clnivel.setMinWidth((tb.getMaxWidth()/4)-1);
          
        tb.getColumns().addAll(clnombre,clfecha,clpuntos,clnivel);
        
        vb.getChildren().addAll(lb,tb,bt);
        vb.setAlignment(Pos.TOP_CENTER);
        vb.setSpacing(15);
        vb.setPadding(new Insets(15,15,15,15));
        
        
        root.getChildren().add(vb);
        
    
    
    }
    
    public static ArrayList<Jugador> cargarDatos(){
        // crea el flujo para leer desde el archivo
        File file = new File("src/recursos/datos/jugadores.txt");
        ArrayList<Jugador> players = new ArrayList<>();
        Scanner scanner;
        
        try {
            //se pasa el flujo al objeto scanner
            scanner = new Scanner(file, "ISO-8859-1");
            
            while (scanner.hasNextLine()) {
                // el objeto scanner lee linea a linea desde el archivo
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                //se usa una expresión regular
                //que valida que antes o despues de una coma (,) exista cualquier cosa
                //parte la cadena recibida cada vez que encuentre una coma				
                delimitar.useDelimiter("\\s*,\\s*");
                Jugador jug;
                String name =delimitar.next();
                String date = delimitar.next();
                int points = delimitar.nextInt();
                int level = delimitar.nextInt();
//                System.out.print(name+"l "+lastName+"l "+user+"l "+password+"l "+perfil);
                jug = new Jugador(name,LocalDate.parse(date),points,level);
                players.add(jug);
            }
            
                 //se cierra el ojeto scanner
            scanner.close();
            
        } catch (FileNotFoundException ex){ 
        }
        return players;
    }

    public StackPane getRoot() {
        return root;
    }

    public Button getBt() {
        return bt;
    }
    
    
    
    
}
