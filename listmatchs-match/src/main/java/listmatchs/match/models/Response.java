package listmatchs.match.models;

/* Classe permettant de formater un objet JSON de réponse
 * La variable status est un entier qui gère le status de la requete.
 * Elle est égale a 0 quand il n'y a pas d'erreur et != 0 en cas d'erreur.
 * La variable Data correspond a l'objet de réponse contenant toutes les données 
 * de réponse.
 * J'aime particulièrement représenter les réponses de cette manière car ça 
 * facilite la gestion des erreurs en AngularJS */
public class Response {

  private int status;
  private Object data;

  public Response() {
  }

  public Response(int status, Object data) {
    this.status = status;
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}