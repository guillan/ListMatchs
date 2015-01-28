'use strict';

angular.module('listmatchs')

.controller('loginController',
  ['$scope', '$rootScope', '$location', 'authenticationService', 'matchsService', 'config', 
  function ($scope, $rootScope, $location, authenticationService, matchsService, config) {
        
    /* 1ere chose que l'on fait quand on arrive sur la page de login
     * c'est de se déconnecter (suppression du cookie + headers authentification
     *  par defaut)
     */
    authenticationService.clearCredentials();

    // Quand on clique sur le bouton connexion, cette fonction est appelé
    $scope.login = function () {
          
      //Désactive le bouton connexion
      $scope.dataLoading = true;
            
      // Définie la date du jour
      var today = new Date();
      // Sous la forme yyyy-mm-dd
      var urlDate = today.toISOString().substr(0,10)
            
      //Call de la méthode setCredentials qui crée un cookie avec les infos d'authentification
      authenticationService.setCredentials($scope.username, $scope.password);
            
      //Call de la méthode getData qui appelle le service Rest
      matchsService.getData(config.url, urlDate, function (response) {
        // Si la réponse est OK (ie http code 200)
        if (response.res.err == 0 || response.res.err == 1) {
          //On redirige vers l'url /
          $location.path('/');
        // Sinon
        } else {
          // Call de la methode clearCredentials qui supprime le cookie d'authentification
          // et reset l'entete d'authentification basic.
          authenticationService.clearCredentials();
          // Le message d'erreur affiché a l'écran prend la valeur de l'erreur
          $scope.error = response.res.message;
          // Le bouton de déconnexion est de nouveau activé
          $scope.dataLoading = false;
        }
      });
    };
  }]);