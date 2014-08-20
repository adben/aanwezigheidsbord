'use strict';

angular.module('aanwezigheidsbord')
  .controller('MainCtrl', ['$scope', 'MainService', function ($scope, MainService) {
    
    $scope.aanwezigen = MainService.getAanwezigen();
  }]);
