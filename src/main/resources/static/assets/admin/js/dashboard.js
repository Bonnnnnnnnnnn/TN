let pathDashboard = "http://localhost:8080/rest";
app.controller("ctrl-dashboard", function($scope, $http, $filter){
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
    
    $scope.load_orderAll = function() {
		var url = `${pathDashboard}/orderAll`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Success", resp)
		}).catch(errors => {
			console.log("Error", errors)
		});
	};
	
	// Hàm để thay đổi giá trị trong thẻ h3
        function updateTotalOrder(totalOrder) {
            const h3Element = document.getElementById('totalOrder');
            h3Element.textContent = totalOrder;
        }

        // Gọi API để lấy tổng số lượng đơn hàng và cập nhật giá trị trong thẻ h3
        fetch('/totalOrder') // Đảm bảo rằng đường dẫn '/totalOrder' phù hợp với API của bạn
            .then(response => response.text())
            .then(data => updateTotalOrder(data))
            .catch(error => console.error('Error fetching data:', error));
	
	$scope.load_orderAll();
	
	
	// Hàm để thay đổi giá trị trong thẻ h3 (totalprice)
        function updateTotalPriceOrder(totalPriceOrder) {
            const h3Element = document.getElementById('totalPriceOrder');
            h3Element.textContent = totalPriceOrder;
        }

        // Gọi API để lấy tổng số lượng đơn hàng và cập nhật giá trị trong thẻ h3
        fetch('/totalPriceOrder') // Đảm bảo rằng đường dẫn '/totalPriceOrder' phù hợp với API của bạn
            .then(response => response.text())
            .then(data => updateTotalPriceOrder(data))
            .catch(error => console.error('Error fetching data:', error));
	
	// Hàm để thay đổi giá trị trong thẻ h3 (visitor)
function updateViewVistor(viewVistor) {
    const h3Element = document.getElementById('viewVistor');
    const intValue = parseInt(viewVistor);
    h3Element.textContent = intValue.toString();
}

      // Gọi API để lấy tổng số lượng đơn hàng và cập nhật giá trị trong thẻ h3
fetch('/viewVistor') // Đảm bảo rằng đường dẫn '/viewVistor' phù hợp với API của bạn
    .then(response => response.text())
    .then(data => updateViewVistor(data))
    .catch(error => console.error('Error fetching data:', error));
	
	
	
	
	function updateTotalPriceOrder(totalPriceOrder) {
    const h3Element = document.getElementById('totalPriceOrder');
    
    // Chuyển đổi totalPriceOrder thành số
    const totalPrice = parseFloat(totalPriceOrder);
    
    // Kiểm tra nếu totalPrice là một số hợp lệ
    if (!isNaN(totalPrice)) {
        // Định dạng số và chia thành phần nghìn bằng dấu '.'
        const formattedPrice = totalPrice.toLocaleString('vi-VN');
        
        // Cập nhật giá trị trong thẻ h3
        h3Element.textContent = formattedPrice + " đ";
    } else {
        // Nếu totalPrice không hợp lệ, hiển thị thông báo lỗi
        h3Element.textContent = 'Error: Invalid total price';
    }
}

// Gọi API để lấy tổng số lượng đơn hàng và cập nhật giá trị trong thẻ h3
fetch('/totalPriceOrder') // Đảm bảo rằng đường dẫn '/totalPriceOrder' phù hợp với API của bạn
    .then(response => response.text())
    .then(data => updateTotalPriceOrder(data))
    .catch(error => console.error('Error fetching data:', error));

	
	
	 // Function để tăng số lượt truy cập và hiển thị nó trong thẻ có id là "visitorCount"
    
	
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