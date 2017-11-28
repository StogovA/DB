package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private final String EDIT_PATH = "/fxml/EditPerson.fxml";
    private final String REMOVE_PATH = "/fxml/DeletePerson.fxml";
    private final String ADD = "Add Person";
    private final String EDIT = "Edit Person";
    private final String REMOVE = "Remove Person";

    static ObservableList<Person> observableList;
    static Person workPerson;
    static int workPersonID = -1;

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
    @FXML
    public Button btnEdit;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnAdd;

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
                            workPersonID = row.getIndex();
                            startWindow(EDIT, EDIT_PATH, 400, 150);
                        }
                    }
                });
                return row;
            }
        });
        observableList = FXCollections.observableArrayList(DBHandler.getPersons());
        myTableView.setEditable(true);
        colID.setText("ID");
        colID.setEditable(false);
        colID.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
        colName.setText("Name");
        colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        colAge.setText("Age");
        colAge.setCellValueFactory(new PropertyValueFactory<Person, String>("age"));
        colEmail.setText("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        myTableView.setItems(observableList);
    }


    private void startWindow(String actionType, String path, int width, int height) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(actionType);
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        stage.show();
    }

    public void pressEdit(ActionEvent actionEvent) {
        initWorkPerson();
        startWindow(EDIT, EDIT_PATH, 400, 150);
    }

    public void pressDelete(ActionEvent actionEvent) {
        initWorkPerson();
        startWindow(REMOVE, REMOVE_PATH, 400, 200);
    }

    public void pressAdd(ActionEvent actionEvent) {
        workPersonID = -1;
        workPerson = null;
        startWindow(ADD, EDIT_PATH, 400, 150);
    }

    private void initWorkPerson() {
        workPersonID = myTableView.getSelectionModel().getFocusedIndex();
        workPerson = observableList.get(workPersonID);
    }
}
