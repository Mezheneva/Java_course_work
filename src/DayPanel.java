import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

public class DayPanel extends JPanel {
    private Day day;
    private JLabel label;
    private JTable table;

    private EventDAO dbConnection;

    public DayPanel(EventDAO dbConnection) {

        this.dbConnection = dbConnection;

        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton b1 = new JButton("<-");
        b1.addActionListener(ae -> {
            MainWindow.mainCalendar.add(Calendar.DAY_OF_YEAR, -1);
            this.updatePanel();
        });

        JButton b2 = new JButton("->");
        b2.addActionListener(ae -> {
            MainWindow.mainCalendar.add(Calendar.DAY_OF_YEAR, +1);
            this.updatePanel();
        });

        JButton b3 = new JButton("Update");
        b3.addActionListener(ae -> this.updatePanel());

        JButton b4 = new JButton("Today");
        b4.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.YEAR, MainWindow.date[0]);
            MainWindow.mainCalendar.set(Calendar.MONTH, MainWindow.date[1]);
            MainWindow.mainCalendar.set(Calendar.DAY_OF_MONTH, MainWindow.date[2]);
            this.updatePanel();
        });

        JPanel panelChang = new JPanel();
        panelChang.setLayout(new BorderLayout());
        panelChang.add(b1,BorderLayout.WEST);
        panelChang.add(label,BorderLayout.CENTER);
        panelChang.add(b2,BorderLayout.EAST);

        day = new Day(dbConnection);
        table = new JTable(day);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);


        day.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int col = e.getColumn();

                TableModel model = (TableModel) e.getSource();
                int dayNum = MainWindow.mainCalendar.get(Calendar.DAY_OF_MONTH);
                int monthNum = MainWindow.mainCalendar.get(Calendar.MONTH);
                int yearNum = MainWindow.mainCalendar.get(Calendar.YEAR);
                Object time = model.getValueAt(row, 0);
                Object data = model.getValueAt(row, 1);

                if(data != "" && data != null) {

                    Event event = new Event(String.valueOf(dayNum), String.valueOf(monthNum), String.valueOf(yearNum), String.valueOf(time), String.valueOf(data));
                    try {
                        dbConnection.add(event);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    System.out.println(dayNum + " " + monthNum + " " + yearNum + " " + time + " " + data);
                }

            }
        });

        JScrollPane tablePanel = new JScrollPane(table);

        this.add(panelChang,BorderLayout.NORTH);
        this.add(tablePanel,BorderLayout.CENTER);
        this.add(b3, BorderLayout.SOUTH);
        this.add(b4, BorderLayout.SOUTH);

        this.updatePanel();
    }

    private void updateLabel(){
        String monthName = MainWindow.mainCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        int yearName = MainWindow.mainCalendar.get(Calendar.YEAR);
        label.setText("         " + monthName + "    " + yearName + "        ");
    }

    public void updatePanel(){
        try {
            day.updateDay();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateLabel();
    }

}
