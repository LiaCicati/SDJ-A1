package viewmodel;

import mediator.TemperatureModel;

public class ViewModelFactory
{
  private LogsViewModel logsViewModel;
  private ThermometerViewModel thermometerViewModel;

  public ViewModelFactory(TemperatureModel model)
  {
    this.logsViewModel = new LogsViewModel(model);
    this.thermometerViewModel = new ThermometerViewModel(model);

  }

  public LogsViewModel getLogsViewModel()
  {
    return logsViewModel;
  }

  public ThermometerViewModel getThermometerViewModel()
  {
    return thermometerViewModel;
  }
}
