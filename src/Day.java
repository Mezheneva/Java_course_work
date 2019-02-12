import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class Day extends DefaultTableModel {

    private EventDAO dbConnection;

    private String[] columns = {"Time", "Event"};
    private final int CountOfRow = 16;
    private final int startTime = 8;
    private final int finishTime = 22;

    private boolean[] canEdit = new boolean[]{
            false, true
    };

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    public Day(EventDAO dbConnection) {
        this.dbConnection = dbConnection;

        setColumnIdentifiers(columns);
        setRowCount(CountOfRow);

    }

    public void updateDay() throws SQLException {

        String[] columns = {"Mun", "Tue", "Wed", "Thu", "Fri", "Sat","Sun"};
        int dayName = MainWindow.mainCalendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeekName = MainWindow.mainCalendar.get(Calendar.DAY_OF_WEEK);

        setValueAt(columns[(dayOfWeekName+5)%7] + " " + dayName, 0, 0);

        int thisDay = MainWindow.mainCalendar.get(Calendar.DAY_OF_MONTH);
        int thisMonth = MainWindow.mainCalendar.get(Calendar.MONTH);
        int thisYear = MainWindow.mainCalendar.get(Calendar.YEAR);

        List<Event> todayEvents = dbConnection.getEvents(String.valueOf(thisYear), String.valueOf(thisMonth), String.valueOf(thisDay));

        if (!todayEvents.isEmpty()) {
            for (int time = startTime, i = 1, j=0; time <= finishTime; time++, i++) {
                if(time+":00" == todayEvents.get(j).getTime()) {
                    setValueAt(time + ":00", i % CountOfRow, 0);
                    setValueAt(todayEvents.get(j).getEvent(), i % CountOfRow, 1);
                    j++;
                } else{
                    setValueAt(time + ":00", i % CountOfRow, 0);
                    setValueAt("", i % CountOfRow, 1);
                }
            }
        } else {
            for (int time = startTime, i = 1; time <= finishTime; time++, i++) {
                setValueAt(time + ":00", i % CountOfRow, 0);
                setValueAt("", i % CountOfRow, 1);
            }
        }
    }
}

