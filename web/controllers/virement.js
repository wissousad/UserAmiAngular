(function () {
    'use strict';

    angular
        .module('app')
        .controller('VirementController', VirementController);

    VirementController.$inject = ['$rootScope'];
    function VirementController($rootScope) {
        
        fillCurrentUserAccounts(); 
        
        function fillCurrentUserAccounts() {
            $rootScope.globals.currentUser;
            $rootScope.Ids = [];
            console.log('my user is ' + $rootScope.user);
            console.log('my accounts are ' + $rootScope.user.comptes);

            angular.forEach( $rootScope.user.comptes, function (compte) {
                console.log('value of id in Ids '+compte.id);
                $rootScope.Ids.push(compte.id);
            });
        }
    }
})();