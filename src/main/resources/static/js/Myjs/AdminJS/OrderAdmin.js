const url ="http://localhost:8080/rest/orders";
angular.module("myapp", []).controller("order-ctrl", function($scope, $http){
	$scope.items = [];
    $scope.form = {};

	  $scope.initialize = function() {
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
    
    function change(){
		alert("Hi");
	}
    
	

})