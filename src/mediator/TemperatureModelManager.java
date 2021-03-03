package mediator;

import external.OutTemperature;
import external.Thermometer;
import model.*;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TemperatureModelManager implements TemperatureModel
{
  private TemperatureList temperatureList;
  private Thermometer firstTemperature;
  private Thermometer secondTemperature;
  private OutTemperature outsideTemperature;
  private PropertyChangeSupport property;
  private Heater heater;
  private Temperature highCriticalValue;
  private Temperature lowCriticalValue;

  public TemperatureModelManager()
  {
    heater = new Heater();

    outsideTemperature = new OutTemperature(10,-20,20);
    Thread outsideTempThread = new Thread(outsideTemperature);
    outsideTempThread.setDaemon(true);
    outsideTempThread.start();

    firstTemperature = new Thermometer(10,"Indoor thermometer 1",1,this);
    secondTemperature = new Thermometer(10, "Indoor thermometer 2",7, this);
    Thread firstTempThread = new Thread(firstTemperature);
    Thread secondTempThread = new Thread(secondTemperature);
    firstTempThread.setDaemon(true);
    secondTempThread.setDaemon(true);
    firstTempThread.start();
    secondTempThread.start();



    temperatureList = new TemperatureList();
    property= new PropertyChangeSupport(this);
    outsideTemperature.addListener(this);
    firstTemperature.addListener(this);
    secondTemperature.addListener(this);

    lowCriticalValue = new Temperature("lowCriticalValue",10);
    highCriticalValue = new Temperature("highCriticalValue",20);

  }

  @Override public void addTemperature(Temperature temperature)
  {
    temperatureList.addTemperature(temperature);
  }


  @Override public Temperature getLowCriticalValue()
  {
    return lowCriticalValue;
  }

  @Override public Temperature getHighCriticalValue()
  {
    return highCriticalValue;
  }

  @Override public int getTemperatureListSize()
  {
    return temperatureList.getSize();
  }


  @Override public void setCriticalLowTemperature(double criticalLowTemperatureValue)
  {
    lowCriticalValue = new Temperature("lowCriticalValue", criticalLowTemperatureValue);
    property.firePropertyChange("criticalTemperatureChange",null, lowCriticalValue);
  }

  @Override public void setCriticalHighTemperature(double criticalHighTemperatureValue)
  {
    highCriticalValue = new Temperature("highCriticalValue", criticalHighTemperatureValue);
    property.firePropertyChange("criticalTemperatureChange", null, highCriticalValue);
  }



  @Override public int getHeaterPower()
  {
    return heater.getPower();
  }
  @Override public double getOutsideTemperature()
  {
    return outsideTemperature.getCurrentTemperature();
  }

  @Override public double getFirstThermometerTemperature()
  {
    return firstTemperature.getCurrentTemperature();
  }

  @Override public double getSecondThermometerTemperature()
  {
    return secondTemperature.getCurrentTemperature();
  }
  @Override public void turnUp()
  {
    heater.clickUp();
  }

  @Override public void turnDown()
  {

    heater.clickDown();
  }

  @Override public ArrayList<Temperature> getAllTemp()
  {
    return temperatureList.getAllTemp();
  }
  @Override public Heater getHeater()
  {
    return heater;
  }

  @Override public Temperature getTemperature(int index)
  {
    return temperatureList.getTemperature(index);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
    firstTemperature.addListener(listener);
    secondTemperature.addListener(listener);
    outsideTemperature.addListener(listener);
    heater.addListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
    firstTemperature.removeListener(listener);
    secondTemperature.removeListener(listener);
    outsideTemperature.removeListener(listener);
    heater.removeListener(listener);
  }

  @Override public void propertyChange(PropertyChangeEvent event)
  {
    if (event.getPropertyName().equals("ThermometerTemperature") || event.getPropertyName().equals("outdoorTemperature")){
      temperatureList.addTemperature((Temperature) event.getNewValue());
    }
  }
}
