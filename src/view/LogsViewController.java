package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.DataTableView;
import viewmodel.LogsViewModel;

public class LogsViewController
{
  @FXML private TableView<DataTableView> temperatureListTable;
  @FXML private TableColumn<DataTableView, String> nameColumn;
  @FXML private TableColumn<DataTableView, Number> temperatureColumn;
  @FXML private TableColumn<DataTableView, String> timeColumn;

  private Region root;
  private LogsViewModel viewModel;
  private ViewHandler viewHandler;

  public LogsViewController()
  {
  }

  public void init(ViewHandler viewHandler, LogsViewModel viewModel,
      Region root)
  {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;
    this.root = root;

    nameColumn.setCellValueFactory(
        cellData -> cellData.getValue().getThermometerIdProperty());
    temperatureColumn.setCellValueFactory(
        cellData -> cellData.getValue().getTemperatureProperty());
    timeColumn
        .setCellValueFactory(cellData -> cellData.getValue().getTimeProperty());
    reset();

  }

  public void reset()
  {
    temperatureListTable.setItems(viewModel.getList());
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void openMainView()
  {
    viewHandler.openView("thermometers");
  }

}
