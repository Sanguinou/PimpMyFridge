#include "DHT.h"
#include "Adafruit_Sensor.h"

//thermistor 
float Uref = 5.0;
float Uthermistor = 0.0;
float Ur1 = 0.0;
float R1 = 10000.0;
float Rthermistor = 0.0;

float refTint = 15.0;

//dht22
#define DHTPIN 2 //pin used by the dht22
#define DHTTYPE DHT22 //define the type of dht we're using



DHT dht (DHTPIN, DHTTYPE); //declaring the sensor

//usedpin
#define MOSFETPIN 3
#define alimThermistor 4
#define alimDHT22 5

void setup() 
{
  Serial.begin(9600);
  pinMode(DHTPIN, INPUT);
  pinMode(MOSFETPIN, OUTPUT);
  pinMode(alimThermistor, OUTPUT);
  pinMode(alimDHT22, OUTPUT);
  digitalWrite(5, HIGH);
  dht.begin();
  delay(2000);
}

void loop() 
{
  digitalWrite(alimThermistor, HIGH);
 // digitalWrite(alimDHT22, HIGH);
  
  //Serial.print("La temperature interieur est de: ");
  Serial.println(getTemperature()); //determine the interior temperature by using the thermistor
  //Serial.println(" °C");

  //Serial.print("La temperature exterieur est de: ");
  Serial.println(getDhtT()); //dht22 temperature return
  //Serial.println(" °C");

  //Serial.print("L humidite exterieur est de: ");
  Serial.println(getDhtH()); //dht22 temperature return
  //Serial.println(" %");

  //Serial.print("Point de rosee: ");
  Serial.println(dewPoint());
  //Serial.println(" °C");
  //Serial.println("");

  if(getTemperature() > refTint) 
  {
    digitalWrite(MOSFETPIN, HIGH); //Switch on the fridge
  }

  if(getTemperature() < refTint)
  {
    digitalWrite(MOSFETPIN, LOW); //Switch off the fridge   
  }

  if(getTemperature()< dewPoint())
  {
    digitalWrite(MOSFETPIN, LOW); //Switch off the fridge
  }
  
  digitalWrite(alimThermistor, LOW);
  //digitalWrite(alimDHT22, LOW);
  delay(2500);
  
}

//---Thermistor's temperature functions---//

float getUthermistor()
{  
  //Get the analog value from 0 to 1023 and convert it into volts
  return (analogRead(A0)*5.0/1024.0);
}

float getUR1()
{
  //Mesh law
  return (Uref - getUthermistor());
}

float getRthermistor()
{
  //Voltage divider
  float tempUt = getUthermistor(); 
  return ((R1*tempUt/Uref)/(1-(tempUt/Uref)));
}

float getTemperature()
{
  //Steinhart-hart
  return (1/((log(getRthermistor()/10000.0)/3950.0)+(1.0/(25.0 + 273.15))))-273.15;
}

//---DHT22's temperature functions---//

float getDhtT()
{
  return (dht.readTemperature());
}

float getDhtH()
{
  return (dht.readHumidity());
}

//---Dew point function---//

float dewPoint()
{
  float tempDhtT = getDhtT();
  float tempDhtH = getDhtH();
  float tempConstK = ((237.7*tempDhtT)/(17.7+tempDhtT)) + (log(tempDhtH)/100);

  return ((237.7*tempConstK)/(17.27-tempConstK))+273.15 ;
}




