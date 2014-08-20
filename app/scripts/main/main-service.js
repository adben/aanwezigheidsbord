'use strict';

angular.module('aanwezigheidsbord')
        .service('MainService', function (Restangular) {
            var baseUrl = "http://www.iprofs.nl";
            var aanwezigen = Restangular.all(baseUrl + '/user');

            function deleteAanwezige(id) {
                currentAanwezige = aanwezigen[id];
            }

            function createAanwezige(aanwezige) {

                var newAanwezige = {naam: aanwezige.naam};

                // POST /accounts
                aanwezigen.post(newAanwezige);
            }

            function getAanwezigen(){
                 return aanwezigen;
            }

            return {
                deleteAanwezige : deleteAanwezige,
                createAanwezige: createAanwezige,
                getAanwezigen: getAanwezigen
            };
            // This will query /accounts and return a promise.
            aanwezigen.getList().then(function(aanwezigen) {
                $scope.aanwezigen = aanwezigen;
            });
        });
            /*
            Route: /user POST: With body {"name:"..."}: Returns Status 201/CREATED + Optionally a message when the user is already there

            Route: /user/<name> DELETE: Returns Status 200/OK + Optionally a message when the user was already gone

            Route: /user GET: With nothing: Returns Status 200/OK [names]

            All methods return JSON.
            */


