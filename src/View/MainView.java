package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem1, menuItem2;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    public void createAndShowGUI() {
        createFrame();
        createMenuBar();
        createTable();
        customizeColors();
        frame.setVisible(true);
    }

    private void createFrame() {
        frame = new JFrame("LeagueChecker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItem1 = new JMenuItem("Load combos from file");
        menuItem2 = new JMenuItem("Load accounts from file");
        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }

    private void createTable() {
        String[] columnNames = {"Region", "Username", "Password", "RP", "BE", "Level", "Champions", "Skins", "Details"};
        table = new JTable(0, columnNames.length);
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);
        scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
    }

    private void customizeColors() {
        menuBar.setBackground(new Color(141, 153, 174));
        menuBar.setForeground(new Color(255, 255, 255));
        table.setBackground(new Color(43, 45, 66));
        table.getTableHeader().setBackground(new Color(43, 45, 66));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
    }
}