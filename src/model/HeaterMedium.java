package model;

public class HeaterMedium extends HeaterState
{

  @Override public void clickUp(Heater heater)
  {
    heater.setState(new HeaterHigh(heater));
  }

  @Override public void clickDown(Heater heater)
  {
    heater.setState(new HeaterLow());
  }

  @Override public String getState()
  {
    return "Medium";
  }

}
