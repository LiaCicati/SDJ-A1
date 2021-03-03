package model;

public class HeaterMedium extends HeaterState
{
  public HeaterMedium(Heater heater)
  {
    System.out.println("Heater Medium");
    heater.setPower(2);
  }
  @Override public void clickUp(Heater heater)
  {
    heater.setState(new HeaterHigh(heater));
  }

  @Override public void clickDown(Heater heater)
  {
    heater.setState(new HeaterLow(heater));
  }


}
