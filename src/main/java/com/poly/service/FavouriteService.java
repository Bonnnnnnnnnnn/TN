package com.poly.service;

import java.util.List;

import com.poly.model.Account;
import com.poly.model.Favourite;
import com.poly.model.Product;



public interface FavouriteService {
	
	Favourite findByUsernameAndProductId(String username, String id);
	
	void delete(Favourite favourite);

	Favourite create(Account account, Product product);
	
	Favourite updateLikeOrUnlike(Account account, String id);

	// Bởi vì câu truy vấn trả về một số lượng cột không xác định trước, nên là sử dụng kiểu dữ liệu Object[] để chứa kết quả trả về.
	List<Object[]> getTotalLikesOfProduct();
	
	// Hiển thị thông tin những người đã like sp theo mã sản phẩm
	List<Object[]> getUserInfoWithProductIsLikedByUsers(String id);


}
