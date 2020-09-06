package sample;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.TimeUnit;


class RandomNumber{
    private int random_number;
    public RandomNumber(){
        Random rand = new Random();
        int int_random = rand.nextInt(1000000);
        random_number = int_random;
    }

    public int getRand(){
        return this.random_number;
    }
}



public class MainStage extends Application {
    public int food_id = 1;
    public Scene scene;
    public int null_hamburgur_id;
    public int null_pizza_id;
    public int orderCount_id_1;
    public int orderCount_id_2;
    public int orderCount_id_3;
    public int orderCount_id_4;
    RandomNumber random = new RandomNumber();
    public int new_user_id = random.getRand();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FileInputStream pizza_input=new FileInputStream("restauraunt-images/pizza-image.png");
        Image pizza_image = new Image(pizza_input,453,272,false,false);
        ImageView pizza_image_view=new ImageView(pizza_image);
        Button pizza_button = new Button("Pizza",pizza_image_view);
        pizza_button.styleProperty().bind(Bindings.when(pizza_button.hoverProperty())
                .then("-fx-background-color: orange;-fx-font-size:40;-fx-text-fill: red;")
                .otherwise("-fx-background-color: white;-fx-font-size:40;-fx-text-fill: red;"));
        pizza_button.setWrapText(true);


        VBox root = new VBox();
        scene=new Scene(root,600,600);
        Connect new_connect = new Connect();


