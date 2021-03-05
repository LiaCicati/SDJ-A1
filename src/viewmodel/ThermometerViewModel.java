package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import mediator.TemperatureModel;
import model.Temperature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ThermometerViewModel implements PropertyChangeListener
{
  private TemperatureModel model;

  private StringProperty powerProperty;
  private DoubleProperty firstIndoorProperty;
  private DoubleProperty secondIndoorProperty;
  private DoubleProperty outdoorProperty;

  private DoubleProperty highValueProperty;
  private DoubleProperty lowValueProperty;
  private DoubleProperty setHighTemperature;
  private DoubleProperty setLowTemperature;
  private StringProperty firstThermometerWarning;
  private StringProperty secondThermometerWarning;
  private StringProperty errorProperty;

  public ThermometerViewModel(TemperatureModel model)
  {
    this.model = model;

    this.powerProperty = new SimpleStringProperty();
    this.firstIndoorProperty = new SimpleDoubleProperty();
    this.secondIndoorProperty = new SimpleDoubleProperty();
    this.outdoorProperty = new SimpleDoubleProperty();

    this.firstThermometerWarning = new SimpleStringProperty();
    this.secondThermometerWarning = new SimpleStringProperty();
    this.errorProperty = new SimpleStringProperty();

    this.setLowTemperature = new SimpleDoubleProperty();
    this.setHighTemperature = new SimpleDoubleProperty();

    this.highValueProperty = new SimpleDoubleProperty();
    this.lowValueProperty = new SimpleDoubleProperty();

    getAll();
    model.addListener(this);
  }

  public void getAll()
  {
    updateHeaterPower(model.getHeaterPower());
    firstIndoorProperty.set(model.getFirstThermometerTemperature());
    secondIndoorProperty.set(model.getFirstThermometerTemperature());
    outdoorProperty.set(model.getOutsideTemperature());

    setLowTemperature.set(model.getLowCriticalValue().getValue());
    setHighTemperature.set(model.getHighCriticalValue().getValue());
    highValueProperty.set(model.getHighCriticalValue().getValue());
    lowValueProperty.set(model.getLowCriticalValue().getValue());

    checkWarnings();
  }

  private void updateHeaterPower(int heaterPowerValue)
  {
    switch (heaterPowerValue)
    {
      case 0:
        powerProperty.set("Off");
        break;
      case 1:
        powerProperty.set("Low");
        break;
      case 2:
        powerProperty.set("Medium");
        break;
      case 3:
        powerProperty.set("High");
        break;
    }
  }

  private void checkWarnings()
  {
    if (model.getFirstThermometerTemperature() > model.getHighCriticalValue()
        .getValue())
      firstThermometerWarning.set("Temperature is too high!");
    else if (model.getFirstThermometerTemperature() < model
        .getLowCriticalValue().getValue())
      firstThermometerWarning.set("Temperature is too low!");
    else
      firstThermometerWarning.set("");

    if (model.getSecondThermometerTemperature() > model.getHighCriticalValue()
        .getValue())
      secondThermometerWarning.set("Temperature is too high!");
    else if (model.getSecondThermometerTemperature() < model
        .getLowCriticalValue().getValue())
      secondThermometerWarning.set("Temperature is too low!");
    else
      secondThermometerWarning.set("");
  }

  public void turnUp()
  {
    model.turnUp();
  }

  public void turnDown()
  {
    model.turnDown();
  }

  public DoubleProperty setCriticalHighTemperature()
  {
    return setHighTemperature;
  }

  public void submit()
  {
    model.setCriticalHighTemperature(setHighTemperature.getValue());
    model.setCriticalLowTemperature(setLowTemperature.getValue());
  }

  public DoubleProperty setCriticalLowTemperature()
  {
    return setLowTemperature;
  }

  public DoubleProperty getFirstIndoorProperty()
  {
    return firstIndoorProperty;
  }

  public DoubleProperty getSecondIndoorProperty()
  {
    return secondIndoorProperty;
  }

  public StringProperty getPowerProperty()
  {
    return powerProperty;
  }

  public DoubleProperty getOutdoorProperty()
  {
    return outdoorProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public DoubleProperty getHighValueProperty()
  {
    return highValueProperty;
  }

  public DoubleProperty getLowValueProperty()
  {
    return lowValueProperty;
  }

  public StringProperty firstThermometerWarningProperty()
  {
    return firstThermometerWarning;
  }

  public StringProperty secondThermometerWarningProperty()
  {
    return secondThermometerWarning;
  }

  @Override public void propertyChange(PropertyChangeEvent event)
  {
    Platform.runLater(() -> {
      switch (event.getPropertyName())
      {
        case "ThermometerTemperature":
          Temperature tmp = (Temperature) event.getNewValue();
          if (tmp.getId().equals("Indoor thermometer 1"))
          {
            firstIndoorProperty.set(tmp.getValue());
          }
          else if (tmp.getId().equals("Indoor thermometer 2"))
          {
            secondIndoorProperty.set(tmp.getValue());
          }
          checkWarnings();
          break;

        case "outsideTemperature":
          outdoorProperty.set(((Temperature) event.getNewValue()).getValue());
          break;

        case "power":
          powerProperty.set((String) event.getNewValue());
          break;

        case "criticalTemperatureChange":
          Temperature temp = (Temperature) event.getNewValue();
          if (temp.getId().equals("lowCriticalValue"))
          {
            lowValueProperty.set(temp.getValue());
          }
          else if (temp.getId().equals("highCriticalValue"))
          {
            highValueProperty.set(temp.getValue());
          }
          checkWarnings();
          break;
      }
    });
  }

}
