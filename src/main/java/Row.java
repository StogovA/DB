import java.util.List;

/**
 * Created by Даги on 10.11.2017.
 */
public class Row {
    List<String>columns;

    public Row(List<String>columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Row{" +
                "columns=" + columns +
                '}';
    }
}
