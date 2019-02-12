import com.sun.xml.internal.bind.v2.TODO;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class MonthPanel extends JPanel {

    private Month month;
    private JLabel label;
    private JTable table;

    public MonthPanel(){

        MainWindow.mainCalendar.set(Calendar.DAY_OF_MONTH,1);

        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton b1 = new JButton("<-");
        b1.addActionListener(ae -> {
            MainWindow.mainCalendar.add(Calendar.MONTH, -1);
            this.updatePanel();
        });

        JButton b2 = new JButton("->");
        b2.addActionListener(ae -> {
            MainWindow.mainCalendar.add(Calendar.MONTH, +1);
            this.updatePanel();
        });

        JPanel panelChang = new JPanel();
        panelChang.setLayout(new BorderLayout());
        panelChang.add(b1,BorderLayout.WEST);
        panelChang.add(label,BorderLayout.CENTER);
        panelChang.add(b2,BorderLayout.EAST);

        month = new Month();
        table = new JTable(month);
        table.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < month.columns.length; i++) {
            table.getColumn(month.columns[i]).setCellRenderer(new ButtonRenderer());
            table.getColumn(month.columns[i]).setCellEditor(new ButtonEditor(new JCheckBox()));
        }

        JScrollPane tablePanel = new JScrollPane(table);

        JButton b3 = new JButton("Update");
        b3.addActionListener(ae -> this.updatePanel());

        JButton b4 = new JButton("Today");
        b4.addActionListener(ae -> {
            MainWindow.mainCalendar.set(Calendar.YEAR, MainWindow.date[0]);
            MainWindow.mainCalendar.set(Calendar.MONTH, MainWindow.date[1]);
            MainWindow.mainCalendar.set(Calendar.DAY_OF_MONTH, MainWindow.date[2]);
            this.updatePanel();
        });

        this.add(panelChang,BorderLayout.NORTH);
        this.add(tablePanel,BorderLayout.CENTER);
        this.add(b3, BorderLayout.SOUTH);
        this.add(b4, BorderLayout.SOUTH);

        this.updatePanel();
    }

    public void updateLabel(){
        String monthName = MainWindow.mainCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        int yearName = MainWindow.mainCalendar.get(Calendar.YEAR);
        label.setText("             " + monthName + "  " + yearName + "            ");
    }

    public void updatePanel() {
        month.updateMonth();
        updateLabel();
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else{
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText( (value == null) ? "" : value.toString() );
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String    label;
    private boolean   isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else{
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText( label );
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            MainWindow.mainCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(button.getText()));
            button.addActionListener(e -> MainWindow.openPanel("panelDay"));
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
