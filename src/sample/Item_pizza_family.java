package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Item_pizza_family {
    private String food_text;
    private String image_url;
    private ImageView image_view;
    private Button next;
    private Button previous;
    private Button order;
    private GridPane gridPane;
    public Item_pizza_family(String food_text, String image_url){
        this.food_text = food_text;
        this.image_url = image_url;


        try {
            FileInputStream image_file =new FileInputStream(image_url);
            Image image = new Image(image_file,220,220,false,false);
            this.image_view = new ImageView(image);
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void defineRoot() {
        Label name_label = new Label(this.food_text);
        name_label.setStyle("-fx-font-size: 20;-fx-text-fill: red;");
        order = new Button("Order");
        order.setStyle("-fx-font-size: 16;");
        gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: orange");
        gridPane.add(this.image_view,1,1,2,2);
        gridPane.add(name_label,3,1);
        gridPane.add(order,3,2);
        gridPane.setMargin(name_label, new Insets(20, 0, 0,190));
        gridPane.setMargin(this.image_view, new Insets(40, 0, 0,0));
        gridPane.setMargin(order, new Insets(0, 0, 0,190));
    }

    public void changeRoot(String food_text,String image_url){
        this.food_text = food_text;
        try {
            FileInputStream image_file =new FileInputStream(image_url);
            Image image = new Image(image_file,220,220,false,false);
            this.image_view = new ImageView(image);
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public GridPane getGridPane(){
        return this.gridPane;
    }

    public Button getOrderButton(){
        return this.order;
    }
}