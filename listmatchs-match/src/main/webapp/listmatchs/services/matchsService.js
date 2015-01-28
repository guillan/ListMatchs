'use strict';

angular.module('listmatchs')

.factory('matchsService',
  ['$http', '$rootScope', function ($http, $rootScope) {
    
    /* Fonction permettant de récupérer les informations
     * à partir du webservice Rest */
    function getData(serverUrl, urlAction, callback) {

      var url = serverUrl + urlAction;
      
      //Construction de l'entete d'authentification à partir du cookie auth
      $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.auth.currentUser.authdata;
      
      // appel de l'url du webservice Rest en get
      $http.get(url)
        // Si OK (http code 200) on retourne la valeur du webservice
        .success(function (response) {
          response.res = response.status == 0 ? {err: 0, data: response.data} : {err: 1, message: response.data};
          callback(response);
        })
        // Si KO (http code dans les 4OO) on retour l'erreur retourné
        .error(function (response) {
          response.res = {err: 2, message: response.message}
          callback(response);
        });
    }
    
    /* Fonction permettant de construire les dates de la liste déroulante
     * sous la forme yyyy-mm-dd.
     * Le paramètre est le nombre d'élément de la liste déroulante défini
     * dans le service config */
    function getArrayDate(nb) {
      //Création d'un Array
      var arr = [];
      
      // Ajout dans le tableau d'une date sous la forme yyyy-mm-dd
      for (var i = 0; i < nb; i++) {
        var today = new Date();
        var d = new Date(today.setDate(today.getDate()+i));
        arr.push({
          name: d.toISOString().substr(0,10)
        });
      }
      return arr;
    }

    return {
      getData: getData,
      getArrayDate: getArrayDate
    };
  }]
)