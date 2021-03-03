package model;

public class Heater2 extends HeaterState
{
  public Heater2(Heater heater)
  {
    System.out.println("Heater Medium");
    heater.setPower(2);
  }
  @Override public void clickUp(Heater heater)
  {
    heater.setState(new Heater3(heater));
  }

  @Override public void clickDown(Heater heater)
  {
    heater.setState(new Heater1(heater));
  }


}
