package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;
  private ViewModelFactory viewModelFactory;
  private LogsViewController detailsViewController;
  private ThermometerViewController thermometerViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("thermometers");

  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "thermometers":
        root = loadThermometerView("ThermometersView.fxml");
        break;
      case "logs":
        root = loadLogsView("LogsView.fxml");
        break;
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setMinWidth(root.getPrefWidth());
    primaryStage.setMinHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadLogsView(String fxmlFile)
  {
    if (detailsViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        detailsViewController = loader.getController();
        detailsViewController
            .init(this, viewModelFactory.getLogsViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      detailsViewController.reset();
    }
    return detailsViewController.getRoot();
  }

  private Region loadThermometerView(String fxmlFile)
  {
    if (thermometerViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        thermometerViewController = loader.getController();
        thermometerViewController
            .init(this, viewModelFactory.getThermometerViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      thermometerViewController.reset();
    }
    return thermometerViewController.getRoot();
  }
}

