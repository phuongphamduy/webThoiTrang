var app = angular.module("myProduct", []);
const host = "http://localhost:8080";

app.controller("myCtrl", ($scope, $http) => {
  $scope.products = [];
  $scope.category = [];
  $scope.form = {};

  $scope.reset = () => {
    $scope.form = {};
  };

  $scope.load_all = () => {
    const url = `${host}/rest/categories`;
    $http
      .get(url)
      .then((res) => {
        $scope.category = res.data;
      })
      .catch((error) => {
        console.log("error", error);
      });

    $http.get(`${host}/rest/products`).then((res) => {
      $scope.products = res.data;
      console.log(res);
    });
  };

  $scope.edit = function (item) {
    $scope.form = angular.copy(item);
    console.log(item);
  };

 $scope.create = () => {
	const url = `${host}/rest/products`;
    var item = angular.copy($scope.form);
    
    $http
      .post(url, item)
      .then((res) => {
        $scope.products.push(res.data);
        $scope.reset();
        alert("Them moi thanh cong");
      })
      .catch((err) => {
        console.log("error", err.data.error);
      });
  };
  //upload
  $scope.imageChanged = (files) => {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/products", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((res) => {
        $scope.form.image = res.data.name;
      })
      .catch((error) => {
        alert("lỗi upload hình ảnh");
        console.log("error: ", error);
      });
  };

  //update
  $scope.update = () => {
    var item = angular.copy($scope.form);
    $http
      .put(`/rest/products/${item.id}`, item)
      .then((res) => {
        var index = $scope.products.findIndex((p) => p.id == item.id);
        $scope.products[index] = item;
        $scope.reset();
        alert("update thành công");
      })
      .catch((err) => {
        alert("Lỗi cập nhật sản phẩm");
        console.log("error", err);
      });
  };

  //delete
  $scope.delete = () => {
    var item = angular.copy($scope.form);
    $http
      .delete(`/rest/products/${item.id}`)
      .then((res) => {
        var index = $scope.products.findIndex((p) => p.id == item.id);
        $scope.products.splice(index, 1);
        $scope.reset();
        alert("Delete thành công");
      })
      .catch((err) => {
        alert("Delete thất bại");
        console.log("error", err);
      });
  };

  $scope.load_all();
});
