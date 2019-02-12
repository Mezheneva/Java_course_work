import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Locale;

public class WeekPanel extends JPanel{

    private Week week;
    private JLabel label;
    private JTable table;

    public WeekPanel() {
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton b1 = new JButton("<-");
        b1.addActionListener(ae -> {
            MainWindow.mainCalendar.add(Calendar.DAY_OF_YEAR, -7);
            this.updatePanel();
        });

        JButton b2 = new JButton("->");
        b2.addActionListener(ae -> {
            MainWindow.mainCalendar.add(Calendar.DAY_OF_YEAR, +7);
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

        week = new Week();
        table = new JTable(week);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane tablePanel = new JScrollPane(table);

        this.add(panelChang,BorderLayout.NORTH);
        this.add(tablePanel,BorderLayout.CENTER);
        this.add(b3, BorderLayout.SOUTH);
        this.add(b4, BorderLayout.SOUTH);

        this.updatePanel();
    }

    public void updatePanel(){
        week.updateWeek();
        updateLabel();
    }

    public void updateLabel(){
        String monthName = MainWindow.mainCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        int yearName = MainWindow.mainCalendar.get(Calendar.YEAR);

        label.setText("             " + monthName +"    " + yearName + "            ");
    }
}

