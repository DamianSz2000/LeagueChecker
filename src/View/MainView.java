package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("LeagueChecker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Load combos from file");
        JMenuItem menuItem2 = new JMenuItem("Load accounts from file");
        menu.add(menuItem);
        frame.setJMenuBar(menuBar);
        String[] columnNames = {"Region", "Username", "Password", "RP", "BE", "Level", "Champions", "Skins", "Details"};
        JTable table = new JTable(0, columnNames.length);
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);


        menuBar.setBackground(new Color(141, 153, 174));
        menuBar.setForeground(new Color(255, 255, 255));
        table.setBackground(new Color(43, 45, 66));
        table.getTableHeader().setBackground(new Color(43, 45, 66));
        table.getTableHeader().setForeground(new Color(255, 255, 255));









        frame.setVisible(true);
    }
}
