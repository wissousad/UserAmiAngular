(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope', '$location', 'AuthenticationService', 'FlashService'];
    function HomeController(UserService, $rootScope, $location, AuthenticationService, FlashService) {
        
        var vm = this;

//        vm.user = null;
//        vm.allUsers = [];
//        vm.allFriends = [];
//       
//        vm.deleteUser = deleteUser;
//        vm.addFriend = addFriend;
//        vm.deleteFriend = deleteFriend;
//        
//        initController();

        function initController() {
            loadCurrentUser();
            loadAllUsers();
            loadAllFriends();
        }

        function loadCurrentUser() {
            UserService.GetByName($rootScope.globals.currentUser.username)
                .then(function (user) {
                    vm.user = user;
                });
        }

        function loadAllUsers() {
            UserService.GetAll($rootScope.globals.currentUser.username)
                .then(function (users) {
                    vm.allUsers = users;
                });
        }
        
        function loadAllFriends() {
            UserService.GetAllFriends($rootScope.globals.currentUser.username)
                .then(function (users) {
                    vm.allFriends = users;
                });
        }

        function deleteUser(id) {
          
            UserService.Delete(id)
            .then(function () {
                 AuthenticationService.ClearCredentials();
                 FlashService.Success('Compte supprimé', true);
                 $location.path('/login');
            });
        }
  
        function addFriend(id) {
             UserService.AddFriend($rootScope.globals.currentUser.username,id)
             .then(function () {
                loadAllFriends();
             });
         }
        
        //********
        // Todo
        //********
         function deleteFriend(id) {
             
            UserService.deleteFriend($rootScope.globals.currentUser.username,id)
             .then(function () {
                loadAllFriends();
             });
         }
    }

})();
