package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Даги on 09.11.2017.
 */
public class Main extends Application {
    public static Stage stage;

    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Start.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();

//        Group root = new Group();
//        Scene scene = new Scene(root, 600, 300, Color.BEIGE);
//        primaryStage.setScene(scene);
//        myTableView = new TablePerson(sample.DBHandler.getPersons()).getTablePerson();
//        System.out.println(myTableView.getColumns().get(1));
//        root.getChildren().add(myTableView);
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
