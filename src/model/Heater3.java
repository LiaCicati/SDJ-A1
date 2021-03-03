package model;

public class Heater3 extends HeaterState
{
  private Thread time;
  private boolean completed;

  public Heater3(Heater heater)
  {
    System.out.println("Heater high");
    heater.setPower(3);
    time = new Thread(() -> {
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
    time.start();

  }

  @Override public void clickUp(Heater heater)
  {
    System.out.println("Can not turn it up. It is max power");
    //    time = new Thread(() -> {
    //      try
    //      {
    //        Thread.sleep(1000);
    //        timeout(heater);
    //      }
    //      catch (InterruptedException e)
    //      {
    //        System.out.println("Can not turn it up. It is max power");
    //      }
    //    });
    //    time.start();
  }

  private void timeout(Heater heater)
  {
    if (!completed)
    {
      heater.setState(new Heater2(heater));
      completed = true;
    }
  }

  @Override public void clickDown(Heater heater)
  {

    time.interrupt();

  }
}


