
package Dao;

import Database.MySqlConnection;
import View.EmployeeDashboard;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class EDashboardDao {
   private EmployeeDashboard view;
    MySqlConnection mysql = new MySqlConnection(); 
    
    
//   public static void loadRevenueChartFromDB(JPanel revenueChartPanel) {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/restaurant", "root", "12345678")) {
//            String sql = "SELECT MONTH(order_date) AS month, SUM(total_amount) AS total " +
//                         "FROM orders GROUP BY MONTH(order_date) ORDER BY MONTH(order_date)";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//
//            String[] monthNames = {
//                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
//                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
//            };
//
//            while (rs.next()) {
//                int month = rs.getInt("month");
//                double total = rs.getDouble("total");
//                dataset.addValue(total, "Revenue", monthNames[month - 1]);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        JFreeChart lineChart = ChartFactory.createLineChart(
//            "Monthly Revenue", "Month", "Revenue (Rs)",
//            dataset, PlotOrientation.VERTICAL,
//            false, true, false
//        );
//
//        CategoryPlot plot = lineChart.getCategoryPlot();
//        plot.setBackgroundPaint(Color.WHITE);
//        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
//
//        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
//        renderer.setSeriesPaint(0, new Color(52, 152, 219));
//        renderer.setSeriesStroke(0, new BasicStroke(2.5f));
//        renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6));
//        renderer.setSeriesShapesVisible(0, true);
//        plot.setRenderer(renderer);
//
//        ChartPanel chartPanel = new ChartPanel(lineChart);
//        chartPanel.setPreferredSize(new Dimension(revenueChartPanel.getWidth(), revenueChartPanel.getHeight()));
//        revenueChartPanel.removeAll();
//        revenueChartPanel.setLayout(new BorderLayout());
//        revenueChartPanel.add(chartPanel, BorderLayout.CENTER);
//        revenueChartPanel.revalidate();
//        revenueChartPanel.repaint();
//    }
    
    public static void loadMonthlyOrderChart(JPanel panel) {
    Map<String, Integer> monthOrderMap = new LinkedHashMap<>();

    // Step 1: Initialize last 6 months
    LocalDate now = LocalDate.now();
    DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");

    for (int i = 5; i >= 0; i--) {
        LocalDate date = now.minusMonths(i);
        String month = date.format(monthFormatter);
        monthOrderMap.put(month, 0); // default 0 orders
    }

    // Step 2: Query orders grouped by MONTH
    try (Connection conn = new MySqlConnection().openConnection()) {
        String sql = "SELECT DATE_FORMAT(order_date, '%b') as month, COUNT(*) as total " +
                     "FROM orders " +
                     "WHERE order_date >= CURDATE() - INTERVAL 6 MONTH " +
                     "GROUP BY month " +
                     "ORDER BY STR_TO_DATE(month, '%b')";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String month = rs.getString("month"); // 'Jan', 'Feb', etc.
            int total = rs.getInt("total");

            if (monthOrderMap.containsKey(month)) {
                monthOrderMap.put(month, total);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading monthly orders: " + e.getMessage());
        return;
    }

    // Step 3: Build dataset
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (Map.Entry<String, Integer> entry : monthOrderMap.entrySet()) {
        dataset.addValue(entry.getValue(), "Orders", entry.getKey());
    }

    // Step 4: Create chart
    JFreeChart chart = ChartFactory.createLineChart(
        "Monthly Order Comparison", "Month", "Orders",
        dataset, PlotOrientation.VERTICAL, false, true, false
    );

    CategoryPlot plot = chart.getCategoryPlot();
    plot.setBackgroundPaint(Color.WHITE);
    plot.setRangeGridlinePaint(Color.GRAY);
    plot.setOutlineVisible(false);

    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
    renderer.setSeriesPaint(0, new Color(0, 123, 255));
    renderer.setSeriesStroke(0, new BasicStroke(2.5f));
    renderer.setSeriesShapesVisible(0, true);
    renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6));
    plot.setRenderer(renderer);

    // Step 5: Add to panel
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(600, 350));
    panel.removeAll();
    panel.setLayout(new BorderLayout());
    panel.add(chartPanel, BorderLayout.CENTER);
    panel.revalidate();
    panel.repaint();
}

    public static void loadRevenueChartFromDB(JPanel panel) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    try (Connection conn = new MySqlConnection().openConnection()) {
        String sql = "SELECT DATE(order_date) as date, COUNT(*) as count " +
                     "FROM orders " +
                     "WHERE order_date >= CURDATE() - INTERVAL 30 DAY " +
                     "GROUP BY DATE(order_date) " +
                     "ORDER BY DATE(order_date)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String date = rs.getString("date");
            int count = rs.getInt("count");
            dataset.addValue(count, "Orders", date);
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to load order statistics.");
    }

    JFreeChart chart = ChartFactory.createLineChart(
        "Orders in Last 30 Days", "Date", "Number of Orders",
        dataset, PlotOrientation.VERTICAL, false, true, false
    );

    CategoryPlot plot = chart.getCategoryPlot();
    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
    renderer.setSeriesShapesVisible(0, true);
    renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6));
    renderer.setSeriesPaint(0, new Color(79, 129, 189));
    plot.setRenderer(renderer);

    ChartPanel chartPanel = new ChartPanel(chart);
    panel.removeAll();
    panel.setLayout(new BorderLayout());
    panel.add(chartPanel, BorderLayout.CENTER);
    panel.validate();
}

}
