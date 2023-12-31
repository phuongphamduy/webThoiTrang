const url = "http://localhost:8080/rest/orders";
var app = angular.module("myapp", ["ngRoute"]);
app.controller("order-ctrl", function ($scope, $http) {
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

    $scope.update = () => {
        var item = angular.copy($scope.form);
        $http.put(`http://localhost:8080/rest/orders/${item.id}`, item)
            .then(resp => {
                var index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items[index] = item;
                alert("Cập nhật thành công");
            })
            .catch(error => {
                alert("Lỗi cập nhật sản phẩm")
            })
    }
})