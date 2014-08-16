'use strict';

/**
 * @ngdoc function
 * @name sharemouz.controller:HomeProfilCtrl
 * @description
 * # HomeProfilCtrl
 * Controller of the sharemouz
 */
 var homeProfilCtrl= function ($scope, $http, $location, $modal) {
  $scope.profil = {};
  $http.get('/api/profils/current').then(function(response) {
    $scope.profil= response.data;
  });

  $scope.profilOffers = function() {
    var href = withRel($scope.profil.links, 'offers').href;
    $http.get(href).then(function(response) {
      $scope.items= response.data.content;
    });
  };
  $scope.addOffer= function() {
    var modalInstance = $modal.open({
      controller : 'AddOfferModalCtrl',
      resolve: {
        profil : function() {
          return $scope.profil;
        }
      },
      templateUrl: 'views/home/addOffer.html'
    })
    .result;
  };
  
  var withRel= function(linksList, rel) {
    return linksList.filter(function(l) {
      return l.rel === rel;
    })[0];
  };
};

 /**
 * @ngdoc function
 * @name sharemouz.controller:AddOfferModalCtrl
 * @description
 * # AddOfferModalCtrl
 * Controller of the sharemouz
 */
 var addOfferModalCtrl= function($scope, $http, $modalInstance, profil) {
  $scope.newOffer= {};
  $scope.ok = function() {
    console.log($scope.newOffer);
    $http.post('/api/offers/', $scope.newOffer).then(function(response) {
      $modalInstance.close(true);
    });
  };
  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
};

angular.module('sharemouz')
.controller('HomeProfilCtrl', ['$scope', '$http', '$location', '$modal', homeProfilCtrl])
.controller('AddOfferModalCtrl', ['$scope', '$http', '$modalInstance', 'profil', addOfferModalCtrl]);