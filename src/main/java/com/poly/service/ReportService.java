package com.poly.service;

import java.util.Date;
import java.util.List;

import com.poly.bean.MyItem;

public interface ReportService {
    
    public List<MyItem> reportReceipt(Date date, int limit);
    
}
