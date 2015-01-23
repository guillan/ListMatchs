package listmatchs.match.boot;

import listmatchs.match.config.MatchConfig;

import org.springframework.boot.SpringApplication;

/* Permet de lancer l'application */
public class ListmatchsMatchApplication {

  public static void main(String[] args) {
	  
    SpringApplication.run(MatchConfig.class, args);
  
  }

}
