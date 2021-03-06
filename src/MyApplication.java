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
    ViewHandler view = new ViewHandler(viewModelFactory);

    Thermometer t1 = new Thermometer("Indoor temperature 1", 1, 4, 10, 1,
        model);
    Thermometer t2 = new Thermometer("Indoor temperature 2", 1, 6, 10, 7,
        model);
    OutTemperature out = new OutTemperature("Outdoor temperature", model, 10,
        5);

    Thread th1 = new Thread(t1);
    Thread th2 = new Thread(t2);
    Thread th3 = new Thread(out);
    th1.setDaemon(true);
    th2.setDaemon(true);
    th3.setDaemon(true);
    th1.start();
    th2.start();
    th3.start();

    view.start(primaryStage);
  }
}
