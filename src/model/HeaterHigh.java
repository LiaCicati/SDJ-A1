package model;

import mediator.TemperatureModel;

public class HeaterHigh extends HeaterState
{
  private Thread timer;

  public synchronized void startTimer(Heater heater, TemperatureModel model)
  {
    this.timer = new Thread(() -> {
      try
      {
        Thread.sleep(6000);
        heater.setState(new HeaterMedium());
        model.heaterStateChange();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    });
    timer.start();
  }

  @Override public void clickUp(Heater heater)
  {
    System.out.println("Max state reached");
  }

  @Override public void clickDown(Heater heater)
  {
    timer.interrupt();
    heater.setState(new HeaterMedium());
  }

  @Override public String getState()
  {
    return "High";
  }
}


