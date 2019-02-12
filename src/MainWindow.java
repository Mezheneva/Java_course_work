import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainWindow extends JFrame {

    public static Calendar mainCalendar;

    private static CardLayout layout;
    private static JPanel mainPanel;

    private JPanel panelYear;
    private JPanel panelMonth;
    private JPanel panelWeek;
    private JPanel panelDay;

    public static int date[] = {2019, 1, 12} ;

    public MainWindow(String title) throws HeadlessException {
        super(title);
        this.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());

        mainCalendar = new GregorianCalendar();
        //date = mainCalendar.getTime();

        layout = new CardLayout();
        mainPanel = new JPanel(layout);

        EventDAO eventDAO = null;
        try {
            eventDAO = new EventDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        eventDAO.createTable();

        panelYear = new YearPanel();
        panelMonth = new MonthPanel();
        panelWeek = new WeekPanel();
        panelDay = new DayPanel(eventDAO);

        mainPanel.add(panelYear, "panelYear");
        mainPanel.add(panelMonth, "panelMonth");
        mainPanel.add(panelWeek, "panelWeek");
        mainPanel.add(panelDay, "panelDay");


        // create buttons
        JPanel buttonPanel = new JPanel();
        JButton yearButton = new JButton("Year");
        JButton monthButton = new JButton("Month");
        JButton weekButton = new JButton("Week");
        JButton dayButton = new JButton("Day");

        buttonPanel.add(dayButton);
        buttonPanel.add(weekButton);
        buttonPanel.add(monthButton);
        buttonPanel.add(yearButton);

        // create action listeners for buttons
        yearButton.addActionListener(e -> {
            layout.show(mainPanel, "panelYear");
        });

        monthButton.addActionListener(event -> {
            layout.show(mainPanel, "panelMonth");
        });

        weekButton.addActionListener(event -> {
            layout.show(mainPanel, "panelWeek");
        });

        dayButton.addActionListener(event -> {
            layout.show(mainPanel, "panelDay");
        });


        // add card & button panels to the main window
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.NORTH);
    }

    public static void openPanel(String panelName) {
        layout.show(mainPanel, panelName);
    }
}
