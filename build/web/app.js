(function () {
    'use strict';

    angular
            .module('app', ['ngRoute', 'ngCookies', 'ngIdle'])
            .config(config)
            .run(run);

    config.$inject = ['$routeProvider', 'KeepaliveProvider', 'IdleProvider'];
    function config($routeProvider, KeepaliveProvider, IdleProvider) {
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
                .when('/conseiller', {
                    controller: 'HomeController',
                    templateUrl: 'conseiller.html',
                    controllerAs: 'vm'
                })
                .when('/disconnect', {
                    controller: 'DisconnectController',
                    templateUrl: 'login.html',
                    controllerAs: 'vm'
                })
                .otherwise({redirectTo: '/login'});

        // gérer session
        IdleProvider.idle(3600); 
        IdleProvider.timeout(3600);
        KeepaliveProvider.interval(90); 
//        KeepaliveProvider.http('/api/heartbeat'); // URL that makes sure session is alive
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http', 'Idle'];

    function run($rootScope, $location, $cookies, $http, Idle) {

        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });

        // gérer session
        if ($rootScope.typeClient) /* s'il est connecté lancer  watch de session */ {
            Idle.watch();
        }

        $rootScope.$on('IdleStart', function () {
            if ($rootScope.typeClient) /* vérfier qu'il est connceté */ {
                var answer = confirm("Votre session sera désactivé dans 1 minute, Cliquez su oui pour la réactiver");
                if (answer === true) {
                    Idle.watch(); // poursuivre session
                } else {
                    $location.path('/disconnect'); // se déconnecter sinon
                }
            }
        });
        $rootScope.$on('IdleTimeout', function () {
            if ($rootScope.typeClient) {
                $location.path('/disconnect');
            }
        });
    }

})();