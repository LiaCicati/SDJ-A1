package mediator;

import model.*;

import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface TemperatureModel extends PropertyChangeListener,UnnamedPropertyChangeSubject
{
  void addTemperature(Temperature temperature);
  Temperature getTemperature(int index);
  void setCriticalLowTemperature(double criticalLowTemperatureValue);
  void setCriticalHighTemperature(double criticalHighTemperatureValue);
  void turnUp();
  void turnDown();
  int getTemperatureListSize();
  int getHeaterPower();
  double getOutsideTemperature();
  double getFirstThermometerTemperature();
  double getSecondThermometerTemperature();
  Temperature getHighCriticalValue();
  Temperature getLowCriticalValue();
  Heater getHeater();
  ArrayList<Temperature> getAllTemp();

}
