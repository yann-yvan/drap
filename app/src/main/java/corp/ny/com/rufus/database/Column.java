package corp.ny.com.rufus.database;

import android.text.TextUtils;

/**
 * Created by Yann Yvan CEO of N.Y. Corp. on 04/05/18.
 */
public class Column {
    private String name;
    private boolean isNullable = false;
    private boolean isUnique = false;
    private boolean isPrimary = false;
    private boolean isIncrement = false;
    private boolean isSigned = false;
    private int size;
    private String defValue;
    private String check;

    private Column(String name) {
        this.name = name;
    }

    /**
     * @param name
     * @param size
     */
    private Column(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public static Column instantiate(String begin, int size) {
        return new Column(begin, size);
    }

    public static Column instantiate(String begin) {
        return new Column(begin);
    }

    /**
     * @param value
     * @return
     */
    public Column defValue(String value) {
        defValue = value;
        return this;
    }

    /**
     * @param values
     * @return
     */
    public Column check(String... values) {
        check = TextUtils.join(",", values);
        return this;
    }

    /**
     * @return
     */
    protected Column primary() {
        isPrimary = true;
        return this;
    }

    /**
     * @return
     */
    protected Column incremental() {
        isIncrement = true;
        return this;
    }

    /**
     * @return
     */
    protected Column signed() {
        isSigned = true;
        return this;
    }

    /**
     * @return
     */
    public Column nullable() {
        isNullable = true;
        return this;
    }

    /**
     * @return
     */
    public Column unique() {
        isUnique = true;
        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "\n%s%s%s%s%s%s%s%s"
                , name
                , (size > 0 ? String.format("(%s)", size) : "")
                , (isNullable ? "" : (isPrimary ? "" : " NOT NULL"))
                , (defValue == null ? "" : String.format(" DEFAULT '%s'", defValue))
                , (check == null ? "" : String.format(" CHECK(%s)", check))
                , (isPrimary ? " PRIMARY KEY" : "")
                , (isIncrement ? " AUTOINCREMENT" : "")
                , (isUnique ? " UNIQUE" : "")
        );
    }
}
