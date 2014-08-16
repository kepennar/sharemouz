'use strict';

/**
 * @ngdoc filter
 * @name sampleApp.filter:myFilter
 * @function
 * @description
 * # myFilter
 * Filter in the sampleApp.
 */
angular.module('sharemouz')
  .filter('myFilter', function () {
    return function (input) {
      return 'myFilter filter: ' + input;
    };
  });
