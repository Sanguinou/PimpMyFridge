package model;

public class Model {

	private float consigne = 0;
	private float humidite = 0;
	private float temperatureInterne = 0;
	private float temperatureExterne = 0;
	
	private Boolean etatFrigo = false;
	
	//get & set temperature consigne
	public float getTemperatureConsigne() {
		return consigne;
	}
	public void setTemperatureConsigne(float consigne) {
		this.consigne = consigne;
	}
	//get & set temperature interne
	public float getTemperatureInterne() {
		return temperatureInterne;
	}
	public void setTemperatureInterne(float temperatureInterne) {
		this.temperatureInterne = temperatureInterne;
	}
	//get & set temperature externe
	public float getTemperatureExterne() {
		return temperatureExterne;
	}
	public void setTemperatureExterne(float temperatureExterne) {
		this.temperatureExterne = temperatureExterne;
	}
	//get & set humidite
	public float getHumidite() {
		return humidite;
	}
	public void setHumidite(float humidite) {
		this.humidite = humidite;
	}
	//Get & set etatFrigo
	public Boolean getEtatFrigo() {
		return etatFrigo;
	}
	public void setEtatFrigo(Boolean etatFrigo) {
		this.etatFrigo = etatFrigo;
	}

}
