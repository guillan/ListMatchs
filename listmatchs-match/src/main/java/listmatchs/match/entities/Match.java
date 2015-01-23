package listmatchs.match.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Entité JPA représentant la classe T_MATCH

@Entity
@Table(name = "T_MATCH")
public class Match implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "MATCH_ID")
  private Long id;

  @Column(name = "MATCH_DATE")
  private String date;
  
  @Column(name = "MATCH_TIME")
  private String time;

  @Column(name = "MATCH_HOME_TEAM")
  private String homeTeam;

  @Column(name = "MATCH_AWAY_TEAM")
  private String awayTeam;

  public Match(){
  }

  public Match(Long id, String date, String time, String homeTeam, String awayTeam) {
    this.id = id;
    this.date = date;
    this.time = time;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
  }

  @Override
  public String toString() {
    return String.format("Match[%o, %s, %s, %s, %s]", id, date, time, homeTeam, awayTeam);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
  
  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
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
