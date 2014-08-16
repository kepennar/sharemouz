'use strict';

/**
 * @ngdoc overview
 * @name sharemouz
 * @description
 * # sharemouz
 *
 * Main module of the application.
 */
var app= angular
  .module('sharemouz', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.router',
    'ui.bootstrap'
  ]);

app.config(function ($locationProvider, $routeProvider, $stateProvider) {
    $locationProvider.html5Mode(true);
    $routeProvider
      .otherwise({ redirectTo: '/site'});


    $stateProvider
      .state('home', {
        url: '/site',
        views: {
          'profilView': { templateUrl: 'views/home/profil.html', controller: 'HomeProfilCtrl'}
        }
      });
  });
  app.run(function run($http) {
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");
    $http.defaults.headers.post[header] = token;
  });