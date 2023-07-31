var app = angular.module("myproduct", []);
const url = "http://localhost:8080/rest/product";

app.controller("myctrl", ($scope, $http) => {
  $scope.form = {};
  $scope.product = [];

  $scope.reset = () => {
    $scope.form = {};
  };

  $scope.load_all = () => {
    $http
      .get(url)
      .then((res) => {
        $scope.product = res.data;
        $scope.category = res.data.category;
        console.log("success", res.data);
      })
      .catch((error) => {
        console.log("error", error);
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

  $scope.load_all();
});
