import javax.swing.table.DefaultTableModel;
import java.util.Calendar;

public class Month extends DefaultTableModel{
    String [] columns = {"Mun","Tue","Wed","Thu","Fri","Sat", "Sun"};


    public Month() {
        setColumnIdentifiers(columns);
    }

    public void updateMonth() {
        MainWindow.mainCalendar.set(Calendar.DAY_OF_MONTH, 1);

        int startDay = MainWindow.mainCalendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = MainWindow.mainCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = MainWindow.mainCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

        setRowCount(0);
        setRowCount(weeks);

        int i = (startDay+5)%7;
        for(int day=1;day<=numberOfDays;day++,i++){
            setValueAt(day, i/7 , i%7 );
        }

    }
}
