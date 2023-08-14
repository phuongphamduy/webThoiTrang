const url = "http://localhost:8080/rest/orderDetails";
angular.module("myapp", []).controller("orderDetails-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.form = {};

    $scope.initialize = function () {
        // load order
        $http.get(url)
            .then(resp => {
                $scope.items = resp.data;
            })
    }

    //khoi dau
    $scope.initialize();


    $scope.edit = (item) => {
        $scope.form = angular.copy(item);
    }




})