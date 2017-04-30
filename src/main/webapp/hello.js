angular.module('demo', [])
.controller('HelloJs', function($scope, $http) {
    $http.get('http://127.0.0.1:9996/pik/greeting').
        then(function(response) {
            $scope.greeting = response.data;
        });
});