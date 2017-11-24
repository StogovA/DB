package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.DBHandler;
import sample.Main;
import sample.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Даги on 09.11.2017.
 */
public class Controller implements Initializable {
    @FXML
    public TableView myTableView;
    @FXML
    public TableColumn colID;
    @FXML
    public TableColumn colName;
    @FXML
    public TableColumn colAge;
    @FXML
    public TableColumn colEmail;

    public static ObservableList<Person> observableList;
    public static Person workPerson;
    public static int workPersonInt;

    public void initialize(URL location, ResourceBundle resources) {
        myTableView.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow<Person> call(TableView tv) {
                TableRow<Person> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2 && (!row.isEmpty())) {
                            workPerson = row.getItem();
                            workPersonInt = row.getIndex();
                            startEdit();
                        }
                    }
                });
                return row;
            }
        });
        observableList = FXCollections.observableArrayList(DBHandler.getPersons());
        myTableView.setEditable(true);
        colID.setText("ID");
        colID.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
        colName.setText("Name");
        colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        colAge.setText("Age");
        colAge.setCellValueFactory(new PropertyValueFactory<Person, String>("age"));
        colEmail.setText("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        myTableView.setItems(observableList);
    }


    private void startEdit() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/EditPerson.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Edit Person");
        stage.setScene(new Scene(root, 400, 150));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        stage.show();
    }
}
