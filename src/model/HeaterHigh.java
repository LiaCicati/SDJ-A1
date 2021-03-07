package model;

public class HeaterHigh extends HeaterState
{
  private Thread timer;
  private boolean completed;

  public HeaterHigh(Heater heater)
  {
    System.out.println("Heater high");

    timer  = new Thread(() -> {
      try{
        Thread.sleep(4000);
        timeout(heater);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    });
    timer.start();
  }

  public void timeout(Heater heater)
  {
    if(!completed)
    {
      heater.setState(new HeaterMedium());
      completed = true;
    }
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


