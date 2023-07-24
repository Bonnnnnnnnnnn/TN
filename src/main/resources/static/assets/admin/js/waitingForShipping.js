let pathWaitingForShipping = "http://localhost:8080/rest";
app.controller("ctrl-waitingForShipping", function($scope, $http, $filter){
	$scope.items = [];
	$scope.form = {};
    $scope.selectedCreatedDate = '';
	
	// Hàm xử lý sự kiện khi giá trị của trường input thay đổi
    $scope.handleChange = function() {
        // Định dạng giá trị của $scope.selectedCreatedDate thành yyyy-MM-dd
        $scope.formattedDate = $filter('date')($scope.selectedCreatedDate, 'yyyy-MM-dd');
        $http.get(`/rest/order-by-day/` + $scope.formattedDate).then(resp =>{
			$scope.items = resp.data;
		});	
    };
    
    // Hiển thị danh sách order đang giao
	$scope.load_waitingForShipping = function() {
		var url = `${pathWaitingForShipping}/waitingForShipping`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Success", resp)
		}).catch(errors => {
			console.log("Error", errors)
		});
	}

	$scope.update = function(id) {
		var xacNhan = confirm("Bạn có muốn xác nhận không?");
		if (xacNhan) {
			// Find the order in $scope.items by its ID
	        const orderToUpdate = $scope.items.find(item => item.id === id);
	        if (!orderToUpdate) {
	            console.error('Order not found.');
	            return;
	        }
	
	        // Set the new status
	        orderToUpdate.status = 'Đã giao';
	
	        // Send the updated order to the server
	        $http.put(`/rest/waitingForShipping/${id}`, orderToUpdate)
	            .then(function(response) {
	                console.log('Order updated successfully:', response.data);
	                alert("Duyệt thành công!");
	                $scope.load_waitingForShipping();
	            })
	            .catch(function(error) {
	                console.error('Error updating order:', error);
	                alert("Duyệt thất bại!");
	            });
	   	}else{
			// Người dùng chọn "Cancel", không thực hiện xóa
			console.log("Cancel");
		}
	};
	
	$scope.cancel = function(id) {
		var xacNhan = confirm("Bạn có muốn huỷ không?");
		if (xacNhan) {
			// Find the order in $scope.items by its ID
	        const orderToUpdate = $scope.items.find(item => item.id === id);
	        if (!orderToUpdate) {
	            console.error('Order not found.');
	            return;
	        }
	
	        // Set the new status
	        orderToUpdate.status = 'Đã hủy';
	
	        // Send the updated order to the server
	        $http.put(`/rest/waitingForShipping/${id}`, orderToUpdate)
	            .then(function(response) {
	                console.log('Order updated successfully:', response.data);
	                alert("Hủy thành công!");
	                $scope.load_waitingForShipping();
	            })
	            .catch(function(error) {
	                console.error('Error updating order:', error);
	                alert("Hủy thất bại!");
	            });
	    }else{
			// Người dùng chọn "Cancel", không thực hiện xóa
			console.log("Cancel");
		}
	};
	
	$scope.load_waitingForShipping();

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
	$scope.prev = function(){
		if($scope.currentPage > 0){
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