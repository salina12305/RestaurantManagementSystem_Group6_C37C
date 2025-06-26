
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
import javax.swing.JLabel;
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
    
    
   public static void loadRevenueChartFromDB(JPanel revenueChartPanel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/restaurant", "root", "12345678")) {
            String sql = "SELECT MONTH(order_date) AS month, SUM(total_amount) AS total " +
                         "FROM orders GROUP BY MONTH(order_date) ORDER BY MONTH(order_date)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            String[] monthNames = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
            };

            while (rs.next()) {
                int month = rs.getInt("month");
                double total = rs.getDouble("total");
                dataset.addValue(total, "Revenue", monthNames[month - 1]);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
            "Monthly Revenue", "Month", "Revenue (Rs)",
            dataset, PlotOrientation.VERTICAL,
            false, true, false
        );

        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(52, 152, 219));
        renderer.setSeriesStroke(0, new BasicStroke(2.5f));
        renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6));
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(revenueChartPanel.getWidth(), revenueChartPanel.getHeight()));
        revenueChartPanel.removeAll();
        revenueChartPanel.setLayout(new BorderLayout());
        revenueChartPanel.add(chartPanel, BorderLayout.CENTER);
        revenueChartPanel.revalidate();
        revenueChartPanel.repaint();
    }
}
