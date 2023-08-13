const url ="http://localhost:8080/rest/orderDetails";
angular.module("myapp", []).controller("order-ctrl", function($scope, $http){
	$scope.items = [];
    $scope.form = {};

	  $scope.initialize = function() {
        // load order
         var item = angular.copy($scope.form);
        $http.get(`http://localhost:8080/rest/orderDetails/${item.id}`, item)
        .then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhật thành công");
        })
        .catch(error => {
            alert("Lỗi cập nhật sản phẩm")
        }) 
        
    }
    
    //khoi dau
    $scope.initialize();
    
    


 
	

})