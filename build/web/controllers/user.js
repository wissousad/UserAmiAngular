(function () {
    'use strict';

    angular
            .module('app')
            .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope', '$location', '$cookies'];
    function HomeController(UserService, $rootScope, $location, $cookies) {

        var vm = this;
        // partie particulier / professionnel
        loadAccounts();
        vm.loadTransactions = loadTransactions;

        // partie conseiller
        // fillClientsAccounts(); 
        console.log("typeClient in global is " + $rootScope.globals.currentUser.typeClient);
        if ($rootScope.globals.currentUser.typeClient === 'conseiller') {
            fillParticuliers();
            fillProfessionnels();
            changeHeader(1);
        } else {
            changeHeader(2);
        }

        // afficher comptes dans ng-repeat
        function loadAccounts() {
            $rootScope.comptes = [];
            console.log('my user is ' + $rootScope.user);
            angular.forEach($rootScope.user.comptes, function (compte) {
                $rootScope.comptes.push(compte);
            });
        }
        // charger les transactions d'un compte
        function loadTransactions(idCompte) {

            console.log('value of vm.idCompte ' + idCompte);
            UserService.GetTransactions(idCompte)
                    .then(function (transactions) {
                        if (transactions !== null) {
                            $rootScope.temp = transactions;
                            // remplir les transaction dans ng-repeat
                            $location.path('/transactions');
                        } else {
                            console.log('no transactions found');   // à changer après afficher une page avec message
                        }
                    });
        }


//        function fillClientsAccounts() {
//            $rootScope.comptes = [];
//
//            console.log('accounts are ' + $rootScope.comptes);
//            angular.forEach($rootScope.comptes.comptes, function (compte) {
//                $rootScope.comptes.push(compte);
//                console.log('foreach');
//                console.log('value of transaction' + compte);
//
//            });
//        }

        function fillParticuliers() {
            $rootScope.particuliers = [];

            console.log('particuliers are ' + $rootScope.user.particuliers);
            angular.forEach($rootScope.user.particuliers, function (particulier) {
                $rootScope.particuliers.push(particulier);
                console.log('foreach');
                console.log('value of transaction' + particulier);

            });
        }

        function fillProfessionnels() {
            $rootScope.professionnels = [];

            console.log('professionnels are ' + $rootScope.user.professionnels);
            angular.forEach($rootScope.user.professionnels, function (professionnel) {
                $rootScope.professionnels.push(professionnel);
                console.log('foreach');
                console.log('value of transaction' + professionnel);

            });
        }

        function changeHeader(user) {
            if (user === 1)                 /* conseiller */ {
                document.getElementById("li1").innerHTML = " <a class=' active' href='#!/login'>Accueil</a>";
                document.getElementById("li2").innerHTML = "<a class=' active' href='#!/disconnect'>Se déconnexter</a>";
                document.getElementById("li3").innerHTML = "";
                document.getElementById("li4").innerHTML = "";
            } else {
                document.getElementById("li1").innerHTML = " <a class=' active' href='#!/login'>Accueil</a>";
                document.getElementById("li2").innerHTML = "<a class=' active' href='#!/virement'>Virement</a>";
                document.getElementById("li3").innerHTML = "<a class=' active' href='#!/disconnect'>Se déconnexter</a>";
                document.getElementById("li4").innerHTML = "";
            }
        }
    }

})();
