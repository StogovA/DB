package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DBHandler;
import sample.Person;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Даги on 28.11.2017.
 */
public class DeleteController implements Initializable{
    @FXML
    public TextField txtID;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtAge;
    @FXML
    public TextField txtEmail;
    @FXML
    public Button btnYes;
    @FXML
    public Button btnNo;
    private Person workPerson;

    public void pressYes(ActionEvent actionEvent) {
        DBHandler.removePerson(workPerson.getId());
        Controller.observableList.remove(workPerson);
        close();
    }

    public void pressNo(ActionEvent actionEvent) {
        close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        workPerson = Controller.workPerson;
        txtID.setText(workPerson.getId());
        txtID.setEditable(false);
        txtName.setText(workPerson.getName());
        txtName.setEditable(false);
        txtAge.setText(workPerson.getAge());
        txtAge.setEditable(false);
        txtEmail.setText(workPerson.getEmail());
        txtEmail.setEditable(false);
    }

    private void close() {
        Stage stage = (Stage) btnNo.getScene().getWindow();
        stage.close();
    }
}
