package com.poly.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;
import com.poly.model.MailInfo;
import com.poly.service.AccountService;
import com.poly.service.EmailService;

@Service
public class AccountServicelmpl implements AccountService{

	@Autowired 
	AccountDAO dao;
	
	  @Autowired
	  PasswordEncoder passwordEncoder; // Tiêm bộ mã hóa mật khẩu vào
	  
	  @Autowired
	    private EmailService emailService; // Service để gửi email
	  
	  @Autowired
		private TemplateEngine templateEngine;
	
	
	@Override
	public Account findById(String username) {		
		return dao.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}
	
	 @Override
	    public Account create(Account account) {
	        account.setPassword(passwordEncoder.encode(account.getPassword())); // Hash the password before saving
	        Account savedAccount = dao.save(account);

	        // Gửi email chào mừng
	        try {
	            sendWelcomeEmail(savedAccount);
	        } catch (MessagingException e) {
	            System.out.println("Failed to send welcome email");
	            // Xử lý lỗi gửi email nếu cần thiết
	        }

	        return savedAccount;
	    }
	
	@Override
    public Account updatePassword(Account account, String newPassword) {
        account.setPassword(passwordEncoder.encode(newPassword)); // Hash the new password before saving
        return dao.save(account);
    }
	
	@Override
    public Account resetPassword(String email) {
        Account existAccount = dao.findByEmail(email);
        if (existAccount != null) {
            String newPass = String.valueOf((int) (Math.random() * ((9999 - 1000) + 1)) + 1000);
            String encodedPassword = passwordEncoder.encode(newPass); // Mã hoá mật khẩu mới trước khi lưu
            existAccount.setPassword(encodedPassword);
            return dao.save(existAccount); // Lưu thông tin người dùng với mật khẩu đã mã hoá
        }
        return null;
    }


	private void sendWelcomeEmail(Account account) throws MessagingException {
        MailInfo mail = new MailInfo();
        mail.setTo(account.getEmail());
        mail.setSubject("Chào mừng bạn");
        mail.setBody("Chào mừng " + account.getFullname() + " đến với TastyBites!\nChúng tôi xin gửi tặng bạn mã giảm giá...");

        emailService.send(mail); // Gọi service để gửi email chào mừng

        // Truyền dữ liệu vào mẫu email bằng Thymeleaf
        Context context = new Context();
        context.setVariable("username", account.getUsername());
        context.setVariable("fullname", account.getFullname());
        context.setVariable("discountCode", "ABC123"); // Thay đổi thành dữ liệu thực tế (mã giảm giá)

        String htmlContent = templateEngine.process("welcome_mail", context);
        mail.setBody(htmlContent);

        emailService.send(mail); // Gửi message đến SMTP server
    }

}
