import external.OutTemperature;
import external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;
import mediator.TemperatureModel;
import mediator.TemperatureModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    TemperatureModel model = new TemperatureModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }
}
