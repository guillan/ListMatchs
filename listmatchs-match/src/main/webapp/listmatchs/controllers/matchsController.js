'use strict';

angular.module('listmatchs')

.controller('matchsController',
    ['$scope', 'matchsService', 'config',
    function ($scope, matchsService, config) {
    	
    	// Construction de la liste déroulante des dates
    	$scope.dateList = matchsService.getArrayDate(config.nb_date);
    	// La valeur par défault de la liste déroulante est la 1ere (la date du jour)
        $scope.selectedDate = $scope.dateList[0];
    	
        //Fonction permettant l'appel du webservice Rest pour une date donnée
        $scope.execute = function (urlDate) {
        	
        	//Si urlDate n'est pas renseigné, on initialise la date à la date du jour
            if (urlDate == undefined) {
            	var today = new Date();
                urlDate = today.toISOString().substr(0,10)
            }
            
            // Appel de la fonction getData pour récupérer les données
            matchsService.getData(config.url, urlDate, function (response) {
            	//Si on arrive à récupérer les données
            	if (response.res.err == 0) {
            		$scope.show = true;
                    $scope.matchs = response.res.data;
                // Sinon
                } else {
                    $scope.error = response.res.message;
                }
            });
        }
        
        /* Fonction appeler à chaque changement de la liste déroulante
         * Elle appele la fonction execute avec la valeur de la liste déroulante
         */
        $scope.update = function() {
            $scope.execute($scope.selectedDate.name);
        }

    }]);