package listmatchs.match.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import listmatchs.match.config.MatchConfig;
import listmatchs.match.entities.Match;
import listmatchs.match.services.MatchService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/* Quelaues tests JUnit pour tester les methodes du projet DAO 
 * Cette classe n'est pas idéale. Ces tests devraient par exemple
 * être exécutés sur une BDD indépendante sinon les tests peuvent 
 * planter s'il y a deja des données en base. */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MatchConfig.class)
public class MatchTest {

  @Autowired
  private MatchService matchService;

  @Test
  public void test() {
    List<Match> matchs = new ArrayList<Match>();
    matchs.add(new Match(1L, "2015-01-23", "10:00", "team1", "team2"));
    matchs.add(new Match(2L, "2015-01-23", "11:00", "team3", "team4"));

    //Test de la methode save
    int res = matchService.saveMatchs(matchs);
    assertEquals(res, 2);

    //Test de la recuperation des matchs par date
    List<Match> matchsRes = matchService.getMatchsByDate("2015-01-23");
    assertEquals(matchs.toString(), matchsRes.toString());

    //Test du comptage des matchs par date
    Long nbMatchs = matchService.countByDate("2015-01-23");
    assertEquals(new Long(2L), nbMatchs);
    
    //Ajout d'un match pour le lendemain
    List<Match> matchsTomorrow = new ArrayList<Match>();
    matchsTomorrow.add(new Match(3L, "2015-01-24", "12:00", "team5", "team6"));
    res = matchService.saveMatchs(matchsTomorrow);
    assertEquals(res, 1);
    
    //Test de la recuperation des matchs d'aujourd'hui
    matchsRes = matchService.getMatchsByDate("2015-01-23");
    assertEquals(matchs.toString(), matchsRes.toString());
    
    //Test du comptage des matchs d'aujourd'hui
    nbMatchs = matchService.countByDate("2015-01-23");
    assertEquals(new Long(2L), nbMatchs);

    //Test suppression des matchs
    matchService.deleteMatchs(matchs);
    matchsRes = matchService.getMatchsByDate("2015-01-23");
    assertTrue(matchsRes.isEmpty());
    matchService.deleteMatchs(matchsTomorrow);
    matchsRes = matchService.getMatchsByDate("2015-01-24");
    assertTrue(matchsRes.isEmpty());
  }

}
