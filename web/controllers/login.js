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
        changeHeader();

        function initController() {
            if ($rootScope.user) {
                var nom = $rootScope.user.nom;
                console.log('nom ' + nom);
                console.log('i work');
                document.getElementById("formDiv").innerHTML = "<h2>Bienvenue " + nom + " </h2>";
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
        function changeHeader() {
            if ($rootScope.typeClient)  /* connecté */ {
                console.log('type '+ $rootScope.typeClient);
                if ($rootScope.typeClient !== 'conseiller') {
                    document.getElementById("li1").innerHTML = " <a class=' active' href='#!/login'>Accueil</a>";
                    document.getElementById("li2").innerHTML = "<a class=' active' href='#!/virement'>Virement</a>";
                    document.getElementById("li3").innerHTML = "<a class=' active' href='#!/client'>Mes comptes</a>";
                    document.getElementById("li4").innerHTML = "<a class=' active' href='#!/disconnect'>Se déconnecter</a>";
                } else {
                    document.getElementById("li1").innerHTML = " <a class=' active' href='#!/login'>Accueil</a>";
                    document.getElementById("li2").innerHTML = "<a class=' active' href='#!/conseiller'>Clients et comptes</a>";
                    document.getElementById("li3").innerHTML = "";
                    document.getElementById("li4").innerHTML = "";
                }

            } else {
                document.getElementById("li1").innerHTML = "";
                document.getElementById("li2").innerHTML = "";
                document.getElementById("li3").innerHTML = "";
                document.getElementById("li4").innerHTML = "";
            }
        }
    }

})();


