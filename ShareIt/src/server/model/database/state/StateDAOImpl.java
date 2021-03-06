package server.model.database.state;

import shared.transferobjects.State;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements methods from its interface and provides access to a database(State in this case)
 */
public class StateDAOImpl
{
  private static StateDAOImpl instance;
  private String password;

  private StateDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized StateDAOImpl getInstance()
  {
    if (instance == null)
    {
      try
      {
        instance = new StateDAOImpl();
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
   * Reads all states from database by connecting to the database and get all table contents
   *
   * @return all state names in a arraylist
   */
  public List<State> readState()
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.state");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<State> arrayListToReturn = new ArrayList<>();
      while (resultSet.next())
      {
        arrayListToReturn.add(new State(resultSet.getString("name")));
      }
      return arrayListToReturn;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}
