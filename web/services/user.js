(function () {
    'use strict';

    angular
            .module('app')
            .factory('UserService', UserService);

    UserService.$inject = ['$http', '$q'];

    function UserService($http, $q) {

        var service = {};

        service.GetAllFriends = GetAllFriends;
        service.GetByName = GetByName;

        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        service.AddFriend = AddFriend;
        service.DeleteFriend = DeleteFriend;
        service.GetTransactions = GetTransactions;
        service.TransferMoney = TransferMoney;

        return service;

        function GetTransactions(idCompte) {
            console.log('get transactions');
            var deferred = $q.defer();
            $http({
                url: 'http://localhost:8089/banque2/getTransactions.htm',
                method: "POST",
                data: {
                    'idCompte': idCompte
                }
            }).then(
                    function (response) {
                        var transactions = response.data;  // liste de transaction en json
                        console.log('transactions:' + transactions);
                        deferred.resolve(transactions);
                    },
                    function (errResponse) {
                        console.error('Error while getting transactions : ' + errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }
        function TransferMoney(idCompteSender, idCompteReceiver, montant) {
            console.log('get transactions');
            var deferred = $q.defer();
            $http({
                url: 'http://localhost:8089/banque2/transfer.htm',
                method: "POST",
                data: {
                    'idCompteSender': idCompteSender, 
                    'idCompteReceiver' : idCompteReceiver,
                    'montant' : montant
                }
            }).then(
                    function (response) {
                        var reponse = response.data;  
                        console.log('message de reponse:' + reponse);
                        deferred.resolve(reponse);
                    },
                    function (errResponse) {
                        console.error('Error while getting response : ' + errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }
        function GetAllFriends(numero_compte) {
            console.log('get all friends');
            var deferred = $q.defer();
            $http({
                url: 'http://localhost:8089/banque2/getAllFriends.htm',
                method: "POST",
                data: {
                    'numero_compte': numero_compte
                }
            }).then(
                    function (response) {
                        var users = response.data;
                        console.log('users friend:' + users);
                        deferred.resolve(users);
                    },
                    function (errResponse) {
                        console.error('Error while getting User : ' + errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        function GetByName(numero_compte, typeClient) {
            console.log('get User 2');
            var deferred = $q.defer();
            $http({
                url: 'http://localhost:8089/banque2/getClient.htm',
                method: "POST",
                data: {
                    'numero_compte': numero_compte,
                    'typeClient': typeClient
                }
            }).then(
                    function (response) {
                        var user = response.data;
                        deferred.resolve(user);
                        console.log('good response');
                        console.log('data' + user);
                    },
                    function (errResponse) {
                        var user = null;
                        deferred.resolve(user);
                        console.log('bad response');
                        console.log('errResponse is ' + errResponse);

                    }
            );
            return deferred.promise;
        }

        function Create(user) {
            console.log('Creating User');
            var deferred = $q.defer();
            $http({
                url: 'http://localhost:8089/banque2/userCreate.htm',
                method: "POST",
                data: {
                    'numero_compte': user.numero_compte,
                    'pass': user.password,
                    'firstName': user.firstName,
                    'lastName': user.lastName
                }
            }).then(
                    function (response) {

                        deferred.resolve(response);
                    },
                    function (errResponse) {
                        console.error('Error while creating User : ' + errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        function Update(user) {
            console.log('Creating User');
            var deferred = $q.defer();
            $http({
                url: 'http://localhost:8089/banque2/userUpdate.htm',
                method: "PUT",
                data: {
                    'numero_compte': user.numero_compte,
                    'pass': user.password,
                    'firstName': user.firstName,
                    'lastName': user.lastName
                }
            }).then(
                    function (response) {
                        var user = response.data;
                        deferred.resolve(user);
                    },
                    function (errResponse) {
                        console.error('Error while creating User : ' + errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        function Delete(id)
        {
            console.log('Delete User');
            var deferred = $q.defer();
            $http({
                url: 'http://localhost:8089/banque2/userDelete.htm',
                method: "POST",
                data: {
                    'numero_compte': id
                }
            }).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while creating User : ' + errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        function AddFriend(id1, id2)
        {
            console.log('Add Friend');
            var deferred = $q.defer();
            $http({
                url: 'http://localhost:8089/banque2/addFriend.htm',
                method: "POST",
                data: {
                    'numero_compte': id1,
                    'loginFriend': id2
                }
            }).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while add Friend : ' + errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        //********
        // Todo
        //********
        function DeleteFriend(id1, id2)
        {
            console.log('Delete Friend');
            var deferred = $q.defer();
            $http({
                url: 'http://localhost:8089/banque2/addFriend.htm',
                method: "POST",
                data: {
                    'numero_compte': id1,
                    'loginFriend': id2
                }
            }).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while delete Friend : ' + errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }
    }

})();
 