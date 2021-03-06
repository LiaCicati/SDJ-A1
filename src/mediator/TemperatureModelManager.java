package mediator;
import model.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TemperatureModelManager implements TemperatureModel
{
  private TemperatureList temperatureList;
  private PropertyChangeSupport property;
  private Heater heater;
  private Temperature highCriticalValue;
  private Temperature lowCriticalValue;

  public TemperatureModelManager()
  {
    heater = new Heater();

    temperatureList = new TemperatureList();
    property = new PropertyChangeSupport(this);

    lowCriticalValue = new Temperature("lowCriticalValue", 10);
    highCriticalValue = new Temperature("highCriticalValue", 20);

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

  @Override public synchronized void addTemperature(String id, double value)
  {
    Temperature temperature = new Temperature(id, value);
    Temperature old = getLastInsertedTemperature(id);
    this.temperatureList.addTemperature(temperature);
    if (old != null && old.getValue() != temperature.getValue())
    {
      System.out.println("-->" + temperature + " (from: " + old + ")");
      property.firePropertyChange("temperature", old, temperature);
    }
  }

  public synchronized Temperature getLastInsertedTemperature(String id)
  {
    return temperatureList.getLastTemperature(id);
  }

  @Override public void setCriticalLowTemperature(
      double criticalLowTemperatureValue)
  {
    lowCriticalValue = new Temperature("lowCriticalValue",
        criticalLowTemperatureValue);
    property.firePropertyChange("criticalTemperatureChange", null,
        lowCriticalValue);
  }

  @Override public void setCriticalHighTemperature(
      double criticalHighTemperatureValue)
  {
    highCriticalValue = new Temperature("highCriticalValue",
        criticalHighTemperatureValue);
    property.firePropertyChange("criticalTemperatureChange", null,
        highCriticalValue);
  }

  @Override public int getHeaterPower()
  {
    return heater.getPower();
  }

  @Override public void turnUp()
  {
    heater.clickUp();
    if (getHeaterPower() == 3)
    {
      heater.startTimer(this);
    }
    heaterStateChange();
  }

  @Override public void turnDown()
  {
    heater.clickDown();
    heaterStateChange();
  }

  @Override public ArrayList<Temperature> getAllTemp()
  {
    return temperatureList.getAllTemp();
  }

  @Override public void heaterStateChange()
  {
    property.firePropertyChange("power", null, getHeaterStatus());
  }

  @Override public String getHeaterStatus()
  {
    return heater.getStatus();
  }

  @Override public Heater getHeater()
  {
    return heater;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

}
