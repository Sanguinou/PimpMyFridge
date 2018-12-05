package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;

public class Panel extends JPanel{
	
	private Chart chart;
	private TimeSeriesCollection dataset;
	GridBagConstraints c = new GridBagConstraints();
	
	public Panel() {
		this.setBackground(Color.GRAY);
		//createChart();
		//this.add(chart, c);
	}
	
	public GridBagConstraints PanelChart(GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 6;
        c.gridwidth = 8;
        c.weightx = c.weighty = 1;
        c.fill = c.BOTH;
        c.insets = new Insets(0,10,0,10);
		return c;
	}
	
	public JFreeChart createChart() {
		dataset = new TimeSeriesCollection();
		
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"Température en fonction du temps", 
				"Temps (min)","Temperature (°C)",
				dataset,
				true,
				true,
				false
				);
		return chart;
	}
}

