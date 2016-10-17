var app = angular.module('app', [
    'ngRoute',
    'ngResource',
    'ngSanitize'
]);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/static/views/accounts.html'
        })
        .when('/account/:id', {
            templateUrl: '/static/views/account.html',
            controller: 'AccountController'
        })
        .otherwise(
            {redirectTo: '/'}
        );
}).filter('comma2dot', function () {
    return function (input) {
        return input ? input.replace(/,/g, '.') : input;
    };
});

