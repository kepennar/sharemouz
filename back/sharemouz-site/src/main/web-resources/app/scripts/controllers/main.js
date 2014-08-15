'use strict';

/**
 * @ngdoc function
 * @name sharemouz.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sharemouz
 */
angular.module('sharemouz')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
