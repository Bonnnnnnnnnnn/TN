package com.poly.service.impl;

import com.poly.dao.DiscountDAO;
import com.poly.model.Discount;
import com.poly.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountDAO discountDAO;

    @Override
    public Discount findByCode(String code) {
        return discountDAO.findByCode(code);
    }

    @Override
    public Discount findById(Long id) {
        return discountDAO.findById(id);
    }
}
