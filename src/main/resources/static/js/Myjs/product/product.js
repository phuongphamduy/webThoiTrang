var app = angular.module("myProduct", []);
const url = "http://localhost:8080/rest/product";

app.controller("myCtrl", function ($scope, $http) {
  $scope.product = [];

  $scope.initialize = function () {
    $http
      .get(url)
      .then((resp) => {
        $scope.product = resp.data;
      })
      .catch((error) => {
        console.log(error);
      });
  };

  app.create = () => {
    var item = angular.copy($scope.form);
    $http
      .get(url)
      .then((res) => {
        $scope.items.push(item);
        $scope.reset();
        console.log("create");
      })
      .catch((err) => {
        console.log("error", err);
      });
  };

  $scope.initialize();
});
