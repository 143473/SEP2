package server.model.database.message;

import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Member;
import shared.transferobjects.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Class that implements methods from its interface and provides access to a database(Message in this case)
 *
 */
public class MessageDAOImpl
{
  private static MessageDAOImpl instance;
  private String password;

  private MessageDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized MessageDAOImpl getInstance()
  {
    if (instance == null)
    {
      try
      {
        instance = new MessageDAOImpl();
      }
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
      }
    }
    return instance;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
            password);
  }
  /**
   * Gets all received messages for logged in member by connecting to the database and get all table contents that are matched with given data
   * @param loggedUserId The user that is currently logged in
   * @return all the messages connected with this particular member
   */
  public ArrayList<Message> getAllReceivedMessages(int loggedUserId)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM share_it.message WHERE member_to = ? AND time IN (SELECT MAX(time) FROM share_it.message WHERE member_to = ? GROUP BY member_from) ORDER BY time DESC ;");
      statement.setInt(1, loggedUserId);
      statement.setInt(2, loggedUserId);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Message> arrayListToReturn = new ArrayList<>();

      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        Date d2 = resultSet.getTimestamp("time");
        int fromUserId = resultSet.getInt("member_from");
        Member member = MemberDAOImpl.getInstance().getMemberById(fromUserId);

        arrayListToReturn.add(new Message(d2, member.getUsername(), text));
      }
      return arrayListToReturn;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * Creates new message by connecting to a database then inserting data provided by Member to the database
   * @param message message object that will be sent
   * @return new object of Message with data which was provided by Member while creating new Message
   */
  public Message sendMessage(Message message)
  {

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO share_it.message(text, time, member_from, member_to) VALUES (?, ?, ?, ?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, message.getText());
      Date currentTime = Calendar.getInstance().getTime();
      Timestamp ts = new Timestamp(currentTime.getTime());

      statement.setTimestamp(2, ts);
      statement.setInt(3, message.getMemberFrom());
      statement.setInt(4, message.getMemberTo());
      statement.executeUpdate();

      Message returnMessage = new Message(ts,
          MemberDAOImpl.getInstance().getMemberById(message.getMemberFrom())
              .getUsername(), message.getText());
      returnMessage.setMemberFrom(message.getMemberFrom());
      returnMessage.setMemberTo(message.getMemberTo());
      return returnMessage;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * Gets all the Messages connected to the Member that sent it and LoggedIn Member that received it by connecting to the database and get all table contents that are matched with given data
   * @param fromUserid Member that sent a message
   * @param loggedUserId Member that is geting messaged
   * @return a list of all messages that are matched with given data
   */
  public ArrayList<Message> getMessagesFromUser(int loggedUserId,
      int fromUserid)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT time, text, username FROM share_it.message, share_it.member WHERE ((member_from = ? AND member_to = ?)OR(member_from = ? AND member_to = ?))AND (member.id = message.member_from) ORDER BY time;");
      statement.setInt(1, loggedUserId);
      statement.setInt(2, fromUserid);
      statement.setInt(3, fromUserid);
      statement.setInt(4, loggedUserId);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Message> arrayListToReturn = new ArrayList<>();

      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        Date d2 = resultSet.getTimestamp("time");
        String username = resultSet.getString("username");

        arrayListToReturn.add(new Message(d2, username, text));
      }
      return arrayListToReturn;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}

