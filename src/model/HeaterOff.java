package model;

public class HeaterOff extends HeaterState
{
  @Override public void clickUp(Heater heater)
  {
    heater.setState(new HeaterLow());
  }

  @Override public void clickDown(Heater heater)
  {
    System.out.println("Min state reached");
  }

  @Override public String getState()
  {
    return "Off";
  }

}
