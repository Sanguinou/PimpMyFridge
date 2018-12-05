package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;

import model.Model;
import model.SerialTest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * The type View.
 */
public class View implements Observer {

    private Frame frame;
    private JPanel panel;
    private JPanel tempPanel;
    
    private Dropdown dropdown;
    
    private Label consigne;
    private Label interieure;
    private Label humidite;
    private Label pointderosee;
    private Label exterieure;
    private Label title;
    
    private Label tempConsigne;
    private Label tempInterieure;
    private Label tempHumidite;
    private Label tempPointDeRosee;
    private Label tempExterieure;
    
    private Chart chart;
    
    private SerialTest m;

    /**
     * Instantiates a new View.
     */
    public View(){
    	//instance des class
        panel = new JPanel();
        tempPanel = new JPanel();
        frame = new Frame();
        chart = new Chart(createChart());

        panel.setBackground(Color.decode("#FFFFFF"));
        tempPanel.setBackground(Color.decode("#FFFFFF"));
        chart.setForeground(Color.decode("#FFFFFF"));

        frame.setContentPane(panel);
        panel.setLayout(new GridBagLayout());

        
        dropdown = new Dropdown();
        //instance label
        consigne = new Label();
        consigne.setText("Temperature consigne");
        
        tempConsigne = new Label();
        tempConsigne.setText("test");

        interieure = new Label();
        interieure.setText("Temperature interieure");
        tempInterieure = new Label();

        pointderosee = new Label();
        pointderosee.setText("Point de ros�e");
        tempPointDeRosee = new Label();

        exterieure = new Label();
        exterieure.setText("Temperature exterieure");
        tempExterieure = new Label();

        humidite = new Label();
        humidite.setText("Humidite:");
        tempHumidite = new Label();

        tempPanel.setLayout(new GridLayout(6,2));

        tempPanel.add(dropdown);
        //ajout label au pan
        tempPanel.add(consigne);
        tempPanel.add(interieure);
        tempPanel.add(humidite);
        tempPanel.add(pointderosee);
        tempPanel.add(exterieure);
        
        tempPanel.add(tempConsigne);
        tempPanel.add(tempInterieure);
        tempPanel.add(tempHumidite);
        tempPanel.add(tempPointDeRosee);
        tempPanel.add(tempExterieure);
        //grid pour positionner 
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 6;
        c.gridwidth = 8;
        c.weightx = c.weighty = 1;
        c.fill = c.BOTH;
        c.insets = new Insets(0,10,0,10);
        panel.add(chart, c);
        c.gridx = 8;
        c.gridy = 3;
        c.gridheight = 6;
        c.gridwidth = 4;
        c.weightx = c.weighty = 0;
        
        c.insets = new Insets(0,10,0,10);
        panel.add(tempPanel, c);
        refresh();

        frame.revalidate();
        frame.repaint();
    }
    private TimeSeriesCollection dataset;
    private TimeSeries canetteSeries;
    private TimeSeries interieurSeries;
    private TimeSeries exterieurSeries;

    /**
     * Add data to canette series.
     *
     * @param data the data
     */
    public void addDataToCanetteSeries(double data){
        canetteSeries.add(new Second(new Date()), data);
    }

    /**
     * Add data to interieur series.
     *
     * @param data the data
     */
    public void addDataToInterieurSeries(double data){
        interieurSeries.add(new Second(new Date()), data);
    }

    /**
     * Add data to exterieur series.
     *
     * @param data the data
     */
    public void addDataToExterieurSeries(double data){
        exterieurSeries.add(new Second(new Date()), data);
    }

    /**
     * Create chart j free chart.
     *
     * @return the j free chart
     */
	public JFreeChart createChart(){
        canetteSeries = new TimeSeries("Canette", Second.class);
        canetteSeries.setMaximumItemAge(3600);

        interieurSeries = new TimeSeries("Interieur", Second.class);
        interieurSeries.setMaximumItemAge(3600);

        exterieurSeries = new TimeSeries("Exterieur", Second.class);
        exterieurSeries.setMaximumItemAge(3600);

        dataset = new TimeSeriesCollection();
        dataset.addSeries(canetteSeries);
        dataset.addSeries(interieurSeries);
        dataset.addSeries(exterieurSeries);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Temperature en fonction du temps",
                "Temps (min)", "Temperature (°C)",
                dataset,
                true,
                true,
                false
        );
        return chart;
    }

    /**
     * Refresh.
     */
    public void refresh(){
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Alerte rosee.
     */
    public void alerteRosee(){
        JOptionPane.showMessageDialog(null, "Il y a risque de condensation.");
    }

    /**
     * Alerte temp.
     */
    public void alerteTemp(){
        JOptionPane.showMessageDialog(null, "Variation de temperature anormale.");
    }

    /**
     * Set consigne.
     *
     * @param temp the temp
     */
    public void setTempConsigne(int temp){
        this.consigne.setText("Temperature consigne : " + temp + " °C");
    }

    /**
     * Set temp canette.
     *
     * @param temp the temp
     */
    public void setDewPoint(float temp){
        this.pointderosee.setText("Point de ros�e : " + String.format("%.2f", temp) + " °C");
    }

    /**
     * Set temp externe.
     *
     * @param temp the temp
     */
    public void setTempExterne(float temp){
        this.exterieure.setText("Temperature exterieure : " + String.format("%.2f", temp) + " °C");
    }

    /**
     * Set temp interne.
     *
     * @param temp the temp
     */
    public void setTempInterne(float temp){
        this.interieure.setText("Temperature interieure : " + String.format("%.2f", temp) + " °C");
    }

    /**
     * Set humidite.
     *
     * @param temp the temp
     */
    public void setHumidite(float temp){
        this.humidite.setText("Humidite : " + temp + "%");
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("test");
		this.m = (SerialTest) arg0;
		tempHumidite.setText(Float.toString(m.dhtH));
		tempInterieure.setText(Float.toString(m.temperature));
		tempExterieure.setText(Float.toString(m.dhtT));
		tempPointDeRosee.setText(Float.toString(m.dewPoint));
	}
}