        pizza_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox v_box = new VBox();
                int count = new_connect.countRow("pizza");
                for(food_id=1;food_id<=count;food_id++){
                    Hashtable selected_pizza = new_connect.selectAllByID("pizza", food_id);
                    if (food_id == 1){
                        orderCount_id_1 = 0;
                        Item_pizza_family new_item = new Item_pizza_family(selected_pizza.get("name").toString()
                                , selected_pizza.get("url").toString());
                        new_item.defineRoot();
                        GridPane new_item_root = new_item.getGridPane();
                        v_box.getChildren().add(new_item_root);
                        Button order_button = new_item.getOrderButton();
                        Label order_message = new Label();
                        order_message.setStyle("-fx-text-fill: blue;-fx-font-size: 16");
                        new_item_root.add(order_message,2,3,2,1);
                        new_item_root.setMargin(order_message, new Insets(0, 0, 0,240));
                        order_button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                orderCount_id_1++;
                                long millis=System.currentTimeMillis();
                                Date date = new Date(millis);
                                System.out.println(date);
                                new_connect.insertDataIntoOrders(new_user_id,1,0,"pizza",date);
                                order_message.setText(orderCount_id_1 + " Order Placed!!");
                            }
                        });
                    }else if(food_id == 2){
                        orderCount_id_2 = 0;
                        Item_pizza_medium new_item = new Item_pizza_medium(selected_pizza.get("name").toString()
                                , selected_pizza.get("url").toString());
                        new_item.defineRoot();
                        GridPane new_item_root = new_item.getGridPane();
                        v_box.getChildren().add(new_item_root);
                        Button order_button = new_item.getOrderButton();
                        Label order_message = new Label();
                        order_message.setStyle("-fx-text-fill: blue;-fx-font-size: 16");
                        new_item_root.add(order_message,2,3,2,1);
                        new_item_root.setMargin(order_message, new Insets(0, 0, 0,240));
                        order_button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                orderCount_id_2++;
                                long millis=System.currentTimeMillis();
                                Date date = new Date(millis);
                                new_connect.insertDataIntoOrders(new_user_id,2,0,"pizza",date);
                                order_message.setText(orderCount_id_2 + " Order Placed!!");
                            }
                        });
                    }else if(food_id == 3){
                        orderCount_id_3 = 0;
                        Item_pizza_small new_item = new Item_pizza_small(selected_pizza.get("name").toString()
                                , selected_pizza.get("url").toString());
                        new_item.defineRoot();
                        GridPane new_item_root = new_item.getGridPane();
                        v_box.getChildren().add(new_item_root);
                        Button order_button = new_item.getOrderButton();
                        Label order_message = new Label();
                        order_message.setStyle("-fx-text-fill: blue;-fx-font-size: 16");
                        new_item_root.add(order_message,2,3,2,1);
                        new_item_root.setMargin(order_message, new Insets(0, 0, 0,240));
                        order_button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                orderCount_id_3++;
                                long millis=System.currentTimeMillis();
                                Date date = new Date(millis);
                                new_connect.insertDataIntoOrders(new_user_id,3,0,"pizza",date);
                                order_message.setText(orderCount_id_3 + " Order Placed!!");
                            }
                        });
                    }
                }
                ScrollPane root = new ScrollPane();
                root.setContent(v_box);
                root.setFitToWidth(true);
                scene = new Scene(root,600,400);
                primaryStage.setScene(scene);
            }
        });



        FileInputStream hamburgur_input=new FileInputStream("restauraunt-images/hamburgur-image.jpg");
        Image hamburgur_image = new Image(hamburgur_input,340,272,false,false);
        ImageView hamburgur_image_view=new ImageView(hamburgur_image);
        Button hamburgur_button=new Button("Hamburger",hamburgur_image_view);
        hamburgur_button.styleProperty().bind(Bindings.when(hamburgur_button.hoverProperty())
                .then("-fx-background-color: orange;-fx-font-size:40;-fx-text-fill: red;")
                .otherwise("-fx-background-color: white;-fx-font-size:40;-fx-text-fill: red;"));


        hamburgur_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox v_box = new VBox();
                int count = new_connect.countRow("hamburgur");
                for(food_id=1;food_id<=count;food_id++){
                    Hashtable selected_hamburger = new_connect.selectAllByID("hamburgur", food_id);
                    if (food_id == 1){
                        orderCount_id_1 = 0;
                        Item_hamburger_classic_single new_item = new Item_hamburger_classic_single(selected_hamburger.get("name").toString()
                                , selected_hamburger.get("url").toString());
                        new_item.defineRoot();
                        GridPane new_item_root = new_item.getGridPane();
                        v_box.getChildren().add(new_item_root);
                        Button order_button = new_item.getOrderButton();
                        Label order_message = new Label();
                        order_message.setStyle("-fx-text-fill: blue;-fx-font-size: 16");
                        new_item_root.add(order_message,2,3,2,1);
                        new_item_root.setMargin(order_message, new Insets(0, 0, 0,240));
                        order_button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                orderCount_id_1++;
                                long millis=System.currentTimeMillis();
                                Date date = new Date(millis);
                                new_connect.insertDataIntoOrders(new_user_id,0,1,"hamburger",date);
                                order_message.setText(orderCount_id_1 + " Order Placed!!");
                            }
                        });
                    }else if(food_id == 2){
                        orderCount_id_2 = 0;
                        Item_hamburger_classic_double new_item = new Item_hamburger_classic_double(selected_hamburger.get("name").toString()
                                , selected_hamburger.get("url").toString());
                        new_item.defineRoot();
                        GridPane new_item_root = new_item.getGridPane();
                        v_box.getChildren().add(new_item_root);
                        Button order_button = new_item.getOrderButton();
                        Label order_message = new Label();
                        order_message.setStyle("-fx-text-fill: blue;-fx-font-size: 16");
                        new_item_root.add(order_message,2,3,2,1);
                        new_item_root.setMargin(order_message, new Insets(0, 0, 0,240));
                        order_button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                orderCount_id_2++;
                                long millis=System.currentTimeMillis();
                                Date date = new Date(millis);
                                new_connect.insertDataIntoOrders(new_user_id,0,2,"hamburger",date);
                                order_message.setText(orderCount_id_2 + " Order Placed!!");
                            }
                        });
                    }else if(food_id == 3){
                        orderCount_id_3 = 0;
                        Item_hamburger_supertasty_single new_item = new Item_hamburger_supertasty_single(selected_hamburger.get("name").toString()
                                , selected_hamburger.get("url").toString());
                        new_item.defineRoot();
                        GridPane new_item_root = new_item.getGridPane();
                        v_box.getChildren().add(new_item_root);
                        Button order_button = new_item.getOrderButton();
                        Label order_message = new Label();
                        order_message.setStyle("-fx-text-fill: blue;-fx-font-size: 16");
                        new_item_root.add(order_message,2,3,2,1);
                        new_item_root.setMargin(order_message, new Insets(0, 0, 0,240));
                        order_button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                orderCount_id_3++;
                                long millis=System.currentTimeMillis();
                                Date date = new Date(millis);
                                new_connect.insertDataIntoOrders(new_user_id,0,3,"hamburger",date);
                                order_message.setText(orderCount_id_3 + " Order Placed!!");
                            }
                        });
                    }else if(food_id == 4){
                        orderCount_id_4 = 0;
                        Item_hamburger_supertasty_double new_item = new Item_hamburger_supertasty_double(selected_hamburger.get("name").toString()
                                , selected_hamburger.get("url").toString());
                        new_item.defineRoot();
                        GridPane new_item_root = new_item.getGridPane();
                        v_box.getChildren().add(new_item_root);
                        Button order_button = new_item.getOrderButton();
                        Label order_message = new Label();
                        order_message.setStyle("-fx-text-fill: blue;-fx-font-size: 16");
                        new_item_root.add(order_message,2,3,2,1);
                        new_item_root.setMargin(order_message, new Insets(0, 0, 0,240));
                        order_button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                orderCount_id_4++;
                                long millis=System.currentTimeMillis();
                                Date date = new Date(millis);
                                new_connect.insertDataIntoOrders(new_user_id,0,4,"hamburger",date);
                                order_message.setText(orderCount_id_4 + " Order Placed!!");
                            }
                        });
                    }
                }
                ScrollPane root = new ScrollPane();
                root.setContent(v_box);
                root.setFitToWidth(true);
                scene = new Scene(root,600,400);
                primaryStage.setScene(scene);
            }
        });



        root.getChildren().addAll(pizza_button,hamburgur_button);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MainStage");
        primaryStage.show();
    }
}
