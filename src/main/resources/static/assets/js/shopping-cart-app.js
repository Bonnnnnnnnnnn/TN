const app = angular.module("shopping-cart", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
  // Trong AngularJS controller
	$scope.preventEmptyInput = function(event) {
	    if (event.keyCode === 8 || event.keyCode === 46) { // Kiểm tra phím backspace và phím delete
	        event.preventDefault();
	    }
	};
  
  
  // Trong controller hoặc script tương ứng
	$scope.getSubtotal = function() {
	    var subtotal = 0;
	    for (var i = 0; i < $scope.cart.items.length; i++) {
	        var item = $scope.cart.items[i];
	        subtotal += item.price * item.qty;
	    }
	    return subtotal;
	};
  
  $scope.cart = {
    items: [],
    add(id) {
      var item = this.items.find((item) => item.id == id);
      if (item) {
        item.qty++;
        this.saveToLocalStorage();
      } else {
        $http.get(`/rest/products/${id}`).then((resp) => {
          resp.data.qty = 1;
          this.items.push(resp.data);
          this.saveToLocalStorage();
        });
      }
      alert("Thêm vào giỏ thành công!");
    },
    add2(id) {
	  var quantityInput = document.getElementById("quantityInput");
      var quantityValue = parseInt(quantityInput.value);
      var item = this.items.find((item) => item.id == id);
      if (item) {
        item.qty += quantityValue;
        this.saveToLocalStorage();
      } else {
        $http.get(`/rest/products/${id}`).then((resp) => {
          resp.data.qty = quantityValue;
          this.items.push(resp.data);
          this.saveToLocalStorage();
        });
      }
      alert("Thêm vào giỏ thành công!");
    },
    remove(id) {
      var index = this.items.findIndex((item) => item.id == id);
      this.items.splice(index, 1);
      this.saveToLocalStorage();
    },
    clear() {
      this.items = [];
      this.saveToLocalStorage();
    },
    amt_of() {},
    get count() {
      return this.items
        .map((item) => item.qty)
        .reduce((total, qty) => (total += qty), 0);
    },
    get amount() {
      return this.items
        .map((item) => item.qty * item.price)
        .reduce((total, qty) => (total += qty), 0);
    },
    saveToLocalStorage() {
      var json = JSON.stringify(angular.copy(this.items));
      localStorage.setItem("cart", json);
    },
    loadFormLocalStrorage() {
      var json = localStorage.getItem("cart");
      this.items = json ? JSON.parse(json) : [];
    },
  };
  $scope.cart.loadFormLocalStrorage();
  $scope.order = {
    createDate: new Date(),
    address: "",
    account: { username: $("#username").text() },
    get orderDetails() {
      return $scope.cart.items.map((item) => {
        return {
          product: { id: item.id },
          price: item.price,
          quantity: item.qty,
        };
      });
    },
    purchase() {
      var order = angular.copy(this);
      $http.post("/rest/orders", order).then((resp) => {
        alert("Đặt hàng thành công!");
        $scope.cart.clear();
        console.log(resp.data);
        location.href = "/order/detail/" + resp.data.id;
      }).catch(error=>{
        alert("Đặt hàng lỗi!");
        console.log(error);
      });
    },
  };
});
