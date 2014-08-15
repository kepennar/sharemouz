'use strict';

/**
 * @ngdoc function
 * @name sharemouz.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the sharemouz
 */
angular.module('sharemouz')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
