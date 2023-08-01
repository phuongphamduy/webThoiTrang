app.controller("account-ctrl", function($scope, $http){
	$scope.items = [];
	$scope.form = {};
	
	
	$scope.initialize=function(){
		$http.get("/rest/accounts").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)	
			})
		});
	}
	
	$scope.create=function(){
		alert("sss");
	}
})