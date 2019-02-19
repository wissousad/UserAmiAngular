(function () {
    'use strict';

    angular
        .module('app')
        .controller('TransactionsController', TransactionsController);

    TransactionsController.$inject = ['$rootScope'];
    function TransactionsController($rootScope) {
        
        fillTransactions(); 
        changeHeader();
        
        function fillTransactions() {
           $rootScope.transactions = [];
            console.log('my transactions are ' + $rootScope.temp);
            angular.forEach($rootScope.temp.transactions, function (transaction) {
                $rootScope.transactions.push(transaction);
                console.log('foreach');
                console.log('value of transaction' +transaction);
                
            });
        }
         function changeHeader() {
                document.getElementById("li1").innerHTML = " <a class=' active' href='#!/login'>Accueil</a>";
                document.getElementById("li2").innerHTML = "<a class=' active' href='#!/client'>Mes comptes</a>";
                document.getElementById("li3").innerHTML = "<a class=' active' href='#!/disconnect'>Se déconnexter</a>";
                document.getElementById("li4").innerHTML = "";
            
        }
    }

})();
