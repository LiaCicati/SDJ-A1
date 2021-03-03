package model;

public class HeaterLow extends HeaterState
{

  public HeaterLow(Heater heater)
  {
    System.out.println("Heater Low");
    heater.setPower(1);
  }
  @Override public void clickUp(Heater heater)
  {
    heater.setState(new HeaterMedium(heater));
  }

  @Override public void clickDown(Heater heater)
  {
    heater.setState(new HeaterOff(heater));
  }

}
