(function () {
    'use strict';

    angular
            .module('app')
            .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$cookies', '$rootScope', 'UserService'];
    function AuthenticationService($cookies, $rootScope, UserService) {
        var service = {};

        service.Login = Login;
        service.SetCredentials = SetCredentials;
        service.ClearCredentials = ClearCredentials;

        return service;

        function Login(numero_compte, password, typeClient, callback) {
            var response;
            UserService.GetByName(numero_compte, typeClient)
                    .then(function (user) {
                        if (user !== null) {
                            if (user.password === password) {
                                response = {success: true};
                                console.log('user is ' + user);
                                console.log('user accounts are ' + user.comptes);
                                $rootScope.user=user;
                            } else {
                                response = {success: false, message: 'Erreur login / Mot de passe'};
                                console.log('user.password ' + user.password);
                                console.log('password ' + password);
                            }
                        } else {
                            response = {success: false, message: 'Compte inexistant'};
                        }
                        callback(response);
                    });
        }

        function SetCredentials(numero_compte, password) {
            $rootScope.globals = {
                currentUser: {
                    numero_compte: numero_compte,
                    password: password
                }
            };
            console.log('currentUser: numero_compte:' + $rootScope.globals.currentUser.numero_compte + 'pass:' + $rootScope.globals.currentUser.password
                    );
            $cookies.putObject('globals', $rootScope.globals);
        }

        function ClearCredentials() {
            $cookies.remove('globals');
        }
    }

})();