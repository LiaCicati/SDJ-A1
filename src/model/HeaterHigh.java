package model;

public class HeaterHigh extends HeaterState
{
  private Thread timer;
  private boolean completed;

  public HeaterHigh(Heater heater)
  {
    System.out.println("Heater high");
    heater.setPower(3);
    timer = new Thread(() -> {
      try
      {
        Thread.sleep(5000);
        timeout(heater);
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

  private void timeout(Heater heater)
  {
    if (!completed)
    {
      heater.setState(new HeaterMedium(heater));
      completed = true;
    }
  }

  @Override public void clickDown(Heater heater)
  {

    timer.interrupt();

  }
}


