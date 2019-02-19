(function () {
    'use strict';
    angular
            .module('app')
            .controller('DisconnectController', DisconnectController);

    DisconnectController.$inject = ['AuthenticationService', '$location'];
    function DisconnectController(AuthenticationService, $location) {

        deconnecter();

        function deconnecter() {
            AuthenticationService.ClearCredentials();
            $location.path('/login');
        }
    }

})();
