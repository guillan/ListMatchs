package listmatchs.match.services;

import java.util.List;

import listmatchs.match.entities.Match;

public interface MatchService {

  /**
   * Get all the matchs of a specific day
   * @param date
   * @return List of matchs
   */
  public List<Match> getMatchsByDate(String date);

  /**
   * Count the number of matchs of a specific day
   * @param date
   * @return Number of match
   */
  public Long countByDate(String date);

  /** 
   * Save a list of matchs
   * @param list of matchs
   * @return number of matchs saved
   */
  public int saveMatchs(List<Match> lm);
  
  /**
   * Save the matchs from the external webservice
   * @param date
   * @return number of matchs saved
   * @throws Exception
   */
  public int saveMatchs(String date) throws Exception;

  /**
   * Delete a list of matchs
   * @param list of matchs
   */
  public void deleteMatchs(List<Match> lm);
}
