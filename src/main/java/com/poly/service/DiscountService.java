package com.poly.service;

import com.poly.model.Discount;
import org.springframework.stereotype.Service;

@Service
public interface DiscountService {

    Discount findByCode(String code);
}
