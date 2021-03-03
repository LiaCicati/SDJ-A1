package external;

import model.Temperature;
import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OutTemperature implements Runnable, UnnamedPropertyChangeSubject
{

  private double currentTemperature;
  private double minTemp;
  private double maxTemp;
  private PropertyChangeSupport property;

  public OutTemperature(double currentTemperature, double minTemp, double maxTemp)
  {

    this.currentTemperature = currentTemperature;
    this.minTemp = minTemp;
    this.maxTemp = maxTemp;
    property = new PropertyChangeSupport(this);
  }

  /*** Calculating the external temperature.* Values are only valid if the temperature is being measured
   * * approximately every 10th second.** @param t0  the last measured external temperature
   * * @param min a lower limit (may temporally be deceeded)
   * * @param max an upper limit (may temporally be exceeded)
   * * @return an updated external temperature*/

  public double externalTemperature(double t0, double min, double max)
  {
    double left = t0 - min;
    double right = max - t0;
    int sign = Math.random() * (left + right) > left ? 1 : -1;
    t0 += sign * Math.random();
    return t0;
  }

  @Override public void run()
  {
    while (true)
    {
      currentTemperature = externalTemperature(currentTemperature, minTemp, maxTemp);
      property.firePropertyChange("outsideTemperature", null,
          new Temperature("Outside Temperature", currentTemperature));
      System.out
          .printf("Outside temperature: %.2f Min:%.2f Max:%.2f\n", currentTemperature,
              maxTemp, maxTemp);
      try
      {
        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public double getCurrentTemperature()
  {
    return currentTemperature;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }
}
