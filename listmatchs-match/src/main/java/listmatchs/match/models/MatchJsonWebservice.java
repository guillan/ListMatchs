package listmatchs.match.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Représente l'objet que l'on récupère du WS externe.
 * L'annotation @JsonIgnoreProperties(ignoreUnknown = true) permet d'ignorer
 * les balises Json non définies dans cette classe.*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchJsonWebservice {

  private String id;
  private String date;
  private String homeTeam;
  private String awayTeam;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(String homeTeam) {
    this.homeTeam = homeTeam;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(String awayTeam) {
    this.awayTeam = awayTeam;
  }

}
