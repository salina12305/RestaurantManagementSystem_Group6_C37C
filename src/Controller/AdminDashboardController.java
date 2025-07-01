package Controller;

import Dao.ReviewDAO;
import Dao.RevenueDAO;
import Model.Review;
import Model.RevenueEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.time.Month;
import java.util.List;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class AdminDashboardController {

    public static void loadRecentReviews(JLabel[] nameLabels, JLabel[] reviewLabels, JFrame frame) {
        if (nameLabels.length != 3 || reviewLabels.length != 3) {
            throw new IllegalArgumentException("Exactly 3 name and review labels are required.");
        }

        List<Review> reviews = ReviewDAO.getRecentReviews(3);

        for (int i = 0; i < reviews.size(); i++) {
            String name = reviews.get(i).getCustomerName();
            String review = reviews.get(i).getReviewText();

            if (review.length() > 100) {
                review = review.substring(0, 100) + "...";
            }

            nameLabels[i].setText(name);
            reviewLabels[i].setText(review);
        }

        for (int i = reviews.size(); i < 3; i++) {
            nameLabels[i].setText("No customer");
            reviewLabels[i].setText("No review available");
        }
    }

    public static void loadRevenueChart(JPanel panel, JFrame frame) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<RevenueEntry> revenueEntries = RevenueDAO.getMonthlyRevenues();

        for (RevenueEntry entry : revenueEntries) {
            String monthName = Month.of(entry.getMonth()).name().substring(0, 3);
            dataset.addValue(entry.getTotal(), "Revenue", monthName);
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Monthly Revenue", "Month", "Revenue",
            dataset, PlotOrientation.VERTICAL, false, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
    }

    public static void loadRevenueChartFromDB(JPanel revenueChartPanel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<RevenueEntry> revenueEntries = RevenueDAO.getMonthlyRevenues();

        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                               "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        for (RevenueEntry entry : revenueEntries) {
            dataset.addValue(entry.getTotal(), "Revenue", monthNames[entry.getMonth() - 1]);
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
            "Monthly Revenue", "Month", "Revenue (Rs)",
            dataset, PlotOrientation.VERTICAL, false, true, false
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

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(revenueChartPanel.getWidth(), revenueChartPanel.getHeight()));
        revenueChartPanel.removeAll();
        revenueChartPanel.setLayout(new BorderLayout());
        revenueChartPanel.add(chartPanel, BorderLayout.CENTER);
        revenueChartPanel.revalidate();
        revenueChartPanel.repaint();
    }

    public static void applyHoverEffect(JButton button) {
        Color originalColor = button.getBackground();
        Color hoverColor = new Color(200, 200, 255);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }

    public static void applyHoverEffect(JPanel panel) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void applyHoverEffect(JLabel label) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
