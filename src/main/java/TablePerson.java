import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Created by Даги on 11.11.2017.
 */
public class TablePerson {
    List<Row> rowList;

    public TablePerson(List<Row> rowList) {
        this.rowList = rowList;
        for (Row row : rowList) {
            System.out.println(row);
        }
    }

    public TableView<Row> getTablePerson() {

        ObservableList<Row> observableList = FXCollections.observableArrayList(rowList);

        TableView<Row> persons = new TableView<Row>();
        List<String>names = DBHandler.getNamesColumn();
        for (int i = 0; i < DBHandler.getColumnCount(); i++) {
            TableColumn idColumn = new TableColumn(names.get(i));
            idColumn.setCellValueFactory(
                    new PropertyValueFactory<Row, String>(""));
            persons.getColumns().add(idColumn);
        }

        TableColumn idColumn = new TableColumn("user ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<Row,String>("id"));

        TableColumn nameColumn = new TableColumn("user name");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Row, String>("name"));

        TableColumn ageColumn = new TableColumn("user age");
        ageColumn.setCellValueFactory(
                new PropertyValueFactory<Row, String>("age"));

        TableColumn emailColumn = new TableColumn("user email");
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<Row, String>("email"));

        persons.setItems(observableList);
        persons.getColumns().addAll(idColumn, nameColumn, ageColumn, emailColumn);

        return persons;
    }
}
