(function () {
    'use strict';

    angular
            .module('app')
            .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService', '$rootScope'];

    function LoginController($location, AuthenticationService, FlashService, $rootScope) {
        var vm = this;

        vm.login = login;

        initController();

        function initController() {
            if (!$rootScope.user ==='undefined') {
                var nom = $rootScope.user.nom;
                console.log('nom '+nom);
                console.log('i work');
                document.getElementById("thisDiv").innerHTML = "<h2>Bienvenue "+ nom+" </h2>";
            }
            console.log('im called');

        }

        function login() {
            AuthenticationService.Login(vm.numero_compte, vm.password, vm.typeClient, function (response) {
                if (response.success) {
//                    AuthenticationService.SetCredentials(vm.numero_compte, vm.password);
                    AuthenticationService.SetCredentials(vm.numero_compte, vm.password, vm.typeClient);
                    FlashService.Success('Utilisateur connecté', true);
                    if (vm.typeClient !== "conseiller") {
                        $location.path('/client');
                    } else {
                        $location.path('/conseiller');
                    }
                } else {
                    FlashService.Error(response.message);
                }
            });
        }
        ;
    }

})();


