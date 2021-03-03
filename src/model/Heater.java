package model;

import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Heater implements UnnamedPropertyChangeSubject
{

  private HeaterState state;
  private int power;
  PropertyChangeSupport property;

  public Heater()
  {
    this.property = new PropertyChangeSupport(this);
    this.state = new HeaterOff(this);

  }

  public void clickUp()
  {
    state.clickUp(this);
  }

  public void clickDown()
  {
    state.clickDown(this);
  }

  public void setState(HeaterState state)
  {
    this.state = state;
  }

  public int getPower()
  {
    return power;
  }

  public void setPower(int power)
  {
    this.power = power;
    this.property.firePropertyChange("power", null, power);
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