package client.views.manage_account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.manage_account.ManageAccountViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Rental;

import java.util.List;
/**
 * A class that manages an interface and handle interactions in ManageAccount view
 */
public class ManageAccountController
{
  @FXML private Label usernameLabel;
  @FXML private Label locationLabel;
  @FXML private Label ratingLabel;
  @FXML private Label addressLabel;
  @FXML private Label contactLabel;
  @FXML private Label otherInformationLabel;
  @FXML private FlowPane flowPane;

  private ViewHandler viewHandler;
  private ManageAccountViewModel manageAccountViewModel;
  /**
   * Init.
   *
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    manageAccountViewModel = viewModelFactory.getManageAccountViewModel();
    usernameLabel.textProperty()
        .bind(manageAccountViewModel.getUsernameLabel());
    locationLabel.textProperty()
        .bind(manageAccountViewModel.getLocationLabel());
    ratingLabel.textProperty().bind(manageAccountViewModel.getRatingLabel());
    addressLabel.textProperty().bind(manageAccountViewModel.getAddressLabel());
    contactLabel.textProperty().bind(manageAccountViewModel.getContactLabel());
    otherInformationLabel.textProperty()
        .bind(manageAccountViewModel.getOtherInformationLabel());
    manageAccountViewModel.setProfile();
    displayRentals(manageAccountViewModel.getRentalsOfMemberList());
  }
  /**
   * Changes a view if button was pressed.
   */
  public void editOrDeleteInformationButton()
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.editOrDeleteAccount());
  }
  /**
   * Changes a view if button was pressed.
   */
  public void addRentalButton()
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.addRental());
  }
  /**
   * Displays rentals.
   *
   * @param rentals Rentals that are being displayed
   */
  public void displayRentals(List<Rental> rentals)
  {
    if (rentals != null && !rentals.isEmpty())
    {
      for (int i = 0; i < rentals.size(); i++)
      {
        Image image = new Image(rentals.get(i).getPictureLink());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(275);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setId(String.valueOf(rentals.get(i).getId()));
        imageView.getStyleClass().add("image");
        flowPane.getChildren().add(new StackPane(
            new InfoOverlay(imageView, rentals.get(i).toString())));
        flowPane.getChildren().get(i)
            .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
              viewHandler
                  .setView(viewHandler.menu(), viewHandler.manageRentals());
              manageAccountViewModel.getRental(event.getSource());
            });
      }
    }
  }
  /**
   * Changes a view if button was pressed and sets data of actually viewed member to use it in another view.
   */
  public void viewRating()
  {
    manageAccountViewModel.setMember();
    viewHandler.setView(viewHandler.menu(), viewHandler.viewRating());
  }
}
