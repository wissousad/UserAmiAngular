(function () {
    'use strict';

    angular
        .module('app')
        .controller('ConseillerController', ConseillerController);

    ConseillerController.$inject = ['$rootScope'];
    function ConseillerController($rootScope) {
        
        fillClientsAccounts(); 
        fillParticuliers();
        fillProfessionnels();
        
        function fillClientsAccounts() {
           $rootScope.comptes = [];
           
            console.log('accounts are ' + $rootScope.comptes);
            angular.forEach($rootScope.comptes.comptes, function (compte) {
                $rootScope.comptes.push(compte);
                console.log('foreach');
                console.log('value of transaction' +compte);
                
            });
        }
        
        function fillParticuliers() {
           $rootScope.particuliers = [];
           
            console.log('particuliers are ' + $rootScope.particuliers);
            angular.forEach($rootScope.particuliers.particuliers, function (particulier) {
                $rootScope.particuliers.push(particulier);
                console.log('foreach');
                console.log('value of transaction' +particulier);
                
            });
        }
        
        function fillProfessionnels() {
           $rootScope.particuliers = [];
           
            console.log('professionnels are ' + $rootScope.professionnels);
            angular.forEach($rootScope.professionnels.professionnels, function (professionnel) {
                $rootScope.professionnels.push(professionnel);
                console.log('foreach');
                console.log('value of transaction' +professionnel);
                
            });
        }
    }

})();
