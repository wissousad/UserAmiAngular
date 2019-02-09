(function () {
    'use strict';

    angular
            .module('app')
            .controller('VirementController', VirementController);

    VirementController.$inject = ['$rootScope', '$location', 'UserService', 'FlashService'];
    function VirementController($rootScope, $location, UserService, FlashService) {
        var vm = this;
        fillCurrentUserAccounts();
        vm.verifyForm = verifyForm;


        function fillCurrentUserAccounts() {
            $rootScope.globals.currentUser;
            $rootScope.Ids = [];
            console.log('my user is ' + $rootScope.user);
            console.log('my accounts are ' + $rootScope.user.comptes);

            angular.forEach($rootScope.user.comptes, function (compte) {
                console.log('value of id in Ids ' + compte.id);
                $rootScope.Ids.push(compte.id);
            });
        }

        function verifyForm() {
            if (vm.montant === null || vm.montant === "" || vm.idCompteReceiver === null || vm.idCompteReceiver === "" || vm.idCompteSender === null || vm.idCompteSender === "") {
                alert("Veuillez s'il vous plait remplir tous les champs.");
                return false;
            } else if (vm.montant.value > 3000) {
                alert("Champ mal rempli! Montant maximum par jour = 3000");
                return false;
            } else if (vm.idCompteSender === vm.idCompteReceiver) {
                alert("Champ mal rempli! Veuillez choisir des numéros de comptes différents");
                return false;
            } else if (vm.montant.value < 10) {
                alert("Champ mal rempli! Montant minimum = 10");
                return false;
            } else if (vm.idCompteReceiver.toString().length < 11) {
                alert("Champ mal rempli! Veuillez resaisir le numéro de compte destinataire");
                return false;
            }
            if (confirm("Êtes vous sûr(e) de bien vouloir virer " + vm.montant + " Euros au compte numéro " + vm.idCompteReceiver + " ?")) {
                console.log('Form is submitting');
                UserService.TransferMoney(vm.idCompteSender, vm.idCompteReceiver, vm.montant)
                        .then(function (reponse) {
                            if (reponse.message === 'SUCCESS') {
                              FlashService.Success('Montant envoyé', true);
                               console.log(' in success value of reponse.message= '+reponse.message);

                            } else if (reponse !== null) {
                               FlashService.Error(reponse.message);
                               console.log(' in not null value of reponse.message= '+reponse.message);
                            }else {
                                // penser à utilise la page d'erreur ??
                               FlashService.Error("Erreur de source inconnue, veuillez réessayer");
                            }
                        });
            } else {
                console.log('User clicked no.');
            }


            angular.forEach($rootScope.user.comptes, function (compte) {
                console.log('value of id in Ids ' + compte.id);
                $rootScope.Ids.push(compte.id);
            });
        }
    }



})();