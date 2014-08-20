'use strict';

angular.module('aanwezigheidsbord')
        .service('MainService', ['Restangular', function (Restangular) {
            var baseUrl = 'http://10.0.0.1';
            // var aanwezigen = Restangular.all(baseUrl + '/user');
            var aanwezigen = ['Daan', 'Frederik', 'Adolfo'];

            function deleteAanwezige(name) {
                // Route: /user/<name> DELETE: Returns Status 200/OK + Optionally a message when the user was already gone
                var currentAanwezige = aanwezigen[name];
                currentAanwezige.remove().then(function(response) {
                    console.log('Object removed OK');
                    return response;
                }, function() {
                    console.log('There was an error deleting');
                });
            }

            function createAanwezige(aanwezige) {

                var newAanwezige = [aanwezige.naam];

                // POST /accounts
                aanwezigen.post(newAanwezige).then(function(response) {
                    console.log('Object createrd OK');
                    return response;
                }, function() {
                    console.log('There was an error creating');
                });
            }

            function getAanwezigen(){
                 return aanwezigen;
            }

            return {
                deleteAanwezige : deleteAanwezige,
                createAanwezige: createAanwezige,
                getAanwezigen: getAanwezigen
            };

        }]);
            /*
            Route: /user POST: With body {"name:"..."}: Returns Status 201/CREATED + Optionally a message when the user is already there

            Route: /user/<name> DELETE: Returns Status 200/OK + Optionally a message when the user was already gone

            Route: /user GET: With nothing: Returns Status 200/OK [names]

            All methods return JSON.
            */


