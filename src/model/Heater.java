package model;

import mediator.TemperatureModel;
import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Heater
{

  private HeaterState state;


  public Heater()
  {

    this.state = new HeaterOff();

  }

  public void startTimer(TemperatureModel model)
  {
    state.startTimer(this, model);
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
  public String getStatus()
  {
    return state.getState();
  }

  public int getPower()
  {
    switch (getStatus())
    {
      case "Off":
        return 0;
      case "Low":
        return 1;
      case "Medium":
        return 2;
      case "High":
        return 3;
      default:
        return -1;
    }
  }
}