var app = angular.module('catalogueRouter', ['ngRoute', 'catalogue']);

app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){

    $locationProvider.hashPrefix('');

    $routeProvider.when('/', {
        templateUrl: 'views/app.html',
        controller:'BaseController'
    }).
    otherwise({
        redirectTo:'/'
    });

}]);