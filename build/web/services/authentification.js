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
 
        function Login(numero_compte, password, callback) {
                var response;
                UserService.GetByName(numero_compte, password)
                    .then(function (user) {
                        if (user !== null) {
                            if (user.password === password) {
                                response = { success: true };
                            }
                            else {
                                response = { success: false, message: 'Erreur login / Mot de passe' };
                                console.log('user.password is '+ user.password);
                                console.log('password in param is:' +password);
                                console.log('num_compte is: '+user.numero_compte);
                                console.log('username is: '+user.username);
                            }
                        }
                        else {
                            response = { success: false, message: 'Compte inexistant' };
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