import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даги on 10.11.2017.
 */
public class rowFactory {
    private List<Row> rowList;

    public rowFactory() {
        initPersonList();
    }

    private void initPersonList(){
//        try {
//            rowList = new ArrayList<Row>();
//            ResultSet resultSet = DBHandler.statement.executeQuery("SELECT * from users");
//            while (resultSet.next()){
//                rowList.add(new Row(resultSet.getInt(1),resultSet.getString(2),
//                        resultSet.getInt(3),resultSet.getString(4)));
//            }
//        }catch (SQLException e){}
    }

    public List<Row> getRowList() {
        return rowList;
    }
}
