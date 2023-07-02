let pathCategory = "http://localhost:8080/rest";

app.controller("ctrl-category", function($scope, $http){
	$scope.form = {};
	$scope.items = {};
	
	$scope.load_all = function(){
        var url = `${pathCategory}/category`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp)
        }).catch(errors => {
            console.log("Error", errors)
        });
    }
    
    $scope.edit = function(id){
		var url = `${pathCategory}/category/${id}`;
		$http.get(url).then(resp => {
			$scope.form = angular.copy(resp.data); // Thêm angular.copy để tạo một bản sao của đối tượng
			console.log("Success", resp);
		}).catch(errors => {
			console.log("Error", errors);
		});
	}
	
	$scope.update = function(){
        var category = angular.copy($scope.form);
        var url = `${pathCategory}/category/${$scope.form.id}`;
        $http.put(url, category).then(resp => {
            var index = $scope.items.findIndex(category => category.id == $scope.form.id);
            $scope.items[index] = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    
    $scope.create = function(){
        var item = angular.copy($scope.form);
        var url = `${pathCategory}/category`;
        $http.post(url, item).then(resp => {
            $scope.items.push(resp.data); // Sử dụng resp.data thay vì item để lấy đối tượng đã được tạo
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    
    $scope.delete = function(id){
        var url = `${pathCategory}/category/${id}`;
        $http.delete(url).then(resp => {
            var index = $scope.items.findIndex(item => item.id == id);
            $scope.items.splice(index, 1);
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
	
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append("file", files[0]);
		$http
			.post("/rest/upload/images", data, {
				transformRequest: angular.identity,
				headers: { "Content-Type": undefined },
			})
			.then((resp) => {
				$scope.form.image = resp.data.name;
			})
			.catch((error) => {
				alert("Lỗi upload hình ảnh!");
				console.log("Error", error);
			});
	};
	
	$scope.reset = function(){
		$scope.form = {};
	}
    
    $scope.load_all();
	$scope.reset();
    
});
