angular.module('demo', [])
.controller('HelloJs', function($scope, $http) {
    $http.get(document.location.href + 'greeting').
        then(function(response) {
            $scope.greeting = response.data;
        });
});