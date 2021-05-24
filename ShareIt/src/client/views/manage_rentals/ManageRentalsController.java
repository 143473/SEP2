package client.views.manage_rentals;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.manage_rentals.ManageRentalsViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.model.database.rental.RentalDAOImpl;
import shared.transferobjects.Rental;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ManageRentalsController {
  @FXML private TextField searchField;

  @FXML private Label nameOfRentalLabel;
  @FXML private Label descriptionLabel;
  @FXML private Label stateLabel;
  @FXML private Label priceLabel;
  @FXML private Label otherInformationLabel;
  @FXML private Label categoryLabel;

  private ViewHandler viewHandler;
  private ManageRentalsViewModel manageRentalsViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
    this.viewHandler = viewHandler;
    manageRentalsViewModel = viewModelFactory.getManageRentalsViewModel();
    nameOfRentalLabel.textProperty().bind(manageRentalsViewModel.nameOfRentalProperty());
    descriptionLabel.textProperty().bind(manageRentalsViewModel.descriptionOfRentalProperty());
    stateLabel.textProperty().bind(manageRentalsViewModel.stateOfRentalProperty());
    priceLabel.textProperty().bind(manageRentalsViewModel.priceOfRentalProperty());
    otherInformationLabel.textProperty().bind(manageRentalsViewModel.otherInformationOfRentalProperty());
    categoryLabel.textProperty().bind(manageRentalsViewModel.categoryOfRentalProperty());
  }

  public void changeButton(ActionEvent actionEvent) throws IOException {
    viewHandler.setView(viewHandler.menu(), viewHandler.editRental());
    manageRentalsViewModel.getSelectedRental();
  }

  public void deleteButton(ActionEvent actionEvent) throws IOException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
    alert.setTitle("Delete rental");
    alert.setHeaderText("Are you sure?");
    alert.getDialogPane().setContentText("Are you sure you want to permanent delete this rental?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      boolean success = manageRentalsViewModel.deleteRental();
      System.out.println("Controller delete" + success);
      if (success) {
        Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
        alert = new Alert(Alert.AlertType.INFORMATION, "");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Rental successfully deleted");
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("Click ok to get to your profile.");

        Optional<ButtonType> result2 = alert.showAndWait();
        if (result2.get() == ButtonType.OK) {
          viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
        }
      }
    }
  }

  public void goBackToProfileOverviewButton(ActionEvent actionEvent) throws IOException {
    viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
  }
}
