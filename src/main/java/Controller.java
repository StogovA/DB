import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Даги on 09.11.2017.
 */
public class Controller {
    @FXML
    public TableView myTableView;

    private ResultSet resultSet;
    private int countColumn;

//    private int getCountColumn() throws SQLException {
//        resultSet = DBHandler.statement.executeQuery("SELECT * from users");
//        return resultSet.getMetaData().getColumnCount();
//    }

}
