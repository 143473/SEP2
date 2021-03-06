package shared.transferobjects;

import java.io.Serializable;
/**
 * A class that handles Ratings information.
 */
public class Rating implements Serializable
{
  private double rating;
  private final String commentary;
  private final int memberFrom;
  private final int memberTo;
  /**
   * Constructor initializing fields.
   *
   * @param rating     Rating that was chosen while rating a member.
   * @param commentary Commentary that was added while rating a member.
   * @param memberFrom Member's ID that rated another member.
   * @param memberTo   Member's ID that was rated.
   */
  public Rating(double rating, String commentary, int memberFrom, int memberTo)
  {
    this.rating = rating;
    this.commentary = commentary;
    this.memberFrom = memberFrom;
    this.memberTo = memberTo;
  }
    /**
     * Gets rating that Member gave.
     *
     * @return double type of rating.
     */
  public double getRating()
  {
    return rating;
  }
    /**
     * Sets rating that Member gave.
     *
     * @param rating double type of rating.
     */
  public void setRating(double rating)
  {
    this.rating = rating;
  }
    /**
     * Gets commentary that Member gave.
     *
     * @return String of commentary.
     */
  public String getCommentary()
  {
    return commentary;
  }
    /**
     * Gets member that was rating.
     *
     * @return int type of Member's ID that rated other member.
     */
  public int getMemberFrom()
  {
    return memberFrom;
  }
    /**
     * Gets member that was rated.
     *
     * @return int type of Member's ID that was rated.
     */
  public int getMemberTo()
  {
    return memberTo;
  }
}
