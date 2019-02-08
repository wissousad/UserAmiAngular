(function () {
    'use strict';

    angular
            .module('app', ['ngRoute', 'ngCookies'])
            .config(config)
            .run(run);

    config.$inject = ['$routeProvider'];
    function config($routeProvider) {
        $routeProvider
                .when('/', {
                    controller: 'LoginController',
                    templateUrl: 'login.html',
                    controllerAs: 'vm'
                })

                .when('/login', {
                    controller: 'LoginController',
                    templateUrl: 'login.html',
                    controllerAs: 'vm'
                })

                .when('/client', {
                    controller: 'HomeController',
                    templateUrl: 'client.html',
                    controllerAs: 'vm'
                })
                .when('/register', {
                    controller: 'RegisterController',
                    templateUrl: 'register.html',
                    controllerAs: 'vm'
                })
                .when('/transactions', {
                    controller: 'TransactionsController',
                    templateUrl: 'transactions.html',
                    controllerAs: 'vm'
                })
                .when('/virement', {
                    controller: 'VirementController',
                    templateUrl: 'virement.html',
                    controllerAs: 'vm'
                })
                .otherwise({redirectTo: '/login'});
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];

    function run($rootScope, $location, $cookies, $http) {

        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
//            if (restrictedPage && !loggedIn) {
//                $location.path('/login');
//            }
        });
    }

})();