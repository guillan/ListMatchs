'use strict';

angular.module('listmatchs')

.factory('authenticationService',
    ['$base64', '$http', '$cookieStore', '$rootScope',
    function ($base64, $http, $cookieStore, $rootScope) {
        var service = {};

        /* Cette fonction permet de creer un cookie contenant
         * l'utilisateur et la valeur en base64 du couple login/mdp */
        service.setCredentials = function (username, password) {
        	
        	//Appel de la fonction base64 qui encode le mot de passe
            var authdata = $base64.encode(username + ':' + password);
        	
            /* Création d'une variable dans rootScope qui stocke les infos
             * rootScope est le parent de scope il est utilisé dans tous les 
             * controlleur */
            $rootScope.auth = {
                currentUser: {
                    username: username,
                    authdata: authdata
                }
            };

            // On stocke la variable précédement crée dans un cookie appelé auth
            $cookieStore.put('auth', $rootScope.auth);
        };

        /* Cette fonction permet de supprimer le cookie auth
         * et également de changer l'entête pour supprimer la précédente
         */
        service.clearCredentials = function () {
            $rootScope.auth = {};
            $cookieStore.remove('auth');
            $http.defaults.headers.common.Authorization = 'Basic ';
        };

        return service;
    }]
)