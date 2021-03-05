package model;

public class HeaterLow extends HeaterState
{

  @Override public void clickUp(Heater heater)
  {
    heater.setState(new HeaterMedium());
  }

  @Override public void clickDown(Heater heater)
  {
    heater.setState(new HeaterOff());
  }

  @Override public String getState()
  {
    return "Low";
  }

}
