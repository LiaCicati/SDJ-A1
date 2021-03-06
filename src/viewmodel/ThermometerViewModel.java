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
    powerProperty.setValue(model.getHeaterStatus());
    setLowTemperature.set(model.getLowCriticalValue().getValue());
    setHighTemperature.set(model.getHighCriticalValue().getValue());
    highValueProperty.set(model.getHighCriticalValue().getValue());
    lowValueProperty.set(model.getLowCriticalValue().getValue());

    checkWarnings();
  }

  private void checkWarnings()
  {
    if (firstIndoorProperty.get() > highValueProperty.get())
      firstThermometerWarning.set("Temperature is too high!");
    else if (firstIndoorProperty.get() < lowValueProperty.get())
      firstThermometerWarning.set("Temperature is too low!");
    else
      firstThermometerWarning.set("");

    if (secondIndoorProperty.get() > highValueProperty.get())
      secondThermometerWarning.set("Temperature is too high!");
    else if (secondIndoorProperty.get() < lowValueProperty.get())
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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "temperature":
          Temperature temp = (Temperature) evt.getNewValue();

          checkWarnings();
          switch (temp.getId())
          {
            case "Indoor temperature 1":
              firstIndoorProperty.set(temp.getValue());
              break;
            case "Indoor temperature 2":
              secondIndoorProperty.set(temp.getValue());
              break;
            case "Outdoor temperature":
              outdoorProperty.set(temp.getValue());
              break;

          }
        case "power":
          powerProperty.setValue(model.getHeaterStatus());
          break;
        case "criticalTemperatureChange":
          Temperature temperature = (Temperature) evt.getNewValue();
          if (temperature.getId().equals("lowCriticalValue"))
          {
            lowValueProperty.set(temperature.getValue());
          }
          else if (temperature.getId().equals("highCriticalValue"))
          {
            highValueProperty.set(temperature.getValue());
          }
          checkWarnings();
          break;
      }
    });
  }

}
