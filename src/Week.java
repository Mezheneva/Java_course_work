import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Week extends DefaultTableModel {
    private String [] columns = {"Time", "Mun","Tue","Wed","Thu","Fri","Sat", "Sun"};
    private final int CountOfRow = 16;
    private final int startTime = 8;
    private final int finishTime = 22;

    public Week() {
        setColumnIdentifiers(columns);
        setRowCount(CountOfRow);
    }

    private boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false, false
    };

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    public void updateWeek(){
        MainWindow.mainCalendar.set(Calendar.DAY_OF_WEEK, 2);

        setRowCount(0);
        setRowCount(CountOfRow);

        for(int time = startTime, i = 1; time<=finishTime;time++, i++){
            setValueAt(time + ":00", i%CountOfRow , 0 );
        }

        int startName = MainWindow.mainCalendar.get(Calendar.DAY_OF_MONTH);
        int numberOfDays = MainWindow.mainCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayNum = startName;

        for(int j = 1; j<columns.length; j++) {
            if(dayNum > numberOfDays) {
                dayNum = 1;
                int newMonth =  MainWindow.mainCalendar.get(Calendar.MONTH);
                newMonth= (newMonth+2)%12;

                setValueAt(dayNum + "." + newMonth,0 ,j%columns.length);
            }else {
                setValueAt(dayNum + "",0 ,j%columns.length);
            }
            dayNum++;
        }
    }
}
