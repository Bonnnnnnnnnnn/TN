package com.poly.dao;

import java.util.Date;
import java.util.List;

import com.poly.bean.MyItem;

public interface ReportDAO {
	 public List<MyItem> reportReceipt(Date date, int limit);
}


   
    
