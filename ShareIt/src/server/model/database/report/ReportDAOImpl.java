package server.model.database.report;

import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Rating;
import shared.transferobjects.Report;

import java.sql.*;

public class ReportDAOImpl implements ReportDAO
{
  private static ReportDAOImpl instance;
  private String password;

  private ReportDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized ReportDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new ReportDAOImpl();
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
  @Override public Report create(String feedback, String username1,
      String username2) throws SQLException
  {
    try(Connection connection = getConnection()){

      int memberId1 = MemberDAOImpl.getInstance().readIdByUsername(username1);
      int memberId2 = MemberDAOImpl.getInstance().readIdByUsername(username2);

      System.out.println("member from: "+username1+" member to: "+username2);


      PreparedStatement statement = connection.prepareStatement("INSERT INTO share_it.report(commentary,member_from, member_to) VALUES (?, ?, ?);");
      statement.setString(1, feedback);
      statement.setInt(2,memberId1);
      statement.setInt(3,memberId2);

      System.out.println(statement);
      statement.executeUpdate();

      return new Report(feedback,memberId1,memberId2);
    }
  }

  @Override public Report getReport(String fromUsername, String toUsername)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {

      int fromId = MemberDAOImpl.getInstance().readIdByUsername(fromUsername);
      int toId = MemberDAOImpl.getInstance().readIdByUsername(toUsername);
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM share_it.report WHERE member_from = ? AND member_to = ?  ");
      statement.setInt(1, fromId);
      statement.setInt(2, toId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next())
      {
        return new Report(resultSet.getString("commentary"),
            fromId, toId);
      }
      return null;
    }
  }

  @Override public void updateReport(Report report)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE share_it.report SET commentary = ? WHERE member_from = ? AND member_to = ?");
      statement.setString(1, report.getCommentary());
      statement.setInt(2,report.getMemberFrom());
      statement.setInt(3, report.getMemberTo());
      statement.executeQuery();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}