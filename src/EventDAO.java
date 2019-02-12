import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements DAO<Event>{

    Connection connection;

    public EventDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:apiConnect.db3");
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Event (" +
                "dayNum TEXT, monthNum TEXT, yearNum TEXT, time TEXT, event TEXT);";
        try {
            DriverManager.registerDriver (new JDBC());
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void add(Event event) throws SQLException {
        String sql = "INSERT INTO Event (dayNum, monthNum, yearNum, time, event) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, event.getDayNum());
        statement.setString(2, event.getMonthNum());
        statement.setString(3, event.getYearNum());
        statement.setString(4, event.getTime());
        statement.setString(5, event.getEvent());
        int row = statement.executeUpdate();
    }

    @Override
    public List<Event> getAll() throws SQLException {
        String sql = "SELECT * FROM Event;";
        List<Event> events = new ArrayList<>();
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        for(int i = 0; resultSet.next(); i++){
            events.add(new Event(resultSet.getString("dayNum"), resultSet.getString("monthNum"),
                    resultSet.getString("yearNum"), resultSet.getString("time"), resultSet.getString("event")));
        }
        return events;
    }

    public List<Event> getEvents(String yearNum, String monthNum, String dayNum) throws SQLException {
        List<Event> allEvents = getAll();
        List<Event> todayEvents = new ArrayList<>();

        for (int i=0; i<allEvents.size();i++){
            if (allEvents.get(i).getYearNum() == yearNum && allEvents.get(i).getMonthNum() == monthNum && allEvents.get(i).getDayNum() == dayNum)
            {
                todayEvents.add(allEvents.get(i));
            }
        }

        return todayEvents;
    }
}
