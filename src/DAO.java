import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    void add(T o) throws SQLException;
    List<T> getAll() throws SQLException;

}