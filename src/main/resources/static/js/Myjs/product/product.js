var app = angular.module('myProduct', []);
const url = "http://localhost:8080/rest/product"

app.controller('myCtrl', function($scope, $http) {

    $scope.product = [];

    $scope.initialize = function() {
        $http.get(url)
        .then(resp => {
            $scope.product = resp.data;
        })
        .catch(error => {
            console.log(error);
        })
    }

    $scope.initialize();

    
})