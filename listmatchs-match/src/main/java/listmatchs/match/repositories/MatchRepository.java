package listmatchs.match.repositories;

import java.util.List;

import listmatchs.match.entities.Match;

import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
  
  List<Match> findByDate(String date);
  
  Long countByDate(String date);
}
