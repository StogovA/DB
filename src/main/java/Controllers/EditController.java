package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DBHandler;
import sample.Person;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Даги on 23.11.2017.
 */
public class EditController implements Initializable {
    @FXML
    public TextField txtID;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtAge;
    @FXML
    public TextField txtEmail;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnSave;
    @FXML
    public Label lblError;
    private Person workPerson;
    private int workPersonID;
    private String nextID;

    public void pressCancel(ActionEvent actionEvent) {
        close();
    }

    public void pressSave(ActionEvent actionEvent) {
        if (isCorrectAge()) {
            if (workPersonID < 0) {
                Person person = new Person(nextID, txtName.getText(), txtAge.getText(), txtEmail.getText());
                Controller.observableList.add(person);
                DBHandler.addPerson(person);
                close();
            } else {
                setPerson();
                Controller.observableList.set(Controller.workPersonID, workPerson);
                DBHandler.updatePerson(workPerson);
                close();
            }
        }
    }

    private void setPerson() {
        workPerson.setId(txtID.getText());
        workPerson.setName(txtName.getText());
        workPerson.setAge(txtAge.getText());
        workPerson.setEmail(txtEmail.getText());
    }

    private boolean isCorrectAge() {
        if (!isDigit(txtAge.getText())) {
            lblError.setText("not a valid Age");
            return false;
        } else {
            return true;
        }
    }

    private void close() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        workPersonID = Controller.workPersonID;
        txtID.setEditable(false);
        if (workPersonID >= 0) {
            workPerson = Controller.observableList.get(workPersonID);
            txtID.setText(workPerson.getId());
            txtName.setText(workPerson.getName());
            txtAge.setText(workPerson.getAge());
            txtEmail.setText(workPerson.getEmail());
        } else {
            nextID = DBHandler.getNextID();
            txtID.setText(nextID);
        }
    }

    private boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
