package mediator;

import model.*;
import utility.observer.subject.UnnamedPropertyChangeSubject;
import java.util.ArrayList;

public interface TemperatureModel extends UnnamedPropertyChangeSubject
{
  void addTemperature(String id, double value);
  void setCriticalLowTemperature(double criticalLowTemperatureValue);
  void setCriticalHighTemperature(double criticalHighTemperatureValue);
  void turnUp();
  void turnDown();
  int getTemperatureListSize();
  int getHeaterPower();
  Temperature getHighCriticalValue();
  Temperature getLowCriticalValue();
  Heater getHeater();
  ArrayList<Temperature> getAllTemp();

  void heaterStateChange();
  String getHeaterStatus();

}
