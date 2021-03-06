package client.views.chat_write_message;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.chat_write_message.ChatWriteMessageViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * A class that manages an interface and handle interactions in ChatWriteMessage view
 */
public class ChatWriteMessageController
{
  @FXML private Label toUsernameLabel;
  @FXML private TextArea textChatArea;
  @FXML private TextField inputTextChatField;

  private ViewHandler viewHandler;
  private ChatWriteMessageViewModel chatWriteMessageViewModel;
  /**
   * Init.
   * loads the initial data
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    textChatArea.clear();
    chatWriteMessageViewModel = viewModelFactory
        .getChatWriteMessagesViewModel();
    toUsernameLabel.textProperty()
        .bind(chatWriteMessageViewModel.getUsername());
    inputTextChatField.textProperty()
        .bindBidirectional(chatWriteMessageViewModel.getInputTextChat());
    chatWriteMessageViewModel.getMember();
    textChatArea.textProperty().bind(chatWriteMessageViewModel.getMessages());
    chatWriteMessageViewModel.loadLogs();
  }

  public void onGoBack()
  {
    textChatArea.clear();
    chatWriteMessageViewModel.loadAllReceivedMessages();
    viewHandler.setView(viewHandler.menu(), viewHandler.chatReceived());
  }

  public void onSend() {
    chatWriteMessageViewModel.sendMessage();
    inputTextChatField.clear();
  }

  public void reporterMemberClickedOn() {
    chatWriteMessageViewModel.setReceiverNameLabel();
    viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
  }
}
