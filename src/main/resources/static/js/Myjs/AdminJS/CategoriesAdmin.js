const url ="http://localhost:8080/rest/categories";
angular.module("myapp", []).controller("category-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.form = {};
   

    $scope.initialize = function() {
        // load cate
        $http.get(url)
        .then(resp => {
            $scope.items = resp.data;
        })
    }

    //khoi dau
    $scope.initialize();

    $scope.reset = () => {
        $scope.form = {};
    }

    $scope.edit = (item) => {
        $scope.form = angular.copy(item);
    }
 
    $scope.create = () => {
        var item = angular.copy($scope.form);
        $http.post(url, item)
        .then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm sản phẩm mới thành công");
        })
        .catch(error => {
            alert("Lỗi thêm mới sản phẩm");
            console.log("Error: ", error);
        })
    }

    $scope.update = () => {
        var item = angular.copy($scope.form);
        $http.put(`http://localhost:8080/rest/categories/${item.id}`, item)
        .then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhật thành công");
        })
        .catch(error => {
            alert("Lỗi cập nhật sản phẩm")
        })
    }

    $scope.delete = (item) => {
        $http.delete(`/rest/categories/${item.id}`)
        .then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index , 1);
            $scope.reset();
            alert("Xóa thành công");
        })
        .catch(error => {
            alert("Lỗi xóa sản phẩm")
        })
    }

})