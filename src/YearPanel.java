import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class YearPanel extends JPanel {

    private JLabel label;
    private Year year;

    public YearPanel() {
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton b1 = new JButton("<-");
        b1.addActionListener(ae -> {
            MainWindow.mainCalendar.add(Calendar.YEAR, -1);
            this.updatePanel();
        });

        JButton b2 = new JButton("->");
        b2.addActionListener(ae -> {
            MainWindow.mainCalendar.add(Calendar.YEAR, +1);
            this.updatePanel();
        });

        JPanel panelChang = new JPanel();
        panelChang.setLayout(new BorderLayout());
        panelChang.add(b1, BorderLayout.WEST);
        panelChang.add(label, BorderLayout.CENTER);
        panelChang.add(b2, BorderLayout.EAST);

        year = new Year();

        JButton b3 = new JButton("Update");
        b3.addActionListener(ae -> this.updatePanel());

        JButton b4 = new JButton("Today");
        b4.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.YEAR, MainWindow.date[0]);
            MainWindow.mainCalendar.set(Calendar.MONTH, MainWindow.date[1]);
            MainWindow.mainCalendar.set(Calendar.DAY_OF_MONTH, MainWindow.date[2]);
            this.updatePanel();
        });

        this.add(panelChang, BorderLayout.NORTH);
        this.add(year, BorderLayout.SOUTH);
        this.add(b3, BorderLayout.NORTH);
        this.add(b4, BorderLayout.NORTH);

        this.updatePanel();
    }

    public void updateLabel() {
        int yearName = MainWindow.mainCalendar.get(Calendar.YEAR);
        label.setText(" " + yearName + " ");
    }

    public void updatePanel() {
        updateLabel();
    }
}
