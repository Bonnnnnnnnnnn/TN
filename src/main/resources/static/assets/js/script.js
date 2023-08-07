const registrationButton = document.getElementById("register");
const loginButton = document.getElementById("login");
const container = document.getElementById("container");

registrationButton.addEventListener("click",() => {
    container.classList.add("right-panel-active");
});

loginButton.addEventListener("click",() => {
    container.classList.remove("right-panel-active");
});


/*// Bắt sự kiện khi biểu mẫu đăng ký được gửi
document.querySelector(".register-container form").addEventListener("submit", function(event) {
    event.preventDefault(); // Ngăn chặn gửi biểu mẫu mặc định
  
    // Lấy giá trị từ các trường nhập
    var name = this.querySelector("input[type='text']").value;
    var email = this.querySelector("#register-email").value;
    var password = this.querySelector("input[type='password']").value;
    var confirmPassword = this.querySelectorAll("input[type='password']")[1].value;
  
    // Kiểm tra và xử lý lỗi
    var errorMessage = document.getElementById("error-message-register");
    errorMessage.innerHTML = ""; // Xóa thông báo lỗi hiện tại
  
    if (name.trim() === "") {
      errorMessage.innerHTML = "Vui lòng nhập tên của bạn.";
      return;
    }
  
    if (!/^[\p{L}\s]+$/u.test(name)) {
      errorMessage.innerHTML = "Tên chỉ được chứa các ký tự chữ cái và khoảng trắng.";
      return;
    }
  
    if (email.trim() === "") {
      errorMessage.innerHTML = "Vui lòng nhập email.";
      return;
    }
  
    if (!/@/.test(email)) {
      errorMessage.innerHTML = "Email không hợp lệ, hãy nhập theo định dạng abc@...";
      return;
    }
  
    if (password.trim() === "") {
      errorMessage.innerHTML = "Vui lòng nhập mật khẩu.";
      return;
    }
  
    if (confirmPassword.trim() === "") {
      errorMessage.innerHTML = "Vui lòng xác nhận mật khẩu.";
      return;
    }
  
    if (password !== confirmPassword) {
      errorMessage.innerHTML = "Mật khẩu và xác nhận mật khẩu không khớp.";
      return;
    }
  
    if (!/(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+=]).{8,}/.test(password)) {
      errorMessage.innerHTML = "Mật khẩu phải có ít nhất một chữ cái viết hoa, một số và một ký tự đặc biệt.";
      return;
    }
  
    // Hiển thị bảng thông báo đăng ký thành công
    var successModal = document.getElementById("success-modal");
    successModal.style.display = "block";
  
    // Xóa nội dung của biểu mẫu sau khi đăng ký thành công
    this.reset();
  });
  
  


// Bắt sự kiện khi biểu mẫu đăng nhập được gửi
document.querySelector(".login-container form").addEventListener("submit", function(event) {
    event.preventDefault(); // Ngăn chặn gửi biểu mẫu mặc định

    // Lấy giá trị từ các trường nhập
    var email = this.querySelector("input[type='email']").value;
    var password = this.querySelector("input[type='password']").value;

    // Kiểm tra và xử lý lỗi
    var errorMessage = document.getElementById("error-message");
    errorMessage.innerHTML = ""; // Xóa thông báo lỗi hiện tại

    if (email.trim() === "") {
        errorMessage.innerHTML = "Vui lòng nhập email.";
        return;
    }

    if (password.trim() === "") {
        errorMessage.innerHTML = "Vui lòng nhập mật khẩu.";
        return;
    }

   // Gửi biểu mẫu nếu không có lỗi
this.submit();

// Bắt sự kiện khi người dùng nhấp vào nút "Register" trên overlay
document.getElementById("register").addEventListener("click", function() {
    document.getElementById("container").classList.add("right-panel-active");
});


// Bắt sự kiện khi người dùng nhấp vào nút "Login" trên overlay
document.getElementById("login").addEventListener("click", function() {
    document.getElementById("container").classList.remove("right-panel-active");
});
});
*/