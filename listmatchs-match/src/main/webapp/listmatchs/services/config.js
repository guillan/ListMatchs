/* Classe de configuration contenant pour l'instant l'url
 * du service Rest ainsi qu'une variable contenant le nombre
 * de date a affiché dans la liste déroulante
 */
angular.module('listmatchs')
  .factory('config', function () {
    return {
    	url: "http://localhost:8080/matchs/",
    	nb_date: "7"
    }
  });