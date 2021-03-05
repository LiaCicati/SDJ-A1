package model;

import mediator.TemperatureModel;

public abstract class HeaterState
{
  public abstract void clickUp(Heater heater);
  public abstract void clickDown(Heater heater);
  public abstract String getState();

  public void startTimer(Heater heater, TemperatureModel model)
  {

  }
}

