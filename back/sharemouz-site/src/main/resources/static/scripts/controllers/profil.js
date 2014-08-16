'use strict';

/**
 * @ngdoc function
 * @name sharemouz.controller:ProfilCtrl
 * @description
 * # ProfilCtrl
 * Controller of the sharemouz
 */
angular.module('sharemouz')
  .controller('ProfilCtrl', ['$scope', '$http', '$location', function ($scope, $http, $location) {
  	$scope.profil = {};
  	$http.get('/api/profils/current').then(function(response) {
  		$scope.profil= response.data;
  	});

  	$scope.profilOffers = function() {
  		var href = withRel($scope.profil.links, 'offers').href;
  		$location.path(href);
  	};
    
    var withRel= function(linksList, rel) {
    	return linksList.filter(function(l) {
    		return l.rel === rel;
    	})[0];
    };
  }]);