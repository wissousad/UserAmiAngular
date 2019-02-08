(function () {
    'use strict';

    angular
        .module('app')
        .controller('TransactionsController', TransactionsController);

    TransactionsController.$inject = ['$rootScope'];
    function TransactionsController($rootScope) {
        
        fillTransactions(); 
        
        function fillTransactions() {
           $rootScope.transactions = [];
            console.log('my transactions are ' + $rootScope.temp);
            angular.forEach($rootScope.temp.transactions, function (transaction) {
                $rootScope.transactions.push(transaction);
                console.log('foreach');
                console.log('value of transaction' +transaction);
                
            });
        }
    }

})();
