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
//  {
//    TemperatureModel model = new TemperatureModelManager();
//    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
//    ViewHandler view = new ViewHandler(viewModelFactory);
//
//    Thermometer t1 = new Thermometer("t1",1,4,15,1,model);
//    Thermometer t2 = new Thermometer("t2",1,6,15,7,model);
//    OutTemperature out = new OutTemperature("t3",model,0,5);
//
//    Thread th1 = new Thread(t1);
//    Thread th2 = new Thread(t2);
//    Thread th3 = new Thread(out);
//    th1.start();
//    th2.start();
//    th3.start();
//
//    view.start(primaryStage);
//  }
  {
    TemperatureModel model = new TemperatureModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }
}
