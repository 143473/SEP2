package client.viewmodel.manage_account;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.util.ArrayList;
/**
 * A class that holds and manages data from the ManageAccount view.
 */
public class ManageAccountViewModel
{
  private SimpleStringProperty usernameLabel;
  private SimpleStringProperty locationLabel;
  private SimpleStringProperty ratingLabel;
  private SimpleStringProperty addressLabel;
  private SimpleStringProperty contactLabel;
  private SimpleStringProperty otherInformationLabel;
  private RentalModel rentalModel;
  private MemberModel memberModel;

  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param rentalModel The model that this ViewModel uses
   * @param memberModel The model that this ViewModel uses
   */
  public ManageAccountViewModel(RentalModel rentalModel,
      MemberModel memberModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;

    usernameLabel = new SimpleStringProperty();
    locationLabel = new SimpleStringProperty();
    ratingLabel = new SimpleStringProperty();
    addressLabel = new SimpleStringProperty();
    contactLabel = new SimpleStringProperty();
    otherInformationLabel = new SimpleStringProperty();
  }

    /**
     * Sets logged in member information in the controller.
     */
  public void setProfile()
  {
    Member member = memberModel
        .getMemberByUsername(memberModel.getLoggedInUsername());
    usernameLabel.setValue("Username: " + member.getUsername());
    locationLabel.setValue("Location: " + member.getAddressCity());
    ratingLabel.setValue("Rating: " + (member.getAverageReview()));
    addressLabel.setValue(
        "Address: " + member.getAddressStreet() + ", " + member.getAddressNo());
    contactLabel.setValue(
        "Contact: " + member.getPhoneNo() + "\n" + member.getEmailAddress());
    otherInformationLabel
        .setValue("Other Information: " + member.getOtherInformation());
  }

  public ArrayList<Rental> getRentalsOfMemberList()
  {
    return rentalModel.getRentalsOfMemberList();
  }

    /**
     * On mouse click, the clicked object is retrieved and analyzed. The purpose
     * is to get the the member's id stored in the ImageView id field. The id is
     * used to retrieve the selected rental from the member's list of rentals, and
     * set it to the RentalModel.
     *
     * @param object the object
     */
  public void getRental(Object object)
  {
    if (object instanceof StackPane)
    {
      StackPane stackPane = (StackPane) object;
      if (stackPane.getChildren().get(0) instanceof InfoOverlay)
      {
        InfoOverlay infoOverlay = (InfoOverlay) stackPane.getChildren().get(0);
        if (infoOverlay.getContent() instanceof ImageView)
        {
          ImageView imageView = (ImageView) infoOverlay.getContent();
          for (int i = 0; i < getRentalsOfMemberList().size(); i++)
          {
            if (imageView.getId().equals(
                String.valueOf(getRentalsOfMemberList().get(i).getId())))
            {
              rentalModel.sendSelectedRental(getRentalsOfMemberList().get(i));
              rentalModel.setSelectedRental(getRentalsOfMemberList().get(i));
            }
          }
        }
      }
    }
  }

  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }

  public StringProperty getLocationLabel()
  {
    return locationLabel;
  }

  public StringProperty getRatingLabel()
  {
    return ratingLabel;
  }

  public StringProperty getAddressLabel()
  {
    return addressLabel;
  }

  public StringProperty getContactLabel()
  {
    return contactLabel;
  }

  public StringProperty getOtherInformationLabel()
  {
    return otherInformationLabel;
  }

  public void setMember()
  {
    memberModel.setMemberUsername(usernameLabel.getValue().substring(10));
  }
}
