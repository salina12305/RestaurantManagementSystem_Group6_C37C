

package Controller;

import Database.MySqlConnection;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.time.Month;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.awt.geom.Ellipse2D;


public class EDashboardController {
    MySqlConnection mysql= new MySqlConnection();
    
    public static void loadRevenueChart(JPanel panel, JFrame frame) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();



    JFreeChart chart = ChartFactory.createBarChart(
        "Monthly Revenue", "Month", "Revenue", dataset,
        PlotOrientation.VERTICAL, false, true, false
    );

    ChartPanel chartPanel = new ChartPanel(chart);
    panel.removeAll();
    panel.add(chartPanel, BorderLayout.CENTER);
    panel.validate();
}


    public static void applyHoverEffect(JButton button) {
        Color originalColor = button.getBackground();
        Color hoverColor = new Color(200, 200, 255); // Light blue on hover

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }

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
        System.out.println("Error loading chart data:");
        e.printStackTrace();
    }

    // Chart Styling
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
    lineChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));

    // Add to panel
    ChartPanel chartPanel = new ChartPanel(lineChart);
    chartPanel.setPreferredSize(new Dimension(revenueChartPanel.getWidth(), revenueChartPanel.getHeight()));
    revenueChartPanel.removeAll();
    revenueChartPanel.setLayout(new BorderLayout());
    revenueChartPanel.add(chartPanel, BorderLayout.CENTER);
    revenueChartPanel.revalidate();
    revenueChartPanel.repaint();
    }
}






