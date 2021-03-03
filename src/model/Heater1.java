package model;

public class Heater1 extends HeaterState
{

  public Heater1(Heater heater)
  {
    System.out.println("Heater Low");
    heater.setPower(1);
  }
  @Override public void clickUp(Heater heater)
  {
    heater.setState(new Heater2(heater));
  }

  @Override public void clickDown(Heater heater)
  {
    heater.setState(new HeaterOff(heater));
  }

}
