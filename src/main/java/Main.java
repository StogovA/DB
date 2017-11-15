import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Даги on 09.11.2017.
 */
public class Main extends Application {
    @FXML
    public TableView myTableView;
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300, Color.BEIGE);
        primaryStage.setScene(scene);
        myTableView = new TablePerson(DBHandler.getRows()).getTablePerson();
        root.getChildren().add(myTableView);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
