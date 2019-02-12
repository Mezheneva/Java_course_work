import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


public class Year extends JPanel {

    public Year() {
        setLayout(new GridLayout(4, 0, 5, 10));
        JButton b1 = new JButton(" " + "JANUARY" + " ");
        JButton b2 = new JButton(" " + "FEBRUARY" + " ");
        JButton b3 = new JButton(" " + "MARCH" + " ");
        JButton b4 = new JButton(" " + "APRIL" + " ");
        JButton b5 = new JButton(" " + "MAY" + " ");
        JButton b6 = new JButton(" " + "JUNE" + " ");
        JButton b7 = new JButton(" " + "JULY" + " ");
        JButton b8 = new JButton(" " + "AUGUST" + " ");
        JButton b9 = new JButton(" " + "SEPTEMBER" + " ");
        JButton b10 = new JButton(" " + "OCTOBER" + " ");
        JButton b11 = new JButton(" " + "NOVEMBER" + " ");
        JButton b12 = new JButton(" " + "DECEMBER" + " ");

        b1.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.JANUARY);
            MainWindow.openPanel("panelMonth");
        });
        b2.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.FEBRUARY);
            MainWindow.openPanel("panelMonth");
        });
        b3.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.MARCH);
            MainWindow.openPanel("panelMonth");
        });
        b4.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.APRIL);
            MainWindow.openPanel("panelMonth");
        });
        b5.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.MAY);
            MainWindow.openPanel("panelMonth");
        });
        b6.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.JUNE);
            MainWindow.openPanel("panelMonth");
        });
        b7.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.JULY);
            MainWindow.openPanel("panelMonth");
        });
        b8.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.AUGUST);
            MainWindow.openPanel("panelMonth");
        });
        b9.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            MainWindow.openPanel("panelMonth");
        });
        b10.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.OCTOBER);
            MainWindow.openPanel("panelMonth");
        });
        b11.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.NOVEMBER);
            MainWindow.openPanel("panelMonth");
        });
        b12.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.DECEMBER);
            MainWindow.openPanel("panelMonth");
        });


        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.add(b5);
        this.add(b6);
        this.add(b7);
        this.add(b8);
        this.add(b9);
        this.add(b10);
        this.add(b11);
        this.add(b12);

        MainWindow.mainCalendar.set(Calendar.MONTH, Calendar.JANUARY);
        MainWindow.mainCalendar.set(Calendar.DAY_OF_YEAR, 1);
    }
}

