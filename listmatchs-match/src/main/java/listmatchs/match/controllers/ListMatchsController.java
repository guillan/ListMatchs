package listmatchs.match.controllers;

import listmatchs.match.models.Response;
import listmatchs.match.services.MatchService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/* Controller de l'application
 * Le controleur répond uniquement avec une URL de la forme matchs/date
 * La format de la date est yyyy-mm-dd. Exemple 2015-01-17
 * C'est le controlleur qui gère l'ensemble des Exceptions que l'on peut rencontrer.
 * L'objet retourné est de type Response (voir commentaire dans la classe
 * pour plus d'information). En cas d'exception, on peut voir que le status est 
 * différent de 0. */

@RestController
public class ListMatchsController {

  private static final Logger logger = Logger.getLogger(ListMatchsController.class);

  @Autowired
  private MatchService matchService;

  @RequestMapping(value = "/matchs/{date}", method = RequestMethod.GET)
  public Response getMatchsByDate(@PathVariable("date") String date) {

    try {
      matchService.saveMatchs(date);
      return new Response(0, matchService.getMatchsByDate(date));
    } catch (Exception e) {
      logger.error("[getMatchsByDate]", e);
      return new Response(1, "Error during getting matchs");
    }
  }

}
