package corp.ny.com.rufus.database;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yann Yvan CEO of N.Y. Corp. on 04/05/18.
 */
public class Schema {
    private String tableName;
    private List<Column> columns = new ArrayList<>();
    private List<Constraint> constraints = new ArrayList<>();

    private Schema(String tableName) {
        this.tableName = tableName;
    }

    public static Schema instantiate(String tableName) {
        return new Schema(tableName);
    }

    /**
     * Create a new auto-incrementing integer (4-byte) column on the table
     *
     * @param column
     */
    public void increments(String column) {
        columns.add(Column.instantiate(String.format("`%s` INTEGER", column)).primary().incremental());
    }

    /**
     * Specify the primary key(s) for the table.
     *
     * @param column
     */
    public void primary(String... column) {
        for (int i = 0; i < column.length; i++) {
            column[i] = String.format("`%s`", column[i]);
        }
        constraints.add(Constraint.primary(String.format("PRIMARY KEY (%s)", TextUtils.join(",", column))));
    }

    /**
     * Create a new text column on the table.
     *
     * @param column
     * @return
     */
    public Column text(String column) {
        columns.add(Column.instantiate(String.format("`%s` TEXT", column)));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new string column on the table.
     *
     * @param column
     * @return
     */
    public Column string(String column) {
        columns.add(Column.instantiate(String.format("`%s` VARCHAR", column), 45));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new string column on the table.
     *
     * @param column
     * @param size
     * @return
     */
    public Column string(String column, int size) {
        columns.add(Column.instantiate(String.format("`%s` VARCHAR", column), size));
        return columns.get(columns.size() - 1);
    }


    /**
     * Create a new integer (4-byte) column on the table.
     *
     * @param column
     * @return
     */
    public Column integer(String column) {
        columns.add(Column.instantiate(String.format("`%s` INTEGER", column), 10));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new integer (4-byte) column on the table.
     *
     * @param column
     * @param size
     * @return
     */
    public Column integer(String column, int size) {
        columns.add(Column.instantiate(String.format("`%s` INTEGER", column), size));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new double column on the table
     *
     * @param column
     * @return
     */
    public Column doubles(String column) {
        columns.add(Column.instantiate(String.format("`%s` DOUBLE", column)));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new float column on the table.
     *
     * @param column
     * @return
     */
    public Column floats(String column) {
        columns.add(Column.instantiate(String.format("`%s` FLOAT", column)));
        return columns.get(columns.size() - 1);
    }

    public Constraint foreign(String column) {
        constraints.add(Constraint.instantiate(column));
        return constraints.get(constraints.size() - 1);
    }

    @Override
    public String toString() {
        return String.format("CREATE TABLE IF NOT EXISTS %s ( %s%s );"
                , tableName, TextUtils.join(",", columns.toArray())
                , (constraints.isEmpty() ? ""
                        : String.format(",\n%s"
                        , TextUtils.join(",", constraints.toArray()))));
    }
}
