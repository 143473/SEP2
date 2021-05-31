package client.views.log_in;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.state.AdministratorState;
import client.model.state.MemberState;
import client.viewmodel.log_in.LogInViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.CustomPasswordField;

public class LogInController
{
  @FXML private ScrollPane parent;
  @FXML private TextField userNameField;
  @FXML private CustomPasswordField passwordField;

  private LogInViewModel logInViewModel;
  private ViewHandler viewHandler;
  private Notifications notifications;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    logInViewModel = viewModelFactory.getLogInViewModel();
    userNameField.textProperty()
        .bindBidirectional(logInViewModel.getUserName());
    passwordField.textProperty()
        .bindBidirectional(logInViewModel.getPasswordField());

    notifications = Notifications.create().title("Error - invalid input!")
        .graphic(new Rectangle(300, 300, Color.RED))
        .hideAfter(Duration.seconds(3));
    userNameField.clear();
    passwordField.clear();
  }

  public void logInButton()
  {

    if (checkFieldNotEmpty(userNameField) && checkPasswordFieldNotEmpty(
        passwordField))
    {
      String memberUsername = logInViewModel.onLogInButton();
      System.out.println(memberUsername);

      if (memberUsername != null)
      {
        if (memberUsername.equals("administrator"))
        {
          logInViewModel.setUserType("administrator");
          viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
        }
        else
        {
          logInViewModel.setUserType("member");
          viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
        }
      }
      else
      {
        notifications.owner(parent).text("Incorrect username or password")
            .showError();
      }
    }
  }

  public void createNewAccount()
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.createAccount());
  }

  public boolean checkFieldNotEmpty(TextField textField)
  {
    if (textField.textProperty().getValue() == null || textField.textProperty()
        .getValue().isBlank())
    {
      notifications.owner(parent).text("Username cannot be empty").showError();
      return false;
    }
    return true;
  }

  public boolean checkPasswordFieldNotEmpty(CustomPasswordField textField)
  {
    if (textField.textProperty().getValue() == null || textField.textProperty()
        .getValue().isBlank())
    {
      notifications.owner(parent).text("Password cannot be empty").showError();
      return false;
    }
    return true;
  }
}
