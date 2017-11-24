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
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by Даги on 23.11.2017.
 */
public class EditController implements Initializable{
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

    public void pressCancel(ActionEvent actionEvent) {
       close();
    }

    public void pressSave(ActionEvent actionEvent) {
        if (isCorrectId() && isCorrectAge()) {
            workPerson.setId(txtID.getText());
            workPerson.setName(txtName.getText());
            workPerson.setAge(txtAge.getText());
            workPerson.setEmail(txtEmail.getText());
            Controller.observableList.set(Controller.workPersonInt,workPerson);
            close();
        }
    }

    private boolean isCorrectAge() {
        if (!isDigit(txtAge.getText())){
            lblError.setText("not a valid Age");
            return false;
        }else {
            return true;
        }
    }

    private boolean isCorrectId() {
        String id = txtID.getText();
        if (!isDigit(id)){
            lblError.setText("not a valid ID");
            return false;
        }else if (!Objects.equals(workPerson.getId(), id) && !DBHandler.checkID(Integer.parseInt(id))){
            lblError.setText("ID already exists. Please enter another ID");
            return false;
        }
        return true;
    }

    private void close(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        workPerson = Controller.observableList.get(Controller.workPersonInt);
        txtID.setText(workPerson.getId());
        txtName.setText(workPerson.getName());
        txtAge.setText(workPerson.getAge());
        txtEmail.setText(workPerson.getEmail());
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
