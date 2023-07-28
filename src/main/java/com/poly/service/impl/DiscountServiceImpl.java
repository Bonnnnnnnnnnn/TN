package com.poly.service.impl;

import com.poly.dao.DiscountDAO;
import com.poly.model.Discount;
import com.poly.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDAO discountDAO;

    @Override
    public Discount findByCode(String code) {
        return discountDAO.findByCode(code);
    }

    @Override
    public Discount findById(Long id) {
        return discountDAO.findById(id);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountDAO.findAll();
    }

    @Override
    public Discount createDiscount(Discount discount) {
        return discountDAO.save(discount);
    }

    @Override
    public Discount updateDiscount(Discount discount) {
        return discountDAO.saveAndFlush(discount);
    }

    @Override
    public void deleteDiscount(Long id) {
        discountDAO.deleteById(id);
    }
}
