package listmatchs.match.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import listmatchs.match.entities.Match;
import listmatchs.match.models.MatchJsonWebservice;
import listmatchs.match.repositories.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

@Service("MatchService")
public class MatchServiceImpl implements MatchService {

  @Autowired
  private MatchRepository matchRepository;

  @Override
  public List<Match> getMatchsByDate(String date) {
    return Lists.newArrayList(matchRepository.findByDate(date));
  }

  @Override
  public Long countByDate(String date) {
    return matchRepository.countByDate(date);
  }

  @Override
  public int saveMatchs(List<Match> lm) {
    List<Match> res = Lists.newArrayList(matchRepository.save(lm));
    return res.size();
  }
  
  @Override
  public int saveMatchs(String date) {
	    
    /* On compte le nombre de match spécifié par la Date
     * deja present dans la BDD. Si des matchs sont deja présents
     * on ne fait rien. Cela a pour but de ne pas appeler le Webservice externe
     * a chaque appel serveur et aussi gagner en performance.
     */
    if (countByDate(date) > 0) {
      return 0;
    }
    
    List<Match> lm = new ArrayList<Match>();
    RestTemplate restTemplate = new RestTemplate();
    
    /* A la place de mettre l'URL du webservice externe en dur, le mieux est de cree une table
     * de configuration dans la BDD qui va stocker cette adresse. Cela permet en cas 
     * de changement de l'url du webservice de la modifier sans redemarrer le serveur */
    List<MatchJsonWebservice> matchsFromWs = Arrays.asList(restTemplate.getForObject("http://www.football-data.org/fixtures", MatchJsonWebservice[].class));
    for (MatchJsonWebservice matchFromWs : matchsFromWs) {
      lm.add(new Match(Long.parseLong(matchFromWs.getId()),
    		 matchFromWs.getDate().substring(0, 10),
    		 matchFromWs.getDate().substring(11, 16),
             matchFromWs.getHomeTeam(), matchFromWs.getAwayTeam()));
    }
    return saveMatchs(lm);
  }

  @Override
  public void deleteMatchs(List<Match> lm) {
    matchRepository.delete(lm);
  }

}
