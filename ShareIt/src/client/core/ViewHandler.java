package client.core;

import client.views.add_rental.AddRentalController;
import client.views.chat_received_messages.ChatReceivedMessagesController;
import client.views.chat_write_message.ChatWriteMessageController;
import client.views.create_account.CreateAccountController;
import client.views.log_in.LogInController;
import client.views.main_view.MainController;
import client.views.menu.MenuController;
import client.views.search_for_rental.SearchForRentalController;
import client.views.view_rating.ViewRatingController;
import client.views.view_reported_member.ViewReportedMemberController;
import client.views.view_reported_member_list.ViewReportedMemberListController;
import client.views.welcome_page.WelcomePageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private final Stage stage;
  private final Scene scene;
  private final ViewModelFactory viewModelFactory;

  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    this.stage = stage;
    scene = new Scene(new Region());
  }

  public void start() throws Exception
  {
    setView(menu(),manageRentals());
  }

  public void setView(Node menu, Node content) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/main_view/Main.fxml"));
    loader.load();
    MainController main = loader.getController();
    main.getMainPane().getChildren().setAll(menu,content);
    scene.setRoot(main.getMainPane());
    stage.setScene(scene);
    stage.show();
  }

  public Node menu() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/menu/Menu.fxml"));
    Node menu = loader.load();
    MenuController menuController = loader.getController();
    menuController.init(this, viewModelFactory);
    return menu;
  }

  public Node addRental() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/add_rental/AddRental.fxml"));
    Node content = loader.load();
    AddRentalController addRentalController = loader.getController();
    addRentalController.init(this, viewModelFactory);
    stage.setTitle("Add Rental");
    return content;
  }

  public Node chatReceived() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/chat_received_messages/ChatRecievedMessages.fxml"));
    Node content = loader.load();
    ChatReceivedMessagesController chatReceivedMessagesController = loader.getController();
    chatReceivedMessagesController.init(this, viewModelFactory);
    return content;
  }

  public Node chatWrite() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/chat_write_message/ChatWriteMessage.fxml"));
    Node content = loader.load();
    ChatWriteMessageController chatWriteMessageController = loader.getController();
    chatWriteMessageController.init();
    return content;
  }

  public Node createAccount() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/create_account/CreateAccount.fxml"));
    Node content = loader.load();
    CreateAccountController createAccountController = loader.getController();
    createAccountController.init(this, viewModelFactory);
    return content;
  }

  public Node logIn() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/log_in/LogIn.fxml"));
    Node content = loader.load();
    LogInController logInController = loader.getController();
    logInController.init(this, viewModelFactory);
    return content;
  }

  public Node manageAccount() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/manage_account/ManageAccount.fxml"));
    Node content = loader.load();
    //LogInController logInController = loader.getController();
    //ogInController.init(this, viewModelFactory);
    return content;
  }
  public Node manageRentals() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/manage_rentals/ManageRentals.fxml"));
    Node content = loader.load();
    //LogInController logInController = loader.getController();
    //ogInController.init(this, viewModelFactory);
    return content;
  }
  public Node rateFeedback() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/rate_feedback/RateFeedback.fxml"));
    Node content = loader.load();
    //LogInController logInController = loader.getController();
    //ogInController.init(this, viewModelFactory);
    return content;
  }
  public Node reportMember() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/report_member/ReportMember.fxml"));
    Node content = loader.load();
    //LogInController logInController = loader.getController();
    //ogInController.init(this, viewModelFactory);
    return content;
  }
  public Node searchForMember() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/search_for_member/SearchForMember.fxml"));
    Node content = loader.load();
    //LogInController logInController = loader.getController();
    //ogInController.init(this, viewModelFactory);
    return content;
  }
  public Node searchForRental() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/search_for_rental/SearchForRental.fxml"));
    Node content = loader.load();
    SearchForRentalController searchForRentalController = loader.getController();
    searchForRentalController.init(this, viewModelFactory);
    return content;
  }
  public Node viewMemberProfile() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_member_profile/ViewMemberProfile.fxml"));
    Node content = loader.load();
    //LogInController logInController = loader.getController();
    //ogInController.init(this, viewModelFactory);
    return content;
  }
  public Node viewRating() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_rating/ViewRating.fxml"));
    Node content = loader.load();
    //LogInController logInController = loader.getController();
    //ogInController.init(this, viewModelFactory);
    return content;
  }
  public Node viewRatingFull() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_rating_full/ViewRatingFull.fxml"));
    Node content = loader.load();
    //LogInController logInController = loader.getController();
    //ogInController.init(this, viewModelFactory);
    return content;
  }
  public Node viewRental() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_rental/ViewRental.fxml"));
    Node content = loader.load();
    ViewRatingController viewRatingController = loader.getController();
    viewRatingController.init(this, viewModelFactory);
    return content;
  }
  public Node viewReportedMember() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_reported_member/ViewReportedMember.fxml"));
    Node content = loader.load();
    ViewReportedMemberController viewReportedMemberController = loader.getController();
    viewReportedMemberController.init(this, viewModelFactory);
    return content;
  }
  public Node viewReportedMemberList() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_reported_member_list/ViewReportedMemberList.fxml"));
    Node content = loader.load();
    ViewReportedMemberListController viewReportedMemberListController = loader.getController();
    viewReportedMemberListController.init(this, viewModelFactory);
    return content;
  }
  public Node welcomePage() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/welcome_page/WelcomePage.fxml"));
    Node content = loader.load();
    WelcomePageController welcomePageController = loader.getController();
    welcomePageController.init(this, viewModelFactory);
    return content;
  }
}
