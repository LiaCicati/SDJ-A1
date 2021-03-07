package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.ThermometerViewModel;

import java.util.Optional;

public class ThermometerViewController
{
  @FXML private Label indoorThermometer1;
  @FXML private Label indoorThermometer2;
  @FXML private Label outdoorThermometer;
  @FXML private Label heaterLabel;
  @FXML public Label indoorThermometer1Warning;
  @FXML public Label indoorThermometer2Warning;
  @FXML public Label criticalLowLabel;
  @FXML public Label criticalHighLabel;
  @FXML private TextField highValue;
  @FXML private TextField lowValue;

  private Region root;
  private ThermometerViewModel viewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ThermometerViewModel viewModel,
      Region root)
  {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;
    this.root = root;

    heaterLabel.textProperty().bind(viewModel.getPowerProperty());
    indoorThermometer1Warning.textProperty()
        .bind(viewModel.firstThermometerWarningProperty());
    indoorThermometer2Warning.textProperty()
        .bind(viewModel.secondThermometerWarningProperty());


    Bindings.bindBidirectional(outdoorThermometer.textProperty(), viewModel.getOutdoorProperty(), new StringIntegerConverter(0));
    Bindings.bindBidirectional(indoorThermometer1.textProperty(), viewModel.getFirstIndoorProperty(), new StringIntegerConverter(0));
    Bindings.bindBidirectional(indoorThermometer2.textProperty(), viewModel.getSecondIndoorProperty(), new StringIntegerConverter(0));

    Bindings.bindBidirectional(criticalHighLabel.textProperty(),
        viewModel.getHighValueProperty(), new StringIntegerConverter(0));
    Bindings.bindBidirectional(criticalLowLabel.textProperty(),
        viewModel.getLowValueProperty(), new StringIntegerConverter(0));

    Bindings.bindBidirectional(lowValue.textProperty(),
        viewModel.setCriticalLowTemperature(), new StringIntegerConverter(0));
    Bindings.bindBidirectional(highValue.textProperty(),
        viewModel.setCriticalHighTemperature(), new StringIntegerConverter(0));
  }

  public void reset()
  {
    viewModel.getAll();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML public void upClicked()
  {
    viewModel.turnUp();
  }

  @FXML public void downClicked()
  {
    viewModel.turnDown();
  }

  @FXML public void openLogs()
  {
    viewHandler.openView("logs");
  }

  public void setValue()
  {
    confirmation();
    viewModel.submit();
  }

  private boolean confirmation()
  {
    if (Double.parseDouble(highValue.getText()) <= 40
        && Double.parseDouble(lowValue.getText()) >= -20)
    {
      return false;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Warning");
    if (Double.parseDouble(highValue.getText()) <= 40)
    {
      lowValue.setText("-20");
      alert.setHeaderText("Minimum temperature can not be lower than -20");
    }
    else if (Double.parseDouble(lowValue.getText()) >= -20)
    {
      highValue.setText("40");
      alert.setHeaderText("Maximum temperature can not be higher than 40");
    }
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
}
