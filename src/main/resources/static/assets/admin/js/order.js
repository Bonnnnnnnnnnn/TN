let pathOrder = "http://localhost:8080/rest";
app.controller("ctrl-order", function($scope, $http, $filter){
	$scope.items = [];
	$scope.selectedCreatedDate = '';
	$scope.form = {};
	
	// Hàm xử lý sự kiện khi giá trị của trường input thay đổi
    $scope.handleChange = function() {
        // Định dạng giá trị của $scope.selectedCreatedDate thành yyyy-MM-dd
        $scope.formattedDate = $filter('date')($scope.selectedCreatedDate, 'yyyy-MM-dd');
        $http.get(`/rest/order-by-day/` + $scope.formattedDate).then(resp =>{
			$scope.items = resp.data;
		});	
    };
    
    // Hiển thị thông tin số lượng sản phẩm
	$scope.load_all = function() {
		var url = `${pathOrder}/order`;
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
	// Pagination
	$scope.currentPage = 1; // Khởi tạo trang hiện tại là trang 1
	$scope.itemsPerPage = 5; // Thiết lập số sản phẩm hiển thị trên mỗi trang
	
	// Lấy tổng số trang
	$scope.totalPages = function() {
		var totalPages = 0;
		for (var i = 0; i < $scope.items.length; i += $scope.itemsPerPage) {
			totalPages++;
		}
		return totalPages;

	};

	// begin <=> currentPage
	$scope.prev = function() {
		if ($scope.currentPage > 0) {
			$scope.currentPage -= itemsPerPage;
		}
	}
	$scope.setPage = function(pageNum) {
		$scope.currentPage = pageNum;
	};


	// Lấy các sản phẩm của trang hiện tại
	$scope.getCurrentItems = function() {
		var startIndex = ($scope.currentPage - 1) * $scope.itemsPerPage;
		var endIndex = startIndex + $scope.itemsPerPage;
		return $scope.items.slice(startIndex, endIndex);
	};

	$scope.setPageFirst = function() {
		$scope.currentPage = 1;
	};

	$scope.setPageLast = function() {
		$scope.currentPage = $scope.totalPages();
	};
	
});