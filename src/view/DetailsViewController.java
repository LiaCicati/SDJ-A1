package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.DataTableView;
import viewmodel.DetailsViewModel;

public class DetailsViewController
{
  @FXML private TableView<DataTableView> temperatureListTable;
  @FXML private TableColumn<DataTableView,String> nameColumn;
  @FXML private TableColumn<DataTableView,Number> temperatureColumn;
  @FXML private TableColumn<DataTableView,String> timeColumn;

  private Region root;
  private DetailsViewModel viewModel;
  private ViewHandler viewHandler;

  public DetailsViewController()
  {

  }

  public void init(ViewHandler viewHandler,DetailsViewModel viewModel, Region root )
  {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;
    this.root = root;

    nameColumn.setCellValueFactory(
        cellData -> cellData.getValue().getThermometerIdProperty()
    );
    temperatureColumn.setCellValueFactory(
        cellData -> cellData.getValue().getTemperatureProperty()
    );
    timeColumn.setCellValueFactory(
        cellData -> cellData.getValue().getTimeProperty()
    );
    temperatureListTable.setItems(viewModel.getList());

    temperatureListTable.setSelectionModel(null);
    nameColumn.setSortable(false);
    temperatureColumn.setSortable(false);
    timeColumn.setSortable(false);
  }

  public void reset()
  {
    temperatureListTable.setItems(viewModel.getList());
  }
  public Region getRoot()
  {
    return root;
  }

  @FXML public void back()
  {
    viewHandler.openView("thermometer");
  }

}
