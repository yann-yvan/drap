package corp.ny.com.rufus.database;

/**
 * Created by Yann Yvan CEO of N.Y. Corp. on 05/05/18.
 */
public class Constraint {
    private String column;
    private String references;
    private String table;
    private String primaries;
    private Action onDelete = Action.NO_ACTION;
    private Action onUpdate = Action.NO_ACTION;

    private Constraint() {
    }

    private Constraint(String column) {
        this.column = column;
    }

    public static Constraint instantiate(String column) {
        return new Constraint(column);
    }

    public static Constraint primary(String columns) {
        return Constraint.getInstance().setPrimaries(columns);
    }

    public static Constraint getInstance() {
        return new Constraint();
    }

    /**
     * Define primary key(s)
     *
     * @param primaries
     * @return
     */
    private Constraint setPrimaries(String primaries) {
        this.primaries = primaries;
        return this;
    }

    /**
     * Specify the foreign table id
     *
     * @param column
     * @return
     */
    public Constraint references(String column) {
        references = column;
        return this;
    }

    /**
     * Specify the foreign table name
     *
     * @param table name
     * @return the constraint
     */
    public Constraint on(String table) {
        this.table = table;
        return this;
    }

    public Constraint onDelete(Action action) {
        onDelete = action;
        return this;
    }

    public Constraint onUpdate(Action action) {
        onUpdate = action;
        return this;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if (primaries != null)
            return primaries;
        else
            return String.format("\n%s %s %s\n%s\n%s",
                    String.format("CONSTRAINT `fk_%s1`", table),
                    String.format("FOREIGN KEY (`%s`)", column),
                    String.format("REFERENCES %s (`%s`)", table, references),
                    String.format("ON DELETE %s", onDelete.toString()),
                    String.format("ON UPDATE %s", onUpdate.toString()));
    }

    public enum Action {
        RESTRICT("RESTRICT"),
        CASCADE("CASCADE"),
        SET_NULL("SET NULL"),
        NO_ACTION("NO ACTION");

        private String value;

        Action(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
