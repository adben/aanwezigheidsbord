'use strict';

angular.module('aanwezigheidsbord')
        .service('MainService', [function (Restangular) {


            var baseUrl = "http://www.iprofs.nl";
            var aanwezigen = Restangular.all(baseUrl + '/user');

            function deleteAanwezige(name) {
                // Route: /user/<name> DELETE: Returns Status 200/OK + Optionally a message when the user was already gone
                currentAanwezige = aanwezigen[name];
                currentAanwezige.remove().then(function(response) {
                    console.log("deleted aanwezige");
                    return response;
                }, function(){
                    console.log("there was an error deleting the aanwezig");
                })
            }

            function createAanwezige(aanwezige) {
                var newAanwezige = {naam: aanwezige.naam};
                // POST /accounts
                aanwezigen.post(newAanwezige).then(function(response) {
                    console.log("Saved the newly created aanwezige");
                    return response;
                }, function() {
                    console.log("There was an error saving the new aanwezige.");
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


