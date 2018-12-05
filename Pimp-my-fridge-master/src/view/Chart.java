package view;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart extends ChartPanel{

	private static final long serialVersionUID = 1L;
	
	public Chart(JFreeChart chart){
        super(chart);
        chart.setBackgroundPaint(Color.decode("#FFFFFF"));
        chart.setBorderPaint(Color.white);
        setFontColor(Color.decode("#0077AC"));
    }
	
	private void setFontColor(Color fontColor) {
        JFreeChart chart = getChart();
        chart.getTitle().setPaint(fontColor);
        Plot plot = chart.getPlot();
        if (plot instanceof CategoryPlot) {
            setAxisFontColor(((CategoryPlot) plot).getDomainAxis(), fontColor);
            setAxisFontColor(((CategoryPlot) plot).getRangeAxis(), fontColor);
        } else if (plot instanceof XYPlot) {
            setAxisFontColor(((XYPlot) plot).getDomainAxis(), fontColor);
            setAxisFontColor(((XYPlot) plot).getRangeAxis(), fontColor);
        }
    }
	
	private void setAxisFontColor(Axis axis, Color fontColor) {
        if (!fontColor.equals(axis.getLabelPaint()))
            axis.setLabelPaint(fontColor);
        if (!fontColor.equals(axis.getTickLabelPaint()))
            axis.setTickLabelPaint(fontColor);
    }
	
}

