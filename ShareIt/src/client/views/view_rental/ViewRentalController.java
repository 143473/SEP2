package client.views.view_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.state.StateManager;
import client.viewmodel.view_rental.ViewRentalViewModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.MaskerPane;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

public class ViewRentalController
{


  @FXML private TextField searchField;
  
  @FXML private Label nameOfRentalLabel;
  @FXML private Label descriptionLabel;
  @FXML private Label stateLabel;
  @FXML private Label priceLabel;
  @FXML private Label otherInformationLabel;
  @FXML private Label categoriesLabel;
  @FXML private ImageView imageView;
  @FXML private Button goBackButton;
  private Image picture;
  
  @FXML private Label usernameLabel;
  @FXML private Label locationLabel;
  @FXML private Label ratingLabel;


  private ViewHandler viewHandler;
  private ViewRentalViewModel viewRentalViewModel;


  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
      throws IOException
  {
    viewRentalViewModel = viewModelFactory.getViewRentalViewModel();
    this.viewHandler = viewHandler;

    Bindings.bindBidirectional(this.imageView.imageProperty(), viewRentalViewModel.imagePropertyProperty());
    Bindings.bindBidirectional(this.imageView.idProperty(), viewRentalViewModel.getImageIdMemberId());
    //imageView.setImage(picture);

    nameOfRentalLabel.textProperty().bind(viewRentalViewModel.nameOfRentalProperty());
    descriptionLabel.textProperty().bind(viewRentalViewModel.descriptionOfRentalProperty());
    stateLabel.textProperty().bind(viewRentalViewModel.stateOfRentalProperty());
    priceLabel.textProperty().bind(viewRentalViewModel.priceOfRentalProperty());
    otherInformationLabel.textProperty().bind(viewRentalViewModel.otherInformationOfRentalProperty());
    categoriesLabel.textProperty().bind(viewRentalViewModel.categoryOfRentalProperty());
    usernameLabel.textProperty().bind(viewRentalViewModel.usernameOfRentalProperty());
    locationLabel.textProperty().bind(viewRentalViewModel.locationOfRentalProperty());
    ratingLabel.textProperty().bind(viewRentalViewModel.ratingOfUserOfRentalProperty());

    if(viewRentalViewModel.getUserType().equals("Administrator")){
      goBackButton.setText("Go back to member page");
    }
  }


  public void goBackToSearchResultsButton(ActionEvent actionEvent)
      throws IOException
  {
    if(viewRentalViewModel.getUserType().equals("Administrator")){
      viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }
    else{
      viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental());
    }

  }

  public void seeMoreButton(ActionEvent actionEvent) throws IOException
  {
    viewRentalViewModel.setMemberUsername();
    //if member's profile I want to see is mine, send me to view my profile
    if(viewRentalViewModel.usernameOfRentalProperty().getValue().equals(StateManager.getInstance().getUsername())){
      viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
    }
    else{
      viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }

    //viewRentalViewModel.getMemberById();
  }
}
