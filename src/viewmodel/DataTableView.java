package viewmodel;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Temperature;

public class DataTableView
{
  private DoubleProperty temperatureProperty;
  private StringProperty thermometerIdProperty;
  private StringProperty timeProperty;

  public DataTableView(Temperature temperature)
  {
    this.thermometerIdProperty = new SimpleStringProperty(temperature.getId());
    this.temperatureProperty = new SimpleDoubleProperty(temperature.getValue());
    this.timeProperty = new SimpleStringProperty(
        temperature.getTime().getTimestamp());
  }

  public StringProperty getThermometerIdProperty()
  {
    return thermometerIdProperty;
  }

  public DoubleProperty getTemperatureProperty()
  {
    return temperatureProperty;
  }

  public StringProperty getTimeProperty()
  {
    return timeProperty;
  }

  public Temperature getTemperature()
  {
    return new Temperature(thermometerIdProperty.get(), temperatureProperty.get());
  }
}
