(function () {
    'use strict';
 
    angular
        .module('app')
        .controller('LoginController', LoginController);
 
    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;
 
        vm.login = login;
 
        initController();
        
        function initController() {
            AuthenticationService.ClearCredentials();
        }
 
        function login() {
            AuthenticationService.Login(vm.numero_compte,vm.password, vm.typeClient, function (response) {
                if (response.success) {
                    AuthenticationService.SetCredentials(vm.numero_compte, vm.password);
                  //  AuthenticationService.SetCredentials(vm.numero_compte,vm.password ,vm.typeClient);
                    FlashService.Success('Utilisateur connecté', true);
                    $location.path('/client');
                } else {
                    FlashService.Error(response.message);
                }
            });
        };
    }
 
})();


