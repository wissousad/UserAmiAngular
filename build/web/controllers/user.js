(function () {
    'use strict';

    angular
            .module('app')
            .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope', '$location'];
    function HomeController(UserService, $rootScope, $location) {

        var vm = this;
        loadAccounts();
        vm.loadTransactions = loadTransactions;

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
                            $rootScope.temp=transactions;
                            // remplir les transaction dans ng-repeat
                            $location.path('/transactions');
                        } else {
                            console.log('no transactions found');   // à changer après afficher une page avec message
                        }
                    });
        }
    }

})();
