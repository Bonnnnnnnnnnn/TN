package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.FavouriteDAO;
import com.poly.model.Account;
import com.poly.model.Favourite;
import com.poly.model.Product;
import com.poly.service.FavouriteService;

@Service
public class FavouriteServiceImpl implements FavouriteService {

	@Autowired
	FavouriteDAO dao;

	@Override
	public Favourite findByUsernameAndProductId(String username, String productId) {
		return dao.findByUsernameAndProductId(username, productId);
	}

	@Override
	public void delete(Favourite favourite) {
		dao.delete(favourite);
	}

	@Override
	public Favourite create(Account account, Product product) {
		Favourite existFavourite = findByUsernameAndProductId(account.getUsername(), product.getId().toString());
		if (existFavourite == null) {
			existFavourite = new Favourite();
			existFavourite.setAccount(account);
			existFavourite.setProduct(product);
			return dao.save(existFavourite);
		}
		return existFavourite;
	}

	@Override
	public Favourite updateLikeOrUnlike(Account account, String productId) {
		Favourite existFavourite = findByUsernameAndProductId(account.getUsername(), productId);
		if (existFavourite != null) {
			return dao.save(existFavourite);
		}
		return null;
	}


	@Override
	public List<Object[]> getTotalLikesOfProduct() {
		return dao.getTotalLikesOfProduct();
	}

	@Override
	public List<Object[]> getUserInfoWithProductIsLikedByUsers(String productId) {
		return dao.getUserInfoWithProductIsLikedByUsers(productId);
	}
}
