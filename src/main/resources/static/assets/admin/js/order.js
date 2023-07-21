let pathOrder = "http://localhost:8080/rest";
app.controller("ctrl-order", function($scope, $http, $filter){
	$scope.items = [];
	$scope.form = {};
    
    // Hiển thị thông tin số lượng sản phẩm
	$scope.load_all = function() {
		var url = `${pathOrder}/orderConfirm`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Success", resp)
		}).catch(errors => {
			console.log("Error", errors)
		});
	}
	
	$scope.update = function() {
		var item = angular.copy($scope.form);
		var url = `${pathOrder}/order/${$scope.form.id}`;
		$http.put(url, item).then(resp => {
			var index = $scope.items.findIndex(item => item.id == $scope.form.id);
			$scope.items[index] = resp.data;
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		});
	}
	
	$scope.load_all();
});