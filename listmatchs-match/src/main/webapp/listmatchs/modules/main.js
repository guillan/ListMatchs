'use strict';

// Les dépendances utilisées par le module sont définies ici
angular.module('listmatchs', [
    'ngRoute',
    'ngCookies',
    'base64'
])

// Définition de la route de l'application
.config(['$routeProvider', function ($routeProvider) {

  $routeProvider
    /* Quand l'url login est appelé le controlleur loginController
     * et la vue login.html sont utilisés
     */
    .when('/login', {
      controller: 'loginController',
      templateUrl: 'views/login.html',
    })
        
    /* Quand l'url / est appelé le controlleur matchsController
     * et la vue matchs.html sont utilisés
     */
    .when('/', {
      controller: 'matchsController',
      templateUrl: 'views/matchs.html'
    })

    // Si une autre adresse est appelé on redirige vers /
    .otherwise({ redirectTo: '/' });
}])

.run(['$rootScope', '$location', '$cookieStore', '$http',
  function ($rootScope, $location, $cookieStore, $http) {
    // Cette partie permet de garder l'utilisateur logger quand on recharge la page
    $rootScope.auth = $cookieStore.get('auth') || {};
    if ($rootScope.auth.currentUser) {
      $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.auth.currentUser.authdata;
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
      /* Cette partie permet de rediger l'utilisateur vers la page 
       * de login quand il n'est pas logué
       */
      if ($location.path() !== '/login' && !$rootScope.auth.currentUser) {
        $location.path('/login');
      }
    });
  }]);