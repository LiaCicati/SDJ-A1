package viewmodel;

import mediator.TemperatureModel;

public class ViewModelFactory
{
  private DetailsViewModel detailsViewModel;
  private ThermometerViewModel thermometerViewModel;

  public ViewModelFactory(TemperatureModel model)
  {
    this.detailsViewModel = new DetailsViewModel(model);
    this.thermometerViewModel = new ThermometerViewModel(model);

  }

  public DetailsViewModel getDetailsViewModel()
  {
    return detailsViewModel;
  }

  public ThermometerViewModel getThermometerViewModel()
  {
    return thermometerViewModel;
  }
}
